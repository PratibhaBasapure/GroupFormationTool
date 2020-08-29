package CSCI5308.GroupFormationTool.Email;

import CSCI5308.GroupFormationTool.SystemConfig;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class EmailSessionManager {
    private static EmailSessionManager singleInstance = null;
    private Properties prop = null;

    private String user = null;
    private String password = null;
    private String contentType = null;
    public EmailSessionManager(){
        IEmailConfiguration config = SystemConfig.instance().getEmailConfiguration();
        user = config.getUserName();
        password = config.getPassword();
        contentType = config.getContentType();
        prop = new Properties();
        prop.put("mail.smtp.auth",config.getAuth());
        prop.put("mail.smtp.starttls.enable",config.getStartTls());
        prop.put("mail.smtp.host",config.getHost());
        prop.put("mail.smtp.port",config.getPort());
        prop.put("mail.smtp.trust",config.getHost());
    }
    public static EmailSessionManager instance(){
        if (null == singleInstance){
            singleInstance = new EmailSessionManager();
        }
        return singleInstance;
    }
    public Session getSession(){
        return Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user,password);
            }
        });
    }
}
