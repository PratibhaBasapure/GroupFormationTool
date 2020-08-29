package CSCI5308.GroupFormationTool.QuestionTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.Questions.ISortQuestions;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Questions.SortQuestions;

class SortQuestionsTest {
	
	List<Question> questions = new ArrayList<Question>();
	List<Question> sortedQuestions1 = new ArrayList<Question>();
	List<Question> sortedQuestions2 = new ArrayList<Question>();
	ISortQuestions sq = new SortQuestions();
	
	{
		Question question1 = new Question();
        question1.setTitle("Question1");
        question1.setCreatedOn(new Date(1592464606579L));
        
        Question question2 = new Question();
        question2.setTitle("Question2");
        question2.setCreatedOn(new Date(1592464656474L));
        
        Question question3 = new Question();
        question3.setTitle("Question3");
        question3.setCreatedOn(new Date(1592464680406L));
        
		questions.add(question1);
		questions.add(question3);
		questions.add(question2);
		
		// Ground truth
		sortedQuestions1.add(question1);
		sortedQuestions1.add(question2);
		sortedQuestions1.add(question3);

		sortedQuestions2.add(question3);
		sortedQuestions2.add(question2);
		sortedQuestions2.add(question1);
	}

	@Test
	void testSortAscendingByTitle() {
		// Test 1: Returned list must not be empty
		Assert.isTrue(sq.sortAscendingByTitle(questions).isEmpty() == false);
				
		// Test 2: Returned list must match sortedQuestions1
		Assert.isTrue(sq.sortAscendingByTitle(questions).equals(sortedQuestions1));
	}

	@Test
	void testSortDescendingByTitle() {		
		// Test 1: Returned list must not be empty
		Assert.isTrue(sq.sortDescendingByTitle(questions).isEmpty() == false);
				
		// Test 1: Returned list must match sortedQuestions2
		Assert.isTrue(sq.sortDescendingByTitle(questions).equals(sortedQuestions2));
	}

	@Test
	void testSortNewestToOldest() {
		// Test 1: Returned list must not be empty
		Assert.isTrue(sq.sortNewestToOldest(questions).isEmpty() == false);
		
		// Test 1: Returned list must match sortedQuestions2
		Assert.isTrue(sq.sortNewestToOldest(questions).equals(sortedQuestions2));
	}

	@Test
	void testSortOldestToNewest() {
		// Test 1: Returned list must not be empty
		Assert.isTrue(sq.sortOldestToNewest(questions).isEmpty() == false);
		
		// Test 1: Returned list must match sortedQuestions1
		Assert.isTrue(sq.sortOldestToNewest(questions).equals(sortedQuestions1));
	}

}
