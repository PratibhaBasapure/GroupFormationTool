package CSCI5308.GroupFormationTool.PasswordConstraintTest;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordChecker;
import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordConstraintConfiguration;
import CSCI5308.GroupFormationTool.passwordConstraint.PasswordCheckingFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckingFacadeTest {

    @Test
    void testCheck() {
        IPasswordChecker checker = new PasswordCheckingFacade();
        StringBuffer sb = new StringBuffer("");
        IPasswordConstraintConfiguration config = SystemConfig.instance().getPasswordConstraintConfiguration();
        assertTrue(checker.check("A@bcqrew",sb));
        if (null != config.getPasswordUpperMin() ||
        null != config.getPasswordLowerMin()||
        null != config.getPasswordMax()||
        null != config.getPasswordMin()||
        null != config.getPasswordSymbolMin()){
            assertFalse(checker.check("a@bcqrew",sb));
            assertFalse(checker.check("A@BCQWEG",sb));
            assertFalse(checker.check("a@bc",sb));
            assertFalse(checker.check("A@abcdfef",sb));
            assertFalse(checker.check("A@BCjoifreqoj94787943saf",sb));
        }
    }
}