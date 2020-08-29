package CSCI5308.GroupFormationTool.GroupFormationTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationController;

class GroupFormationControllerTest {

	@Test
	void testParseComparisonChoices() {
		String body = "id=5&noOfQuestions=3&questionid=77&questionid=78&comparisonChoice77=1&weight=10&weight=50&comparisonChoice78=0";

		List<Boolean> output = new ArrayList<Boolean>(Arrays.asList(true, false));

		GroupFormationController gfc = new GroupFormationController();

		Assert.isTrue(gfc.parseComparisonChoices(body).equals(output));
	}

	@Test
	void testNormaliseWeights() {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(50, 100));
		List<Double> output = new ArrayList<Double>(
				Arrays.asList(Math.round((50/150.0) * 100.0) / 100.0, Math.round((100/150.0) * 100.0) / 100.0));

		GroupFormationController gfc = new GroupFormationController();
		
		Assert.isTrue(gfc.normaliseWeights(input).equals(output));
	}
}
