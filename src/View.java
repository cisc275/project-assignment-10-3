import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class View extends JPanel{
	//Finding the size of screen for the frame to match
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final int frameWidth = screenSize.width;
    private final int frameHeight = screenSize.height;
    
    private ClapperRail clapperRail; //Clapper Rail
    private RedKnot redKnot; //Red Knot

    private Map mapRN = new Map(frameWidth-148, frameWidth-130);            //Creating a map to show migration
    //
    //
    private StatusBar statusBar= new StatusBar(100,30);      //Status bar for CR game
    //
    //
    private ScoreBoard scoreBoard = new ScoreBoard();                       // Scoreboard for RK game
    
    //Creating array lists to draw multiple items to the frame during the games
    ArrayList<Items> items = new ArrayList<>();              
    ArrayList<Items> CRitems = new ArrayList<>();
    
    ArrayList<Integer> background = new ArrayList<>();
    
    //Iterators to go through array of items and background images
    Iterator<Integer> itbackground;
    Iterator<Items> iterator;
    
    private int powerupSpawnCounter=0;
    private int itemSpawnCounter = 0;                                      //Keeps track of items on screen in RN game 
    Random random;
    
    BufferedImage pic_redKnot_mini;                                       //BufferedImage for RN minimap image
    BufferedImage pic_clapperRail;                                        //BufferedImage for CR image
    BufferedImage pic_clapperRail_mini;                                   //BufferedImage for CR minimap image
    BufferedImage pic_food;                                               //BufferedImage for CR food image
    BufferedImage pic_map;												  //BufferedImage for minimap image
    BufferedImage pic_delaware;
    BufferedImage pic_obstacle;                                           //BufferedImage for obstacle images
    BufferedImage pic_snake;                                              //BufferedImage for CR snake image
    BufferedImage pic_RNFood;                                             //BufferedImage for RN food image
    BufferedImage pic_RNCar;                                              //BufferedImage for RN car image
    BufferedImage pic_RNPlane;                                            //BufferedImage for RN plane image
    BufferedImage pic_RNFly;                                              //BufferedImage for RN plane image
    BufferedImage pic_RNSnail;                                            //BufferedImage for RN minimap image
    BufferedImage pic_power;
    Image pic_menu;                                                       //BufferedImage for RN snail image
    ArrayList<BufferedImage> pics_redKnot =new ArrayList<>();             //Array list for red knot pics to be drawn to screen
    BufferedImage pic_icon_RN;                                            //BufferedImage for RN image on main menu
    BufferedImage pic_icon_CR;                                            //BufferedImage for CR image on main menu
    Image pic_water;                                                      //BufferedImage for water image
    BufferedImage redCircle;											  //BufferedImage of a Sign
    BufferedImage greenCircle;											  //BufferedImage of a Sign
    
    Quiz quiz_RN = new Quiz("quiz/RNQuiz.txt");
    Quiz quiz_CR = new Quiz("quiz/CRQuiz.txt");
    
    JFrame frame;
    
    JButton button_clapperrail;
    JButton button_redknote;
    JButton button_menu;
    JButton button_submit;
    JButton button_saveNquit;
    JButton button_next;
    JButton button_continue;
    
    JRadioButton button_A;
    JRadioButton button_B;
    JRadioButton button_C;
    JRadioButton button_D;
    ButtonGroup group = new ButtonGroup(); // Button group that has 4 JRadioButton for the quiz
    
    GameStatus gameStatus = GameStatus.Menu;
    
//    private boolean answerRightFlag = false; 
//    private boolean answerWrongFlag = false;
    
    private boolean answerRightFlag; // a flag to indicate if the answer is right
	private boolean answerWrongFlag; // a flag to indicate if the answer is wrong 
    
	final int frameCountFly=4; // RN: 4 frame for the bird
    private int picNumFly=0; // RN: the current frame
    private int tutorialLevel = 1; // LevelConuter in Tutorial
    
    // constants
    private final int RK_SPAWN_SPEED = 40;
    
    public View() {
//    	System.out.println(frameWidth);
//    	System.out.println(frameHeight);
    	background.add(0);
    	background.add(frameWidth);
    	this.setLayout(new FlowLayout());
    	
    	//Create Images
    	createImages();

    	//Buttons
    	makeButtons();

    	random = new Random();
    	
    	//Create Birds
  
    	redKnot = new RedKnot(frameHeight/2-32);
    	clapperRail = new ClapperRail(frameWidth/2-120, frameHeight/2-100);
    	frame = new JFrame();
    	
    	//Create Quiz
    	quiz_RN.openFile();
    	quiz_RN.readFile();
    	quiz_RN.closeFile();
 
    	quiz_CR.openFile();
    	quiz_CR.readFile();
    	quiz_CR.closeFile();
    	
    	
    	//Set Frame
    	frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	
    	//Full Screen
    	frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    	frame.setUndecorated(false);
    	frame.setVisible(true);
    }
    
    //Getters & Setters
    
    public int getTutorialLevel() {
		return tutorialLevel;
	}

	public void setTutorialLevel(int tutorialLevel) {
		this.tutorialLevel = tutorialLevel;
	}
	
    public int getFrameWidth() {
		return frameWidth;
	}  
	public ArrayList<Integer> getBackGround() {
		return background;
	}

	public boolean isAnswerRightFlag() {
		return answerRightFlag;
	}

	public void setAnswerRightFlag(boolean anserRightFlag) {
		this.answerRightFlag = anserRightFlag;
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
	
	public int getFrameHeight() {
		return frameHeight;
	}
	
	public ScoreBoard getScoreBoard() {
		return scoreBoard;
	}
	
    public ArrayList<Items> getCRitems() {
		return CRitems;
	}
    
	public RedKnot getRedKnot() {
		return redKnot;
	}
	
	public ClapperRail getClapperRail() {
		return clapperRail;
	}
	
	public ArrayList<Items> getItems() {
		return items;
	}
	
	public Map getMapRN() {
		return mapRN;
	}
	
	public StatusBar getStatusBar() {
		return statusBar;
	}
	
	
	/**
	 * makes buttons and styles for menu
	 */
	private void makeButtons() {
		this.setLayout(null);
		button_redknote = new JButton("Red Knot", new ImageIcon(pic_icon_RN));  
		button_redknote.setBounds(frameWidth/2-650, 200, 512, 256);
    	button_redknote.setBackground(Color.BLUE);
    	button_redknote.setOpaque(false);
    	button_redknote.setContentAreaFilled(false);
    	button_redknote.setBorderPainted(false);
    	button_redknote.setActionCommand("redKnot");
    	button_redknote.setFont(new Font("Arial", Font.PLAIN, 40));
    	button_redknote.setVisible(true);
    	
    	button_clapperrail = new JButton("Clapper Rail", new ImageIcon(pic_icon_CR));  
    	button_clapperrail.setBounds(frameWidth/2-100, 200, 512, 256);
    	button_clapperrail.setBackground(Color.BLUE);
    	button_clapperrail.setOpaque(false);
    	button_clapperrail.setContentAreaFilled(false);
    	button_clapperrail.setBorderPainted(false);
    	button_clapperrail.setActionCommand("clapperRail");
    	button_clapperrail.setFont(new Font("Arial", Font.PLAIN, 40));
    	button_clapperrail.setVisible(true);
    	
    	button_menu = new JButton("Menu");
    	button_menu.setBounds(frameWidth/2-32, 0, 64, 32);
    	button_menu.setBackground(Color.GRAY);
    	button_menu.setOpaque(false);
    	button_menu.setActionCommand("menu");
    	button_menu.setVisible(false);
    	
    	button_submit = new JButton("Submit");
    	button_submit.setBounds(frameWidth/2+200, frameHeight-300, 96, 32);
    	button_submit.setBackground(Color.GRAY);
    	button_submit.setOpaque(false);
    	button_submit.setActionCommand("submit");
    	button_submit.setEnabled(false);
    	button_submit.setVisible(false);
    	
    	button_saveNquit = new JButton("Save and Quit");
    	button_saveNquit.setBounds(frameWidth/2+64, 0, 128, 32); //64 , 32
    	button_saveNquit.setBackground(Color.GRAY);
    	button_saveNquit.setOpaque(false);
    	button_saveNquit.setActionCommand("savequit");
    	button_saveNquit.setVisible(false);
    	
    	button_next = new JButton("Next");
    	button_next.setBounds(frameWidth/2+64, 0, 64, 32);
    	button_next.setBackground(Color.GRAY);
    	button_next.setOpaque(false);
    	button_next.setActionCommand("next");
    	button_next.setVisible(false);
    	
    	button_continue = new JButton("Continue"); /////////////////////////
    	button_continue.setBounds(100, frameHeight - 100, 96, 32);
    	button_continue.setBackground(Color.GRAY);
    	button_continue.setOpaque(false);
    	button_continue.setActionCommand("continue");
    	button_continue.setVisible(true);
    	
    	button_A = new JRadioButton();
    	button_B = new JRadioButton();
    	button_C = new JRadioButton();
    	button_D = new JRadioButton();
    	
    	button_A.setBounds(frameWidth/2-200, frameHeight/2-128, 512, 32);
    	button_B.setBounds(frameWidth/2-200, frameHeight/2-64, 512, 32);
    	button_C.setBounds(frameWidth/2-200, frameHeight/2, 512, 32);
    	button_D.setBounds(frameWidth/2-200, frameHeight/2+64, 512, 32);
    	
//    	ButtonGroup group = new ButtonGroup();
		group.add(button_A);
		group.add(button_B);
		group.add(button_C);
		group.add(button_D);
    	
    	button_A.setActionCommand("A");
		button_B.setActionCommand("B");
		button_C.setActionCommand("C");
		button_D.setActionCommand("D");
		
		button_A.setVisible(false);
    	button_B.setVisible(false);
    	button_C.setVisible(false);
    	button_D.setVisible(false);

    	this.add(button_redknote);
    	this.add(button_menu);
    	this.add(button_clapperrail);
    	this.add(button_submit);
    	this.add(button_saveNquit);
    	this.add(button_next);
    	this.add(button_continue);
    	
    	this.add(button_A);
		this.add(button_B);
		this.add(button_C);
		this.add(button_D);
	}
	
	
	public void paintComponent(Graphics g) {
		
		if(this.gameStatus == GameStatus.RN) {
			//Draw the background with the x location in the arraylist 
			itbackground = background.iterator();
			while(itbackground.hasNext()) {
				int tempInt = itbackground.next();
				g.drawImage(pic_menu, tempInt, 0, this);
			}
			
			iterator = items.iterator();
			
	    	while(iterator.hasNext()) {
	    		
	    		Items tempItem = iterator.next();
	    		switch(tempItem.getItemID()) {
	    		case PowerUp:
	    			g.drawImage(pic_power, tempItem.getX(),tempItem.getY(),tempItem.getWidth(),tempItem.getLength(),this);
	    			break;
	    		case Fly:
	    			g.drawImage(pic_RNFly, tempItem.getX(), tempItem.getY(), tempItem.getWidth(), tempItem.getLength(), this);
	    			break;
	    		case Plane:
	    			g.drawImage(pic_RNPlane, tempItem.getX(), tempItem.getY(), tempItem.getWidth(), tempItem.getLength(), this);
	    			break;
	    		case Snail:
	    			g.drawImage(pic_RNSnail, tempItem.getX(), tempItem.getY(), tempItem.getWidth(), tempItem.getLength(), this);
	    			break;
	    		case Car:
	    			g.drawImage(pic_RNCar, tempItem.getX(), tempItem.getY(), tempItem.getWidth(), tempItem.getLength(), this);
	    			break;
	    			
	    		}	
	    	}
	    	//RN: Red Knot
	    	picNumFly=(picNumFly+1)%frameCountFly;
	    	g.drawImage(pics_redKnot.get(picNumFly), redKnot.getX(), redKnot.getY(), 200, 200, this);
	    	
	    	//RN: Mini Map
	    	g.drawImage(pic_map, mapRN.getX(), mapRN.getY(), Color.GRAY, this);
	    	g.drawRect(mapRN.getX(), mapRN.getY(), pic_map.getWidth(),pic_map.getHeight());
	    	g.drawImage(pic_redKnot_mini, mapRN.getStatus(), mapRN.getStatus_Y(), this);
	    
	    	//RN: ScoreBoard
	    	g.setColor(Color.WHITE);
	    	g.fillRoundRect(ScoreBoard.X, ScoreBoard.Y, ScoreBoard.LENGTH, ScoreBoard.WIDTH, 5, 5);
	    	g.setColor(Color.BLACK);
	    	g.drawRoundRect(ScoreBoard.X, ScoreBoard.Y, ScoreBoard.LENGTH, ScoreBoard.WIDTH, 5, 5);
	    	g.setColor(Color.RED); 
	    	Font font = new Font("Serif", Font.BOLD, 50);
	    	g.setFont(font);
	    	g.drawString("Score: " + scoreBoard.getScore(), ScoreBoard.X+10, ScoreBoard.Y+50);
						 	
		}else if(this.gameStatus == GameStatus.RNTutorial) {
			
			g.setFont(new Font("Serif", Font.PLAIN, 30));
			itbackground = background.iterator();
			while(itbackground.hasNext()) {
				int tempInt = itbackground.next();
				g.drawImage(pic_menu, tempInt, 0, this);
			}
			switch(tutorialLevel) {
			case 7:
				g.setColor(Color.RED);
				g.drawString("Good Job!", frameWidth/2-50, 100);
				g.drawString("You are all set! Click The [Next] button on the top", frameWidth/2-300, 550);
				g.drawImage(pic_RNPlane, frameWidth/2-500, 300, 180, 100, this);
				g.drawImage(pic_RNCar, frameWidth/2-250, 300, 180, 108, this);
				g.drawImage(pic_RNFly, frameWidth/2+100, 350, 64, 64, this);
				g.drawImage(pic_RNSnail, frameWidth/2+250, 350, 64, 64, this);
				g.drawImage(pic_power, frameWidth/2+400, 350, 64, 64,this);
				g.drawImage(greenCircle,frameWidth/2+25,275, 500, 200, this);
				g.drawImage(redCircle,frameWidth/2-600,250, 625, 225, this);
			case 6:
				if(tutorialLevel == 6) {
					iterator = items.iterator();
					g.setColor(Color.RED);
					g.drawString("Go Catch that fly!", frameWidth/2-100, 100);
			    	while(iterator.hasNext()) {
			    		Items tempItem = iterator.next();
			    		g.drawImage(pic_RNFly, tempItem.getX(),tempItem.getY(),tempItem.getWidth(),tempItem.getLength(),this);
			    	}
				}
				
			case 5:
				g.setColor(Color.WHITE);
		    	g.fillRoundRect(ScoreBoard.X, ScoreBoard.Y, ScoreBoard.LENGTH, ScoreBoard.WIDTH, 5, 5);
		    	g.setColor(Color.BLACK);
		    	g.drawRoundRect(ScoreBoard.X, ScoreBoard.Y, ScoreBoard.LENGTH, ScoreBoard.WIDTH, 5, 5);
		    	g.setColor(Color.RED); 
		    	g.drawString("Score: " + scoreBoard.getScore(), ScoreBoard.X+10, ScoreBoard.Y+50);
		    	if(tutorialLevel == 5) {
		    		g.drawOval(ScoreBoard.X-48, ScoreBoard.Y-16, ScoreBoard.LENGTH+100, ScoreBoard.WIDTH+32);
			    	g.drawString("The Score Board shows your grade", ScoreBoard.X + ScoreBoard.LENGTH+60, ScoreBoard.Y + 32);
		    	}
			case 4:
				g.setColor(Color.BLACK);
				g.drawImage(pic_map, mapRN.getX(), mapRN.getY(), Color.GRAY, this);
		    	g.drawRect(mapRN.getX(), mapRN.getY(), pic_map.getWidth(),pic_map.getHeight());
		    	g.drawImage(pic_redKnot_mini, 1390, 80, this);
		    	g.setColor(Color.RED);
		    	if(tutorialLevel == 4) {
		    		g.fillRect(frameWidth-140, 85, 80, 4); 
					g.drawString("Red Knot stops at Delaware Bay", frameWidth-540, 90);
		    	}
				
			case 3: 
				
				if(tutorialLevel == 3) {
					g.drawImage(pic_map, mapRN.getX(), mapRN.getY(), Color.GRAY, this);
			    	g.drawRect(mapRN.getX(), mapRN.getY(), pic_map.getWidth(),pic_map.getHeight());
			    	g.drawImage(pic_redKnot_mini, 1310, 30, this);
			    	g.setColor(Color.RED);
					g.fillRect(frameWidth-220, 35, 80, 4); //1220
					g.drawString("Red Knot migrates from Canada", frameWidth-620, 50); 
				}
			case 2:
				
		    	if(tutorialLevel == 2) {
		    		g.drawImage(pic_map, mapRN.getX(), mapRN.getY(), Color.GRAY, this);
			    	g.drawRect(mapRN.getX(), mapRN.getY(), pic_map.getWidth(),pic_map.getHeight());
			    	g.setColor(Color.RED);
		    		g.drawOval(mapRN.getX()-32, mapRN.getY()-32, 192 , 192);
		    		g.drawString("Here is a Mini Map shows the migration.", mapRN.getX()-550, mapRN.getY()+80 );
		    	}
			case 1:
				g.setColor(Color.RED);
				picNumFly=(picNumFly+1)%frameCountFly;
		    	g.drawImage(pics_redKnot.get(picNumFly), redKnot.getX(), redKnot.getY(), 200, 200, this);
		    	if(tutorialLevel == 1) {
		    		g.drawOval(redKnot.getX(), redKnot.getY(), 200, 200);
		    		g.drawString("Try using the Arrow Keys to controll the bird. Click [Next] on the top to continue", redKnot.getX()+200, redKnot.getY()+100);
		    	}
		    	break;
			}
		}
		
		else if(this.gameStatus == GameStatus.CR) {
			// CR: Background
			g.drawImage(pic_water, 0, 0, this);
			
			//CR: Mini Map
			g.drawImage(pic_delaware, mapRN.getX(), mapRN.getY(), 128, 128, Color.GRAY, this);
			g.drawRect(mapRN.getX(), mapRN.getY(), pic_map.getWidth(),pic_map.getHeight());
			g.drawImage(pic_clapperRail_mini, frameWidth-100, (int)(0.625*(-50)+ 111.25), this);
		
			//CR: draw the item in the arraylist
			iterator = CRitems.iterator();
			while(iterator.hasNext()) {
				Items tempItem = iterator.next();
				if(tempItem.getItemID() == ItemsID.Food) {
					g.drawImage(pic_food, tempItem.getX(), tempItem.getY(), 64, 64, this);
				}else {
					g.drawImage(pic_snake, tempItem.getX(), tempItem.getY(), 64, 64, this);
				}
			}
			//CR: Clapper Rail
			g.drawImage(pic_clapperRail, clapperRail.getX(), clapperRail.getY(), 200, 200, this);
			
			//CR: Status Bar
			for(int i = 0; i < statusBar.getStatus(); i++) {
				g.drawImage(pic_food, StatusBar.x, (StatusBar.y+i*StatusBar.length), StatusBar.length, StatusBar.length, this);
			}
		
		}else if(this.gameStatus == GameStatus.CRQUIZ) {
			//CRQUIZ: background
			g.drawImage(pic_water, 0, 0, this);
			
			//CRQUIZ: Question
			g.setFont(new Font("Serif", Font.PLAIN, 30));
			g.drawString(quiz_CR.getQuestions().get(quiz_CR.getQuestionIndex()).getQuestion(), 200, 200);
			
			//CRQUIZ: Check the answer and give the result
			if(answerRightFlag) {
				g.setFont(new Font("Serif", Font.PLAIN, 30));
				g.drawString("Your Answer is Correct! " , frameWidth/2-300, frameHeight/2+250);
				button_submit.setEnabled(false);
			}else if(answerWrongFlag) {
				g.setFont(new Font("Serif", Font.PLAIN, 30));
				g.drawString("Unfortunately The right answer is " + 
			quiz_CR.getQuestions().get(quiz_CR.getQuestionIndex()).getCorrectanswer(), frameWidth/2-500, frameHeight/2+250);
				button_submit.setEnabled(false);
			}
			
		}else if(this.gameStatus == GameStatus.RNQUIZ) {
			//RNQUIZ: background
			g.drawImage(pic_menu, 0, 0, this);
			
			//RNQUIZ: Question
			g.setFont(new Font("Serif", Font.PLAIN, 30));
			g.drawString(quiz_RN.getQuestions().get(quiz_RN.getQuestionIndex()).getQuestion(), 200, 200);
			
			//RNQUIZ: Check the answer and give the result
			if(answerRightFlag) {
				g.setFont(new Font("Serif", Font.PLAIN, 30));
				g.drawString("Good Job! Your Final Score is " + (scoreBoard.getScore()+10), frameWidth/2-300, frameHeight/2+250);
				button_submit.setEnabled(false);
			}else if(answerWrongFlag) {
				g.setFont(new Font("Serif", Font.PLAIN, 30));
				g.drawString("Unfortunately The right answer is " + 
			quiz_RN.getQuestions().get(quiz_RN.getQuestionIndex()).getCorrectanswer()
			 + " Your Final Score is " + scoreBoard.getScore(), frameWidth/2-500, frameHeight/2+250);
				button_submit.setEnabled(false);
			}
			
		}else if(this.gameStatus == GameStatus.Menu){	
			//Menu: Background
			g.drawImage(pic_menu, 0, 0, this);		
		}
    }

    //update birds location, items location, and score(status)
    public void update(RedKnot redKnot, ClapperRail clapperrail, Map mapRN, 
    		GameStatus gameStatus, ScoreBoard scoreBoard, ArrayList<Items> items,
    		ArrayList<Items> CRitems, Quiz quiz_RN, Quiz quiz_CR, 
    		boolean answerRightFlag, boolean answerWrongFlag, ArrayList<Integer> background, int tutorialLevel, StatusBar statusBar) {
    	//Update everything from model to view
    	this.gameStatus = gameStatus;
    	this.items = items;
    	this.CRitems = CRitems;
    	this.mapRN = mapRN;
    	this.quiz_RN = quiz_RN;
    	this.quiz_CR = quiz_CR;
    	this.answerRightFlag = answerRightFlag;
    	this.answerWrongFlag = answerWrongFlag;
    	this.redKnot = redKnot;
    	this.clapperRail = clapperrail;
    	this.scoreBoard = scoreBoard;
    	this.background = background;
    	this.tutorialLevel = tutorialLevel;
    	this.statusBar = statusBar;
    	
    	if(this.gameStatus == GameStatus.RN) {
    		
    		//Arrange the button visibility in game mode
    		button_continue.setVisible(false);
			button_saveNquit.setVisible(true);
			button_redknote.setVisible(false);
			button_next.setVisible(false);
			button_clapperrail.setVisible(false);
			button_menu.setVisible(true);
			button_submit.setEnabled(false);
			button_submit.setVisible(false);
			button_A.setVisible(false);
	    	button_B.setVisible(false);
	    	button_C.setVisible(false);
	    	button_D.setVisible(false);
	    	
	    	//RN: SpawnCounter add new random item to the items ArrayList
	    	itemSpawnCounter++;
        	if(itemSpawnCounter >= RK_SPAWN_SPEED) {
        		itemSpawnCounter = 0;
        		
        		switch(random.nextInt(4)) {
        		case 0:
        			this.items.add(new Obstacle(frameWidth, random.nextInt(100)+100, 180, 100, ItemsID.Plane));
        			break;
        		case 1:
        			this.items.add(new Food(frameWidth, random.nextInt(400) + (frameHeight - 400) , 64, 64, ItemsID.Snail));
        			break;
        		case 2:
        			this.items.add(new Food(frameWidth, random.nextInt(100)+100, 64, 64, ItemsID.Fly));
        			break;
        		case 3:
        			this.items.add(new Obstacle(frameWidth, frameHeight-250, 200, 128, ItemsID.Car));
        			break; 			
        		}

        	}
        	
        	//RN: Power up Counter, add to the Arraylist 
        	powerupSpawnCounter++;
        	if(powerupSpawnCounter==500) {
        		powerupSpawnCounter=0;
        		this.items.add(new PowerUp(frameWidth, random.nextInt(400)+(frameHeight-400),32,32,ItemsID.PowerUp));
        	}	
    			
    	}else if(this.gameStatus == GameStatus.RNTutorial) {
    		switch(this.tutorialLevel) {
    		case 6:
    			button_next.setEnabled(false);
    			if(this.items.size() <= 0) {
    				this.items.add(new Food(frameWidth, random.nextInt(100)+100, 64, 64, ItemsID.Fly));
    			}
    			break;
    		default:
    			button_next.setEnabled(true);
    			break;
    		}
    		
    		button_next.setVisible(true);
    		button_continue.setVisible(false);
    		button_saveNquit.setVisible(false);
			button_redknote.setVisible(false);
			button_clapperrail.setVisible(false);
			button_menu.setVisible(true);
			button_submit.setEnabled(false);
			button_submit.setVisible(false);
			button_A.setVisible(false);
	    	button_B.setVisible(false);
	    	button_C.setVisible(false);
	    	button_D.setVisible(false);
	    	
	    	
    	}else if(this.gameStatus == GameStatus.CR) {
    		//Arrange the button visibility in game mode
    		button_saveNquit.setVisible(true);
    		button_redknote.setVisible(false);
			button_clapperrail.setVisible(false);
			button_next.setVisible(false);
			button_menu.setVisible(true);
			button_continue.setVisible(false);
			
			button_submit.setEnabled(false);
			button_submit.setVisible(false);
			button_A.setVisible(false);
	    	button_B.setVisible(false);
	    	button_C.setVisible(false);
	    	button_D.setVisible(false);
	    	
	    	//CR: add one random item to the array list if it is empty
    		if(this.CRitems.size() == 0) {
    			switch(random.nextInt(8)) {
    			case 0:
    				this.CRitems.add(new Food(frameWidth/2-32, frameHeight/2-32+200, 32, 32 , ItemsID.Food));
    				break;
    			case 1:
    				this.CRitems.add(new Food(frameWidth/2-32, frameHeight/2-32-200, 32, 32 , ItemsID.Food));
    				break;
    			case 2:
    				this.CRitems.add(new Food(frameWidth/2-32+200, frameHeight/2-32, 32, 32 , ItemsID.Food));
    				break;
    			case 3:
    				this.CRitems.add(new Food(frameWidth/2-32-200, frameHeight/2-32, 32, 32 , ItemsID.Food));
    				break;
    			case 4:
    				this.CRitems.add(new Obstacle(frameWidth/2-32, frameHeight/2-32+200, 32, 32, ItemsID.Obstacle));
    				break;
    			case 5:
    				this.CRitems.add(new Obstacle(frameWidth/2-32, frameHeight/2-32-200,32, 32, ItemsID.Obstacle));
    				break;
    			case 6:
    				this.CRitems.add(new Obstacle(frameWidth/2-32+200, frameHeight/2-32,32, 32, ItemsID.Obstacle));
    				break;
    			case 7:
    				this.CRitems.add(new Obstacle(frameWidth/2-32-200, frameHeight/2-32,32, 32, ItemsID.Obstacle));
    				break;
    			}
    		}
    			
    		
    	}else if(this.gameStatus == GameStatus.CRQUIZ) {
    		// Arrane the button in quiz mode
    		button_A.setText(quiz_CR.getQuestions().get(quiz_CR.getQuestionIndex()).getAnswers()[0]);
    		button_B.setText(quiz_CR.getQuestions().get(quiz_CR.getQuestionIndex()).getAnswers()[1]);
    		button_C.setText(quiz_CR.getQuestions().get(quiz_CR.getQuestionIndex()).getAnswers()[2]);
    		button_D.setText(quiz_CR.getQuestions().get(quiz_CR.getQuestionIndex()).getAnswers()[3]);
    		
    		button_A.setFont(new Font("Arial", Font.PLAIN, 30));
			button_B.setFont(new Font("Arial", Font.PLAIN, 30));
			button_C.setFont(new Font("Arial", Font.PLAIN, 30));
			button_D.setFont(new Font("Arial", Font.PLAIN, 30));
			
    		button_redknote.setVisible(false);
			button_clapperrail.setVisible(false);
			button_menu.setVisible(true);
			
			button_continue.setVisible(false);
			button_submit.setVisible(true);
			button_next.setVisible(false);
			button_A.setVisible(true);
	    	button_B.setVisible(true);
	    	button_C.setVisible(true);
	    	button_D.setVisible(true);
			
		}else if(this.gameStatus == GameStatus.RNQUIZ) {
			// Arrane the button in quiz mode
			button_A.setText(quiz_RN.getQuestions().get(quiz_RN.getQuestionIndex()).getAnswers()[0]);
			button_B.setText(quiz_RN.getQuestions().get(quiz_RN.getQuestionIndex()).getAnswers()[1]);
			button_C.setText(quiz_RN.getQuestions().get(quiz_RN.getQuestionIndex()).getAnswers()[2]);
			button_D.setText(quiz_RN.getQuestions().get(quiz_RN.getQuestionIndex()).getAnswers()[3]);
			
			button_A.setFont(new Font("Arial", Font.PLAIN, 30));
			button_B.setFont(new Font("Arial", Font.PLAIN, 30));
			button_C.setFont(new Font("Arial", Font.PLAIN, 30));
			button_D.setFont(new Font("Arial", Font.PLAIN, 30));
			
			button_continue.setVisible(false);
			button_redknote.setVisible(false);
			button_clapperrail.setVisible(false);
			button_menu.setVisible(true);
			button_next.setVisible(false);
			
			button_submit.setVisible(true);
			button_A.setVisible(true);
	    	button_B.setVisible(true);
	    	button_C.setVisible(true);
	    	button_D.setVisible(true);

		}else if(this.gameStatus == GameStatus.Menu) {
			//Arrange the button in menu mode
			button_continue.setVisible(true);
			button_menu.setVisible(false);
			button_redknote.setVisible(true);
			button_clapperrail.setVisible(true);
			button_saveNquit.setVisible(false);
			button_next.setVisible(false);
			
			button_submit.setEnabled(false);
			button_submit.setVisible(false);
	    	button_A.setVisible(false);
	    	button_B.setVisible(false);
	    	button_C.setVisible(false);
	    	button_D.setVisible(false);
		}
    	frame.repaint();	// call the painComponent();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    //Create Images 
    public void createImages() {
    	BufferedImage bufferedImage;
    	try {
    		pic_power=ImageIO.read(new File("images/components/powerup.png"));
    		pic_food = ImageIO.read(new File("images/projectile/Food.png"));
    		pic_RNFood = ImageIO.read(new File("images/projectile/RN_Food.png"));
    		pic_snake = ImageIO.read(new File("images/projectile/Snake.png"));
    		pic_obstacle = ImageIO.read(new File("images/projectile/Obstacles.png"));
    		pic_map = ImageIO.read(new File("images/Components/Map.png"));
    		pic_delaware=ImageIO.read(new File("images/Components/delaware.png"));
    		pic_menu = ImageIO.read(new File("images/background/menu_BG.png")).getScaledInstance(this.frameWidth,this.frameHeight,Image.SCALE_SMOOTH);
    		pic_water = ImageIO.read(new File("images/background/Water.png")).getScaledInstance(this.frameWidth,this.frameHeight,Image.SCALE_SMOOTH);
    		pic_RNCar = ImageIO.read(new File("images/projectile/Car.png"));
    		pic_RNCar = ImageIO.read(new File("images/projectile/Car.png"));
    		pic_RNFly = ImageIO.read(new File("images/projectile/Fly.png"));
    		pic_RNPlane = ImageIO.read(new File("images/projectile/Plane.png"));
    		pic_RNSnail = ImageIO.read(new File("images/projectile/Snail.png"));
    		greenCircle=ImageIO.read(new File("images/components/Green Circle.png"));
    		redCircle=ImageIO.read(new File("images/components/Red Circle.png"));
    		
    		pic_icon_RN = ImageIO.read(new File("images/birds/icon_RN.png"));
    		pic_icon_CR = ImageIO.read(new File("images/birds/icon_CR.png"));
    		
    		pic_clapperRail = ImageIO.read(new File("images/birds/CR.png"));
    		pic_redKnot_mini = ImageIO.read(new File("images/birds/RN_mini.png"));
    		pic_clapperRail_mini = ImageIO.read(new File("images/birds/CR_mini.png"));
    		bufferedImage = ImageIO.read(new File("Images/birds/RN1.png"));
			pics_redKnot.add(bufferedImage);
			bufferedImage = ImageIO.read(new File("Images/birds/RN2.png"));
			pics_redKnot.add(bufferedImage);
			bufferedImage = ImageIO.read(new File("Images/birds/RN3.png"));
			pics_redKnot.add(bufferedImage);
			bufferedImage = ImageIO.read(new File("Images/birds/RN4.png"));
			pics_redKnot.add(bufferedImage);
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}
