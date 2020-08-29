package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.LinkedHashMap;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Response.Response;

public interface IGroupFormationAlgorithmPersistence {
	public boolean createAlgorithm(GroupFormationAlgorithm algorithm);
	public GroupFormationAlgorithm loadAlgorithmByCourse(Course course);
	public LinkedHashMap<User, List<Response>> loadUsersResponsesByCourseID(Long courseID);
}
