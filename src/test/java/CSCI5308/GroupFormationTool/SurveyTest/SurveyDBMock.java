package CSCI5308.GroupFormationTool.SurveyTest;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.Survey;

import java.util.ArrayList;
import java.util.List;

public class SurveyDBMock implements ISurveyPersistence {

    @Override
    public boolean createSurvey(Survey survey) {
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
        return true;
    }

    @Override
    public boolean publishSurvey(Survey survey) {
        User user = new User();
        user.setID(1);
        user.setBannerID("B00847415");
        user.setPassword("Pass@123");
        user.setFirstName("Pratz");
        user.setLastName("B");
        user.setEmail("pr676280@dal.ca");
        if (survey != null) {
            if (survey.getCourseID() == 1 && survey.getInstructor().getBannerID() == user.getBanner()
                    && survey.getInstructor().getFirstName() == user.getFirstName()
                    && survey.getInstructor().getLastName() == user.getLastName()
                    && survey.getInstructor().getPassword() == user.getPassword()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Question> loadSurveyQuestions(Long id) {
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

        if (id != 0) {
            return questions;
        }
        return null;
    }

    @Override
    public int isSurveyPublished(Long courseID) {
        if (courseID == 1) {
            return 1;
        }
        return 0;
    }

    @Override
    public List<Question> loadSurveyQuestionsByCourseId(Long id) {
        ArrayList<Question> questions = new ArrayList<Question>();
        ArrayList<Question> surveyQuestions = new ArrayList<Question>();

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

        if (id != 0) {
            for (Question q : questions) {
                if (q.getId() == id) {
                    surveyQuestions.add(q);
                }
            }
            return surveyQuestions;
        }
        return null;
    }

    @Override
    public User findInstructorOfTA(Long id) {
        User user = new User();
        user.setID(1);
        user.setBannerID("B00847415");
        user.setPassword("Pass@123");
        user.setFirstName("Pratz");
        user.setLastName("B");
        user.setEmail("pr676280@dal.ca");
        if (id == 1) {
            return user;
        }
        return null;
    }
}
