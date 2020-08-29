package CSCI5308.GroupFormationTool.GroupFormation;

public class SimilarComparisonFactory extends ComparisonFactory {
	
	@Override
	public INumericComparison getNumericComparer(ComparisonMethod method) {
		switch (method) {
			case CLOSE:
				return new CloseNumericSimilarity();
				
			default:
				break;
		}
		
		return null;
	}

	@Override
	public ISingleChoiceComparison getSingleChoiceComparer(ComparisonMethod method) {
		switch (method) {
			case EQUAL:
				return new EqualsSingleChoiceSimilarity();
				
			default:
				break;
		}
		
		return null;
	}

	@Override
	public IMultipleChoiceComparison getMultipleChoiceComparer(ComparisonMethod method) {
		switch (method) {
			case AVERAGEMATCH:
				return new AverageEqualsMultipleChoiceSimilarity();
				
			default:
				break;
		}
		
		return null;
	}

	@Override
	public ITextComparison getTextComparer(ComparisonMethod method) {
		switch (method) {
			case EQUAL:
				return new EqualsTextSimilarity();
				
			default:
				break;
		}
		
		return null;
	}
}
