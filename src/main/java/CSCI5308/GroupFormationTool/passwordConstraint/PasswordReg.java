package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.SystemConfig;

public enum PasswordReg {
    LOWER("[^a-z]","lower class")
    ,UPPER("[^A-Z]","upper class")
    ,NONE(null,null)
    ,SPECIAL("[a-zA-Z0-9\\s+]","special characters")
    ,BANNED(SystemConfig.instance().getPasswordConstraintConfiguration().getBannedRe(),"banned combination");
    private final String format;
    private final String info;
    private PasswordReg(String reg, String info){
        this.format = reg;
        this.info = info;
    }

    public String getFormat() {
        return format;
    }

    public String getInfo() {
        return info;
    }
}
