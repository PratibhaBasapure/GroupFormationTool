package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUserNotifications;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Email.CallSendEmail;

import javax.mail.MessagingException;

public class UserNotificationMock implements IUserNotifications {
    public void sendUserLoginCredentials(User user, String rawPassword) throws MessagingException {
        CallSendEmail callSendEmail = new CallSendEmail("test@dal.ca","title","this is a new test");
    }
}
