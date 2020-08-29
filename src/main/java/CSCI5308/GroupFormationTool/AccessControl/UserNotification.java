package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Email.CallSendEmail;

import javax.mail.MessagingException;

public class UserNotification implements IUserNotifications {

    private final static String LOGIN_CREDENTIAL_SUBJECT = "Congratulation for your enrollment, here is your password";

    @Override
    public void sendUserLoginCredentials(User user, String rawPassword) throws MessagingException {
        CallSendEmail sender = new CallSendEmail(user.getEmail(), LOGIN_CREDENTIAL_SUBJECT, rawPassword);
        sender.send();
    }
}
