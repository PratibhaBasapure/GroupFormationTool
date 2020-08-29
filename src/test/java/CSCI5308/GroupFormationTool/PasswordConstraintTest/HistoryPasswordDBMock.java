package CSCI5308.GroupFormationTool.PasswordConstraintTest;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.passwordConstraint.IHistoryPasswordDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryPasswordDBMock implements IHistoryPasswordDB {
    private Map<Long, List<String >> mockDB = null;


    public HistoryPasswordDBMock() {
        this.mockDB = new HashMap<>();
    }

    @Override
    public void loadHistoryPasswordWithLimit(User user, List<String > passwords, Integer length) {
        List<String > lastPasswords = mockDB.get(user.getID());
        passwords.addAll(lastPasswords.subList(lastPasswords.size()-length,lastPasswords.size()));
        passwords.forEach(pwd->{
            System.out.println(pwd);
        });
    }

    @Override
    public boolean addNewHistoryPassword(User user) {
        if (!mockDB.containsKey(user.getID())){
            mockDB.put(user.getID(),new ArrayList<>());
        }
        mockDB.get(user.getID()).add(SystemConfig.instance().getPasswordEncryption().encryptPassword(user.getPassword()));
        return true;
    }
}
