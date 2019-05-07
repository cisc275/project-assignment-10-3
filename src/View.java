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
    private StatusBar statusBar= new StatusBar(100,frameHeight/2-150);      //Status bar for CR game
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
    Image pic_menu;                                                       //BufferedImage for RN snail image
    ArrayList<BufferedImage> pics_redKnot =new ArrayList<>();             //Array list for red knot pics to be drawn to screen
    BufferedImage pic_icon_RN;                                            //BufferedImage for RN image on main menu
    BufferedImage pic_icon_CR;                                            //BufferedImage for CR image on main menu
    Image pic_water;                                                      //BufferedImage for water image
    
    
    
    Quiz quiz_RN = new Quiz("quiz/RNQuiz.txt");
    Quiz quiz_CR = new Quiz("quiz/CRQuiz.txt");
    
    JFrame frame;
    
    JButton button_clapperrail;
    JButton button_redknote;
    JButton button_menu;
    JButton button_submit;
    JButton button_start;
    
    JRadioButton button_A;
    JRadioButton button_B;
    JRadioButton button_C;
    JRadioButton button_D;
    ButtonGroup group = new ButtonGroup(); // = new ButtonGroup();
    
    JPanel rkSide;
    JPanel crSide;
    
    private JTextArea rkTextArea;
    private JTextArea crTextArea;
    
    GameStatus gameStatus = GameStatus.Menu;
    
    private boolean answerRightFlag = false;
    private boolean answerWrongFlag = false;
    private boolean tutorialFlag = true;
    
    final int frameCountFly=4;
    private int picNumFly=0;
    
   
   
   BufferedImage[] pics;
    
    public View() {
    	System.out.println(frameWidth);
    	System.out.println(frameHeight);
    	background.add(0);
    	background.add(frameWidth);
    	this.setLayout(new FlowLayout());
    	
    	//Create Images
    	createImages();

    	//Buttons
    	makeButtons();

    	//make text areas for instructions for games
    	makeTextAreas();
    	random = new Random();
    	
    	//Create Birds
  
    	redKnot = new RedKnot(frameHeight/2-32);
    	clapperRail = new ClapperRail(frameWidth/2-120, frameHeight/2-100);
    	frame = new JFrame();
    	
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
    
    public int getFrameWidth() {
		return frameWidth;
	}  
	public ArrayList<Integer> getBackGround() {
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
	 * makes, writes, and places both text boxes into menu 
	 */
	private void makeTextAreas() {
		final int textAreaWidth = 530;
		final int textAreaHeight = 500;
		// init textareas
		rkTextArea = new JTextArea();
		crTextArea = new JTextArea();
						

				
		rkTextArea.setEditable(false);
		rkTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
		rkTextArea.setVisible(false);
		rkTextArea.setOpaque(false);
		rkTextArea.setBounds(frameWidth/2-250,100,textAreaWidth,textAreaHeight);
		rkTextArea.setLineWrap(true);
				
		crTextArea.setEditable(false);
		crTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
		crTextArea.setVisible(false);
		crTextArea.setOpaque(false);
		crTextArea.setBounds(frameWidth/2-250,100, textAreaWidth,textAreaHeight);
		crTextArea.setLineWrap(true);
				
		this.add(rkTextArea);
		this.add(crTextArea);
				
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
    	
    	button_start = new JButton("Start");
    	button_start.setBounds(frameWidth/2+200, frameHeight-200, 64, 32); //64 , 32
    	button_start.setBackground(Color.GRAY);
    	button_start.setOpaque(false);
    	button_start.setActionCommand("start");
    	button_start.setVisible(false);
    	
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
    	this.add(button_start);
    	
    	this.add(button_A);
		this.add(button_B);
		this.add(button_C);
		this.add(button_D);
	}
	
	
	public void paintComponent(Graphics g) {
		
		if(this.gameStatus == GameStatus.RN) {
			//g.drawImage(pic_menu, 0, 0, this);
			itbackground = background.iterator();
			while(itbackground.hasNext()) {
				int tempInt = itbackground.next();
				g.drawImage(pic_menu, tempInt, 0, this);
			}
			
			if(!tutorialFlag) {
				
				iterator = items.iterator();
				
		    	while(iterator.hasNext()) {
		    		
		    		Items tempItem = iterator.next();
		    		switch(tempItem.getItemID()) {
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
		    	picNumFly=(picNumFly+1)%frameCountFly;
		    	g.drawImage(pics_redKnot.get(picNumFly), redKnot.getX(), redKnot.getY(), 200, 200, this);
		    	g.drawImage(pic_map, mapRN.getX(), mapRN.getY(), Color.GRAY, this);
		    	g.drawRect(mapRN.getX(), mapRN.getY(), pic_map.getWidth(),pic_map.getHeight());
		    	
		    	
		    	g.setColor(Color.WHITE);
		    	g.drawRect(scoreBoard.getX(), scoreBoard.getY(), scoreBoard.getLength(), scoreBoard.getWidth());
		    	g.fillRect(scoreBoard.getX(), scoreBoard.getY(), scoreBoard.getLength(), scoreBoard.getWidth());
		    	g.setColor(Color.BLACK);
		    	g.drawString("Score: " + scoreBoard.getScore(), scoreBoard.getX()+5, scoreBoard.getY()+20);
		    	//
		    	//
		    	g.drawImage(pic_redKnot_mini, mapRN.getStatus(), mapRN.getStatus_Y(), this);
			}
			

	    	
	    	
		}else if(this.gameStatus == GameStatus.CR) {
			g.drawImage(pic_water, 0, 0, this);
			if(!tutorialFlag) {
				g.drawImage(pic_delaware, mapRN.getX(), mapRN.getY(), 128, 128, Color.GRAY, this);
				g.drawImage(pic_clapperRail_mini, frameWidth-100, (int)(0.625*(-50)+ 111.25), this);
			//	g.drawRect(mapRN.getX(), mapRN.getY(), pic_delaware.get,pic_delaware.getHeight());
			
				iterator = CRitems.iterator();
				while(iterator.hasNext()) {
					Items tempItem = iterator.next();
					if(tempItem.getItemID() == ItemsID.Food) {
						g.drawImage(pic_food, tempItem.getX(), tempItem.getY(), 64, 64, this);
					}else {
						g.drawImage(pic_snake, tempItem.getX(), tempItem.getY(), 64, 64, this);
					}
					
				}
				g.drawImage(pic_clapperRail, clapperRail.getX(), clapperRail.getY(), 200, 200, this);
				
				g.drawRect(statusBar.getX(), statusBar.getY(), statusBar.getLength(), statusBar.getWidth());
				g.setColor(Color.PINK);
				g.fillRect(statusBar.getX(), statusBar.getY(), statusBar.getLength(), statusBar.getStatus());
			}
			
			
		}else if(this.gameStatus == GameStatus.CRQUIZ) {
			g.drawImage(pic_water, 0, 0, this);
			g.setFont(new Font("Arial", Font.PLAIN, 30));
			g.drawString(quiz_CR.getQuestions().get(quiz_CR.getQuestionIndex()).getQuestion(), 200, 200);
			if(answerRightFlag) {
				g.drawString("Your Answer is Correct! " , frameWidth/2-300, frameHeight/2+250);
				button_submit.setEnabled(false);
			}else if(answerWrongFlag) {
				g.drawString("Unfortunately The right answer is " + 
			quiz_CR.getQuestions().get(quiz_CR.getQuestionIndex()).getCorrectanswer(), frameWidth/2-500, frameHeight/2+250);
				button_submit.setEnabled(false);
			}
			
		}else if(this.gameStatus == GameStatus.RNQUIZ) {
			//print the result on the screen
			g.drawImage(pic_menu, 0, 0, this);
			g.setFont(new Font("Arial", Font.PLAIN, 30));
			g.drawString(quiz_RN.getQuestions().get(quiz_RN.getQuestionIndex()).getQuestion(), 200, 200);
			if(answerRightFlag) {
				g.drawString("Good Job! Your Final Score is " + (scoreBoard.getScore()+10), frameWidth/2-300, frameHeight/2+250);
				button_submit.setEnabled(false);
			}else if(answerWrongFlag) {
				g.drawString("Unfortunately The right answer is " + 
			quiz_RN.getQuestions().get(quiz_RN.getQuestionIndex()).getCorrectanswer()
			 + " Your Final Score is " + scoreBoard.getScore(), frameWidth/2-500, frameHeight/2+250);
				button_submit.setEnabled(false);
			}
			
		}else if(this.gameStatus == GameStatus.Menu){	
				g.drawImage(pic_menu, 0, 0, this);		
		}
    }
    
    //repaint();
    //update birds location, items location, and score(status)
    public void update(RedKnot redKnot, ClapperRail clapperrail, Map mapRN, 
    		GameStatus gameStatus, ScoreBoard scoreBoard, ArrayList<Items> items,
    		ArrayList<Items> CRitems, Quiz quiz_RN, Quiz quiz_CR, 
    		boolean answerRightFlag, boolean answerWrongFlag, boolean tutorialFlag, ArrayList<Integer> background ) {
    	this.gameStatus = gameStatus;
    	this.items = items;
    	this.CRitems = CRitems;
    	this.mapRN = mapRN;
    	this.quiz_RN = quiz_RN;
    	this.quiz_CR = quiz_CR;
    	this.answerRightFlag = answerRightFlag;
    	this.answerWrongFlag = answerWrongFlag;
    	this.tutorialFlag = tutorialFlag;
    	this.redKnot = redKnot;
    	this.clapperRail = clapperRail;
    	this.scoreBoard = scoreBoard;
    	this.background = background;
    	
    	if(this.gameStatus == GameStatus.RN) {
    		
    		if(tutorialFlag) {
    			rkTextArea.setVisible(true); // text area true
    			button_start.setVisible(true);
    			button_menu.setVisible(true);
        		crTextArea.setVisible(false);
        		button_redknote.setVisible(false);
    			button_clapperrail.setVisible(false);
    			button_submit.setEnabled(false);
    			button_submit.setVisible(false);
    			button_A.setVisible(false);
    	    	button_B.setVisible(false);
    	    	button_C.setVisible(false);
    	    	button_D.setVisible(false);
    		}else {
    			rkTextArea.setVisible(false);
    			crTextArea.setVisible(false);
    			button_start.setVisible(false);
    			button_redknote.setVisible(false);
    			button_clapperrail.setVisible(false);
    			button_menu.setVisible(true);
    			button_submit.setEnabled(false);
    			button_submit.setVisible(false);
    			button_A.setVisible(false);
    	    	button_B.setVisible(false);
    	    	button_C.setVisible(false);
    	    	button_D.setVisible(false);
    	    	itemSpawnCounter++;
            	if(itemSpawnCounter >= 40) {
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
    		}	
    	}else if(this.gameStatus == GameStatus.CR) {
    		
    		if(tutorialFlag) {
    			crTextArea.setVisible(true);
    			button_start.setVisible(true);
    			button_menu.setVisible(true);
        		rkTextArea.setVisible(false);
        		button_redknote.setVisible(false);
    			button_clapperrail.setVisible(false);
    			button_submit.setEnabled(false);
    			button_submit.setVisible(false);
    			button_A.setVisible(false);
    	    	button_B.setVisible(false);
    	    	button_C.setVisible(false);
    	    	button_D.setVisible(false);
    		}else {
        		rkTextArea.setVisible(false);
        		crTextArea.setVisible(false);
        		button_start.setVisible(false);
        		button_redknote.setVisible(false);
    			button_clapperrail.setVisible(false);
    			button_menu.setVisible(true);
    			
    			button_submit.setEnabled(false);
    			button_submit.setVisible(false);
    			button_A.setVisible(false);
    	    	button_B.setVisible(false);
    	    	button_C.setVisible(false);
    	    	button_D.setVisible(false);
    	    	
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
}
    		
    	}else if(this.gameStatus == GameStatus.CRQUIZ) {
    		
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
			
			button_submit.setVisible(true);
			button_A.setVisible(true);
	    	button_B.setVisible(true);
	    	button_C.setVisible(true);
	    	button_D.setVisible(true);
			
		}else if(this.gameStatus == GameStatus.RNQUIZ) {
			
			button_A.setText(quiz_RN.getQuestions().get(quiz_RN.getQuestionIndex()).getAnswers()[0]);
			button_B.setText(quiz_RN.getQuestions().get(quiz_RN.getQuestionIndex()).getAnswers()[1]);
			button_C.setText(quiz_RN.getQuestions().get(quiz_RN.getQuestionIndex()).getAnswers()[2]);
			button_D.setText(quiz_RN.getQuestions().get(quiz_RN.getQuestionIndex()).getAnswers()[3]);
			
			button_A.setFont(new Font("Arial", Font.PLAIN, 30));
			button_B.setFont(new Font("Arial", Font.PLAIN, 30));
			button_C.setFont(new Font("Arial", Font.PLAIN, 30));
			button_D.setFont(new Font("Arial", Font.PLAIN, 30));
			
			button_redknote.setVisible(false);
			button_clapperrail.setVisible(false);
			button_menu.setVisible(true);
			
			button_submit.setVisible(true);
			button_A.setVisible(true);
	    	button_B.setVisible(true);
	    	button_C.setVisible(true);
	    	button_D.setVisible(true);
			
			
			
		}else if(this.gameStatus == GameStatus.Menu) {
			

			rkTextArea.setVisible(false);
    		crTextArea.setVisible(false);
			button_menu.setVisible(false);
			button_redknote.setVisible(true);
			button_clapperrail.setVisible(true);
			button_start.setVisible(false);
			
			button_submit.setEnabled(false);
			button_submit.setVisible(false);
	    	button_A.setVisible(false);
	    	button_B.setVisible(false);
	    	button_C.setVisible(false);
	    	button_D.setVisible(false);
	    	
		}
    	
    	
    	frame.repaint();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    
    //
    //Create Images 
    //
    
    public void createImages() {
    	BufferedImage bufferedImage;
    	try {
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
