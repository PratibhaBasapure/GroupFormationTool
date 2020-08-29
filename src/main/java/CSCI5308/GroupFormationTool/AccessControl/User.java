package CSCI5308.GroupFormationTool.AccessControl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import CSCI5308.GroupFormationTool.Logger.ErrorLoggerFactory;
import CSCI5308.GroupFormationTool.Logger.ILogger;
import CSCI5308.GroupFormationTool.Logger.ILoggerFactory;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.SystemConfig;

import javax.mail.MessagingException;

public class User
{
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	
	private long id;
	private String password;
	private String bannerID;
	private String firstName;
	private String lastName;
	private String email;
	
	public User()
	{
		setDefaults();
	}
	
	public User(long id, IUserPersistence persistence)
	{
		setDefaults();
		persistence.loadUserByID(id, this);
	}
	
	public User(String bannerID, IUserPersistence persistence)
	{
		setDefaults();
		persistence.loadUserByBannerID(bannerID, this);
	}
	
	public void setDefaults()
	{
		id = -1;
		password = "";
		bannerID = "";
		firstName = "";
		lastName = "";
		email = "";
	}
	
	public void setID(long id)
	{
		this.id = id;
	}
	
	public long getID()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getId()
	{
		return id;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setBannerID(String bannerID)
	{
		this.bannerID = bannerID;
	}
	
	public String getBannerID()
	{
		return bannerID;
	}
	public String getBanner()
	{
		return bannerID;
	}
	
	public void setFirstName(String name)
	{
		firstName = name;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setLastName(String name)
	{
		lastName = name;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public boolean isValidUser()
	{
		return id != -1; 
	}
	
	public boolean createUser(
		IUserPersistence userDB,
		IPasswordEncryption passwordEncryption,
		IUserNotifications notification) {
		String rawPassword = password;
		this.password = passwordEncryption.encryptPassword(this.password);
		boolean success = userDB.createUser(this);
		ILoggerFactory loggerFactory = new ErrorLoggerFactory();
		ILogger logger = loggerFactory.createLogger();
		if (success && (null != notification))
		{
			try {
				notification.sendUserLoginCredentials(this, rawPassword);
			} catch (MessagingException e) {
				logger.logMessage(e.getMessage(),"Check with the mail server ", SystemConfig.instance().getLogDB());
				e.printStackTrace();
			}
		}
		return success;
	}
	
	public boolean updateUser(IUserPersistence userDB)
	{
		return userDB.updateUser(this);
	}
	
	private static boolean isStringNullOrEmpty(String s)
	{
		if (null == s)
		{
			return true;
		}
		return s.isEmpty();
	}
	
	public static boolean isBannerIDValid(String bannerID)
	{
		return !isStringNullOrEmpty(bannerID);
	}
		
	public static boolean isFirstNameValid(String name)
	{
		return !isStringNullOrEmpty(name);
	}
	
	public static boolean isLastNameValid(String name)
	{
		return !isStringNullOrEmpty(name);
	}
	
	public static boolean isEmailValid(String email)
	{
		if (isStringNullOrEmpty(email))
		{
			return false;
		}
		 
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", password='" + password + '\'' +
				", bannerID='" + bannerID + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}