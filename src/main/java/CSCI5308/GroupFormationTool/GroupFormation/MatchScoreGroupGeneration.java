package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;

public class MatchScoreGroupGeneration implements IGroupGeneration {

	public LinkedHashMap<List<User>, Double> removeAlreadyGroupedStudents(LinkedHashMap<List<User>, Double> compMatrix,
			List<User> groupedUsers) {
		LinkedHashMap<List<User>, Double> newCompMatrix = new LinkedHashMap<List<User>, Double>();

		for (Map.Entry<List<User>, Double> row : compMatrix.entrySet()) {
			boolean containsFlag = false;
			for (User student : groupedUsers) {
				if (row.getKey().contains(student)) {
					containsFlag = true;
					break;
				}
			}
			if (containsFlag == false) {
				newCompMatrix.put(row.getKey(), row.getValue());
			}
		}

		return newCompMatrix;
	}

	public User getMaxSumScoredStudent(LinkedHashMap<List<User>, Double> compMatrix, List<User> groupedUsers) {
		LinkedHashMap<User, Double> sumScorePairs = new LinkedHashMap<User, Double>();
		double maxSumScore = 0.0;
		User bestMatchedStudent = null;

		for (Map.Entry<List<User>, Double> row : compMatrix.entrySet()) {
			if ((groupedUsers.contains(row.getKey().get(0)) || groupedUsers.contains(row.getKey().get(1)))
					&& groupedUsers.containsAll(row.getKey()) == false) {
				User student = row.getKey().get(0);
				if (groupedUsers.contains(row.getKey().get(1)) == false) {
					student = row.getKey().get(1);
				}

				if (sumScorePairs.containsKey(student)) {
					sumScorePairs.put(student, sumScorePairs.get(student) + row.getValue());
				} else {
					sumScorePairs.put(student, row.getValue());
				}
			}
		}
		for (Map.Entry<User, Double> row : sumScorePairs.entrySet()) {
			if (row.getValue() > maxSumScore) {
				maxSumScore = row.getValue();
				bestMatchedStudent = row.getKey();
			}
		}

		return bestMatchedStudent;
	}

	public List<User> getMaxScoredPair(LinkedHashMap<List<User>, Double> compMatrix) {
		double maxScore = 0.0;
		List<User> bestPair = null;

		for (Map.Entry<List<User>, Double> row : compMatrix.entrySet()) {
			if (row.getValue() > maxScore) {
				maxScore = row.getValue();
				bestPair = row.getKey();
			}
		}

		return bestPair;
	}

	@Override
	public List<Group> generateGroups(LinkedHashMap<List<User>, Double> matchMatrix, int groupSize, List<User> students,
			Course course) {
		List<Group> groups = new ArrayList<Group>();

		for (int i = 0; i < students.size() / groupSize; i++) {
			List<User> groupx = new ArrayList<User>();
			groupx = getMaxScoredPair(matchMatrix);
			if (groupSize > groupx.size()) {
				for (int j = groupx.size(); j < groupSize; j++) {
					User bestMatchedStudentx = getMaxSumScoredStudent(matchMatrix, groupx);
					groupx.add(bestMatchedStudentx);
				}
			}
			groups.add(new GroupBuilder().setStudents((ArrayList) groupx).setCourse(course).setCreatedOn(new Date())
					.getGroup());
			matchMatrix = removeAlreadyGroupedStudents(matchMatrix, groupx);
		}

		List<User> extras = new ArrayList<User>();
		for (Group group : groups) {
			for (User student : group.getStudents()) {
				if (students.contains(student)) {
					students.remove(student);
				}
			}
		}

		if (students.size() < groups.size()) {
			for (int k = 0; k < students.size(); k++) {
				groups.get(groups.size() - 1 - k).getStudents().add(students.get(k));
			}
		} else {
			groups.add(new GroupBuilder().setStudents((ArrayList) students).setCourse(course).setCreatedOn(new Date())
					.getGroup());
		}

		return groups;
	}

}
