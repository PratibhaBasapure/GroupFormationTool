package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.LinkedHashMap;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;

public interface IGroupGeneration {
	List<Group> generateGroups(LinkedHashMap<List<User>, Double> matchMatrix, int groupSize, List<User> students, Course course);
}
