package CSCI5308.GroupFormationTool.passwordConstraint;

public class PasswordRegCheckerBuilder implements IPasswordRegCheckerBuilder {
    private String reg;
    private String info;
    @Override
    public void setReg(PasswordReg reg) {
        this.reg = reg.getFormat();
        this.info = reg.getInfo();
    }

    @Override
    public void reset() {
        this.reg = null;
        this.info = null;
    }

    @Override
    public IPasswordChecker getResult() {
        return new PasswordRegChecker(this.reg,this.info);
    }
}
