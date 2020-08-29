package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Logger.ErrorLoggerFactory;
import CSCI5308.GroupFormationTool.Logger.ILogger;
import CSCI5308.GroupFormationTool.Logger.ILoggerFactory;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyDB implements ISurveyPersistence {
    @Override
    public boolean createSurvey(Survey survey) {
        CallStoredProcedure proc = null;
        ILoggerFactory loggerFactory = new ErrorLoggerFactory();
        ILogger logger = loggerFactory.createLogger();
        try {
            proc = new CallStoredProcedure("spCreateSurvey(?, ?)");
            proc.setParameter(1, survey.getCourseID());
            proc.setParameter(2, survey.getInstructor().getID());
            ResultSet resultSet = proc.executeWithResults();
            int surveyIdfromDB = 0;
            while (resultSet.next()) {
                surveyIdfromDB = resultSet.getInt(1);
            }
            createSurveyQuestions(surveyIdfromDB, survey.getSurveyQuestionList());
            return true;
        } catch (SQLException e) {
            logger.logMessage(e.getMessage(), "Check surveyID in DB and error is from createSurvey method", SystemConfig.instance().getLogDB());
            e.printStackTrace();
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    @Override
    public boolean publishSurvey(Survey survey) {
        ILoggerFactory loggerFactory = new ErrorLoggerFactory();
        ILogger logger = loggerFactory.createLogger();
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spPublishSurvey(?)");
            proc.setParameter(1, survey.getCourseID());
            ResultSet resultSet = proc.executeWithResults();
            int surveyIdfromDB = 0;
            while (resultSet.next()) {
                surveyIdfromDB = resultSet.getInt(1);
            }
            return true;
        } catch (SQLException e) {
            logger.logMessage(e.getMessage(), "Check surveyID from DB and error is from publishSurvey method", SystemConfig.instance().getLogDB());
            e.printStackTrace();
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    @Override
    public List<Question> loadSurveyQuestions(Long courseId) {
        ILoggerFactory loggerFactory = new ErrorLoggerFactory();
        ILogger logger = loggerFactory.createLogger();
        CallStoredProcedure proc = null;
        try {
            if (isSurveyPublished(courseId) != 0) {
                List<Question> surveyQuestions = new ArrayList<Question>();
                List<Long> questionIDs = null;
                IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
                int surveyID = isSurveyPublished(courseId);
                proc = new CallStoredProcedure("spLoadSurveyQuestionsBySurveyId(?)");
                proc.setParameter(1, surveyID);
                ResultSet resultSet = proc.executeWithResults();
                while (resultSet.next()) {
                    int questionID = resultSet.getInt(1);
                    Question question = new Question();
                    question = questionDB.loadQuestionById(questionID);
                    surveyQuestions.add(question);
                }
                return surveyQuestions;
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.logMessage(e.getMessage(), "Check questionID in DB and error is from loadSurveyQuestions method", SystemConfig.instance().getLogDB());
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return null;
    }

    @Override
    public int isSurveyPublished(Long courseID) {
        ILoggerFactory loggerFactory = new ErrorLoggerFactory();
        ILogger logger = loggerFactory.createLogger();
        CallStoredProcedure proc = null;
        int surveyID = 0;
        try {
            proc = new CallStoredProcedure("spIsSurveyPublished(?)");
            proc.setParameter(1, courseID);
            ResultSet resultSet = proc.executeWithResults();
            while (resultSet.next()) {
                surveyID = resultSet.getInt(1);
            }
            return surveyID;
        } catch (SQLException e) {
            logger.logMessage(e.getMessage(), "Check surveyID in DB and error is from isSurveyPublished method", SystemConfig.instance().getLogDB());
            e.printStackTrace();
            return surveyID;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    @Override
    public List<Question> loadSurveyQuestionsByCourseId(Long courseID) {
        ILoggerFactory loggerFactory = new ErrorLoggerFactory();
        ILogger logger = loggerFactory.createLogger();
        CallStoredProcedure proc = null;
        List<Question> questionList = new ArrayList<Question>();
        try {
            proc = new CallStoredProcedure("spLoadSurveyQuestionsByCourseId(?)");
            proc.setParameter(1, courseID);
            ResultSet results = proc.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    Question question = new Question();
                    question.setId(results.getInt(1));
                    question.setType(results.getString(2));
                    question.setTitle(results.getString(3));
                    question.setText(results.getString(4));
                    question.setCreatedOn(results.getDate(5));
                    questionList.add(question);
                }
            }
        } catch (SQLException e) {
            logger.logMessage(e.getMessage(), "Check question details in DB and error is from loadSurveyQuestionsByCourseId method", SystemConfig.instance().getLogDB());
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return questionList;
    }

    @Override
    public User findInstructorOfTA(Long courseID) {
        ILoggerFactory loggerFactory = new ErrorLoggerFactory();
        ILogger logger = loggerFactory.createLogger();
        CallStoredProcedure proc = null;
        int instructorId = 0;
        try {
            proc = new CallStoredProcedure("spFindInstructorOfTA(?)");
            proc.setParameter(1, courseID);
            ResultSet results = proc.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    instructorId = results.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.logMessage(e.getMessage(), "Check InstructorID in DB and error is from findInstructorOfTA method", SystemConfig.instance().getLogDB());
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        User u = new User();
        u.setID(instructorId);
        return u;
    }

    private boolean createSurveyQuestions(int surveyIdfromDB, ArrayList<Question> surveyQuestionList) {
        ILoggerFactory loggerFactory = new ErrorLoggerFactory();
        ILogger logger = loggerFactory.createLogger();
        CallStoredProcedure proc = null;
        try {
            for (int i = 0; i < surveyQuestionList.size(); i++) {
                proc = new CallStoredProcedure("spCreateSurveyQuestions(?,?)");
                proc.setParameter(1, surveyIdfromDB);
                proc.setParameter(2, surveyQuestionList.get(i).getId());
                proc.execute();
            }
            return true;
        } catch (SQLException e) {
            logger.logMessage(e.getMessage(), "Check stored procedure in DB and error is from createSurveyQuestions method", SystemConfig.instance().getLogDB());
            e.printStackTrace();
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }
}
