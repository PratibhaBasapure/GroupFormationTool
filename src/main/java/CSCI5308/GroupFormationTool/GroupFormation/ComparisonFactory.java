package CSCI5308.GroupFormationTool.GroupFormation;

public abstract class ComparisonFactory {
	public static ComparisonFactory getComparisonFactory(boolean isChoiceSimilarity) {
		if (isChoiceSimilarity) {
			return new SimilarComparisonFactory();
		} 
		else {
			return new DissimilarComparisonFactory();
		}
	}
	
	public abstract INumericComparison getNumericComparer(ComparisonMethod method);
	public abstract ISingleChoiceComparison getSingleChoiceComparer(ComparisonMethod method);
	public abstract IMultipleChoiceComparison getMultipleChoiceComparer(ComparisonMethod method);
	public abstract ITextComparison getTextComparer(ComparisonMethod method);
}
