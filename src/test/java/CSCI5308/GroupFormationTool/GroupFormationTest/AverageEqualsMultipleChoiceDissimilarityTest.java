package CSCI5308.GroupFormationTool.GroupFormationTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.GroupFormation.AverageEqualsMultipleChoiceDissimilarity;
import CSCI5308.GroupFormationTool.GroupFormation.IMultipleChoiceComparison;

class AverageEqualsMultipleChoiceDissimilarityTest {

	IMultipleChoiceComparison mcc = new AverageEqualsMultipleChoiceDissimilarity();

	@Test
	void testGetScore() {
		ArrayList<Integer> choices1 = new ArrayList<Integer>(Arrays.asList(1, 2));
		ArrayList<Integer> choices2 = new ArrayList<Integer>(Arrays.asList(1, 2));
		ArrayList<Integer> choices3 = new ArrayList<Integer>(Arrays.asList(3, 4));
		ArrayList<Integer> choices4 = new ArrayList<Integer>(Arrays.asList(1, 2, 3));

		// Test 1: Returned value must be 0/2, i.e., completely similar
		Assert.isTrue(mcc.getScore(choices1, choices2) == 0.0 / 2.0);

		// Test 2: Returned value must be 2/2, i.e., completely dissimilar
		Assert.isTrue(mcc.getScore(choices1, choices3) == 2.0 / 2.0);

		// Test 3: Returned value must be 1/3, i.e., partially dissimilar
		Assert.isTrue(mcc.getScore(choices1, choices4) == 1.0 / 3.0);
	}

}
