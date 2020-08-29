package CSCI5308.GroupFormationTool.passwordConstraint;

public class PasswordRegLengthChecker implements IPasswordChecker{
    private Integer maxLength;
    private Integer miniLength;
    private String reg;
    private String info;

    public PasswordRegLengthChecker(Integer maxLength, Integer miniLength, String reg, String info) {
        this.maxLength = maxLength;
        this.miniLength = miniLength;
        this.reg = reg;
        this.info = info;
    }

    @Override
    public boolean check(String password, StringBuffer sb) {
        boolean result = true;
        if (isReg()){
            password = password.replaceAll(reg,"");
        }
        if (isMin()&&(password.length()<miniLength)){
            if (isReg()){
                patchTag(sb);
            }
            result = false;
            sb.append("The password should have more than ");
            sb.append(miniLength);
            sb.append(" characters.");
            sb.append("<br/> \n");
        }
        if (isMax()&&(password.length()>maxLength)){
            if (isReg()){
                patchTag(sb);
            }
            result = false;
            sb.append("The password should have fewer than ");
            sb.append(maxLength);
            sb.append(" characters.");
            sb.append("<br/> \n");
        }
        return result;
    }
    private boolean isReg(){
        return null!=reg;
    }
    private boolean isMin(){
        return null!=miniLength;
    }
    private boolean isMax(){
        return null!=maxLength;
    }
    private void patchTag(StringBuffer sb){
        sb.append("For ");
        sb.append(info);
        sb.append(":");
    }
}
