package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SurveyController {
    private static final String ID = "id";
    private static final String QUESTIONID = "questionid";

    @GetMapping("/survey/createsurvey")
    public String surveyQuestions(Model model, @RequestParam(name = ID) long courseID) {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
        Question question = new Question();
        question.setInstructor(user);
        List<Question> questionList = question.loadAllQuestionsByInstructor(questionDB);
        List<Question> questionsAddedToSurvey = surveyDB.loadSurveyQuestionsByCourseId(courseID);
        Survey survey = new Survey();
        List<Question> surveyQuestions = survey.getAllSurveyQuestions(questionList, questionsAddedToSurvey);
        int isPublished = surveyDB.isSurveyPublished(courseID);
        model.addAttribute("questionlist", surveyQuestions);
        model.addAttribute("courseid", courseID);
        model.addAttribute("isPublished", isPublished);
        return "createsurvey";
    }

    @RequestMapping(value = "/survey/createsurvey", method = RequestMethod.POST)
    public String createSurvey(Model model, @RequestParam(name = ID) long courseID,
                               @RequestParam(name = QUESTIONID, required = false) ArrayList<Integer> questionIDs) {
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
        Survey survey = new Survey();
        survey.setCourseID(courseID);
        survey.setInstructor(user);
        ArrayList<Question> surveyQuestionList = new ArrayList<Question>();
        for (int i = 0; i < questionIDs.size(); i++) {
            if (questionIDs.get(i) != null && questionIDs.get(i) != 0) {
                Question q = new Question();
                q.setId(questionIDs.get(i));
                surveyQuestionList.add(q);
            }
        }
        survey.setSurveyQuestionList(surveyQuestionList);
        survey.createSurvey(surveyDB);
        ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
        List<Course> allCourses = courseDB.loadAllCourses();
        model.addAttribute("courses", allCourses);
        model.addAttribute("summary", "Survey Created Successfully!!!");
        return "index";
    }

    @GetMapping("/survey/publishsurvey")
    public String publishSurvey(Model model, @RequestParam(name = ID) long courseID) {
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
        Survey survey = new Survey();
        survey.setCourseID(courseID);
        survey.setPublished(true);
        survey.publishSurvey(surveyDB);
        ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
        List<Course> allCourses = courseDB.loadAllCourses();
        model.addAttribute("courses", allCourses);
        model.addAttribute("summary", "Survey published Successfully!!!");
        return "index";
    }

    @RequestMapping(value = "/survey/editsurvey")
    public String editSurvey(Model model, @RequestParam(name = ID) long courseID,
                             @RequestParam(name = QUESTIONID, required = false) ArrayList<Integer> questionIDs) {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
        User instructor = surveyDB.findInstructorOfTA(courseID);
        Question question = new Question();
        question.setInstructor(instructor);
        List<Question> questionList = question.loadAllQuestionsByInstructor(questionDB);
        List<Question> questionsAddedToSurvey = surveyDB.loadSurveyQuestionsByCourseId(courseID);
        Survey survey = new Survey();
        List<Question> surveyQuestions = survey.getAllSurveyQuestions(questionList, questionsAddedToSurvey);
        int isPublished = surveyDB.isSurveyPublished(courseID);
        model.addAttribute("questionlist", surveyQuestions);
        model.addAttribute("courseid", courseID);
        model.addAttribute("isPublished", isPublished);
        return "createsurvey";
    }
}
