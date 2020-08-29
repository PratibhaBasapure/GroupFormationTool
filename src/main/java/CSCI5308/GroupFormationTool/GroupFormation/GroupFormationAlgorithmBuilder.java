package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.Date;
import java.util.List;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Questions.Question;

public class GroupFormationAlgorithmBuilder {
	private long id;
	private Course course;
	private Date createdOn;
	private int groupSize;
	private List<Boolean> comparisonChoices;
	private List<Question> questions;
	private List<Double> weights;

	public GroupFormationAlgorithmBuilder setId(long id) {
		this.id = id;
		return this;
	}

	public GroupFormationAlgorithmBuilder setCourse(Course course) {
		this.course = course;
		return this;
	}

	public GroupFormationAlgorithmBuilder setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	public GroupFormationAlgorithmBuilder setComparisonChoices(List<Boolean> comparisonChoices) {
		this.comparisonChoices = comparisonChoices;
		return this;
	}

	public GroupFormationAlgorithmBuilder setQuestions(List<Question> questions) {
		this.questions = questions;
		return this;
	}

	public GroupFormationAlgorithmBuilder setWeights(List<Double> weights) {
		this.weights = weights;
		return this;
	}
	
	public GroupFormationAlgorithmBuilder setGroupSize(int groupSize) {
		this.groupSize = groupSize;
		return this;
	}
	
	public GroupFormationAlgorithm getGroupFormationAlgorithm() {
		return new GroupFormationAlgorithm(id, course, createdOn, comparisonChoices, questions, weights, groupSize);
	}
}
