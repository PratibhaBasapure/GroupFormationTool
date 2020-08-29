package CSCI5308.GroupFormationTool.Logger;

public enum LogType {
    INFO("INFO"),ERROR("ERROR"),WARNING("WARNING");
    private String typeName;

    LogType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
