package CSCI5308.GroupFormationTool.GroupFormationTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.GroupFormation.DistantNumericDissimilarity;
import CSCI5308.GroupFormationTool.GroupFormation.INumericComparison;

class DistantNumericDissimilarityTest {
	
	INumericComparison nc = new DistantNumericDissimilarity();
	
	@Test
	void testGetScore() {
		int choice1 = 1;
		int choice2 = 1;
		int choice3 = 6;

		// Test 1: Returned value must be 0.0, i.e., similar
		Assert.isTrue(nc.getScore(choice1, choice2) == 0.0);

		// Test 2: Returned value must be 5/6, i.e., highly dissimilar
		Assert.isTrue(nc.getScore(choice1, choice3) == 5.0/6.0);
	}

}
