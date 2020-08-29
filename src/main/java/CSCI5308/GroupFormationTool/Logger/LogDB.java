package CSCI5308.GroupFormationTool.Logger;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Database.ConnectionManager;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.sql.SQLException;

public class LogDB implements ILogDB
{
    private static ILogDB instance;
    public static ILogDB getInstance(){
        if (null == instance){
            instance = new LogDB();
        }
        return instance;
    }
    @Override
    public boolean createRecord(LogDAO log)
    {

        CallStoredProcedure proc = null;
        ILoggerFactory loggerFactory = new ErrorLoggerFactory();
        ILogger logger = loggerFactory.createLogger();
        try
        {
            if (ConnectionManager.instance().getDBConnection().isClosed()){
                return true;
            }
            proc = new CallStoredProcedure("spCreateLog(?,?,?,?,?,?,?)");
            proc.setParameter(1, log.getClassName());
            proc.setParameter(2, log.getMethodName());
            proc.setParameter(3, log.getCreateTime());
            System.out.println(log.getCreateTime());
            proc.setParameter(4, log.getLevel().getTypeName());
            proc.setParameter(5, log.getMessage());
            proc.setParameter(6, log.getPossibleSolution());
            proc.registerOutputParameterLong(7);
            proc.execute();
        }
        catch (SQLException e)
        {
            logger.logMessage(e.getMessage(),"check the database about log" + log.toString() , SystemConfig.instance().getLogDB());
            e.printStackTrace();
            return false;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return true;
    }
}
