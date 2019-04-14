import java.util.ArrayList;
import java.util.Iterator;

public class Model {
	
	private ClapperRail clapperrail;
	private RedKnot redKnot;
	private ScoreBoard scoreboard;
	private Map mapRN;
	
	private int width;
    private int height;

    ArrayList<Items> items;
    Iterator<Items> iterator;
    
	GameStatus gamestatus = GameStatus.Menu;
    
	public Model(RedKnot redKnot, Map mapRN, ArrayList<Items> items) {
		this.redKnot = redKnot;
		this.items = items;
	}
	

	public GameStatus getGamestatus() {
		return gamestatus;
	}


	public void setGamestatus(GameStatus gamestatus) {
		this.gamestatus = gamestatus;
	}


	public RedKnot getRedKnot() {
		return redKnot;
	}

	public void updateLocation() {
		iterator = items.iterator();
		while(iterator.hasNext()) {
			Items tempItem = iterator.next();
			tempItem.setX(tempItem.getX()+tempItem.getxVel());
			tempItem.setY(tempItem.getY()+tempItem.getyVel());
			goodCollision(tempItem,redKnot);
		}
		redKnot.setX(redKnot.getX()+redKnot.getxVel());
		redKnot.setY(redKnot.getY()+redKnot.getyVel());
		
	}
	
	// when the game is complete, this method is called
	public void endGame() {
		
	}

	//this method detects collisions between bird and food and powerUp
	public void goodCollision(Items item, RedKnot redKnot) {
		if(((redKnot.getX()+redKnot.getLength()-item.getX() <= 32) && (redKnot.getX()+redKnot.getLength()-item.getX() >= 0)) && ((redKnot.getX()+redKnot.getLength()-item.getX() <= 32) && (redKnot.getY()+redKnot.getWidth()-item.getY() >= 0)) ) {
			iterator.remove();
		}
	}
	//this method detects collisions between bird and obstacles
	public void badCollision() {
		
	}
	
	//this method gives extra points
	public void powerUp() {
		scoreboard.setScore(scoreboard.getScore()+5);
	}
	
}
