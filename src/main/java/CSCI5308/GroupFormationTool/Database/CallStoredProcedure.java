package CSCI5308.GroupFormationTool.Database;

import CSCI5308.GroupFormationTool.Logger.ErrorLoggerFactory;
import CSCI5308.GroupFormationTool.Logger.ILogger;
import CSCI5308.GroupFormationTool.Logger.ILoggerFactory;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.sql.*;

public class CallStoredProcedure
{
	private String storedProcedureName;
	private Connection connection;
	private CallableStatement statement;
	
	public CallStoredProcedure(String storedProcedureName) throws SQLException
	{
		this.storedProcedureName = storedProcedureName;
		connection = null;
		statement = null;
		openConnection();
		createStatement();
	}
	
	private void createStatement() throws SQLException
	{
		statement = connection.prepareCall("{call " + storedProcedureName + "}");
	}
	
	private void openConnection() throws SQLException
	{
		connection = ConnectionManager.instance().getDBConnection();
	}
	
	public void cleanup()
	{
		ILoggerFactory loggerFactory = new ErrorLoggerFactory();
		ILogger logger = loggerFactory.createLogger();
		try
		{
			if (null != statement)
			{
				statement.close();
			}
			if (null != connection)
			{
				if (!connection.isClosed())
				{
					connection.close();
				}
			}
		}
		catch (Exception e)
		{
			logger.logMessage(e.getMessage(),"Seems no solution to this" , SystemConfig.instance().getLogDB());
		}
	}
	
	public void setParameter(int paramIndex, String value) throws SQLException
	{
		statement.setString(paramIndex, value);
	}
	public void setParameter(int paramIndex, Double value) throws SQLException
	{
		statement.setDouble(paramIndex, value);
	}
	
	public void registerOutputParameterString(int paramIndex) throws SQLException
	{
		statement.registerOutParameter(paramIndex, java.sql.Types.VARCHAR);
	}
	
	public void setParameter(int paramIndex, long value) throws SQLException
	{
		statement.setLong(paramIndex, value);
	}

	public void setParameter(int paramIndex, boolean value) throws SQLException
	{
		statement.setBoolean(paramIndex, value);
	}

	public void registerOutputParameterLong(int paramIndex) throws SQLException
	{
		statement.registerOutParameter(paramIndex, java.sql.Types.BIGINT);
	}
	
	public ResultSet executeWithResults() throws SQLException
	{
		if (statement.execute())
		{
			return statement.getResultSet();
		}
		return null;
	}
	
	public void execute() throws SQLException
	{
		statement.execute();
	}

	public int executeUpdate() throws SQLException
	{
		int st = statement.executeUpdate();
		return st;
	}
	public Long getLongParameters(int paramIndex)throws SQLException
	{
		return statement.getLong(paramIndex);
	}
}
