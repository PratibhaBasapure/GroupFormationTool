package CSCI5308.GroupFormationTool.Logger;

public class InfoLoggerFactory implements ILoggerFactory {
    @Override
    public ILogger createLogger() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        return new InfoLogger(className,methodName);
    }
}
