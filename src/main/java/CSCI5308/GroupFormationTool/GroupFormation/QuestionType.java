package CSCI5308.GroupFormationTool.GroupFormation;

public enum QuestionType {
	NUMERIC {
		public String toString() {
			return "Numeric";
		}
	},
	SINGLECHOICE {
		public String toString() {
			return "Multiple choice – choose one";
		}
	},
	MULTIPLECHOICE {
		public String toString() {
			return "Multiple choice – choose multiple";
		}
	},
	FREETEXT {
		public String toString() {
			return "Free text";
		}
    }
}
