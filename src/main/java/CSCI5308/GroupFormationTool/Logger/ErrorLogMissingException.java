package CSCI5308.GroupFormationTool.Logger;

public class ErrorLogMissingException extends Exception{
    private final static String MSG = "You've missed message in the marked method for logging ";
    public ErrorLogMissingException(){
        ILoggerFactory factory = new ErrorLoggerFactory();
        ILogger logger = factory.createLogger();
        logger.logMessage(MSG," patch the parameter up",LogDB.getInstance());
    }
    public ErrorLogMissingException(String missingName){
        ILoggerFactory factory = new ErrorLoggerFactory();
        ILogger logger = factory.createLogger();
        logger.logMessage(MSG,missingName+" is null",LogDB.getInstance());
    }
}
