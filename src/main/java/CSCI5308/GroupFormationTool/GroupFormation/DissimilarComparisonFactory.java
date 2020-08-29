package CSCI5308.GroupFormationTool.GroupFormation;

public class DissimilarComparisonFactory extends ComparisonFactory {
	
	@Override
	public INumericComparison getNumericComparer(ComparisonMethod method) {
		switch (method) {
			case DISTANT:
				return new DistantNumericDissimilarity();
				
			default:
				break;
		}
		
		return null;
	}

	@Override
	public ISingleChoiceComparison getSingleChoiceComparer(ComparisonMethod method) {
		switch (method) {
			case UNEQUAL:
				return new EqualsSingleChoiceDissimilarity();
				
			default:
				break;
		}
		
		return null;
	}

	@Override
	public IMultipleChoiceComparison getMultipleChoiceComparer(ComparisonMethod method) {
		switch (method) {
			case AVERAGEDIFFERENCE:
				return new AverageEqualsMultipleChoiceDissimilarity();
				
			default:
				break;
		}
		
		return null;
	}

	@Override
	public ITextComparison getTextComparer(ComparisonMethod method) {
		switch (method) {
			case UNEQUAL:
				return new EqualsTextDissimilarity();
				
			default:
				break;
		}
		
		return null;
	}
}
