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
import CSCI5308.GroupFormationTool.Questions.Question;

class GroupBuilderTest {

	long id = 122334;
	Course course = new Course();
	Date createdOn = new Date();
	ArrayList<User> students = new ArrayList<User>(Arrays.asList(new User()));

	@Test
	void testSetId() {
		Group group = new GroupBuilder().setId(id).getGroup();
		Assert.isTrue(group.getId() == id);
	}

	@Test
	void testSetCourse() {
		Group group = new GroupBuilder().setCourse(course).getGroup();
		Assert.isTrue(group.getCourse() == course);
	}

	@Test
	void testSetCreatedOn() {
		Group group = new GroupBuilder().setCreatedOn(createdOn).getGroup();
		Assert.isTrue(group.getCreatedOn() == createdOn);
	}

	@Test
	void testSetStudents() {
		Group group = new GroupBuilder().setStudents(students).getGroup();
		Assert.isTrue(group.getStudents() == students);
	}

	@Test
	void testGetGroup() {
		Group group = new GroupBuilder().setId(id).setCourse(course).setCreatedOn(createdOn).setStudents(students)
				.getGroup();

		Assert.isTrue(group.getId() == id);

		Assert.isTrue(group.getCourse() == course);

		Assert.isTrue(group.getCreatedOn() == createdOn);

		Assert.isTrue(group.getStudents() == students);
	}

}
