package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.Question;

import java.util.List;

public interface ISurveyPersistence {
    public boolean createSurvey(Survey survey);
    public boolean publishSurvey(Survey survey);
    public List<Question> loadSurveyQuestions(Long id);
    public int isSurveyPublished(Long courseID);
    public List<Question> loadSurveyQuestionsByCourseId(Long id);
    public User findInstructorOfTA(Long id);
}
