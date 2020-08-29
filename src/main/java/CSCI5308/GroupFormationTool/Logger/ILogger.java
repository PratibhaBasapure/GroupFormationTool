package CSCI5308.GroupFormationTool.Logger;

public interface ILogger {
    public boolean checkLogValid(String msg,String possibleSolution) ;
    public void logMessage(String msg,String possibleSolution,ILogDB logDB);
}
