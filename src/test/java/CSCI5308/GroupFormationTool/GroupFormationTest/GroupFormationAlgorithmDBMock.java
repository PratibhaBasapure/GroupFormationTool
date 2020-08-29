package CSCI5308.GroupFormationTool.GroupFormationTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationAlgorithm;
import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationAlgorithmBuilder;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationAlgorithmPersistence;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Response.Response;

public class GroupFormationAlgorithmDBMock implements IGroupFormationAlgorithmPersistence {

	@Override
	public boolean createAlgorithm(GroupFormationAlgorithm algorithm) {
		long id = 12345;
		Course course = new Course();
		Date createdOn = new Date();
		int groupSize = 3;
		List<Boolean> comparisonChoices = new ArrayList<Boolean>(Arrays.asList(true));
		List<Question> questions = new ArrayList<Question>(Arrays.asList(new Question()));
		List<Double> weights = new ArrayList<Double>(Arrays.asList(1.0));
		
		GroupFormationAlgorithm algorithm1 = new GroupFormationAlgorithmBuilder()
				.setId(id)
				.setCourse(course)
				.setComparisonChoices(comparisonChoices)
				.setCreatedOn(createdOn)
				.setGroupSize(groupSize)
				.setQuestions(questions)
				.setWeights(weights)
				.getGroupFormationAlgorithm();
		
		return true;
	}

	@Override
	public GroupFormationAlgorithm loadAlgorithmByCourse(Course course) {
		long id = 12345;
		Date createdOn = new Date();
		int groupSize = 3;
		List<Boolean> comparisonChoices = new ArrayList<Boolean>(Arrays.asList(true));
		List<Question> questions = new ArrayList<Question>(Arrays.asList(new Question()));
		List<Double> weights = new ArrayList<Double>(Arrays.asList(1.0));
		
		GroupFormationAlgorithm algorithm = new GroupFormationAlgorithmBuilder()
				.setId(id)
				.setCourse(course)
				.setComparisonChoices(comparisonChoices)
				.setCreatedOn(createdOn)
				.setGroupSize(groupSize)
				.setQuestions(questions)
				.setWeights(weights)
				.getGroupFormationAlgorithm();
		
		return algorithm;
	}

	@Override
	public LinkedHashMap<User, List<Response>> loadUsersResponsesByCourseID(Long courseID) {
		LinkedHashMap<User, List<Response>> responses = new LinkedHashMap<>();
		return responses;
	}

}
