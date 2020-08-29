package CSCI5308.GroupFormationTool.GroupFormationTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.GroupFormation.AverageEqualsMultipleChoiceSimilarity;
import CSCI5308.GroupFormationTool.GroupFormation.CloseNumericSimilarity;
import CSCI5308.GroupFormationTool.GroupFormation.ComparisonFactory;
import CSCI5308.GroupFormationTool.GroupFormation.ComparisonMethod;
import CSCI5308.GroupFormationTool.GroupFormation.EqualsSingleChoiceSimilarity;
import CSCI5308.GroupFormationTool.GroupFormation.EqualsTextSimilarity;
import CSCI5308.GroupFormationTool.GroupFormation.SimilarComparisonFactory;

class SimilarComparisonFactoryTest {

	@Test
	void testGetNumericComparer() {
		ComparisonFactory scf = new SimilarComparisonFactory();

		// Test 1: Returned value must be an instance of type CloseNumericSimilarity
		Assert.isTrue(scf.getNumericComparer(ComparisonMethod.CLOSE) instanceof CloseNumericSimilarity);

		// Test 2: Returned value must be null
		Assert.isNull(scf.getNumericComparer(ComparisonMethod.DISTANT));
	}

	@Test
	void testGetSingleChoiceComparer() {
		ComparisonFactory scf = new SimilarComparisonFactory();

		// Test 1: Returned value must be an instance of type
		// EqualsSingleChoiceSimilarity
		Assert.isTrue(scf.getSingleChoiceComparer(ComparisonMethod.EQUAL) instanceof EqualsSingleChoiceSimilarity);

		// Test 2: Returned value must be null
		Assert.isNull(scf.getSingleChoiceComparer(ComparisonMethod.UNEQUAL));
	}

	@Test
	void testGetMultipleChoiceComparer() {
		ComparisonFactory scf = new SimilarComparisonFactory();

		// Test 1: Returned value must be an instance of type
		// AverageEqualsMultipleChoiceSimilarity
		Assert.isTrue(scf.getMultipleChoiceComparer(
				ComparisonMethod.AVERAGEMATCH) instanceof AverageEqualsMultipleChoiceSimilarity);

		// Test 2: Returned value must be null
		Assert.isNull(scf.getMultipleChoiceComparer(ComparisonMethod.AVERAGEDIFFERENCE));
	}

	@Test
	void testGetTextComparer() {
		ComparisonFactory scf = new SimilarComparisonFactory();

		// Test 1: Returned value must be an instance of type
		// EqualsTextSimilarity
		Assert.isTrue(scf.getTextComparer(ComparisonMethod.EQUAL) instanceof EqualsTextSimilarity);

		// Test 2: Returned value must be null
		Assert.isNull(scf.getTextComparer(ComparisonMethod.UNEQUAL));
	}

}
