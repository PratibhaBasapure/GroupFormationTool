package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Logger.ErrorLoggerFactory;
import CSCI5308.GroupFormationTool.Logger.ILogger;
import CSCI5308.GroupFormationTool.Logger.ILoggerFactory;
import CSCI5308.GroupFormationTool.SystemConfig;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoryPasswordDB implements IHistoryPasswordDB {
    @Override
    public void loadHistoryPasswordWithLimit(User user, List<String > passwords,Integer length) {
        CallStoredProcedure proc = null;
        ILoggerFactory loggerFactory = new ErrorLoggerFactory();
        ILogger logger = loggerFactory.createLogger();
        try
        {
            proc = new CallStoredProcedure("spLoadPasswords(?,?)");
            proc.setParameter(1, user.getID());
            proc.setParameter(2, length);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    long id = results.getLong(1);
                    long userID = results.getLong(2);
                    String password = results.getString(3);
                    String modifyDate = results.getString(4);
                    passwords.add(password);
                }
            }
        }
        catch (SQLException e)
        {
            logger.logMessage(e.getMessage(),"Check"+user.toString(),SystemConfig.instance().getLogDB());
            e.printStackTrace();
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }

    }
    @Override
    public boolean addNewHistoryPassword(User user) {
        CallStoredProcedure proc = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer insertedID = 0;
        ILoggerFactory loggerFactory = new ErrorLoggerFactory();
        ILogger logger = loggerFactory.createLogger();
        try
        {
            proc = new CallStoredProcedure("spCreateHistoryPassword(?,?,?,?)");
            proc.setParameter(1, user.getID());
            proc.setParameter(2, user.getPassword());
            proc.setParameter(3, sdf.format(new Date()));
            proc.registerOutputParameterLong(4);
            proc.execute();
        }
        catch (SQLException e)
        {
            logger.logMessage(e.getMessage(),"Check"+user.toString(),SystemConfig.instance().getLogDB());
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
