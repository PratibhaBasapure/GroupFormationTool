package CSCI5308.GroupFormationTool.Questions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortQuestions implements ISortQuestions {

	@Override
	public List<Question> sortAscendingByTitle(List<Question> questionList) {
		questionList.sort(Comparator.comparing(Question::getTitle));
		if (questionList.isEmpty()) {
			return new ArrayList<Question>();
		}
		return questionList;
	}

	@Override
	public List<Question> sortDescendingByTitle(List<Question> questionList) {
		questionList.sort(Comparator.comparing(Question::getTitle).reversed());
		if (questionList.isEmpty()) {
			return new ArrayList<Question>();
		}
		return questionList;
	}

	@Override
	public List<Question> sortNewestToOldest(List<Question> questionList) {
		questionList.sort(Comparator.comparing(Question::getCreatedOn).reversed());
		if (questionList.isEmpty()) {
			return new ArrayList<Question>();
		}
		return questionList;
	}

	@Override
	public List<Question> sortOldestToNewest(List<Question> questionList) {
		questionList.sort(Comparator.comparing(Question::getCreatedOn));
		if (questionList.isEmpty()) {
			return new ArrayList<Question>();
		}
		return questionList;
	}

}
