package CSCI5308.GroupFormationTool.Security;

import org.springframework.security.crypto.bcrypt.*;

public class BCryptPasswordEncryption implements IPasswordEncryption
{
	private BCryptPasswordEncoder encoder;
	private static IPasswordEncryption instance;
	public static IPasswordEncryption getInstance(){
		if (null == instance){
			instance = new BCryptPasswordEncryption();
		}
		return instance;
	}
	private BCryptPasswordEncryption()
	{
		encoder = new BCryptPasswordEncoder();
	}
	
	public String encryptPassword(String rawPassword)
	{
		return encoder.encode(rawPassword);
	}
	
	public boolean matches(String rawPassword, String encryptedPassword)
	{
		return encoder.matches(rawPassword, encryptedPassword);
	}
}
