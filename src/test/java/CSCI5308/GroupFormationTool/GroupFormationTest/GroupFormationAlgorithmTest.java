package CSCI5308.GroupFormationTool.GroupFormationTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.GroupFormation.ComparisonScoreMatrixGeneration;
import CSCI5308.GroupFormationTool.GroupFormation.Group;
import CSCI5308.GroupFormationTool.GroupFormation.GroupBuilder;
import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationAlgorithm;
import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationAlgorithmBuilder;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationAlgorithmPersistence;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupGeneration;
import CSCI5308.GroupFormationTool.GroupFormation.IMatchMatrixGeneration;
import CSCI5308.GroupFormationTool.GroupFormation.MatchScoreGroupGeneration;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Response.Response;

class GroupFormationAlgorithmTest {
	long id = 122334;
	Course course = new Course();
	Date createdOn = new Date();
	int groupSize = 3;
	List<Boolean> comparisonChoices = new ArrayList<Boolean>(Arrays.asList(true));
	List<Question> questions = new ArrayList<Question>(Arrays.asList(new Question()));
	List<Double> weights = new ArrayList<Double>(Arrays.asList(1.0));

	GroupFormationAlgorithm gfa = new GroupFormationAlgorithm(id, course, createdOn, comparisonChoices, questions,
			weights, groupSize);

	@Test
	void testConstructor() {

		Assert.isTrue(gfa.getId() == id);

		Assert.isTrue(gfa.getCourse() == course);

		Assert.isTrue(gfa.getCreatedOn() == createdOn);

		Assert.isTrue(gfa.getGroupSize() == groupSize);

		Assert.isTrue(gfa.getComparisonChoices().equals(comparisonChoices));

		Assert.isTrue(gfa.getQuestions().equals(questions));

		Assert.isTrue(gfa.getWeights().equals(weights));
	}

	@Test
	void testGetId() {
		Assert.isTrue(gfa.getId() == id);
	}

	@Test
	void testGetCourse() {
		Assert.isTrue(gfa.getCourse() == course);
	}

	@Test
	void testGetCreatedOn() {
		Assert.isTrue(gfa.getCreatedOn() == createdOn);
	}

	@Test
	void testGetComparisonChoices() {
		Assert.isTrue(gfa.getComparisonChoices().equals(comparisonChoices));
	}

	@Test
	void testGetQuestions() {
		Assert.isTrue(gfa.getQuestions().equals(questions));
	}

	@Test
	void testGetWeights() {
		Assert.isTrue(gfa.getWeights().equals(weights));
	}

	@Test
	void testGetGroupSize() {
		Assert.isTrue(gfa.getGroupSize() == groupSize);
	}

	@Test
	void testCreateAlgorithm() {
		int groupSize = 3;

		IGroupFormationAlgorithmPersistence algorithmDB = new GroupFormationAlgorithmDBMock();

		GroupFormationAlgorithm algorithm = new GroupFormationAlgorithmBuilder().setGroupSize(groupSize)
				.getGroupFormationAlgorithm();

		algorithmDB.createAlgorithm(algorithm);

		Assert.isTrue(algorithm.getGroupSize() == groupSize);
	}

	@Test
	void testLoadAlgorithmByCourse() {
		Course course = new Course();

		IGroupFormationAlgorithmPersistence algorithmDB = new GroupFormationAlgorithmDBMock();

		GroupFormationAlgorithm algorithm = algorithmDB.loadAlgorithmByCourse(course);

		Assert.isTrue(algorithm.getCourse() == course);
	}

	@Test
	void testRunAlgorithm() {
		int groupSize = 2;
		IMatchMatrixGeneration matGen = new ComparisonScoreMatrixGeneration();
		IGroupGeneration grpGen = new MatchScoreGroupGeneration();
		Course course = new Course();
		course.setId(2);
		List<Boolean> comparisonChoices = new ArrayList<Boolean>(Arrays.asList(true, false));
		List<Double> weights = new ArrayList<Double>(Arrays.asList(0.5, 0.5));
		LinkedHashMap<List<User>, Double> comparisonMatrix = new LinkedHashMap<List<User>, Double>();
		LinkedHashMap<User, List<Response>> studentsResponses = new LinkedHashMap<User, List<Response>>();
		User student1 = new User();
		User student2 = new User();
		Question q1 = new Question();
		Question q2 = new Question();
		List<Question> questions = new ArrayList<Question>(Arrays.asList(q1, q2));

		student1.setId(1);
		student2.setId(2);

		q1.setId(1);
		q1.setType("Numeric");
		q2.setId(2);
		q2.setType("Multiple choice – choose one");

		GroupFormationAlgorithm algorithm = new GroupFormationAlgorithmBuilder().setCourse(course)
				.setQuestions(questions).setGroupSize(groupSize).setWeights(weights)
				.setComparisonChoices(comparisonChoices).getGroupFormationAlgorithm();

		for (int i = 0; i < 2; i++) {
			Response r1 = new Response();
			r1.setQuestion(q1);
			r1.setSingleresponse("1");

			Response r2 = new Response();
			r2.setQuestion(q2);
			r2.setSingleresponse("1");

			studentsResponses.put(student1, new ArrayList<Response>(Arrays.asList(r1, r2)));
			studentsResponses.put(student2, new ArrayList<Response>(Arrays.asList(r1, r2)));
		}

		List<User> students = new ArrayList<User>(Arrays.asList(student1, student2));
		Group g1 = new GroupBuilder().setStudents(new ArrayList<User>(Arrays.asList(student1, student2))).getGroup();

		List<Group> groupOutput = new ArrayList<Group>(Arrays.asList(g1));

		List<Group> groups = algorithm.runAlgorithm(matGen, grpGen, algorithm, studentsResponses, course);
		Assert.isTrue(groups.get(0).getStudents().equals(groupOutput.get(0).getStudents()));
	}
	
	@Test
	void testLoadUsersResponsesByCourseID() {
		IGroupFormationAlgorithmPersistence algorithmDB = new GroupFormationAlgorithmDBMock();
		Assert.isTrue(algorithmDB.loadUsersResponsesByCourseID(1223L) instanceof LinkedHashMap);
	}
}
