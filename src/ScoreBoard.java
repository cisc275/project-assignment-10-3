
public class ScoreBoard {
	private int length = 80;
	private int width = 30 ;
	private final int x = 20;
	private final int y = 20;
	private int score = 0;
	
	
	
	/*
	 * Description: Will be called if the bird hit an item, and changes the score depend on the item
	 */
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
