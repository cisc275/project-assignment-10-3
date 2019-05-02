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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class View extends JPanel{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final int frameWidth = screenSize.width;
    private final int frameHeight = screenSize.height;
    
    private ClapperRail clapperRail;
    private RedKnot redKnot;

    private Map mapRN = new Map(frameWidth-148, frameWidth-130);
    //
    //
    private StatusBar statusBar= new StatusBar(frameWidth/2-150,frameHeight-150);
    //
    //
    private ScoreBoard scoreBoard = new ScoreBoard();
    
    ArrayList<Items> items = new ArrayList<>();
    ArrayList<Items> CRitems = new ArrayList<>();
    Iterator<Items> iterator;
    
    private int itemSpawnCounter = 0;
    Random random;
    
    BufferedImage pic_redKnot_mini;
    BufferedImage pic_clapperRail;
    BufferedImage pic_food;
    BufferedImage pic_map;
    BufferedImage pic_obstacle;
    BufferedImage pic_snake;
    BufferedImage pic_RNFood;
    BufferedImage pic_menu;
    ArrayList<BufferedImage> pics_redKnot =new ArrayList<>();
    
    
    Quiz quiz_RN = new Quiz("quiz/RNQuiz.txt");
    Quiz quiz_CR = new Quiz("quiz/CRQuiz.txt");
    
    JFrame frame;
    
    JButton button_clapperrail;
    JButton button_redknote;
    JButton button_menu;
    JButton button_submit;
    
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
    
    final int frameCountFly=4;
    private int picNumFly=0;
    
   
   
   BufferedImage[] pics;
    
    public View() {
    	//Buttons
    	
    	this.setLayout(new FlowLayout());
    	
    	
    	//make text areas for instructions for games
    	makeTextAreas();
    	
    	//Buttons
    	makeButtons();

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
    	
    	//Create Images
    	createImages();

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
	/*	rkTextArea = new JTextArea(
				"This is an editable JTextArea. " +
					    "A text area is a \"plain\" text component, " +
					    "which means that although it can display text " +
					    "in any font, all of the text is in the same font.");
		crTextArea = new JTextArea("This is an editable JTextArea. " +
			    "A text area is a \"plain\" text component, " +
			    "which means that although it can display text " +
			    "in any font, all of the text is in the same font.");
		
		rkTextArea.setEditable(false);
		rkTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
		rkTextArea.setVisible(true);
		
		crTextArea.setEditable(false);
		crTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
		crTextArea.setVisible(true);
		
		this.add(rkTextArea, 2, 0);
		this.add(crTextArea, 2, 1);
		*/
	}
	
	/**
	 * makes buttons and styles for menu
	 */
	private void makeButtons() {
		button_redknote = new JButton("Red Knot", new ImageIcon("/images/birds/rkbutton.jpg"));  
    	button_redknote.setBackground(Color.BLUE);
    	button_redknote.setOpaque(true);
    	button_redknote.setActionCommand("redKnot");
    	button_redknote.setPreferredSize(new Dimension(frameWidth / 4, frameHeight / 4));
    	button_redknote.setFont(new Font("Arial", Font.PLAIN, 40));
    	button_redknote.setVisible(true);
    	
    	button_clapperrail = new JButton("Clapper Rail", new ImageIcon("/images/birds/crbutton.jpg"));  
    	button_clapperrail.setBackground(Color.BLUE);
    	button_clapperrail.setOpaque(true);
    	button_clapperrail.setActionCommand("clapperRail");
    	button_clapperrail.setPreferredSize(new Dimension(frameWidth / 4, frameHeight / 4));
    	button_clapperrail.setFont(new Font("Arial", Font.PLAIN, 40));
    	button_clapperrail.setVisible(true);
    	
    	button_menu = new JButton("Menu");
    	button_menu.setBackground(Color.GRAY);
    	button_menu.setOpaque(true);
    	button_menu.setActionCommand("menu");
    	button_menu.setVisible(false);
    	
    	button_submit = new JButton("Submit");
    	button_submit.setBackground(Color.GRAY);
    	button_submit.setOpaque(true);
    	button_submit.setActionCommand("submit");
    	button_submit.setEnabled(false);
    	button_submit.setVisible(false);
    	
    	button_A = new JRadioButton();
    	button_B = new JRadioButton();
    	button_C = new JRadioButton();
    	button_D = new JRadioButton();
    	
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
    	
    	this.add(button_A);
		this.add(button_B);
		this.add(button_C);
		this.add(button_D);
	}
	
	
	public void paintComponent(Graphics g) {
		
		if(this.gameStatus == GameStatus.RN) {
			iterator = items.iterator();
			
	    	while(iterator.hasNext()) {
	    		
	    		Items tempItem = iterator.next();
	    		g.drawImage(pic_RNFood, tempItem.getX(), tempItem.getY(), 64, 64, this);
	    	}
	    	picNumFly=(picNumFly+1)%frameCountFly;
	    	g.drawImage(pics_redKnot.get(picNumFly), redKnot.getX(), redKnot.getY(), 200, 200, this);
	    	g.drawImage(pic_map, mapRN.getX(), mapRN.getY(), Color.GRAY, this);
	    	
	    	
	    	g.setColor(Color.WHITE);
	    	g.drawRect(scoreBoard.getX(), scoreBoard.getY(), scoreBoard.getLength(), scoreBoard.getWidth());
	    	g.fillRect(scoreBoard.getX(), scoreBoard.getY(), scoreBoard.getLength(), scoreBoard.getWidth());
	    	g.setColor(Color.BLACK);
	    	g.drawString("Score: " + scoreBoard.getScore(), scoreBoard.getX()+5, scoreBoard.getY()+20);
	    	//
	    	//
	    	g.drawImage(pic_redKnot_mini, mapRN.getStatus(), mapRN.getStatus_Y(), this);

	    	
	    	
		}else if(this.gameStatus == GameStatus.CR) {
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
			g.fillRect(statusBar.getX(), statusBar.getY(), statusBar.getStatus(), statusBar.getWidth());
			
		}else if(this.gameStatus == GameStatus.CRQUIZ) {
			g.setFont(new Font("Arial", Font.PLAIN, 40));
			g.drawString(quiz_CR.getQuestions().get(quiz_CR.getQuestionIndex()).getQuestion(), frameWidth/2-100, frameHeight/2);
			if(answerRightFlag) {
				g.drawString("Your Answer is Correct! " , frameWidth/2-300, frameHeight/2+200);
				button_submit.setEnabled(false);
			}else if(answerWrongFlag) {
				g.drawString("Unfortunately The right answer is " + 
			quiz_CR.getQuestions().get(quiz_CR.getQuestionIndex()).getCorrectanswer(), frameWidth/2-500, frameHeight/2+200);
				button_submit.setEnabled(false);
			}
			
		}else if(this.gameStatus == GameStatus.RNQUIZ) {
			//print the result on the screen
			g.setFont(new Font("Arial", Font.PLAIN, 40));
			g.drawString(quiz_RN.getQuestions().get(quiz_RN.getQuestionIndex()).getQuestion(), frameWidth/2-100, frameHeight/2);
			if(answerRightFlag) {
				g.drawString("Good Job! Your Final Score is " + (scoreBoard.getScore()+10), frameWidth/2-300, frameHeight/2+200);
				button_submit.setEnabled(false);
			}else if(answerWrongFlag) {
				g.drawString("Unfortunately The right answer is " + 
			quiz_RN.getQuestions().get(quiz_RN.getQuestionIndex()).getCorrectanswer()
			 + " Your Final Score is " + scoreBoard.getScore(), frameWidth/2-500, frameHeight/2+200);
				button_submit.setEnabled(false);
			}
			
		}else if(this.gameStatus == GameStatus.Menu){
			
			
				g.drawImage(pic_menu.getScaledInstance(this.frameWidth,
						this.frameHeight,Image.SCALE_DEFAULT), 0, 0, this);
			
		}
    	
    	
    	
    }
    
    //repaint();
    //update birds location, items location, and score(status)
    public void update(RedKnot redKnot, ClapperRail clapperrail, Map mapRN, 
    		GameStatus gameStatus, ScoreBoard scoreBoard, ArrayList<Items> items,
    		ArrayList<Items> CRitems, Quiz quiz_RN, Quiz quiz_CR, boolean answerRightFlag, boolean answerWrongFlag ) {
    	this.gameStatus = gameStatus;
    	this.items = items;
    	this.CRitems = CRitems;
    	this.mapRN = mapRN;
    	this.quiz_RN = quiz_RN;
    	this.quiz_CR = quiz_CR;
    	this.answerRightFlag = answerRightFlag;
    	this.answerWrongFlag = answerWrongFlag;
    	this.redKnot = redKnot;
    	this.clapperRail = clapperRail;
    	this.scoreBoard = scoreBoard;
    	
    	if(this.gameStatus == GameStatus.RN) {
    		
    		button_redknote.setVisible(false);
			button_clapperrail.setVisible(false);
			button_menu.setVisible(true);
			
			button_submit.setEnabled(false);
			button_submit.setVisible(false);
			button_A.setVisible(false);
	    	button_B.setVisible(false);
	    	button_C.setVisible(false);
	    	button_D.setVisible(false);
			
//			A.setVisible(false);
//			B.setVisible(false);
//			C.setVisible(false);
//			D.setVisible(false);
    		
    		itemSpawnCounter++;
        	if(itemSpawnCounter >= 10) {
        		itemSpawnCounter = 0;
        		this.items.add(new Food(frameWidth, random.nextInt(frameHeight), ItemsID.Food));
        	}
        	
//        	this.scoreBoard.setScore(scoreBoard.getScore());
//        	this.redKnot.setX(redKnot.getX());
//        	this.redKnot.setY(redKnot.getY());
        	/*
        	 * Map Location 
        	 */
        	//this.mapRN.setStatus(mapRN.getStatus());
        	//this.mapRN.setStatus_Y(mapRN.getStatus_Y());
        	
        	//System.out.println(mapRN.updateLocation());
        	
    	}else if(this.gameStatus == GameStatus.CR) {
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
    				this.CRitems.add(new Food(frameWidth/2-32, frameHeight/2-32+200, ItemsID.Food));
    				break;
    			case 1:
    				this.CRitems.add(new Food(frameWidth/2-32, frameHeight/2-32-200, ItemsID.Food));
    				break;
    			case 2:
    				this.CRitems.add(new Food(frameWidth/2-32+200, frameHeight/2-32, ItemsID.Food));
    				break;
    			case 3:
    				this.CRitems.add(new Food(frameWidth/2-32-200, frameHeight/2-32, ItemsID.Food));
    				break;
    			case 4:
    				this.CRitems.add(new Obstacle(frameWidth/2-32, frameHeight/2-32+200, ItemsID.Obstacle));
    				break;
    			case 5:
    				this.CRitems.add(new Obstacle(frameWidth/2-32, frameHeight/2-32-200, ItemsID.Obstacle));
    				break;
    			case 6:
    				this.CRitems.add(new Obstacle(frameWidth/2-32+200, frameHeight/2-32, ItemsID.Obstacle));
    				break;
    			case 7:
    				this.CRitems.add(new Obstacle(frameWidth/2-32-200, frameHeight/2-32, ItemsID.Obstacle));
    				break;
    			}
    			
    		}
//    		this.clapperRail.setX(clapperrail.getX());
//    		this.clapperRail.setY(clapperrail.getY());
    		
    	}else if(this.gameStatus == GameStatus.CRQUIZ) {
    		button_A.setText(quiz_CR.getQuestions().get(quiz_CR.getQuestionIndex()).getAnswers()[0]);
    		button_B.setText(quiz_CR.getQuestions().get(quiz_CR.getQuestionIndex()).getAnswers()[1]);
    		button_C.setText(quiz_CR.getQuestions().get(quiz_CR.getQuestionIndex()).getAnswers()[2]);
    		button_D.setText(quiz_CR.getQuestions().get(quiz_CR.getQuestionIndex()).getAnswers()[3]);
    		
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
			
			button_redknote.setVisible(false);
			button_clapperrail.setVisible(false);
			button_menu.setVisible(true);
			
			button_submit.setVisible(true);
			button_A.setVisible(true);
	    	button_B.setVisible(true);
	    	button_C.setVisible(true);
	    	button_D.setVisible(true);
			
			
			
		}else if(this.gameStatus == GameStatus.Menu) {
			button_menu.setVisible(false);
			button_redknote.setVisible(true);
			button_clapperrail.setVisible(true);
			
			button_submit.setEnabled(false);
			button_submit.setVisible(false);
	    	button_A.setVisible(false);
	    	button_B.setVisible(false);
	    	button_C.setVisible(false);
	    	button_D.setVisible(false);
	    	
//	    	group.clearSelection();
	    	
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
    		
    		pic_clapperRail = ImageIO.read(new File("images/birds/CR.png"));
    		pic_redKnot_mini = ImageIO.read(new File("images/birds/RN_mini.png"));
    		
    		pic_food = ImageIO.read(new File("images/projectile/Food.png"));
    		pic_RNFood = ImageIO.read(new File("images/projectile/RN_Food.png"));
    		pic_snake = ImageIO.read(new File("images/projectile/Snake.png"));
    		pic_obstacle = ImageIO.read(new File("images/projectile/Obstacles.png"));
    		pic_map = ImageIO.read(new File("images/Components/Map.png"));
    		pic_menu = ImageIO.read(new File("images/Sunny_BG.png"));
    		
    		
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
