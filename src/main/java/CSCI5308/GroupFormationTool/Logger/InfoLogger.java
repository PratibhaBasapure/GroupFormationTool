package CSCI5308.GroupFormationTool.Logger;

public class InfoLogger extends AbstractLogger implements ILogger{
    private LogType level;
    private String className;
    private String methodName;

    public InfoLogger(String className, String methodName) {
        level = LogType.INFO;
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public boolean checkLogValid(String msg,String possibleSolution) {
        return null ==msg;
    }

    @Override
    public void logMessage( String msg,String possibleSolution,ILogDB logDB) {
        try{
            if (checkLogValid(msg,possibleSolution)){
                throw new ErrorLogMissingException();
            }
        }catch (ErrorLogMissingException e){
            return;
        }
        StringBuffer sb = buildHeading(this.level,this.className, this.methodName,msg,possibleSolution,logDB);
        if (null != possibleSolution){
            sb.append("\n Suggested Action");
            sb.append(possibleSolution);
            System.out.println(sb.toString());
        }
    }

}
