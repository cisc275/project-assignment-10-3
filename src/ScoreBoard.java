
public class ScoreBoard {
	private int width;
	private int length;
	private int score;
	
	public ScoreBoard(int width, int length, int score) {
		this.width = width;
		this.length = length;
		this.score = score;
	}
	/*
	 * Description: Will be called if the bird hit an item, and changes the score depend on the item
	 */
	public void updateScore() {
		
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
