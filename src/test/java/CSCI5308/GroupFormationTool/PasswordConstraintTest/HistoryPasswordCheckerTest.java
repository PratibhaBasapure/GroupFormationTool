package CSCI5308.GroupFormationTool.PasswordConstraintTest;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.passwordConstraint.HistoryPasswordChecker;
import CSCI5308.GroupFormationTool.passwordConstraint.IHistoryPasswordChecker;
import CSCI5308.GroupFormationTool.passwordConstraint.IHistoryPasswordDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistoryPasswordCheckerTest {
    IHistoryPasswordDB mockDB = null;
    @BeforeEach
    void setUp() {
        mockDB = new HistoryPasswordDBMock();
        User user = new User();
        user.setID(1L);
        user.setPassword("123456");
        mockDB.addNewHistoryPassword(user);
        user.setPassword("234567");
        mockDB.addNewHistoryPassword(user);
        user.setPassword("34567");
        mockDB.addNewHistoryPassword(user);
        user.setPassword("24567");
        mockDB.addNewHistoryPassword(user);
    }

    @Test
    void testCheckDuplicatedPassword() {
        IHistoryPasswordChecker checker = new HistoryPasswordChecker();
        User user = new User();
        user.setID(1L);
        user.setPassword("24567");
        assertTrue(checker.checkDuplicatedPassword(user,"999876", mockDB));
        assertTrue(checker.checkDuplicatedPassword(user,"123456", mockDB));
        assertFalse(checker.checkDuplicatedPassword(user,"234567", mockDB));
        assertFalse(checker.checkDuplicatedPassword(user,"34567", mockDB));
        assertFalse(checker.checkDuplicatedPassword(user,"24567", mockDB));

    }
}