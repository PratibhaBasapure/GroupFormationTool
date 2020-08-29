package CSCI5308.GroupFormationTool.Logger;

public interface ILog {
    public LogType getLevel();
    public String getClassName();
    public String getMethodName();
    public String getCreateTime();
    public String getMessage();
    public String getPossibleSolution();
}
