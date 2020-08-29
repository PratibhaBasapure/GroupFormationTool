package CSCI5308.GroupFormationTool.passwordConstraint;

public class PasswordRegRegLengthCheckerBuilder implements IPasswordRegLengthCheckerBuilder {
    private Integer maxLength;
    private Integer miniLength;
    private String reg;
    private String info;

    @Override
    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void setMiniLength(Integer miniLength) {
        this.miniLength = miniLength;
    }

    @Override
    public void setReg(PasswordReg reg) {
        this.reg = reg.getFormat();
        this.info = reg.getInfo();
    }
    @Override
    public IPasswordChecker getResult(){
        return new PasswordRegLengthChecker(maxLength,miniLength,reg,info);
    }

    @Override
    public void reset() {
        this.maxLength = null;
        this.miniLength = null;
        this.reg = null;
        this.info = null;
    }
}
