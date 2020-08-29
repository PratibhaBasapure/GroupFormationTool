package CSCI5308.GroupFormationTool.GroupFormation;

public class EqualsSingleChoiceDissimilarity implements ISingleChoiceComparison {

	@Override
	public double getScore(int choice1, int choice2) {
		if(choice1 != choice2) {
			return 1.0;
		}
		
		return 0.0;
	}

}
