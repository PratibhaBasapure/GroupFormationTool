package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.util.ArrayList;
import java.util.List;

public class HistoryPasswordChecker implements  IHistoryPasswordChecker {
    @Override
    public boolean checkDuplicatedPassword(User user, String password, IHistoryPasswordDB db) {
        boolean duplicated = false;
        List<String > passwords = new ArrayList<>();
        db.loadHistoryPasswordWithLimit(user,passwords, SystemConfig.instance().getPasswordHistoryConstraintConfiguration().getHistoryPasswordMaximum());
        for (String historyPassword:passwords
        ) {
            duplicated = duplicated || SystemConfig.instance().getPasswordEncryption().matches(password,historyPassword);
        }
        return !duplicated;
    }
}
