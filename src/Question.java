
public class Question {
	String question;
	String[] answers;
	String correctanswer;
	
	public Question(String question, String[] answer, String correctanwer) {
		this.question = question;
		this.answers = answer;
		this.correctanswer = correctanwer;
	}

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
