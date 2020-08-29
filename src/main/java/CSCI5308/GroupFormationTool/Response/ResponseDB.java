package CSCI5308.GroupFormationTool.Response;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Logger.ErrorLoggerFactory;
import CSCI5308.GroupFormationTool.Logger.ILogger;
import CSCI5308.GroupFormationTool.Logger.ILoggerFactory;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResponseDB implements IResponsePersistence {

    private static final int NUMERIC_TYPE_ID = 1;
    private static final int MULTI_CHOICE_MULTI_ONE_TYPE_ID = 2;
    private static final int MULTI_CHOICE_MULTI_MULTI_TYPE_ID = 3;
    private static final int FREE_TEXT_TYPE_ID = 4;

    @Override
    public boolean saveResponse(ArrayList<Response> responses, int surveyID) {
        CallStoredProcedure proc = null;
        ILoggerFactory loggerFactory = new ErrorLoggerFactory();
        ILogger logger = loggerFactory.createLogger();
        try {
            for (Response r : responses) {
                proc = new CallStoredProcedure("spSaveSurveyResponse(?,?,?)");
                proc.setParameter(1,  surveyID);
                proc.setParameter(2, r.getQuestion().getId());
                proc.setParameter(3, r.getQuestion().getInstructor().getID());
                ResultSet resultSet = proc.executeWithResults();
                if(r.getQuestion().getTypeID() == MULTI_CHOICE_MULTI_MULTI_TYPE_ID){
                    while (resultSet.next()){
                        Long responseIdFromDB = resultSet.getLong(1);
                        ArrayList<String> choiceResponses = r.getResponse();
                        for (String choice: choiceResponses
                        ) {
                            proc = new CallStoredProcedure("spSaveSurveyResponseMultipleChoiceMultiple(?,?,?)");
                            proc.setParameter(1, responseIdFromDB);
                            proc.setParameter(2, choice);
                            proc.setParameter(3,r.getQuestion().getTypeID());
                            proc.execute();
                        }
                    }
                }
                if(r.getQuestion().getTypeID() == FREE_TEXT_TYPE_ID) {
                    while (resultSet.next()){
                        Long responseIdFromDB = resultSet.getLong(1);
                        proc = new CallStoredProcedure("spSaveSurveyResponseFreeText(?,?,?)");
                        proc.setParameter(1, responseIdFromDB);
                        proc.setParameter(2,r.getSingleresponse());
                        proc.setParameter(3,r.getQuestion().getTypeID());
                        proc.execute();
                    }
                }
                if(r.getQuestion().getTypeID() == MULTI_CHOICE_MULTI_ONE_TYPE_ID) {
                    while (resultSet.next()){
                        Long responseIdFromDB = resultSet.getLong(1);
                        proc = new CallStoredProcedure("spSaveSurveyResponseMultipleChoiceSingle(?,?,?)");
                        proc.setParameter(1, responseIdFromDB);
                        proc.setParameter(2, r.getSingleresponse());
                        proc.setParameter(3, r.getQuestion().getTypeID());
                        proc.execute();
                    }
                }
                if(r.getQuestion().getTypeID() == NUMERIC_TYPE_ID) {
                    while (resultSet.next()){
                        Long responseIdFromDB = resultSet.getLong(1);
                        proc = new CallStoredProcedure("spSaveSurveyResponseNumeric(?,?,?)");
                        proc.setParameter(1, responseIdFromDB);
                        proc.setParameter(2, r.getSingleresponse());
                        proc.setParameter(3, r.getQuestion().getTypeID());
                        proc.execute();
                    }
                }
            }
            return true;
        }
        catch (SQLException e) {
            logger.logMessage(e.getMessage(),"Check question type in saveResponse function", SystemConfig.instance().getLogDB());
            e.printStackTrace();
            return false;
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    @Override
    public boolean isResponseprovidedByStudent(Long studentId, Long courseID) {
        CallStoredProcedure proc = null;
        ILoggerFactory loggerFactory = new ErrorLoggerFactory();
        ILogger logger = loggerFactory.createLogger();
        try {
            proc = new CallStoredProcedure("spIsResponseprovidedByStudent(?,?)");
            proc.setParameter(1,courseID);
            proc.setParameter(2,studentId);
            ResultSet resultSet = proc.executeWithResults();
            while (resultSet.next()){
                resultSet.getInt(1);
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.logMessage(e.getMessage(),"Check student id in isResponseprovidedByStudent function", SystemConfig.instance().getLogDB());
            return false;
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return false;
    }
}
