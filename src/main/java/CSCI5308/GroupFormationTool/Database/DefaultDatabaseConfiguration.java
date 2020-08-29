package CSCI5308.GroupFormationTool.Database;

public class DefaultDatabaseConfiguration implements IDatabaseConfiguration {
	private static final String URL = System.getenv("DB");
	private static final String USER = System.getenv("USER");
	private static final String PASSWORD = System.getenv("PASSWORD");

	private DefaultDatabaseConfiguration(){

	}

	private static IDatabaseConfiguration instance;
	public static IDatabaseConfiguration getInstance(){
		if (null == instance){
			instance = new DefaultDatabaseConfiguration();
		}
		return instance;
	}

	public String getDatabaseUserName() {
		return USER;
	}

	public String getDatabasePassword() {
		return PASSWORD;
	}

	public String getDatabaseURL() {
		return URL;
	}
}
