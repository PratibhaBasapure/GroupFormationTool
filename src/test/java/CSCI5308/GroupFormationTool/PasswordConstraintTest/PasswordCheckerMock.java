package CSCI5308.GroupFormationTool.PasswordConstraintTest;

import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordChecker;

public class PasswordCheckerMock implements IPasswordChecker {
    @Override
    public boolean check(String password, StringBuffer sb) {
        String temp = password.replaceAll("abc|bcd"," ");
        return temp.equals(password);
    }
}
