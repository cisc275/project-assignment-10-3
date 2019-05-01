import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Model {
	
	private ClapperRail clapperRail;
	private RedKnot redKnot;
	private ScoreBoard scoreBoard;
	private Map mapRN;
	private StatusBar statusBar;
	
	private int frameWidth;
    private int frameHeight;

    ArrayList<Items> items;
    ArrayList<Items> CRitems;
    Iterator<Items> iterator;
    
    Quiz quiz_RN;
    Quiz quiz_CR;
    
	GameStatus gamestatus = GameStatus.Menu;
	private int processCounterRN = 0;
	
	private int screenTime = 0;
	Random r = new Random();
	int QuestionIndex;
	
	private boolean answerRightFlag;
	private boolean answerWrongFlag;
    
	public Model(int frameWidth, int frameHeight, RedKnot redKnot, ClapperRail clapperRail, Map mapRN, 
			ArrayList<Items> items, ArrayList<Items> CRitems,
			ScoreBoard scoreBoard, StatusBar statusBar, Quiz quiz_RN, Quiz quiz_CR,
			boolean answerRightFlag, boolean answerWrongFlag) {
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.redKnot = redKnot;
		this.clapperRail = clapperRail;
		this.mapRN = mapRN;
		this.items = items;
		this.CRitems = CRitems;
		this.scoreBoard = scoreBoard;
		this.statusBar = statusBar;
		this.quiz_CR = quiz_CR;
		this.quiz_RN = quiz_RN;
		this.answerRightFlag = answerRightFlag;
		this.answerWrongFlag = answerWrongFlag;
		
	}
	
	
	
	public boolean isAnswerRightFlag() {
		return answerRightFlag;
	}



	public void setAnswerRightFlag(boolean answerRightFlag) {
		this.answerRightFlag = answerRightFlag;
	}



	public boolean isAnswerWrongFlag() {
		return answerWrongFlag;
	}



	public void setAnswerWrongFlag(boolean answerWrongFlag) {
		this.answerWrongFlag = answerWrongFlag;
	}



	public Quiz getQuiz_RN() {
		return quiz_RN;
	}



	public Quiz getQuiz_CR() {
		return quiz_CR;
	}



	public ArrayList<Items> getCRitems() {
		return CRitems;
	}



	public StatusBar getStatusBar() {
		return statusBar;
	}



	public ArrayList<Items> getItems() {
		return items;
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
				if(!collisionRK(tempItem,redKnot)) {;
				itemsOutOfBounds(tempItem);
				}
				
			}
			
			redKnot.setX(redKnot.getX()+redKnot.getxVel());
			redKnot.setY(redKnot.getY()+redKnot.getyVel());
			birdOutOfBounds(redKnot);
			/*
			 * Map Location
			 */
			processCounterRN++;
			if(processCounterRN >= 10) { //>= 10
				processCounterRN = 0;
				
				mapRN.setStatus(mapRN.getStatus()+1);
				mapRN.setStatus_Y((int)(0.625*(mapRN.getStatus()-frameWidth)+ 111.25));
			}
			
			if(mapRN.getStatus() >= frameWidth-50) {
				quiz_RN.setQuestionIndex(r.nextInt(quiz_RN.getQuestions().size()));
				
				gamestatus = GameStatus.RNQUIZ;
				mapRN.setStatus(frameWidth-130);
				//Need Reset Everything?			
			}
		}else if(gamestatus == GameStatus.CR) {
			iterator = CRitems.iterator();
			while(iterator.hasNext()) {
				Items tempItem = iterator.next();
				if(!collisionCR(tempItem, clapperRail)) {;
					screenTime();
				}				
			}
			if(statusBar.getStatus() >= 300) {
				gamestatus = GameStatus.CRQUIZ;
				clapperRail.setY(frameHeight/2-100);
				clapperRail.setX(frameWidth/2-100);
				statusBar.setStatus(0);
			}
			
			
			
		}else if(gamestatus == GameStatus.RNQUIZ) {			
			
		}else if(gamestatus == GameStatus.CRQUIZ) {
			
		}
	}
	
	// when the game is complete, this method is called
	public void endGame() {
		
	}
	
	public void birdOutOfBounds(RedKnot redKnot) {
		if(redKnot.getX() <= 0) {
			redKnot.setX(0);
		}
		if(redKnot.getY() <= 0) {
			redKnot.setY(0);
		}
		if(redKnot.getX() >= frameWidth - redKnot.getLength()) {
			redKnot.setX(frameWidth - redKnot.getWidth());
		}
		
		if(redKnot.getY() >= frameHeight - redKnot.getWidth()) {
			
			redKnot.setY(frameHeight - redKnot.getWidth());
		}
	}

	public void itemsOutOfBounds(Items item) {
		if(item.getX() <= 0-item.getLength()) {
			iterator.remove();
		}
	}

	public boolean collisionRK(Items item, RedKnot redKnot) {
		Rectangle rk = redKnot.bounds();
		Rectangle i = item.bounds();
		if (rk.intersects(i)) {
			
			switch(item.getItemID()) {
			case Food:
				scoreBoard.setScore(scoreBoard.getScore()+1);
				iterator.remove();
				break;
			case PowerUp:
				break;
			case Obstacle:
				break;
			}
			return true;
	
		}
		return false;
	}
	
	public boolean collisionCR(Items item, ClapperRail clapperRail) {
		Rectangle rk = clapperRail.bounds();
		Rectangle i = item.bounds();
		if (rk.intersects(i)) {
			switch(item.getItemID()) {
			case Food:
				statusBar.setStatus(statusBar.getStatus()+10);
				if(statusBar.getStatus() > 300) {
					statusBar.setStatus(300);
				}
				iterator.remove();
				break;
			case Obstacle:
				statusBar.setStatus(statusBar.getStatus()-10);
				if(statusBar.getStatus() < 0) {
					statusBar.setStatus(0);
				}
				iterator.remove();
				break;
			}
			
			return true;
		}
		return false;
	}
	//this method detects collisions between bird and obstacles
	public void badCollision() {
		
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
