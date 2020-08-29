package CSCI5308.GroupFormationTool.GroupFormationTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.GroupFormation.ComparisonFactory;
import CSCI5308.GroupFormationTool.GroupFormation.DissimilarComparisonFactory;
import CSCI5308.GroupFormationTool.GroupFormation.SimilarComparisonFactory;

class ComparisonFactoryTest {

	@Test
	void testGetComparisonFactory() {
		boolean isChoiceSimilarity1 = true;
		boolean isChoiceSimilarity2 = false;

		// Test 1: Returned value must be an instance of type SimilarComparisonFactory
		Assert.isTrue(ComparisonFactory.getComparisonFactory(isChoiceSimilarity1) instanceof SimilarComparisonFactory);

		// Test 2: Returned value must be an instance of type DissimilarComparisonFactory
		Assert.isTrue(
				ComparisonFactory.getComparisonFactory(isChoiceSimilarity2) instanceof DissimilarComparisonFactory);
	}

}
