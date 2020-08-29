package CSCI5308.GroupFormationTool.Logger;

public class LogDAO implements ILog {
    private LogType level;
    private String className;
    private String methodName;
    private String createTime;
    private String message;
    private String possibleSolution;

    public LogDAO(LogType level, String className, String methodName, String createTime, String message, String possibleSolution) {
        this.level = level;
        this.className = className;
        this.methodName = methodName;
        this.createTime = createTime;
        this.message = message;
        this.possibleSolution = possibleSolution;
    }

    @Override
    public LogType getLevel() {
        return level;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getPossibleSolution() {
        return possibleSolution;
    }
}
