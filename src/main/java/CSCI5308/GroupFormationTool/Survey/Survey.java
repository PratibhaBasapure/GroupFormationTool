package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.Question;

import java.util.ArrayList;
import java.util.List;

public class Survey {
    private long id;
    private long courseID;
    private User instructor;
    private ArrayList<Question> surveyQuestionList;
    private boolean isPublished;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public ArrayList<Question> getSurveyQuestionList() {
        return surveyQuestionList;
    }

    public void setSurveyQuestionList(ArrayList<Question> surveyQuestionList) {
        this.surveyQuestionList = surveyQuestionList;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public boolean createSurvey(ISurveyPersistence surveyDB) {
        return surveyDB.createSurvey(this);
    }

    public boolean publishSurvey(ISurveyPersistence surveyDB) {
        return surveyDB.publishSurvey(this);
    }

    public List<Question> getAllSurveyQuestions(List<Question> questionList, List<Question> questionsAddedToSurvey) {
        for (Question queList : questionList) {
            for (Question q : questionsAddedToSurvey) {
                if (queList.getId() == q.getId()) {
                    queList.setQuestionAddedToSurvey(true);
                }
            }
        }
        return questionList;
    }

    public List<Question> loadSurveyQuestionsByCourseId(ISurveyPersistence surveyDB,Long courseID)
    {
     return surveyDB.loadSurveyQuestionsByCourseId(courseID);
    }
}
