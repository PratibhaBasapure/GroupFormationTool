package CSCI5308.GroupFormationTool.Response;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.Role;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.MultipleChoiceOption;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ResponseController {
    private static final String ID = "id";
    private static final int NUMERIC_TYPE_ID = 1;
    private static final int MULTI_CHOICE_MULTI_ONE_TYPE_ID = 2;
    private static final int MULTI_CHOICE_MULTI_MULTI_TYPE_ID = 3;
    private static final int FREE_TEXT_TYPE_ID = 4;

    @RequestMapping(value = "/survey/submitresponse", method = RequestMethod.POST)
    public String createSurvey(Model model, HttpServletRequest request, @RequestParam(name = ID) long courseID,
                               @RequestParam(name = "questionID", required = false) ArrayList<Integer> questionIDs) {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
        IResponsePersistence responseDB = SystemConfig.instance().getResponseDB();
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
        model.getAttribute("questionlist");
        int surveyID = surveyDB.isSurveyPublished(courseID);
        ArrayList<Response> responses = new ArrayList<>();
        for (int qu: questionIDs) {
            Response response = new Response();
            Question question = questionDB.loadQuestionById(qu);
            question.setInstructor(user);
            response.setQuestion(question);
            if(question.getTypeID() == FREE_TEXT_TYPE_ID || question.getTypeID() == NUMERIC_TYPE_ID){
                if(request.getParameter("result"+qu) != null){
                    response.setSingleresponse(request.getParameter("result"+qu));
                }
            }
            if(question.getTypeID() == MULTI_CHOICE_MULTI_MULTI_TYPE_ID)
            {
                ArrayList<MultipleChoiceOption> choices = question.getMultipleChoiceOption();
                ArrayList<String> userChoices = new ArrayList<>();
                for (MultipleChoiceOption choice: choices) {
                    if(request.getParameter("result"+qu+choice.getOptionNumber()) != null)
                    {
                        userChoices.add(request.getParameter("result"+qu+choice.getOptionNumber()));
                    }
                }
                response.setResponse(userChoices);
            }
            if(question.getTypeID() == MULTI_CHOICE_MULTI_ONE_TYPE_ID)
            {
                ArrayList<MultipleChoiceOption> choices = question.getMultipleChoiceOption();
                for (MultipleChoiceOption choice: choices) {
                    if(request.getParameter("option2"+qu) != null){
                        response.setSingleresponse(request.getParameter("option2"+qu));
                    }
                }
            }
            responses.add(response);
        }
        Response response = new Response();
        response.saveResponse(responseDB, responses, surveyID);
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
        List<Course> allCourses = courseDB.loadAllCourses();
        model.addAttribute("courses", allCourses);
        return "index";
    }

}
