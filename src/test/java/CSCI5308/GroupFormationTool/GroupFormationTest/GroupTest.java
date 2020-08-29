package CSCI5308.GroupFormationTool.GroupFormationTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.GroupFormation.Group;
import CSCI5308.GroupFormationTool.GroupFormation.GroupBuilder;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupPersistence;

class GroupTest {
	long id = 122334;
	Course course = new Course();
	Date createdOn = new Date();
	ArrayList<User> students = new ArrayList<User>(Arrays.asList(new User()));
	
	Group group = new Group(id, course, createdOn, students);
	
	@Test
	void testConstructor() {

		Assert.isTrue(group.getId() == id);

		Assert.isTrue(group.getCourse() == course);

		Assert.isTrue(group.getCreatedOn() == createdOn);

		Assert.isTrue(group.getStudents() == students);
	}

	@Test
	void testGetId() {
		Assert.isTrue(group.getId() == id);
	}

	@Test
	void testGetCourse() {
		Assert.isTrue(group.getCourse() == course);
	}

	@Test
	void testGetCreatedOn() {
		Assert.isTrue(group.getCreatedOn() == createdOn);
	}

	@Test
	void testGetStudents() {
		Assert.isTrue(group.getStudents() == students);
	}

	@Test
	void testCreateGroups() {
		long id = 122;

		IGroupPersistence groupDB = new GroupDBMock();

		Group group = new GroupBuilder().setId(id).getGroup();

		groupDB.createGroups(new ArrayList<Group>(Arrays.asList(group)));

		Assert.isTrue(group.getId() == id);
	}

	@Test
	void testLoadGroupByCourse() {
		Course course = new Course();

		IGroupPersistence groupDB = new GroupDBMock();

		List<Group> groups = groupDB.loadGroupByCourse(course);

		Assert.isTrue(groups.get(0).getCourse() == course);
	}

}
