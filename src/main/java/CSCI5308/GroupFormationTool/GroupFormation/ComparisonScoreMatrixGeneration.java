package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Response.Response;

public class ComparisonScoreMatrixGeneration implements IMatchMatrixGeneration {

	public ArrayList<Integer> convertStringListToIntegerList(List<String> multipleChoices) {
		ArrayList<Integer> multipleChoicesInteger = new ArrayList<Integer>();
		for (String s : multipleChoices) {
			multipleChoicesInteger.add(Integer.parseInt(s));
		}

		return multipleChoicesInteger;
	}

	@Override
	public LinkedHashMap<List<User>, Double> generateMatchMatrix(GroupFormationAlgorithm algorithm,
			LinkedHashMap<User, List<Response>> studentsResponses) {
		LinkedHashMap<List<User>, Double> comparisonMatrix = new LinkedHashMap<List<User>, Double>();
		
		for (Map.Entry<User, List<Response>> row1 : studentsResponses.entrySet()) {
			for (Map.Entry<User, List<Response>> row2 : studentsResponses.entrySet()) {
				if (row1 != row2) {
					List<User> comparingStudents = new ArrayList<User>(Arrays.asList(row1.getKey(), row2.getKey()));
					List<User> comparingStudentsRev = new ArrayList<User>(Arrays.asList(row2.getKey(), row1.getKey()));
					
					if (comparisonMatrix.containsKey(comparingStudents) == false
							&& comparisonMatrix.containsKey(comparingStudentsRev) == false) {
						List<Response> resp1 = row1.getValue();
						List<Response> resp2 = row2.getValue();
						double avgScore = 0.0;
						
						for (int i = 0; i < resp1.size(); i++) {
							Question curQuestion = resp1.get(i).getQuestion();
							double curWeight = 0.0;
							double score = 0.0;
							boolean curComparisonChoice = false;
							String curQuestionType = "Numeric";
							
							// Checking if QuestionIDs match to get correct corresponding weight
							if (curQuestion.getId() == algorithm.getQuestions().get(i).getId()) {
								curWeight = algorithm.getWeights().get(i);
								curQuestionType = algorithm.getQuestions().get(i).getType();
								curComparisonChoice = algorithm.getComparisonChoices().get(i);
							}
							else {
								for (int j = 0; j < resp1.size(); j++) {
									if (curQuestion.getId() == algorithm.getQuestions().get(j).getId()) {
										curWeight = algorithm.getWeights().get(j);
										curQuestionType = algorithm.getQuestions().get(j).getType();
										curComparisonChoice = algorithm.getComparisonChoices().get(j);
										break;
									}
								}
							}
							
							// Getting right factory object for question-wise comparison
							ComparisonFactory factory = ComparisonFactory.getComparisonFactory(curComparisonChoice);
							if (curComparisonChoice) {
								if (curQuestionType.equals(QuestionType.NUMERIC.toString())) {
									INumericComparison comparer = factory.getNumericComparer(ComparisonMethod.CLOSE);
									score = comparer.getScore(Integer.parseInt(resp1.get(i).getSingleresponse()), Integer.parseInt(resp2.get(i).getSingleresponse()));
								}
								else if (curQuestionType.equals(QuestionType.SINGLECHOICE.toString())) {
									ISingleChoiceComparison comparer = factory.getSingleChoiceComparer(ComparisonMethod.EQUAL);
									score = comparer.getScore(Integer.parseInt(resp1.get(i).getSingleresponse()), Integer.parseInt(resp2.get(i).getSingleresponse()));
								}
								else if (curQuestionType.equals(QuestionType.MULTIPLECHOICE.toString())) {
									IMultipleChoiceComparison comparer = factory.getMultipleChoiceComparer(ComparisonMethod.AVERAGEMATCH);
									score = comparer.getScore(convertStringListToIntegerList(resp1.get(i).getResponse()), convertStringListToIntegerList(resp2.get(i).getResponse()));
								}
								else {
									ITextComparison comparer = factory.getTextComparer(ComparisonMethod.EQUAL);
									score = comparer.getScore(resp1.get(i).getSingleresponse(), resp2.get(i).getSingleresponse());
								}
							}
							else {
								if (curQuestionType.equals(QuestionType.NUMERIC.toString())) {
									INumericComparison comparer = factory.getNumericComparer(ComparisonMethod.DISTANT);
									score = comparer.getScore(Integer.parseInt(resp1.get(i).getSingleresponse()), Integer.parseInt(resp2.get(i).getSingleresponse()));
								}
								else if (curQuestionType.equals(QuestionType.SINGLECHOICE.toString())) {
									ISingleChoiceComparison comparer = factory.getSingleChoiceComparer(ComparisonMethod.UNEQUAL);
									score = comparer.getScore(Integer.parseInt(resp1.get(i).getSingleresponse()), Integer.parseInt(resp2.get(i).getSingleresponse()));
								}
								else if (curQuestionType.equals(QuestionType.MULTIPLECHOICE.toString())) {
									IMultipleChoiceComparison comparer = factory.getMultipleChoiceComparer(ComparisonMethod.AVERAGEDIFFERENCE);
									score = comparer.getScore(convertStringListToIntegerList(resp1.get(i).getResponse()), convertStringListToIntegerList(resp2.get(i).getResponse()));
								}
								else {
									ITextComparison comparer = factory.getTextComparer(ComparisonMethod.UNEQUAL);
									score = comparer.getScore(resp1.get(i).getSingleresponse(), resp2.get(i).getSingleresponse());
								}
							}
							
							avgScore += curWeight*score;
							
						}
						comparisonMatrix.put(comparingStudents, avgScore);
					}
				}
			}
		}
		return comparisonMatrix;
	}
}
