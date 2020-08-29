package CSCI5308.GroupFormationTool.GroupFormationTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationAlgorithm;
import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationAlgorithmBuilder;
import CSCI5308.GroupFormationTool.Questions.Question;

class GroupFormationAlgorithmBuilderTest {

	long id = 122334;
	Course course = new Course();
	Date createdOn = new Date();
	int groupSize = 3;
	List<Boolean> comparisonChoices = new ArrayList<Boolean>(Arrays.asList(true));
	List<Question> questions = new ArrayList<Question>(Arrays.asList(new Question()));
	List<Double> weights = new ArrayList<Double>(Arrays.asList(1.0));

	@Test
	void testSetId() {
		GroupFormationAlgorithm gfa = new GroupFormationAlgorithmBuilder().setId(id).getGroupFormationAlgorithm();

		Assert.isTrue(gfa.getId() == id);
	}

	@Test
	void testSetCourse() {
		GroupFormationAlgorithm gfa = new GroupFormationAlgorithmBuilder().setCourse(course)
				.getGroupFormationAlgorithm();

		Assert.isTrue(gfa.getCourse() == course);
	}

	@Test
	void testSetCreatedOn() {
		GroupFormationAlgorithm gfa = new GroupFormationAlgorithmBuilder().setCreatedOn(createdOn)
				.getGroupFormationAlgorithm();

		Assert.isTrue(gfa.getCreatedOn() == createdOn);
	}

	@Test
	void testSetComparisonChoices() {
		GroupFormationAlgorithm gfa = new GroupFormationAlgorithmBuilder().setComparisonChoices(comparisonChoices)
				.getGroupFormationAlgorithm();

		Assert.isTrue(gfa.getComparisonChoices().equals(comparisonChoices));
	}

	@Test
	void testSetQuestions() {
		GroupFormationAlgorithm gfa = new GroupFormationAlgorithmBuilder().setQuestions(questions)
				.getGroupFormationAlgorithm();

		Assert.isTrue(gfa.getQuestions().equals(questions));
	}

	@Test
	void testSetWeights() {
		GroupFormationAlgorithm gfa = new GroupFormationAlgorithmBuilder().setWeights(weights)
				.getGroupFormationAlgorithm();

		Assert.isTrue(gfa.getWeights().equals(weights));
	}

	@Test
	void testSetGroupSize() {
		GroupFormationAlgorithm gfa = new GroupFormationAlgorithmBuilder().setGroupSize(groupSize)
				.getGroupFormationAlgorithm();

		Assert.isTrue(gfa.getGroupSize() == groupSize);
	}

	@Test
	void testGetGroupFormationAlgorithm() {
		GroupFormationAlgorithm gfa = new GroupFormationAlgorithmBuilder()
				.setId(id)
				.setCourse(course)
				.setComparisonChoices(comparisonChoices)
				.setCreatedOn(createdOn)
				.setGroupSize(groupSize)
				.setQuestions(questions)
				.setWeights(weights)
				.getGroupFormationAlgorithm();

		Assert.isTrue(gfa.getId() == id);
		
		Assert.isTrue(gfa.getCourse() == course);
		
		Assert.isTrue(gfa.getCreatedOn() == createdOn);
		
		Assert.isTrue(gfa.getGroupSize() == groupSize);
		
		Assert.isTrue(gfa.getComparisonChoices().equals(comparisonChoices));
		
		Assert.isTrue(gfa.getQuestions().equals(questions));
		
		Assert.isTrue(gfa.getWeights().equals(weights));
	}

}
