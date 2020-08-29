package CSCI5308.GroupFormationTool.GroupFormationTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.GroupFormation.Group;
import CSCI5308.GroupFormationTool.GroupFormation.GroupBuilder;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupGeneration;
import CSCI5308.GroupFormationTool.GroupFormation.MatchScoreGroupGeneration;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Response.Response;

class MatchScoreGroupGenerationTest {

	IGroupGeneration msgg = new MatchScoreGroupGeneration();
	LinkedHashMap<List<User>, Double> comparisonMatrix = new LinkedHashMap<List<User>, Double>();

	User student1 = new User();
	User student2 = new User();
	User student3 = new User();
	User student4 = new User();
	User student5 = new User();

	{
		student1.setId(1);
		student2.setId(2);
		student3.setId(3);
		student4.setId(4);
		student5.setId(5);

		comparisonMatrix.put(new ArrayList<User>(Arrays.asList(student1, student2)), 0.99);
		comparisonMatrix.put(new ArrayList<User>(Arrays.asList(student1, student3)), 0.89);
		comparisonMatrix.put(new ArrayList<User>(Arrays.asList(student1, student4)), 0.69);
		comparisonMatrix.put(new ArrayList<User>(Arrays.asList(student1, student5)), 0.49);
		comparisonMatrix.put(new ArrayList<User>(Arrays.asList(student2, student3)), 0.89);
		comparisonMatrix.put(new ArrayList<User>(Arrays.asList(student2, student4)), 0.78);
		comparisonMatrix.put(new ArrayList<User>(Arrays.asList(student2, student5)), 0.55);
		comparisonMatrix.put(new ArrayList<User>(Arrays.asList(student3, student4)), 0.33);
		comparisonMatrix.put(new ArrayList<User>(Arrays.asList(student3, student5)), 0.97);
		comparisonMatrix.put(new ArrayList<User>(Arrays.asList(student4, student5)), 0.90);
	}

	@Test
	void testGenerateGroups() {
		int groupSize = 3;
		Course course = new Course();
		course.setId(3);
		
		List<User> students = new ArrayList<User>(Arrays.asList(student1, student2, student3, student4, student5));

		Group g1 = new GroupBuilder().setStudents(new ArrayList<User>(Arrays.asList(student1, student2, student3)))
				.getGroup();
		Group g2 = new GroupBuilder().setStudents(new ArrayList<User>(Arrays.asList(student4, student5))).getGroup();
		
		List<Group> groupOutput = new ArrayList<Group>(Arrays.asList(g1, g2));
		
		List<Group> groups = msgg.generateGroups(comparisonMatrix, groupSize, students, course);
		
		Assert.isTrue(groups.get(0).getStudents().equals(groupOutput.get(0).getStudents())
				&& groups.get(1).getStudents().equals(groupOutput.get(1).getStudents()));
	}

	@Test
	void testRemoveAlreadyGroupedStudents() {
		MatchScoreGroupGeneration msgg1 = new MatchScoreGroupGeneration();
		LinkedHashMap<List<User>, Double> comparisonMatrixOutput = new LinkedHashMap<List<User>, Double>();
		comparisonMatrixOutput.put(new ArrayList<User>(Arrays.asList(student4, student5)), 0.90);
		Assert.isTrue(msgg1
				.removeAlreadyGroupedStudents(comparisonMatrix,
						new ArrayList<User>(Arrays.asList(student1, student2, student3)))
				.equals(comparisonMatrixOutput));
	}

	@Test
	void testGetMaxAverageScoredStudent() {
		MatchScoreGroupGeneration msgg1 = new MatchScoreGroupGeneration();
		Assert.isTrue(
				msgg1.getMaxSumScoredStudent(comparisonMatrix, new ArrayList<User>(Arrays.asList(student1, student2)))
						.equals(student3));
	}

	@Test
	void testGetMaxScoredPair() {
		MatchScoreGroupGeneration msgg1 = new MatchScoreGroupGeneration();
		Assert.isTrue(msgg1.getMaxScoredPair(comparisonMatrix)
				.equals(new ArrayList<User>(Arrays.asList(student1, student2))));
	}
}
