package CSCI5308.GroupFormationTool.GroupFormation;

public class CloseNumericSimilarity implements INumericComparison {

	@Override
	public double getScore(int choice1, int choice2) {
		if(choice1 == choice2) {
			return 1.0;
		}
		
		int smaller = choice1;
		int larger = choice1;
		
		if(choice1 > choice2) {
			smaller = choice2;
		}
		else {
			larger = choice2;
			smaller = choice1;
		}
		
		return (double)smaller/larger;
	}

}
