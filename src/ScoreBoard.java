public class ScoreBoard {
	
	public final static int LENGTH = 250;
	public final static int WIDTH = 60 ;
	public final static int X = 20;
	public final static int Y = 20;
	private int score = 0;
	
	// constructor
	public ScoreBoard() {}
	
	// getters and setters
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
}
