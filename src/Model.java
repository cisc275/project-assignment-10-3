import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Model implements Serializable{
	
	private ClapperRail clapperRail;
	private RedKnot redKnot;
	private ScoreBoard scoreBoard;
	private Map mapRN;
	private StatusBar statusBar;
	
	//constants
	private final int RK_GOOD_VALUE = 20;
	private final int RK_BAD_VALUE = 1;
	private final int RK_POWERUP_VALUE = 200;
	private final int CR_STATUS_CAP = 300;
	private final int CR_STATUS_INCREMENT = 15;
	public final static int RK_VELOCITY = 10;
		
	private int frameWidth;
    private int frameHeight;

    ArrayList<Items> items;
    ArrayList<Items> CRitems;
    transient Iterator<Items> iterator;
    
    Quiz quiz_RN;
    Quiz quiz_CR;
    
	GameStatus gamestatus = GameStatus.Menu;
	private int processCounterRN = 0;
	
	private int screenTime = 0;
	Random r = new Random();
	int QuestionIndex;
	
	private boolean answerRightFlag; // a flag to indicate if the answer is right
	private boolean answerWrongFlag; // a flag to indicate if the answer is wrong 
	ArrayList<Integer> background = new ArrayList<>();
	transient Iterator<Integer> itbackground;
	
	private int tutorialLevel = 1; // LevelConuter in Tutorial
    
	// constructor
	public Model(int frameWidth, int frameHeight, RedKnot redKnot, ClapperRail clapperRail, Map mapRN, 
			ArrayList<Items> items, ArrayList<Items> CRitems,
			ScoreBoard scoreBoard, StatusBar statusBar, Quiz quiz_RN, Quiz quiz_CR,
			boolean answerRightFlag, boolean answerWrongFlag, ArrayList<Integer> background, int tutorialLevel) {
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
		this.background = background;
		this.tutorialLevel = tutorialLevel;
	}
	
	// getters and setters
	public int getTutorialLevel() {
		return tutorialLevel;
	}

	public void setTutorialLevel(int tutorialLevel) {
		this.tutorialLevel = tutorialLevel;
	}

	public ArrayList<Integer> getBackground() {
		return background;
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

	//given the state of the game, the locations are all updated
	public void updateLocation() {
		if(gamestatus == GameStatus.RN ) {
			
			//Moving Background
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
				tempItem.setX(tempItem.getX()+Items.X_VEL);
				tempItem.setY(tempItem.getY()+Items.Y_VEL);
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
			}
	
		}else if(gamestatus == GameStatus.RNTutorial) {
			switch(tutorialLevel) {
			case 7:
				
			case 6:
				if(tutorialLevel == 6) {
				iterator = items.iterator();
				while(iterator.hasNext()) {
					Items tempItem = iterator.next();
					tempItem.setX(tempItem.getX() + Items.X_VEL);
					tempItem.setY(tempItem.getY() + Items.Y_VEL);
					if(!collisionRK(tempItem,redKnot)) {;
					itemsOutOfBounds(tempItem);
					}
				}
				
					if(this.scoreBoard.getScore() >= 10) {
						tutorialLevel = 7;
					}
				}
			
			case 5:
			case 4:	
			case 3:
			case 2:	
			case 1:
				background.set(0, background.get(0)-1);
				background.set(1, background.get(1)-1);
				itbackground = background.iterator();
				while(itbackground.hasNext()) {
					int tempInt = itbackground.next();
					if(tempInt <= -frameWidth) {
						itbackground.remove();
					}
				}
				if(background.size() <= 1) {
					background.add(frameWidth);
				}
				redKnot.setX(redKnot.getX()+redKnot.getxVel());
				redKnot.setY(redKnot.getY()+redKnot.getyVel());
				birdOutOfBounds(redKnot);
				break;
			default:
				tutorialLevel = 1;
				gamestatus = GameStatus.RN;
				
			}
		}
		
		else if(gamestatus == GameStatus.CR) {
			//Check for the collision and pops item on screen one at a time
			iterator = CRitems.iterator();
			while(iterator.hasNext()) {
				Items tempItem = iterator.next();
				if(!collisionCR(tempItem, clapperRail)) {;
					screenTime();
				}				
			}
			// if this condition is true, game end; moves to the quiz
			if(statusBar.getStatus() >= CR_STATUS_CAP) {
				gamestatus = GameStatus.CRQUIZ;
				clapperRail.setY(frameHeight/2-100);
				clapperRail.setX(frameWidth/2-100);
				statusBar.setStatus(0);
			}		
		}
	}
	

	/**
	 *  Makes sure the bird is always within the screen
	 * @param redKnot that it will check
	 */
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

	/**
	 * check if the item is out of screen; if true, remove the item from the collection using iterator
	 * @param item 
	 */
	public void itemsOutOfBounds(Items item) {
		if(item.getX() <= 0-item.getLength()) {
			iterator.remove();
		}
	}

	/**
	 * check if the bird is collided with the item; does action depends on the itemID when collides
	 * @param item
	 * @param redKnot
	 * @return whether the item and the redKnot collide
	 */
	public boolean collisionRK(Items item, RedKnot redKnot) {
		Rectangle rk = redKnot.bounds();
		Rectangle i = item.bounds();
		if (rk.intersects(i)) {
			
			switch(item.getItemID()) {
			case PowerUp:
				// powerup adds 200 to score
				scoreBoard.setScore(scoreBoard.getScore()+RK_POWERUP_VALUE);
			case Fly:
			case Snail:
				// flies and snails add 20 to score
				scoreBoard.setScore(scoreBoard.getScore()+RK_GOOD_VALUE);
				iterator.remove();
				break;
			case Plane:
			case Car:
				// hitting cars and planes subtract 1 from score depending on how long the bird is in contact with them
				scoreBoard.setScore(scoreBoard.getScore()-RK_BAD_VALUE);
				break;
			}
			return true;
	
		}
		return false;
	}
	
	/**
	 * check if the bird is collided with the item; does action depends on the itemID when collides
	 * @param item
	 * @param clapperRail
	 * @return whether the item and clapperRail collide
	 */
	public boolean collisionCR(Items item, ClapperRail clapperRail) {
		Rectangle cr = clapperRail.bounds();
		Rectangle i = item.bounds();
		if (cr.intersects(i)) {
			screenTime = 0;
			switch(item.getItemID()) {
			case Food:
				// when CR catches food, increase status bar
				statusBar.setStatus(statusBar.getStatus()+CR_STATUS_INCREMENT);
				if(statusBar.getStatus() > CR_STATUS_CAP) {
					statusBar.setStatus(CR_STATUS_CAP);
				}
				iterator.remove();
				break;
			case Obstacle:
				// when CR hits obstacle, decrease status bar
				statusBar.setStatus(statusBar.getStatus()-CR_STATUS_INCREMENT);
				if(statusBar.getStatus() < 0) {
					statusBar.setStatus(0);
				}
				iterator.remove();
				break;
			default: 
				break;
			}
			return true;
		}
		return false;
	}
	
	/**
	 *  determines the time the item is on the screen if the bird does not collide with it
	 */
	
	public void screenTime() {
		screenTime++;
		if(screenTime >= 20) {
			iterator.remove();
			screenTime = 0;
		}	
	}
	
}
