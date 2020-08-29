package CSCI5308.GroupFormationTool.GroupFormationTest;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import CSCI5308.GroupFormationTool.Response.Response;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.GroupFormation.Group;
import CSCI5308.GroupFormationTool.GroupFormation.GroupBuilder;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupPersistence;
import CSCI5308.GroupFormationTool.Response.Response;

public class GroupDBMock implements IGroupPersistence {

	@Override
	public boolean createGroups(List<Group> groups) {
		long id = 122334;
		Course course = new Course();
		Date createdOn = new Date();
		ArrayList<User> students = new ArrayList<User>(Arrays.asList(new User()));
		
		Group group1 = new GroupBuilder().setId(id).setCourse(course).setCreatedOn(createdOn).setStudents(students)
				.getGroup();
		
		return true;
	}

	@Override
	public ArrayList<Group> loadGroupByCourse(Course course) {
		long id = 122334;
		Date createdOn = new Date();
		ArrayList<User> students = new ArrayList<User>(Arrays.asList(new User()));
		
		Group group1 = new GroupBuilder().setId(id).setCourse(course).setCreatedOn(createdOn).setStudents(students)
				.getGroup();
		
		return new ArrayList(Arrays.asList(group1));
	}


}
