package CSCI5308.GroupFormationTool.SurveyTest;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.QuestionTest.QuestionDBMock;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.MultipleChoiceOption;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Response.IResponsePersistence;
import CSCI5308.GroupFormationTool.ResponseTest.ResponseDBMock;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.Survey;
import CSCI5308.GroupFormationTool.Survey.SurveyDB;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@SuppressWarnings("deprecation")
public class SurveyTest {

    @Test
    public void testGetId() {
        Survey survey = new Survey();
        survey.setId(123);
        assertEquals(123, survey.getId());
    }

    @Test
    public void testSetId() {
        Survey survey = new Survey();
        survey.setId(1234);
        assertEquals(1234, survey.getId());
    }

    @Test
    public void testGetCourseID() {
        Survey survey = new Survey();
        survey.setCourseID(123);
        assertEquals(123, survey.getCourseID());
    }

    @Test
    public void testSetCourseID() {
        Survey survey = new Survey();
        survey.setCourseID(1234);
        assertEquals(1234, survey.getCourseID());
    }

    @Test
    public void testGetInstructor() {
        Survey survey = new Survey();
        User u = new User();
        u.setId(1234);
        survey.setInstructor(u);
        assertEquals(survey.getInstructor().getId(), 1234);
    }

    @Test
    public void testSetInstructor() {
        Question question = new Question();
        User u = new User();
        u.setId(100);
        question.setInstructor(u);
        assertEquals(100, question.getInstructor().getId());
    }

    @Test
    public void testGetSurveyQuestion() {
        Survey s = new Survey();
        ArrayList<Question> questions = new ArrayList<Question>();

        Question question1 = new Question();
        Question question2 = new Question();
        Question question3 = new Question();

        question1.setText("Option 1");
        question1.setId(1);
        questions.add(question1);

        question2.setText("Option 2");
        question2.setId(2);
        questions.add(question2);

        question3.setText("Option 3");
        question3.setId(3);
        questions.add(question3);

        s.setSurveyQuestionList(questions);
        assertEquals(questions, s.getSurveyQuestionList());
    }

    @Test
    public void testSetSurveyQuestionList() {
        Survey s = new Survey();
        ArrayList<Question> questions = new ArrayList<Question>();

        Question question1 = new Question();
        Question question2 = new Question();
        Question question3 = new Question();

        question1.setText("Option 1");
        question1.setId(1);
        questions.add(question1);

        question2.setText("Option 2");
        question2.setId(2);
        questions.add(question2);

        question3.setText("Option 3");
        question3.setId(3);
        questions.add(question3);

        s.setSurveyQuestionList(questions);
        assertEquals(questions, s.getSurveyQuestionList());
    }

    @Test
    public void testIsPublished() {
        Survey survey = new Survey();
        survey.setPublished(true);
        assertEquals(true, survey.isPublished());
    }

    @Test
    public void testSetPublished() {
        Survey survey = new Survey();
        survey.setPublished(true);
        assertEquals(true, survey.isPublished());
    }

    @Test
    public void testCreateSurvey() {
        ISurveyPersistence surveyDB = new SurveyDBMock();
        Survey survey = new Survey();
        User user = new User();
        user.setID(1);
        user.setBannerID("B00847415");
        user.setPassword("Pass@123");
        user.setFirstName("Pratz");
        user.setLastName("B");
        user.setEmail("pr676280@dal.ca");

        survey.setId(1);
        survey.setCourseID(1);
        survey.setInstructor(user);
        survey.setPublished(false);

        ArrayList<Question> questions = new ArrayList<Question>();

        Question question1 = new Question();
        Question question2 = new Question();
        Question question3 = new Question();

        question1.setText("Option 1");
        question1.setId(1);
        questions.add(question1);

        question2.setText("Option 2");
        question2.setId(2);
        questions.add(question2);

        question3.setText("Option 3");
        question3.setId(3);
        questions.add(question3);
        survey.setSurveyQuestionList(questions);

        surveyDB.createSurvey(survey);

        Assert.isTrue(survey.getId() == 1);
        Assert.isTrue(survey.getCourseID() == 1);
        Assert.isTrue(survey.isPublished() == false);
        Assert.isTrue(survey.getInstructor().getBannerID().equals("B00847415"));
        Assert.isTrue(survey.getInstructor().getFirstName().equals("Pratz"));
        Assert.isTrue(survey.getInstructor().getLastName().equals("B"));
        Assert.isTrue(survey.getInstructor().getEmail().equals("pr676280@dal.ca"));
    }

    @Test
    public void testIsSurveyPublished() {
        ISurveyPersistence dbMock = new SurveyDBMock();
        Assert.isTrue(dbMock.isSurveyPublished(Long.valueOf(1)) == 1);
    }

    @Test
    public void testPublishSurvey() {
        ISurveyPersistence dbMock = new SurveyDBMock();
        User user = new User();
        user.setID(1);
        user.setBannerID("B00847415");
        user.setPassword("Pass@123");
        user.setFirstName("Pratz");
        user.setLastName("B");
        user.setEmail("pr676280@dal.ca");

        Survey survey = new Survey();
        survey.setInstructor(user);
        survey.setCourseID(1);
        Assert.isTrue(dbMock.publishSurvey(survey));
    }

    @Test
    public void testLoadSurveyQuestions() {
        ISurveyPersistence dbMock = new SurveyDBMock();
        ArrayList<Question> surveyQuestions = (ArrayList<Question>) dbMock.loadSurveyQuestions((long) 1);
        Assert.isTrue(surveyQuestions.get(0).getText().equals("Option 1"));
        Assert.isTrue(surveyQuestions.get(0).getId() == 1);
        Assert.isTrue(surveyQuestions.get(1).getText().equals("Option 2"));
        Assert.isTrue(surveyQuestions.get(1).getId() == 2);
    }

    @Test
    public void testFindInstructorOfTA() {
        ISurveyPersistence dbMock = new SurveyDBMock();
        User user = dbMock.findInstructorOfTA((long) 1);
        Assert.isTrue(user.getBanner().equals("B00847415"));
        Assert.isTrue(user.getFirstName().equals("Pratz"));
        Assert.isTrue(user.getLastName().equals("B"));
    }

    @Test
    public void testLoadSurveyQuestionsByCourseId() {
        ISurveyPersistence dbMock = new SurveyDBMock();
        ArrayList<Question> q1 = (ArrayList<Question>) dbMock.loadSurveyQuestionsByCourseId((long) 1);
        Assert.isTrue(q1.get(0).getText().equals("Option 1"));
        Assert.isTrue(q1.get(0).getId() == 1);
    }
}
