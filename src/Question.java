
public class Question {
	private String question;
	private String[] answers;
	private String correctanswer;
	
	// constructor
	public Question(String question, String[] answer, String correctanwer) {
		this.question = question;
		this.answers = answer;
		this.correctanswer = correctanwer;
	}

	// getters and setters
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	public String getCorrectanswer() {
		return correctanswer;
	}

	public void setCorrectanswer(String correctanswer) {
		this.correctanswer = correctanswer;
	}
	
	
}
