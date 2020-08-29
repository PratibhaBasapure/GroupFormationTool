package CSCI5308.GroupFormationTool.Email;

import CSCI5308.GroupFormationTool.SystemConfig;

import javax.mail.*;
import javax.mail.internet.*;

public class CallSendEmail {
    private String fromEmail = null;
    private String toEmail = null;
    private String subject = null;
    private String text = null;
    private String contentType = null;
    private Session session = null;
    private Message message = null;

    public CallSendEmail(String toEmail,String subject, String text) throws MessagingException {
        IEmailConfiguration config = SystemConfig.instance().getEmailConfiguration();
        this.fromEmail = config.getUserName();
        this.contentType = config.getContentType();
        this.toEmail = toEmail;
        this.subject = subject;
        this.text = text;
        session = null;
        message = null;
        openSession();
        setupMessage();
    }
    private void openSession(){
        session = EmailSessionManager.instance().getSession();
    }
    private void setupMessage() throws MessagingException {
        message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(subject);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(text, contentType);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

    }
    public void send() throws MessagingException {
        Transport.send(message);
    }
}
