package CSCI5308.GroupFormationTool.Email;

public class DefaultEmailConfiguration implements IEmailConfiguration {
    public static final String  HOST = System.getenv("EMAIL_HOST");
    public static final String  PORT = System.getenv("EMAIL_PORT");
    public static final String  USER = System.getenv("EMAIL_USER");
    public static final String  PASSWORD = System.getenv("EMAIL_PASSWORD");
    public static final String  STARTTLS = System.getenv("EMAIL_STARTTLS");
    public static final String  AUTH = System.getenv("EMAIL_AUTH");
    public static final String  CONTENT_TYPE = System.getenv("EMAIL_CONTENT_TYPE");
    private static IEmailConfiguration instance;
    private DefaultEmailConfiguration(){

    }
    public static IEmailConfiguration getInstance(){
        if (null == instance){
            instance = new DefaultEmailConfiguration();
        }
        return instance;
    }
    @Override
    public String getHost() {
        return HOST;
    }

    @Override
    public String getPort() {
        return PORT;
    }

    @Override
    public String getUserName() {
        return USER;
    }

    @Override
    public String getPassword() {
        return PASSWORD;
    }

    @Override
    public String getAuth() {
        return AUTH;
    }

    @Override
    public String getStartTls() {
        return STARTTLS;
    }

    @Override
    public String getContentType() {
        return CONTENT_TYPE;
    }
}
