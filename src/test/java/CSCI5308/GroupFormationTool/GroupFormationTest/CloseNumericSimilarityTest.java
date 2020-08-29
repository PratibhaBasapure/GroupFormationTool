package CSCI5308.GroupFormationTool.GroupFormationTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.GroupFormation.CloseNumericSimilarity;
import CSCI5308.GroupFormationTool.GroupFormation.INumericComparison;

class CloseNumericSimilarityTest {
	
	INumericComparison nc = new CloseNumericSimilarity();
	
	@Test
	void testGetScore() {
		int choice1 = 1;
		int choice2 = 1;
		int choice3 = 6;

		// Test 1: Returned value must be 1.0, i.e., similar
		Assert.isTrue(nc.getScore(choice1, choice2) == 1.0);

		// Test 2: Returned value must be 1/6, i.e., closely similar
		Assert.isTrue(nc.getScore(choice1, choice3) == 1.0/6.0);
	}

}
