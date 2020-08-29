package CSCI5308.GroupFormationTool.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AbstractLogger {
    public StringBuffer buildHeading(LogType level, String className, String methodName,String msg,String possibleSolution, ILogDB logDB){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (null != logDB){
            logDB.createRecord(new LogDAO(level,className,methodName,sdf.format(new Date()),msg,possibleSolution));
        }
        System.out.println("==============");
        StringBuffer sb = new StringBuffer(sdf.format(new Date()));
        sb.append("[");
        sb.append(level.getTypeName());
        sb.append("], at class '");
        sb.append(className);
        sb.append("' at method '");
        sb.append(methodName);
        sb.append("' ");
        sb.append(msg);
        return sb;
    }
}
