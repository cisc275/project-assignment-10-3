

public class Model {
	
	private ClapperRail clapperrail;
	private RedKnot redknot;
	private ScoreBoard scoreboard;
	
	private int width;
    private int height;

    
	/*
	 * Constructor
	 */
	public Model(ScoreBoard scoreboard) {
		this.scoreboard = scoreboard;
	}
	
	public void updateLocation() {
		
	}
	
	// when the game is complete, this method is called
	public void endGame() {
		
	}

	//this method detects collisions between bird and food and powerUp
	public void goodCollision() {
		
	}
	//this method detects collisions between bird and obstacles
	public void badCollision() {
		
	}
	
	//this method gives extra points
	public void powerUp() {
		scoreboard.setScore(scoreboard.getScore()+5);
	}
	
}
