package CSCI5308.GroupFormationTool.GroupFormation;

public class EqualsTextDissimilarity implements ITextComparison {

	@Override
	public double getScore(String choice1, String choice2) {
		if(choice1.equalsIgnoreCase(choice2) == false) {
			return 1.0;
		}
		
		return 0.0;
	}

}
