package CSCI5308.GroupFormationTool.passwordConstraint;

public interface IPasswordRegLengthCheckerBuilder extends IPasswordRegCheckerBuilder{
    public void setMaxLength(Integer maxLength);
    public void setMiniLength(Integer miniLength);
}
