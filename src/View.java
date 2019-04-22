import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final int frameWidth = screenSize.width;
    private final int frameHeight = screenSize.height;
    
    private ClapperRail clapperRail;
    private RedKnot redKnot;

    private Map mapRN = new Map(frameWidth-80);
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
    
    BufferedImage pic_redKnot;
    BufferedImage pic_clapperRail;
    BufferedImage pic_food;
    BufferedImage pic_map;
    BufferedImage pic_obstacle;
    
    JFrame frame;
    
    JButton button_clapperrail;
    JButton button_redknote;
    JButton button_menu;
    
    GameStatus gameStatus = GameStatus.Menu;
    
    
    
    
    public View() {
    	//Buttons
    	button_redknote = new JButton("Red Knot");  
    	button_redknote.setBackground(Color.GRAY);
    	button_redknote.setOpaque(true
    			);
    	button_redknote.setActionCommand("redKnot");
    	
    	button_clapperrail = new JButton("Clapper Rail");  
    	button_clapperrail.setBackground(Color.GRAY);
    	button_clapperrail.setOpaque(true);
    	button_clapperrail.setActionCommand("clapperRail");
    	
    	button_menu = new JButton("Menu");
    	button_menu.setBackground(Color.GRAY);
    	button_menu.setOpaque(true);
    	button_menu.setActionCommand("menu");
    	
    	this.add(button_redknote);
    	this.add(button_clapperrail);
    	//this.add(button_menu);

    	random = new Random();
    	
    	//Create Birds
    	redKnot = new RedKnot(frameHeight/2-32);
    	clapperRail = new ClapperRail(frameWidth/2-32, frameHeight/2-32);
    	
    	frame = new JFrame();
    	
    	//Create Images
    	createBirdsImage();
    	createFoodRedKnotImage();
    	createMapRedKnotImage();
    	createObstacleRedKnotImage();

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
	
	
	public void paintComponent(Graphics g) {
		
		if(this.gameStatus == GameStatus.RN) {
			iterator = items.iterator();
			
	    	while(iterator.hasNext()) {
	    		
	    		Items tempItem = iterator.next();
	    		g.drawImage(pic_food, tempItem.getX(), tempItem.getY(), Color.GRAY, this);
	    	}
	    	g.drawImage(pic_map, mapRN.getX(), mapRN.getY(), Color.GRAY, this);
	    	g.drawImage(pic_redKnot, redKnot.getX(), redKnot.getY(), Color.GRAY, this);
	    	
	    	g.setColor(Color.WHITE);
	    	g.drawRect(scoreBoard.getX(), scoreBoard.getY(), scoreBoard.getLength(), scoreBoard.getWidth());
	    	g.fillRect(scoreBoard.getX(), scoreBoard.getY(), scoreBoard.getLength(), scoreBoard.getWidth());
	    	g.setColor(Color.BLACK);
	    	g.drawString("Score: " + scoreBoard.getScore(), scoreBoard.getX()+5, scoreBoard.getY()+20);
	    	//g.drawLine(440, 20, mapRN.getStatus(), mapRN.getStatus_Y());
	    	//g.drawLine(440, 20, 490, 70);
	    	
	    	
		}else if(this.gameStatus == GameStatus.CR) {
			iterator = CRitems.iterator();
			while(iterator.hasNext()) {
				Items tempItem = iterator.next();
				if(tempItem.getItemID() == ItemsID.Food) {
					g.drawImage(pic_food, tempItem.getX(), tempItem.getY(), Color.GRAY, this);
				}else {
					g.drawImage(pic_obstacle, tempItem.getX(), tempItem.getY(), Color.GRAY, this);
				}
				
			}
			g.drawImage(pic_clapperRail, clapperRail.getX(), clapperRail.getY(), Color.GRAY, this);
			
			g.drawRect(statusBar.getX(), statusBar.getY(), statusBar.getLength(), statusBar.getWidth());
			g.setColor(Color.PINK);
			g.fillRect(statusBar.getX(), statusBar.getY(), statusBar.getStatus(), statusBar.getWidth());
			
		}else if(this.gameStatus == GameStatus.CRQUIZ) {
			
		}else if(this.gameStatus == GameStatus.RNQUIZ) {
			
		}
    	
    	
    	
    }
    
    //repaint();
    //update birds location, items location, and score(status)
    public void update(RedKnot redKnot, ClapperRail clapperrail, Map mapRN, GameStatus gameStatus, ScoreBoard scoreBoard, ArrayList<Items> items, ArrayList<Items> CRitems) {
    	this.gameStatus = gameStatus;
    	this.items = items;
    	this.CRitems = CRitems;
    	
    	if(this.gameStatus == GameStatus.RN) {
    		
    		itemSpawnCounter++;
        	if(itemSpawnCounter >= 10) {
        		itemSpawnCounter = 0;
        		this.items.add(new Food(frameWidth, random.nextInt(frameHeight), ItemsID.Food));
        	}
        	
        	this.scoreBoard.setScore(scoreBoard.getScore());
        	this.redKnot.setX(redKnot.getX());
        	this.redKnot.setY(redKnot.getY());
        	/*
        	 * Map Location 
        	 */
        	//this.mapRN.setStatus(mapRN.getStatus());
        	//this.mapRN.setStatus_Y(mapRN.getStatus_Y());
        	
        	//System.out.println(mapRN.updateLocation());
        	
    	}else if(this.gameStatus == GameStatus.CR) {
    		if(this.CRitems.size() == 0) {
    			switch(random.nextInt(8)) {
    			case 0:
    				this.CRitems.add(new Food(frameWidth/2-32, frameHeight/2-32+100, ItemsID.Food));
    				break;
    			case 1:
    				this.CRitems.add(new Food(frameWidth/2-32, frameHeight/2-32-100, ItemsID.Food));
    				break;
    			case 2:
    				this.CRitems.add(new Food(frameWidth/2-32+100, frameHeight/2-32, ItemsID.Food));
    				break;
    			case 3:
    				this.CRitems.add(new Food(frameWidth/2-32-100, frameHeight/2-32, ItemsID.Food));
    				break;
    			case 4:
    				this.CRitems.add(new Obstacle(frameWidth/2-32, frameHeight/2-32+100, ItemsID.Obstacle));
    				break;
    			case 5:
    				this.CRitems.add(new Obstacle(frameWidth/2-32, frameHeight/2-32-100, ItemsID.Obstacle));
    				break;
    			case 6:
    				this.CRitems.add(new Obstacle(frameWidth/2-32+100, frameHeight/2-32, ItemsID.Obstacle));
    				break;
    			case 7:
    				this.CRitems.add(new Obstacle(frameWidth/2-32-100, frameHeight/2-32, ItemsID.Obstacle));
    				break;
    			}
    			
    		}
    		this.clapperRail.setX(clapperrail.getX());
    		this.clapperRail.setY(clapperrail.getY());
    		
    	}else if(this.gameStatus == GameStatus.CRQUIZ) {
			
		}else if(this.gameStatus == GameStatus.RNQUIZ) {
			
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
    
    public void createBirdsImage() {
    	try {
    		pic_redKnot = ImageIO.read(new File("images/birds/RN.png"));
    		pic_clapperRail = ImageIO.read(new File("images/birds/CR.png"));
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void createPowerUpImage() {
    	
    }
    
    public void createFoodClapperRailImage() {
    	
    }
    
    public void createFoodRedKnotImage() {
    	try {
    		pic_food = ImageIO.read(new File("images/projectile/Food.png"));
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void createObstacleClapperRailImage() {
    	
    }
    public void createObstacleRedKnotImage() {
    	try {
    		pic_obstacle = ImageIO.read(new File("images/projectile/Obstacles.png"));
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void createMapRedKnotImage() {
    	try {
    		pic_map = ImageIO.read(new File("images/Components/Map.png"));
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    }

}
