package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@SuppressWarnings("deprecation")
public class UserTest
{
	@Test
	public void testConstructor()
	{
		User u = new User();
		Assert.isTrue(u.getBannerID().isEmpty());
		Assert.isTrue(u.getFirstName().isEmpty());
		Assert.isTrue(u.getLastName().isEmpty());
		Assert.isTrue(u.getEmail().isEmpty());
		
		IUserPersistence userDBMock = new UserDBMock();
		u = new User(1, userDBMock);
		Assert.isTrue(u.getBannerID().equals("B00000000"));
		
		u = new User("B00000000", userDBMock);
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}
	
	@Test
	public void testSetID()
	{
		User u = new User();
		u.setID(10);
		Assert.isTrue(10 == u.getID());
	}
	
	@Test
	public void testGetID()
	{
		User u = new User();
		u.setID(10);
		Assert.isTrue(10 == u.getID());
	}
	
	@Test
	public void testSetBannerID()
	{
		User u = new User();
		u.setBannerID("B00000000");
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}
	
	@Test
	public void testGetBannerID()
	{
		User u = new User();
		u.setBannerID("B00000000");
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}
	
	@Test
	public void testSetFirstName()
	{
		User u = new User();
		u.setFirstName("Rob");
		Assert.isTrue(u.getFirstName().equals("Rob"));
	}
	
	@Test
	public void testGetFirstName()
	{
		User u = new User();
		u.setFirstName("Rob");
		Assert.isTrue(u.getFirstName().equals("Rob"));
	}

	@Test
	public void testSetLastName()
	{
		User u = new User();
		u.setLastName("Hawkey");
		Assert.isTrue(u.getLastName().equals("Hawkey"));
	}

	@Test
	public void testGetLastName()
	{
		User u = new User();
		u.setLastName("Hawkey");
		Assert.isTrue(u.getLastName().equals("Hawkey"));
	}
	
	@Test
	public void testSetEmail()
	{
		User u = new User();
		u.setEmail("rhawkey@dal.ca");
		Assert.isTrue(u.getEmail().equals("rhawkey@dal.ca"));
	}
	
	@Test
	public void testGetEmail()
	{
		User u = new User();
		u.setEmail("rhawkey@dal.ca");
		Assert.isTrue(u.getEmail().equals("rhawkey@dal.ca"));
	}
	
	@Test
	public void testCreateUser()
	{		
		IUserPersistence userDB = new UserDBMock();
		User user = new User();
		userDB.createUser(user);
		Assert.isTrue(user.getId() == 0);
		Assert.isTrue(user.getBannerID().equals("B00000000"));
	}

	@Test
	public void testIsBannerIDValid()
	{
		Assert.isTrue(User.isBannerIDValid("B000000000"));
		Assert.isTrue(!User.isBannerIDValid(null));
		Assert.isTrue(!User.isBannerIDValid(""));
	}
		
	@Test
	public void testIsFirstNameValid()
	{
		Assert.isTrue(User.isFirstNameValid("rob"));
		Assert.isTrue(!User.isFirstNameValid(null));
		Assert.isTrue(!User.isFirstNameValid(""));
	}
	
	@Test
	public void testIsLastNameValid()
	{
		Assert.isTrue(User.isLastNameValid("hawkey"));
		Assert.isTrue(!User.isLastNameValid(null));
		Assert.isTrue(!User.isLastNameValid(""));
	}
	
	@Test
	public void testIsEmailValid()
	{
		Assert.isTrue(User.isEmailValid("rhawkey@dal.ca"));
		Assert.isTrue(!User.isEmailValid(null));
		Assert.isTrue(!User.isEmailValid(""));
		Assert.isTrue(!User.isEmailValid("@dal.ca"));
		Assert.isTrue(!User.isEmailValid("rhawkey@"));
	}	
}
