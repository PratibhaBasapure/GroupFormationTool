package CSCI5308.GroupFormationTool.PasswordConstraintTest;

import CSCI5308.GroupFormationTool.passwordConstraint.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordOverallLengthCheckerTest {
    @Test
    public void testCheckLength(){
        IPasswordRegLengthCheckerBuilder builder = new PasswordRegRegLengthCheckerBuilder();
        builder.setMaxLength(12);
        builder.setMiniLength(3);
        builder.setReg(PasswordReg.NONE);
        IPasswordChecker checker = builder.getResult();
        StringBuffer sb = new StringBuffer("");
        assertTrue(checker.check("1234567",sb));
        System.out.println(sb.toString());
        System.out.println("test");
        assertFalse(checker.check("123456789abcd",sb));
        System.out.println(sb.toString());
    }
}
