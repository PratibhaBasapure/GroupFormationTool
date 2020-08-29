package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Logger.ErrorLoggerFactory;
import CSCI5308.GroupFormationTool.Logger.ILogger;
import CSCI5308.GroupFormationTool.Logger.ILoggerFactory;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDB implements IQuestionPersistence {
	private static final int NUMERIC_TYPE_ID = 1;
	private static final int MULTI_CHOICE_MULTI_ONE_TYPE_ID = 2;
	private static final int MULTI_CHOICE_MULTI_MULTI_TYPE_ID = 3;
	private static final int FREE_TEXT_TYPE_ID = 4;

	private static IQuestionPersistence instance;
	public static IQuestionPersistence getInstance(){
		if (null == instance){
			instance = new QuestionDB();
		}
		return instance;
	}
	private QuestionDB(){

	}

	@Override
	public List<Question> loadAllQuestionsByInstructor(long instructorId) {
		CallStoredProcedure proc = null;
		List<Question> questionList = new ArrayList<Question>();
		ILoggerFactory loggerFactory = new ErrorLoggerFactory();
		ILogger logger = loggerFactory.createLogger();
		try {
			proc = new CallStoredProcedure("spFindQuestionsByUserID(?)");
			proc.setParameter(1, instructorId);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					User user = new User();
					Question question = new Question();
					question.setId(results.getInt(1));
					user.setID(results.getLong(2));
					question.setInstructor(user);
					question.setType(results.getString(3));
					question.setTitle(results.getString(4));
					question.setText(results.getString(5));
					question.setCreatedOn(results.getDate(6));
					questionList.add(question);
				}
			}
		} catch (SQLException e) {
			logger.logMessage(e.getMessage(),"Check with instructor id "+instructorId, SystemConfig.instance().getLogDB());

			e.printStackTrace();
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return questionList;
	}

    @Override
    public Question loadQuestionById(long questionId) {
        ArrayList<MultipleChoiceOption> multipleChoiceOptionList = new ArrayList<>();
        CallStoredProcedure proc = null;
        Question question = new Question();
		ILoggerFactory loggerFactory = new ErrorLoggerFactory();
		ILogger logger = loggerFactory.createLogger();
        try {
            proc = new CallStoredProcedure("spLoadQuestionById(?)");
            proc.setParameter(1,questionId);
            ResultSet resultSet = proc.executeWithResults();
            while (resultSet.next())
            {
				question.setId(resultSet.getLong(1));
                int type = resultSet.getInt(3);

                if (type ==MULTI_CHOICE_MULTI_ONE_TYPE_ID || type == MULTI_CHOICE_MULTI_MULTI_TYPE_ID)
                {
                    multipleChoiceOptionList =  loadMultipleChoiceOptions(questionId);
                    question.setMultipleChoiceOption(multipleChoiceOptionList);
                }
                question.setTypeID(resultSet.getInt(3));
                question.setTitle(resultSet.getString(4));
                question.setText(resultSet.getString(5));
                question.setCreatedOn(resultSet.getDate(6));
            }
        } catch (SQLException e) {
			logger.logMessage(e.getMessage(),"Check with question id "+questionId, SystemConfig.instance().getLogDB());
            e.printStackTrace();
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return question;
    }

    private ArrayList<MultipleChoiceOption> loadMultipleChoiceOptions(long questionId){
        ArrayList<MultipleChoiceOption> multipleChoiceOptionList = new ArrayList<>();
        CallStoredProcedure proc = null;
		ILoggerFactory loggerFactory = new ErrorLoggerFactory();
		ILogger logger = loggerFactory.createLogger();
        try {
            proc = new CallStoredProcedure("spLoadQuestionOptionsById(?)");
            proc.setParameter(1, questionId);
            ResultSet resultSet = proc.executeWithResults();
            while (resultSet.next()){
                MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
                multipleChoiceOption.setDisplayText(resultSet.getString(3));
                multipleChoiceOption.setOptionNumber(resultSet.getInt(4));
                multipleChoiceOptionList.add(multipleChoiceOption);
            }
        }
        catch (SQLException e){
			logger.logMessage(e.getMessage(),"Check with question id "+questionId, SystemConfig.instance().getLogDB());
            e.printStackTrace();
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return multipleChoiceOptionList;
    }

	@Override
	public boolean createQuestion(Question question) {
		CallStoredProcedure proc = null;
		ILoggerFactory loggerFactory = new ErrorLoggerFactory();
		ILogger logger = loggerFactory.createLogger();
		try {
			if (question.getTypeID() == NUMERIC_TYPE_ID || question.getTypeID() == FREE_TEXT_TYPE_ID) {
				proc = new CallStoredProcedure("spCreateQuestion(?, ?, ?, ?)");
				proc.setParameter(1, question.getTitle());
				proc.setParameter(2, question.getText());
				proc.setParameter(3, question.getInstructor().getID());
				proc.setParameter(4, question.getTypeID());
				proc.execute();
				return true;

			} else if (question.getTypeID() == MULTI_CHOICE_MULTI_ONE_TYPE_ID || question.getTypeID() == MULTI_CHOICE_MULTI_MULTI_TYPE_ID) {
				proc = new CallStoredProcedure("spCreateQuestion(?, ?, ?, ?)");
				proc.setParameter(1, question.getTitle());
				proc.setParameter(2, question.getText());
				proc.setParameter(3, question.getInstructor().getID());
				proc.setParameter(4, question.getTypeID());
				ResultSet resultSet = proc.executeWithResults();
				int questionIdfromDB = 1;
				while (resultSet.next()) {
					questionIdfromDB = resultSet.getInt(1);
				}
				createMultipleQuestionOptions(questionIdfromDB, question.getMultipleChoiceOption());
				return true;
			}
		} catch (SQLException e) {
			logger.logMessage(e.getMessage(),"Check with question "+ question.toString(), SystemConfig.instance().getLogDB());
			e.printStackTrace();
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return false;
	}

	private boolean createMultipleQuestionOptions(long id, ArrayList<MultipleChoiceOption> options) {
		CallStoredProcedure proc = null;
		ILoggerFactory loggerFactory = new ErrorLoggerFactory();
		ILogger logger = loggerFactory.createLogger();
		try {
			for (int i = 0; i < options.size(); i++) {
				proc = new CallStoredProcedure("spCreateQuestionWithOptions(?, ?, ?)");
				proc.setParameter(1, id);
				proc.setParameter(2, options.get(i).getDisplayText());
				proc.setParameter(3, options.get(i).getOptionNumber());
				proc.execute();
			}
			return true;
		} catch (SQLException e) {
			logger.logMessage(e.getMessage(),"Check with question id "+ id, SystemConfig.instance().getLogDB());
			e.printStackTrace();
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}

	@Override
	public boolean deleteQuestion(Question question) {
		CallStoredProcedure proc = null;
		ILoggerFactory loggerFactory = new ErrorLoggerFactory();
		ILogger logger = loggerFactory.createLogger();
		try {
			proc = new CallStoredProcedure("spDeleteQuestion(?)");
			proc.setParameter(1, question.getId());
			int resultSet = proc.executeUpdate();
			
			if (resultSet == 0) {
				return false;
			}
			return true;

		} catch (SQLException e) {
			logger.logMessage(e.getMessage(),"Check with question "+ question.toString(), SystemConfig.instance().getLogDB());
			e.printStackTrace();
			return false;

		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}

	@Override
	public List<Question> loadAllQuestionTypes() {
		List<Question> questions = new ArrayList<Question>();
		CallStoredProcedure proc = null;
		ILoggerFactory loggerFactory = new ErrorLoggerFactory();
		ILogger logger = loggerFactory.createLogger();
		try {
			proc = new CallStoredProcedure("spLoadAllQuestoinTypes()");
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					int id = results.getInt(1);
					String questionType = results.getString(2);
					Question question = new Question();
					question.setTypeID(id);
					question.setType(questionType);
					questions.add(question);
				}
			}
		} catch (SQLException e) {
			logger.logMessage(e.getMessage(),"Check with question table", SystemConfig.instance().getLogDB());
			e.printStackTrace();
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return questions;
	}
}
