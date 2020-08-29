package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.AccessControl.User;

public interface IHistoryPasswordChecker  {
    public boolean checkDuplicatedPassword(User user, String password,IHistoryPasswordDB db);
}
