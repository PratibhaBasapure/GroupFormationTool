package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.Logger.ErrorLoggerFactory;
import CSCI5308.GroupFormationTool.Logger.ILogger;
import CSCI5308.GroupFormationTool.Logger.ILoggerFactory;
import CSCI5308.GroupFormationTool.SystemConfig;

public class PasswordRegChecker implements IPasswordChecker {
    private String reg;
    private String info;

    public PasswordRegChecker(String reg, String info) {
        this.reg = reg;
        this.info = info;
    }

    @Override
    public boolean check(String password, StringBuffer sb) {
        String temp = password.replaceAll(reg," ");
        ILoggerFactory loggerFactory = new ErrorLoggerFactory();
        ILogger logger = loggerFactory.createLogger();
        if (temp.equals(password)){
            return true;
        }else {
            sb.append("Format: ");
            sb.append(info);
            try{
                sb.append(reg.replace("|",","));
            }catch (NullPointerException e){
                logger.logMessage(e.getMessage(),"check the StringBuffer and config about regex" , SystemConfig.instance().getLogDB());
                e.printStackTrace();
            }
            sb.append(".<br/> \n");
            return false;
        }
    }
}
