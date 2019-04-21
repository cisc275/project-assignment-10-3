import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class Model {
	
	private ClapperRail clapperRail;
	private RedKnot redKnot;
	private ScoreBoard scoreBoard;
	private Map mapRN;
	
	private int width;
    private int height;

    ArrayList<Items> items;
    ArrayList<Items> CRitems;
    Iterator<Items> iterator;
    
	GameStatus gamestatus = GameStatus.Menu;
	private int processCounterRN = 0;
	
	private int screenTime = 0;
    
	public Model(RedKnot redKnot, ClapperRail clapperRail, Map mapRN, ArrayList<Items> items, ArrayList<Items> CRitems, ScoreBoard scoreBoard) {
		this.redKnot = redKnot;
		this.clapperRail = clapperRail;
		this.mapRN = mapRN;
		this.items = items;
		this.CRitems = CRitems;
		this.scoreBoard = scoreBoard;
		
	}



	public int getScreenTime() {
		return screenTime;
	}

	

	public void setScreenTime(int screenTime) {
		this.screenTime = screenTime;
	}



	public ScoreBoard getScoreBoard() {
		return scoreBoard;
	}



	public ClapperRail getClapperrail() {
		return clapperRail;
	}


	public Map getMapRN() {
		return mapRN;
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
		if(gamestatus == GameStatus.RN) {
			iterator = items.iterator();
			while(iterator.hasNext()) {
				Items tempItem = iterator.next();
				tempItem.setX(tempItem.getX()+tempItem.getxVel());
				tempItem.setY(tempItem.getY()+tempItem.getyVel());
				goodCollision(tempItem,redKnot);
			}
			redKnot.setX(redKnot.getX()+redKnot.getxVel());
			redKnot.setY(redKnot.getY()+redKnot.getyVel());
			
			/*
			 * Map Location
			 */
			//processCounterRN++;
//			if(processCounterRN >= 20) {
//				mapRN.setStatus(mapRN.getStatus()+1);
//				mapRN.setStatus_Y((5/7)*mapRN.getStatus()-280);
//				
//			}
//			if(mapRN.getStatus() >= 1000) {
//				gamestatus = GameStatus.Menu;
//			}
	
		}else if(gamestatus == GameStatus.CR) {
			iterator = CRitems.iterator();
			while(iterator.hasNext()) {
				Items tempItem = iterator.next();
				if(!goodCollision(tempItem, clapperRail)) {;
					screenTime();
				}		
			}
		}
		
		
	}
	
	// when the game is complete, this method is called
	public void endGame() {
		
	}

	//this method detects collisions between bird and food and powerUp
	public void goodCollision(Items item, RedKnot redKnot) {
		Rectangle rk = redKnot.bounds();
		Rectangle i = item.bounds();
		if (rk.intersects(i)) {
			if(item instanceof Food) {
				scoreBoard.setScore(scoreBoard.getScore()+1);
				iterator.remove();

			}else if(item instanceof PowerUp) {
				
			}else if(item instanceof Obstacle) {
				
			}
			
		}
	}

	public boolean goodCollision(Items item, ClapperRail clapperRail) {
		Rectangle rk = clapperRail.bounds();
		Rectangle i = item.bounds();
		if (rk.intersects(i)) {
			iterator.remove();
			return true;
		}
		return false;
	}
	
	public void screenTime() {
		screenTime++;
		if(screenTime >= 20) {
			
			iterator.remove();
			
			
			screenTime = 0;
		}
		
	}
	
	//this method gives extra points
	public void powerUp() {
		
	}
	
}
