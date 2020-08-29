package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Logger.ILogger;
import CSCI5308.GroupFormationTool.Logger.ILoggerFactory;
import CSCI5308.GroupFormationTool.Logger.InfoLoggerFactory;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Response.Response;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.Survey;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class GroupFormationController {
	private static final String ID = "id";
	private static final String QUESTIONID = "questionid";

	@GetMapping("/groupformation/algorithm")
	public String surveyQuestions(Model model, @RequestParam(name = ID) long courseID) {
		ILoggerFactory infoLoggerFactory = new InfoLoggerFactory();
		ILogger infoLogger = infoLoggerFactory.createLogger();
		infoLogger.logMessage("/groupformation/algorithm with course id "+ courseID,null, SystemConfig.instance().getLogDB());
		ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
		Survey s = new Survey();
		List<Question> questionsAddedToSurvey = surveyDB.loadSurveyQuestionsByCourseId(courseID);
		model.addAttribute("questionlist", questionsAddedToSurvey);
		model.addAttribute("courseid", courseID);
		return "definealgorithm";
	}

	public List<Boolean> parseComparisonChoices(String requestBody) {
		List<Boolean> allMatches = new ArrayList<Boolean>();
		Matcher m = Pattern.compile("comparisonChoice[0-9]+=[0-9]{1}").matcher(requestBody);

		while (m.find()) {
			int choice = Integer.parseInt(m.group().split("=")[1].trim());

			if (choice == 1) {
				allMatches.add(true);
			} else {
				allMatches.add(false);
			}

		}

		return allMatches;
	}

	public List<Double> normaliseWeights(ArrayList<Integer> enteredWeights) {
		double sum = 0;
		List<Double> normalisedWeights = new ArrayList<Double>();

		for (int i = 0; i < enteredWeights.size(); i++)
			sum += enteredWeights.get(i);

		for (int i = 0; i < enteredWeights.size(); i++) {
			normalisedWeights.add(Math.round((enteredWeights.get(i) / sum) * 100.0) / 100.0);
		}

		return normalisedWeights;
	}

	@RequestMapping(value = "/groupformation/algorithm", method = RequestMethod.POST)
	public String generateAlgo(Model model, @RequestParam(name = ID) long courseID, @RequestBody String body,
			@RequestParam(name = "groupSize") int groupSize,
			@RequestParam(name = QUESTIONID, required = false) ArrayList<Integer> questionIDs,
			@RequestParam(name = "weight", required = false) ArrayList<Integer> weights) {
		ILoggerFactory infoLoggerFactory = new InfoLoggerFactory();
		ILogger infoLogger = infoLoggerFactory.createLogger();
		infoLogger.logMessage("/groupformation/algorithm with course id " + courseID
				+ " body: "+body
				+" group size "+groupSize
				+" questions and weights ",null, SystemConfig.instance().getLogDB());
		Course course = new Course();
		course.setId(courseID);
		List<Question> questions = new ArrayList<Question>();
		for (int qID : questionIDs) {
			Question question = new Question();
			question.setId(qID);
			questions.add(question);
		}
		List<Boolean> comparisonChoices = parseComparisonChoices(body);
		List<Double> newWeights = normaliseWeights(weights);
		IGroupFormationAlgorithmPersistence algorithmDB = SystemConfig.instance().getAlgorithmDB();
		GroupFormationAlgorithm algorithm = new GroupFormationAlgorithmBuilder().setCourse(course)
				.setComparisonChoices(comparisonChoices).setCreatedOn(new Date()).setGroupSize(groupSize)
				.setQuestions(questions).setWeights(newWeights).getGroupFormationAlgorithm();
		boolean status = algorithm.createAlgorithm(algorithm, algorithmDB);

		return "definealgorithm";
	}

	@RequestMapping(value = "/groupformation/groups", method = RequestMethod.GET)
	public String displayGroups(Model model, @RequestParam(name = ID) long courseID) {
		ILoggerFactory infoLoggerFactory = new InfoLoggerFactory();
		ILogger infoLogger = infoLoggerFactory.createLogger();
		infoLogger.logMessage("/groupformation/groups with course id " + courseID ,null, SystemConfig.instance().getLogDB());
		Course course = new Course();
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		model.addAttribute("courseid", courseID);
		Group group = new GroupBuilder().getGroup();
		IGroupPersistence groupDB = SystemConfig.instance().getGroupDB();
		List<Group> groups = group.loadGroupByCourse(groupDB, course);
		model.addAttribute("groups", groups);
		return "displaygroups";
	}

	@RequestMapping(value = "/groupformation/runalgorithm")
	public String generateGroups(Model model, @RequestParam(name = ID) long courseID) {
		ILoggerFactory infoLoggerFactory = new InfoLoggerFactory();
		ILogger infoLogger = infoLoggerFactory.createLogger();
		infoLogger.logMessage("/groupformation/runalgorithm with course id"+ courseID,null, SystemConfig.instance().getLogDB());
		Course course = new Course();
		course.setId(courseID);
		IMatchMatrixGeneration matGen = new ComparisonScoreMatrixGeneration();
		IGroupGeneration grpGen = new MatchScoreGroupGeneration();
		IGroupFormationAlgorithmPersistence algorithmDB = SystemConfig.instance().getAlgorithmDB();
		GroupFormationAlgorithm algorithm = algorithmDB.loadAlgorithmByCourse(course);
		LinkedHashMap<User, List<Response>> responses = algorithmDB.loadUsersResponsesByCourseID(courseID);		
		List<Group> groups = algorithm.runAlgorithm(matGen, grpGen, algorithm, responses, course);
		IGroupPersistence groupDB = SystemConfig.instance().getGroupDB();
		boolean status = groupDB.createGroups(groups);
		
		return "definealgorithm";
	}
}
