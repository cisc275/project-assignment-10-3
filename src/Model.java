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
	
	private boolean answerRightFlag; // a flag to indicate if the answer is right
	private boolean answerWrongFlag; // a flag to indicate if the answer is wrong 
	private boolean  tutorialFlag;   // a flag to make sure the tutorial is before the game
	ArrayList<Integer> background = new ArrayList<>();
	Iterator<Integer> itbackground;
    
	public Model(int frameWidth, int frameHeight, RedKnot redKnot, ClapperRail clapperRail, Map mapRN, 
			ArrayList<Items> items, ArrayList<Items> CRitems,
			ScoreBoard scoreBoard, StatusBar statusBar, Quiz quiz_RN, Quiz quiz_CR,
			boolean answerRightFlag, boolean answerWrongFlag, boolean tutorialFlag, ArrayList<Integer> background) {
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
		this.tutorialFlag = tutorialFlag;
		this.background = background;
		
	}
	
	
	
	public ArrayList<Integer> getBackground() {
		return background;
	}



	public boolean isTutorialFlag() {
		return tutorialFlag;
	}



	public void setTutorialFlag(boolean tutorialFlag) {
		this.tutorialFlag = tutorialFlag;
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

	//Calculate everything here 
	public void updateLocation() { 
		if(gamestatus == GameStatus.RN) {
			if(!tutorialFlag) { // is tutorialFlag is false, the game will start
				//Make Sure the background is moving
				background.set(0, background.get(0)-1);
				background.set(1, background.get(1)-1);
				itbackground = background.iterator();
				while(itbackground.hasNext()) {
					int tempInt = itbackground.next();
					//if the background is out of picture, remove the background
					if(tempInt <= -frameWidth) {
						itbackground.remove();
					}
				}
				// if there is only one background in the arraylist add another one to make sure the screen is smooth
				if(background.size() <= 1) {
					background.add(frameWidth);
				}
				//Update the location and check for the collision and out of screen of the items in the ArrayList 
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
				
				//Calculate the mini bird location on the mini map
				processCounterRN++;
				if(processCounterRN >= 15) { //>= 10
					processCounterRN = 0;
					
					mapRN.setStatus(mapRN.getStatus()+1);
					mapRN.setStatus_Y((int)(0.625*(mapRN.getStatus()-frameWidth)+ 111.25));
				}
				//The RN game end if this condition is true, move to the quiz part
				if(mapRN.getStatus() >= frameWidth-50) {
					quiz_RN.setQuestionIndex(r.nextInt(quiz_RN.getQuestions().size()));
					
					gamestatus = GameStatus.RNQUIZ;
					mapRN.setStatus(frameWidth-130);
					//Need Reset Everything?		
			}
				
			}
		}else if(gamestatus == GameStatus.CR) {
			//Check for the collision and pops item on screen one at a time
			iterator = CRitems.iterator();
			while(iterator.hasNext()) {
				Items tempItem = iterator.next();
				if(!collisionCR(tempItem, clapperRail)) {;
					screenTime();
				}				
			}
			// if this condition is true, game end; moves to the quiz
			if(statusBar.getStatus() >= 300) { //300
				gamestatus = GameStatus.CRQUIZ;
				clapperRail.setY(frameHeight/2-100);
				clapperRail.setX(frameWidth/2-100);
				statusBar.setStatus(0);
			}
		}
	}
	
	//RN: Make sure the bird is always within the screen
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

	//RN: check if the item is out of screen; if true, remove the item from the collection using iterator
	public void itemsOutOfBounds(Items item) {
		if(item.getX() <= 0-item.getLength()) {
			iterator.remove();
		}
	}

	//RN: check if the bird is collided with the item; does action depends on the itemID when collides
	public boolean collisionRK(Items item, RedKnot redKnot) {
		Rectangle rk = redKnot.bounds();
		Rectangle i = item.bounds();
		if (rk.intersects(i)) {
			
			switch(item.getItemID()) {
			case PowerUp:
				scoreBoard.setScore(scoreBoard.getScore()+200);
			case Fly:
			case Snail:
				scoreBoard.setScore(scoreBoard.getScore()+20);
				iterator.remove();
				break;
			case Plane:
			case Car:
				scoreBoard.setScore(scoreBoard.getScore()-1);
				break;
			}
			return true;
	
		}
		return false;
	}
	
	//CR: check if the bird is collided with the item; does action depends on the itemID when collides
	public boolean collisionCR(Items item, ClapperRail clapperRail) {
		Rectangle cr = clapperRail.bounds();
		Rectangle i = item.bounds();
		if (cr.intersects(i)) {
			screenTime = 0;
			switch(item.getItemID()) {
			case Food:
				statusBar.setStatus(statusBar.getStatus()+15);
				if(statusBar.getStatus() > 300) {
					statusBar.setStatus(300);
				}
				iterator.remove();
				break;
			case Obstacle:
				statusBar.setStatus(statusBar.getStatus()-15);
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
	
	//CR: a function that determin the time the item is on the screen if the bird does not collide with it
	public void screenTime() {
		screenTime++;
		if(screenTime >= 20) {
			
			iterator.remove();
			
			
			screenTime = 0;
		}
		
	}
}
