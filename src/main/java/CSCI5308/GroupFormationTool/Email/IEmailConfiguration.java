package CSCI5308.GroupFormationTool.Email;

public interface IEmailConfiguration {
    public String getHost();
    public String getPort();
    public String getUserName();
    public String getPassword();
    public String getAuth();
    public String getStartTls();
    public String getContentType();
}
