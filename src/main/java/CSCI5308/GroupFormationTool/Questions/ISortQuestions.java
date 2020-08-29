package CSCI5308.GroupFormationTool.Questions;

import java.util.List;

public interface ISortQuestions {
	List<Question> sortAscendingByTitle(List<Question> questionList);
	List<Question> sortDescendingByTitle(List<Question> questionList);
	List<Question> sortNewestToOldest(List<Question> questionList);
	List<Question> sortOldestToNewest(List<Question> questionList);
}
