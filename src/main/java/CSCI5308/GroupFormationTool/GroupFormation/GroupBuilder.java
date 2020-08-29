package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.Date;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;

public class GroupBuilder {
	private long id;
	private Course course;
	private Date createdOn;
	private ArrayList<User> students;
	
	public GroupBuilder setId(long id) {
		this.id = id;
		return this;
	}
	
	public GroupBuilder setCourse(Course course) {
		this.course = course;
		return this;
	}
	
	public GroupBuilder setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
		return this;
	}
	
	public GroupBuilder setStudents(ArrayList<User> students) {
		this.students = students;
		return this;
	}
	
	public Group getGroup() {
		return new Group(id, course, createdOn, students);
	}
}
