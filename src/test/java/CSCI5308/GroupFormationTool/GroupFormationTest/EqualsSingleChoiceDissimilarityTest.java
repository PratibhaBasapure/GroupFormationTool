package CSCI5308.GroupFormationTool.GroupFormationTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.GroupFormation.EqualsSingleChoiceDissimilarity;
import CSCI5308.GroupFormationTool.GroupFormation.ISingleChoiceComparison;

class EqualsSingleChoiceDissimilarityTest {
	
	ISingleChoiceComparison scc = new EqualsSingleChoiceDissimilarity();
	
	@Test
	void testGetScore() {
		int choice1 = 1;
		int choice2 = 1;
		int choice3 = 2;

		// Test 1: Returned value must be 0.0, i.e., similar
		Assert.isTrue(scc.getScore(choice1, choice2) == 0.0);

		// Test 2: Returned value must be 1.0, i.e., dissimilar
		Assert.isTrue(scc.getScore(choice1, choice3) == 1.0);
	}

}
