package CSCI5308.GroupFormationTool.GroupFormationTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.GroupFormation.EqualsTextSimilarity;
import CSCI5308.GroupFormationTool.GroupFormation.ITextComparison;

class EqualsTextSimilarityTest {
	
	ITextComparison itc = new EqualsTextSimilarity();
	
	@Test
	void testGetScore() {
		String choice1 = "My skills are: java, python, and html.";
		String choice2 = "My skills are: java, python, and html.";
		String choice3 = "my skills are: java, python, and html.";
		String choice4 = "My skills are: .net, ruby, and html.";
		
		// Test 1: Returned value must be 1.0, i.e., similar
		Assert.isTrue(itc.getScore(choice1, choice2) == 1.0);
		
		// Test 2: Returned value must be 1.0, i.e., similar(ignore case)
		Assert.isTrue(itc.getScore(choice1, choice3) == 1.0);
				
		// Test 3: Returned value must be 0.0, i.e., dissimilar
		Assert.isTrue(itc.getScore(choice1, choice4) == 0.0);
	}

}
