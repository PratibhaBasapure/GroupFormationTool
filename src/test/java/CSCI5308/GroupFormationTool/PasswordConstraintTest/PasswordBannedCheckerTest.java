package CSCI5308.GroupFormationTool.PasswordConstraintTest;

import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordChecker;
import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordRegCheckerBuilder;
import CSCI5308.GroupFormationTool.passwordConstraint.PasswordReg;
import CSCI5308.GroupFormationTool.passwordConstraint.PasswordRegCheckerBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordBannedCheckerTest {
    @Test
    public void testCheckSubstring(){
        IPasswordRegCheckerBuilder builder = new PasswordRegCheckerBuilder();
        builder.setReg(PasswordReg.BANNED);
        IPasswordChecker checker = builder.getResult();
        StringBuffer sb = new StringBuffer("");
        assertTrue(checker.check("fabdce",sb));
        assertFalse(checker.check("fabce",sb));
        assertFalse(checker.check("fbcde",sb));
    }
}
