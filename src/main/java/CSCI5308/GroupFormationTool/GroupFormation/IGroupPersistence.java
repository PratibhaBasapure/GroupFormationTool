package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.List;

import CSCI5308.GroupFormationTool.Courses.Course;

public interface IGroupPersistence
{
	public boolean createGroups(List<Group> group);
	public List<Group> loadGroupByCourse(Course course);

}
