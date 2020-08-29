package CSCI5308.GroupFormationTool.GroupFormationTest;

import static org.junit.jupiter.api.Assertions.*;

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
import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationAlgorithm;
import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationAlgorithmBuilder;
import CSCI5308.GroupFormationTool.GroupFormation.IMatchMatrixGeneration;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Response.Response;

class ComparisonScoreMatrixGenerationTest {

	IMatchMatrixGeneration csmg = new ComparisonScoreMatrixGeneration();
	LinkedHashMap<List<User>, Double> comparisonMatrix = new LinkedHashMap<List<User>, Double>();
	LinkedHashMap<User, List<Response>> studentsResponses = new LinkedHashMap<User, List<Response>>();
	User student1 = new User();
	User student2 = new User();
	Question q1 = new Question();
	Question q2 = new Question();
	Question q3 = new Question();
	Question q4 = new Question();

	{
		student1.setId(1);
		student2.setId(2);

		q1.setId(1);
		q1.setType("Numeric");
		q2.setId(2);
		q2.setType("Multiple choice – choose one");
		q3.setId(3);
		q3.setType("Multiple choice – choose multiple");
		q4.setId(4);
		q4.setType("Free text");
	}

	@Test
	void testGenerateMatchMatrix() {
		Course course = new Course();
		List<Boolean> comparisonChoices = new ArrayList<Boolean>(Arrays.asList(true, false, true, false));
		List<Question> questions = new ArrayList<Question>(Arrays.asList(q1, q2, q3, q4));
		List<Double> weights = new ArrayList<Double>(Arrays.asList(0.25, 0.25, 0.25, 0.25));
		
		GroupFormationAlgorithm algorithm = new GroupFormationAlgorithmBuilder().setCourse(course)
				.setQuestions(questions).setWeights(weights).setComparisonChoices(comparisonChoices)
				.getGroupFormationAlgorithm();

		for (int i = 0; i < 2; i++) {
			Response r1 = new Response();
			r1.setQuestion(q1);
			r1.setSingleresponse("1");

			Response r2 = new Response();
			r2.setQuestion(q2);
			r2.setSingleresponse("1");

			Response r3 = new Response();
			r3.setQuestion(q3);
			r3.setResponse(new ArrayList<String>(Arrays.asList("1", "2")));

			Response r4 = new Response();
			r4.setQuestion(q4);
			r4.setSingleresponse("free text here");

			studentsResponses.put(student1, new ArrayList<Response>(Arrays.asList(r1, r2, r3, r4)));
			studentsResponses.put(student2, new ArrayList<Response>(Arrays.asList(r1, r2, r3, r4)));
		}

		comparisonMatrix.put(new ArrayList<User>(Arrays.asList(student1, student2)), 0.500);
		
		Assert.isTrue(csmg.generateMatchMatrix(algorithm, studentsResponses).equals(comparisonMatrix));
	}

}
