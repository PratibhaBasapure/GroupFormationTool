package CSCI5308.GroupFormationTool.GroupFormation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Logger.ErrorLoggerFactory;
import CSCI5308.GroupFormationTool.Logger.ILogger;
import CSCI5308.GroupFormationTool.Logger.ILoggerFactory;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.Response.Response;

public class GroupFormationAlgorithmDB implements IGroupFormationAlgorithmPersistence {
	
	private static final int NUMERIC_TYPE_ID = 1;
	private static final int MULTI_CHOICE_MULTI_ONE_TYPE_ID = 2;
	private static final int MULTI_CHOICE_MULTI_MULTI_TYPE_ID = 3;
	private static final int FREE_TEXT_TYPE_ID = 4;
	
	@Override
	public boolean createAlgorithm(GroupFormationAlgorithm algorithm) {
		CallStoredProcedure proc = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ILoggerFactory loggerFactory = new ErrorLoggerFactory();
		ILogger logger = loggerFactory.createLogger();
		try {
			proc = new CallStoredProcedure("spCreateAlgorithm(?, ?, ?, ?, ?)");
			proc.setParameter(1, algorithm.getCourse().getId());
			proc.setParameter(2, sdf.format(algorithm.getCreatedOn()));
			proc.setParameter(3, sdf.format(algorithm.getCreatedOn()));
			proc.setParameter(4, algorithm.getGroupSize());
			proc.registerOutputParameterLong(5);
			proc.execute();
			Long algorithmID = proc.getLongParameters(5);
			proc = new CallStoredProcedure("spCreateAlgorithmDefinition(?, ?, ?, ?, ?)");
			for (int i = 0; i < algorithm.getQuestions().size(); i++) {
				proc.setParameter(1, algorithmID);
				proc.setParameter(2, algorithm.getQuestions().get(i).getId());
				proc.setParameter(3, algorithm.getWeights().get(i));
				proc.setParameter(4, algorithm.getComparisonChoices().get(i));
				proc.registerOutputParameterLong(5);
				proc.execute();
			}
		} catch (SQLException e) {
			logger.logMessage(e.getMessage(),"Check create Algorithm method", SystemConfig.instance().getLogDB());
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	@Override
	public GroupFormationAlgorithm loadAlgorithmByCourse(Course course) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ILoggerFactory loggerFactory = new ErrorLoggerFactory();
		ILogger logger = loggerFactory.createLogger();
		List<Question> questions = new ArrayList<>();
		List<Double> weights = new ArrayList<>();
		List<Boolean> comparisonChoices = new ArrayList<>();
		CallStoredProcedure proc = null;
		Long algorithmID = null;
		Date createOn = null;
		Date updateOn = null;
		Integer groupSize = null;
		GroupFormationAlgorithm algorithm = null;
		try {
			proc = new CallStoredProcedure("spLoadAlgorithmByCourseId(?)");
			proc.setParameter(1, course.getId());
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					algorithmID = results.getLong(1);
					createOn = sdf.parse(results.getString(3));
					updateOn = sdf.parse(results.getString(4));
					groupSize = results.getInt(1);
				}
			}
			proc = new CallStoredProcedure("spLoadAlgorithmDefinitionByAlgorithmId(?)");
			proc.setParameter(1, algorithmID);
			results = proc.executeWithResults();
			if (null != results) {
				Question question;
				while (results.next()) {
					question = new Question();
					question.setId(results.getLong(3));
					weights.add(results.getDouble(4));
					comparisonChoices.add(results.getBoolean(5));
					question.setType(results.getString(6));
					questions.add(question);
				}
			}
			algorithm = new GroupFormationAlgorithmBuilder().setId(algorithmID).setCourse(course)
					.setComparisonChoices(comparisonChoices).setCreatedOn(createOn).setGroupSize(groupSize)
					.setQuestions(questions).setWeights(weights).getGroupFormationAlgorithm();

		} catch (SQLException e) {
			logger.logMessage(e.getMessage(),"Check load algorithm by course method", SystemConfig.instance().getLogDB());
		} catch (ParseException e) {
			logger.logMessage(e.getMessage(),"Parse error in load algorithm by course method", SystemConfig.instance().getLogDB());
			e.printStackTrace();
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return algorithm;
	}

	@Override
	public LinkedHashMap<User, List<Response>> loadUsersResponsesByCourseID(Long courseID) {
		ILoggerFactory loggerFactory = new ErrorLoggerFactory();
		ILogger logger = loggerFactory.createLogger();
		CallStoredProcedure proc = null;
		try {
			LinkedHashMap<User, List<Response>> responses = new LinkedHashMap<>();
			proc = new CallStoredProcedure("spLoadSurveyIdByCourseId(?)");
			proc.setParameter(1,courseID);
			
			ResultSet resultSet = proc.executeWithResults();
			while (resultSet.next()){
				int surveyId = resultSet.getInt(1);
				proc = new CallStoredProcedure("spUsersBySurveyId(?)");
				proc.setParameter(1,surveyId);
				
				ResultSet resultSet1 = proc.executeWithResults();
				while (resultSet1.next()) {
					User user = new User();
					List<Response> userResponses = new ArrayList<>();
					
					int userID = resultSet1.getInt(1);
					proc = new CallStoredProcedure("spLoadUser(?)");
					proc.setParameter(1,userID);
					ResultSet userDetails = proc.executeWithResults();

					while (userDetails.next()){
						long userId = userDetails.getLong(1);
						String bannerID = userDetails.getString(2);
						String firstName = userDetails.getString(4);
						String lastName = userDetails.getString(5);
						String email = userDetails.getString(6);
						user.setID(userId);
						user.setBannerID(bannerID);
						user.setFirstName(firstName);
						user.setLastName(lastName);
						user.setEmail(email);
					}
					
					proc = new CallStoredProcedure("spResponseIdByUserIDSurveyId(?,?)");
					proc.setParameter(1,surveyId);
					proc.setParameter(2,userID);
					
					ResultSet responseIDANDQuestionsID = proc.executeWithResults();
					while (responseIDANDQuestionsID.next()){
						int questionId = responseIDANDQuestionsID.getInt(2);
						int responseID = responseIDANDQuestionsID.getInt(1);
						proc = new CallStoredProcedure("spLoadQuestionById(?)");
						proc.setParameter(1,questionId);
						
						ResultSet questionType = proc.executeWithResults();
						while (questionType.next()){
							Response response = new Response();
							Question question = new Question();
							question.setId(questionType.getLong(1));
							response.setQuestion(question);
							int eachQuestionType = questionType.getInt(3);
							
							if (eachQuestionType == MULTI_CHOICE_MULTI_ONE_TYPE_ID){
								proc = new CallStoredProcedure("spLoadMultipleOptionSingleResponse(?)");
								proc.setParameter(1,responseID);
								ResultSet eachQuestionResponse = proc.executeWithResults();
								while (eachQuestionResponse.next()){
									String responseEach = eachQuestionResponse.getString(1);
									response.setSingleresponse(responseEach);
								}
							}
							if (eachQuestionType == FREE_TEXT_TYPE_ID){
								proc = new CallStoredProcedure("spLoadFreeTextResponse(?)");
								proc.setParameter(1,responseID);
								ResultSet eachQuestionResponse = proc.executeWithResults();
								while (eachQuestionResponse.next()){
									String responseEach =  eachQuestionResponse.getString(1);
									response.setSingleresponse(responseEach);
								}
							}
							if (eachQuestionType == NUMERIC_TYPE_ID){
								proc = new CallStoredProcedure("spLoadNumericResponse(?)");
								proc.setParameter(1,responseID);
								ResultSet eachQuestionResponse = proc.executeWithResults();
								while (eachQuestionResponse.next()){
									String responseEach =  eachQuestionResponse.getString(1);
									response.setSingleresponse(responseEach);
								}
							}
							if (eachQuestionType == MULTI_CHOICE_MULTI_MULTI_TYPE_ID){
								proc = new CallStoredProcedure("spLoadMultipleOptionMultipleResponse(?)");
								proc.setParameter(1,responseID);
								ResultSet eachQuestionResponse = proc.executeWithResults();
								ArrayList<String> eachResponses = new ArrayList<>();
								while (eachQuestionResponse.next()){
									String responseEach =  eachQuestionResponse.getString(1);
									eachResponses.add(responseEach);
								}
								response.setResponse(eachResponses);
							}
							userResponses.add(response);
						}
					}
					responses.put(user,userResponses);
				}
			}
			return responses;
		}
		catch (SQLException e) {
			logger.logMessage(e.getMessage(),"Check loadUsersResponsesByCourseID method", SystemConfig.instance().getLogDB());
			e.printStackTrace();
			return null;
		}
		finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}

}
