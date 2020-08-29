package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;

public class AverageEqualsMultipleChoiceSimilarity implements IMultipleChoiceComparison {

	@Override
	public double getScore(ArrayList<Integer> choices1, ArrayList<Integer> choices2) {
		double sum = 0.0;
		ArrayList<Integer> longerList;
		ArrayList<Integer> smallerList;
		
		if (choices1.size() > choices2.size()) {
			longerList = choices1;
			smallerList = choices2;
		}
		else {
			longerList = choices2;
			smallerList = choices1;
		}
		
		for (Integer choice : longerList) {
			if (smallerList.contains(choice)) {
				sum += 1;
			}
		}
		
		return sum/longerList.size();
	}

}
