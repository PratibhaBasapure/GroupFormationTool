package CSCI5308.GroupFormationTool.Courses;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.MultipleChoiceOption;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Response.IResponsePersistence;
import CSCI5308.GroupFormationTool.Response.Response;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Logger.ILogger;
import CSCI5308.GroupFormationTool.Logger.ILoggerFactory;
import CSCI5308.GroupFormationTool.Logger.InfoLoggerFactory;
import CSCI5308.GroupFormationTool.Survey.Survey;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.SystemConfig;

@Controller
public class CourseController
{
	private static final String ID = "id";
	private static final int MULTI_CHOICE_MULTI_ONE_TYPE_ID = 2;
	private static final int MULTI_CHOICE_MULTI_MULTI_TYPE_ID = 3;

	@GetMapping("/course/course")
	public String course(Model model, @RequestParam(name = ID) long courseID)
	{
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
		IResponsePersistence responseDB = SystemConfig.instance().getResponseDB();
		User user = CurrentUser.instance().getCurrentAuthenticatedUser();
		Response responses = new Response();
		model.addAttribute("published", false);
		model.addAttribute("notPublished", true);
		List<Question> surveyQuestions = new ArrayList<>();
		surveyQuestions = surveyDB.loadSurveyQuestions(courseID);
		Boolean isSurveyProvided = responses.isResponseprovidedByStudent(responseDB, user.getID(),courseID);
		if(isSurveyProvided){
			model.addAttribute("issurveynotprovided", false);
		}
		else {
			model.addAttribute("issurveynotprovided", true);
		}
		if (null != surveyQuestions){
			model.addAttribute("responses", responses);
			model.addAttribute("published", true);
			model.addAttribute("notPublished", false);
			ArrayList<MultipleChoiceOption> choices = new ArrayList<>();
			for (Question question : surveyQuestions) {
				if (question.getTypeID() == MULTI_CHOICE_MULTI_ONE_TYPE_ID || question.getTypeID() == MULTI_CHOICE_MULTI_MULTI_TYPE_ID) {
					choices = question.getMultipleChoiceOption();
					for (MultipleChoiceOption option : choices) {
						model.addAttribute("choices", choices);
						question.setMultipleChoiceOption(choices);
					}
				}
			}
			model.addAttribute("questionlist", surveyQuestions);
		}
		Course course = new Course();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		List<Role> userRoles = course.getAllRolesForCurrentUserInCourse();
		if (null == userRoles)
		{
			model.addAttribute("instructor", false);
			model.addAttribute("ta", false);
			model.addAttribute("student", false);
			model.addAttribute("guest", true);
		}
		else
		{
			model.addAttribute("instructor", userRoles.contains(Role.INSTRUCTOR));
			model.addAttribute("ta", userRoles.contains(Role.TA));
			model.addAttribute("student", userRoles.contains(Role.STUDENT));
			model.addAttribute("guest", userRoles.isEmpty());
		}
		return "course/course";
	}

}
