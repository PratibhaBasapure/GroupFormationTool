package CSCI5308.GroupFormationTool.passwordConstraint;

public interface IPasswordConstraintConfiguration {
    public Integer getPasswordMin();
    public Integer getPasswordMax();
    public Integer getPasswordUpperMin();
    public Integer getPasswordLowerMin();
    public Integer getPasswordSymbolMin();
    public String getBannedRe();
}
