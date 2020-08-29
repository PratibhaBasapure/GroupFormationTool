package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.Email.DefaultEmailConfiguration;
import CSCI5308.GroupFormationTool.Email.IEmailConfiguration;
import CSCI5308.GroupFormationTool.GroupFormation.GroupDB;
import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationAlgorithmDB;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationAlgorithmPersistence;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupPersistence;
import CSCI5308.GroupFormationTool.Logger.ILogDB;
import CSCI5308.GroupFormationTool.Logger.LogDB;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.QuestionDB;
import CSCI5308.GroupFormationTool.Response.IResponsePersistence;
import CSCI5308.GroupFormationTool.Response.ResponseDB;
import CSCI5308.GroupFormationTool.Security.*;
import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Database.*;
import CSCI5308.GroupFormationTool.Courses.*;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.SurveyDB;
import CSCI5308.GroupFormationTool.passwordConstraint.DefaultPasswordConstraintConfiguration;
import CSCI5308.GroupFormationTool.passwordConstraint.DefaultPasswordHistoryConstraintConfiguration;
import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordConstraintConfiguration;
import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordHistoryConstraintConfiguration;

/*
 * This is a singleton, we will learn about these when we learn design patterns.
 * 
 * The single responsibility of this singleton is to store concrete classes
 * selected by the system for use in the rest of the system. This will allow
 * a form of dependency injection in places where we cannot use normal
 * dependency injection (for example classes that override or extend existing
 * library classes in the framework).
 */
public class SystemConfig
{
	private static SystemConfig uniqueInstance = null;

	private ILogDB logDB;
	private IPasswordEncryption passwordEncryption;
	private IPasswordConstraintConfiguration passwordConstraintConfiguration;
	private IUserPersistence userDB;
	private IDatabaseConfiguration databaseConfiguration;
	private ICoursePersistence courseDB;
	private IGroupPersistence groupDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private IEmailConfiguration emailConfiguration;
	private IQuestionPersistence questionDB;
	private IPasswordHistoryConstraintConfiguration passwordHistoryConstraintConfiguration;
	private ISurveyPersistence surveyDB;
	private IResponsePersistence responseDB;
	private IGroupFormationAlgorithmPersistence algorithmDB;
	// This private constructor ensures that no class other than System can allocate
	// the System object. The compiler would prevent it.

	public IResponsePersistence getResponseDB() {
		return responseDB;
	}

	public void setResponseDB(IResponsePersistence responseDB) {
		this.responseDB = responseDB;
	}

	public IGroupPersistence getGroupDB() {
		return groupDB;
	}

	public void setGroupDB(IGroupPersistence groupDB) {
		this.groupDB = groupDB;
	}
	
	public IGroupFormationAlgorithmPersistence getAlgorithmDB() {
		return algorithmDB;
	}

	public void setGroupDB(IGroupFormationAlgorithmPersistence algorithmDB) {
		this.algorithmDB = algorithmDB;
	}

	private SystemConfig()
	{
		logDB = LogDB.getInstance();
		passwordConstraintConfiguration =  DefaultPasswordConstraintConfiguration.getInstance();
		passwordEncryption = BCryptPasswordEncryption.getInstance();
		userDB = UserDB.getInstance();
		databaseConfiguration = DefaultDatabaseConfiguration.getInstance();
		courseDB = CourseDB.getInstance();
		courseUserRelationshipDB = CourseUserRelationshipDB.getInstance();
		emailConfiguration = DefaultEmailConfiguration.getInstance();
		questionDB = QuestionDB.getInstance();
		passwordHistoryConstraintConfiguration = DefaultPasswordHistoryConstraintConfiguration.getInstance();
		surveyDB = new SurveyDB();
		responseDB = new ResponseDB();
		groupDB = new GroupDB();
		algorithmDB = new GroupFormationAlgorithmDB();
	}
	public static SystemConfig instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new SystemConfig();
		}
		return uniqueInstance;
	}
	public ILogDB getLogDB() {
		return logDB;
	}
	public IPasswordHistoryConstraintConfiguration getPasswordHistoryConstraintConfiguration()
	{
		return passwordHistoryConstraintConfiguration;
	}

	public void setPasswordHistoryConstraintConfiguration(IPasswordHistoryConstraintConfiguration passwordHistoryConstraintConfiguration)
	{
		this.passwordHistoryConstraintConfiguration = passwordHistoryConstraintConfiguration;
	}
	public IPasswordEncryption getPasswordEncryption()
	{
		return passwordEncryption;
	}

	public void setPasswordEncryption(IPasswordEncryption passwordEncryption)
	{
		this.passwordEncryption = passwordEncryption;
	}

	public IPasswordConstraintConfiguration getPasswordConstraintConfiguration()
	{
		return passwordConstraintConfiguration;
	}

	public void setPasswordConstraintConfiguration(IPasswordConstraintConfiguration passwordConstraintConfiguration)
	{
		this.passwordConstraintConfiguration = passwordConstraintConfiguration;
	}

	public IUserPersistence getUserDB()
	{
		return userDB;
	}

	public void setUserDB(IUserPersistence userDB)
	{
		this.userDB = userDB;
	}

	public IDatabaseConfiguration getDatabaseConfiguration()
	{
		return databaseConfiguration;
	}

	public void setDatabaseConfiguration(IDatabaseConfiguration databaseConfiguration)
	{
		this.databaseConfiguration = databaseConfiguration;
	}

	public IEmailConfiguration getEmailConfiguration()
	{
		return emailConfiguration;
	}

	public void setEmailConfiguration(IEmailConfiguration emailConfiguration)
	{
		this.emailConfiguration = emailConfiguration;
	}

	public void setCourseDB(ICoursePersistence courseDB)
	{
		this.courseDB = courseDB;
	}

	public ICoursePersistence getCourseDB()
	{
		return courseDB;
	}
	
	public void setCourseUserRelationshipDB(ICourseUserRelationshipPersistence courseUserRelationshipDB)
	{
		this.courseUserRelationshipDB = courseUserRelationshipDB;
	}
	
	public ICourseUserRelationshipPersistence getCourseUserRelationshipDB()
	{
		return courseUserRelationshipDB;
	}

	public IQuestionPersistence getQuestionDB()
	{
		return questionDB;
	}

	public void setQuestionDB(IQuestionPersistence questionDB)
	{
		this.questionDB = questionDB;
	}

	public ISurveyPersistence getSurveyDB() {
		return surveyDB;
	}

	public void setSurveyDB(ISurveyPersistence surveyDB) {
		this.surveyDB = surveyDB;
	}
}
