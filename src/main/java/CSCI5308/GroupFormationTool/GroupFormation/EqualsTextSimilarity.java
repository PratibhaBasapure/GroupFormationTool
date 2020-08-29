package CSCI5308.GroupFormationTool.GroupFormation;

public class EqualsTextSimilarity implements ITextComparison {

	@Override
	public double getScore(String choice1, String choice2) {
		if(choice1.equalsIgnoreCase(choice2) == true) {
			return 1.0;
		}
		
		return 0.0;
	}

}
