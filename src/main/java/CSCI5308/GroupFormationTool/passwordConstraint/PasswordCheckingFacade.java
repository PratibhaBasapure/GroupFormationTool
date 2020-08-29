package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.SystemConfig;

public class PasswordCheckingFacade implements IPasswordChecker{


    private static IPasswordChecker instance;

    public static IPasswordChecker getInstance() {
        if (null == instance){
            instance = new PasswordCheckingFacade();
        }
        return instance;
    }

    @Override
    public boolean check(String password, StringBuffer sb) {
        boolean result = true;
        IPasswordConstraintConfiguration config = SystemConfig.instance().getPasswordConstraintConfiguration();
        IPasswordRegLengthCheckerBuilder lengthBuilder = new PasswordRegRegLengthCheckerBuilder();
        IPasswordRegCheckerBuilder regBuilder = new PasswordRegCheckerBuilder();
        IPasswordChecker checker;
        lengthBuilder.setMaxLength(config.getPasswordMax());
        lengthBuilder.setMiniLength(config.getPasswordMin());
        lengthBuilder.setReg(PasswordReg.NONE);
        checker = lengthBuilder.getResult();
        result  = (checker.check(password,sb)) && result;

        lengthBuilder.reset();
        lengthBuilder.setReg(PasswordReg.LOWER);
        lengthBuilder.setMiniLength(config.getPasswordLowerMin());
        checker = lengthBuilder.getResult();
        result  = (checker.check(password,sb)) && result;

        lengthBuilder.reset();
        lengthBuilder.setReg(PasswordReg.UPPER);
        lengthBuilder.setMiniLength(config.getPasswordUpperMin());
        checker = lengthBuilder.getResult();
        result  = (checker.check(password,sb)) && result;

        lengthBuilder.reset();
        lengthBuilder.setReg(PasswordReg.SPECIAL);
        lengthBuilder.setMiniLength(config.getPasswordSymbolMin());
        checker = lengthBuilder.getResult();
        result  = (checker.check(password,sb)) && result;

        regBuilder.setReg(PasswordReg.BANNED);
        checker = regBuilder.getResult();
        result  = (checker.check(password,sb)) && result;

        return result;
    }
}
