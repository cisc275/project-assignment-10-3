import java.io.File;
import java.util.*;

public class Quiz {
	private String filename;
	ArrayList<Question> questions = new ArrayList<>();
	private Scanner std;
	private int questionIndex;
	private String selected = "E";
	
	
	public Quiz(String filename) {
		this.filename = filename;
	}
	
	public void openFile() {
		try {
			std = new Scanner(new File(filename));
			
		}catch(Exception e) {
			System.out.println("could not find file");
		}
	}
	
	public void readFile() {
		while(std.hasNext()) {
			String question = std.nextLine();
			
			String[] ans = {std.nextLine(), std.nextLine(), std.nextLine(), std.nextLine()};

			String corr = std.nextLine();
			questions.add(new Question(question, ans, corr));
		}
	}
	
	public void closeFile() {
		std.close();
	}
	
	
	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public int getQuestionIndex() {
		return questionIndex;
	}

	public void setQuestionIndex(int questionIndex) {
		this.questionIndex = questionIndex;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	
	
	
	
}
