package CSCI5308.GroupFormationTool.Logger;

public class ErrorLogger extends AbstractLogger implements ILogger {
    private LogType level;
    private String className;
    private String methodName;

    public ErrorLogger(String className, String methodName) {
        level = LogType.ERROR;
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public boolean checkLogValid(String msg,String possibleSolution)  {
        try{
            if (null == possibleSolution){
                throw new ErrorLogMissingException("possibleSolution");

            }
            if (null ==msg){
                throw new ErrorLogMissingException("message");
            }
        }catch (ErrorLogMissingException e){
            return true;
        }
        return false;
    }

    @Override
    public void logMessage( String msg,String possibleSolution,ILogDB logDB) {
        if (checkLogValid(msg,possibleSolution)){
            return;
        }
        StringBuffer sb = buildHeading(this.level,this.className, this.methodName,msg,possibleSolution, null);
        if (null != possibleSolution){
            sb.append("\n Suggested Action");
            sb.append(possibleSolution);
            System.err.println(sb.toString());
        }
    }
}
