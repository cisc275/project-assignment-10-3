import java.awt.Color;
import java.awt.Graphics;
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
	private final int frameWidth = 500;// TBD
    private final int frameHeight = 500;
    
    private ClapperRail clapperRail;
    private RedKnot redKnot;
    private ScoreBoard scoreboardRN = new ScoreBoard();
    private Map mapRN = new Map();
    ArrayList<Items> items = new ArrayList<>();
    Iterator<Items> iterator;
    
    private int itemSpawnCounter = 0;
    Random random;
    
    BufferedImage pic_redKnot;
    BufferedImage pic_food;
    BufferedImage pic_map;
    
    JFrame frame;
    
    JButton button_clapperrail;
    JButton button_redknote;
    
    GameStatus gameStatus = GameStatus.Menu;
    
    public View() {
    	
    	button_redknote = new JButton("Red Knot");  
    	button_redknote.setBackground(Color.GRAY);
    	button_redknote.setOpaque(true);
    	button_redknote.setActionCommand("redKnot");
    	
    	button_clapperrail = new JButton("Clapper Rail");  
    	button_clapperrail.setBackground(Color.GRAY);
    	button_clapperrail.setOpaque(true);
    	button_clapperrail.setActionCommand("clapperRail");
    	this.add(button_redknote);
    	this.add(button_clapperrail);
    	
    	
    	random = new Random();
    	redKnot = new RedKnot(frameHeight/2-32);
    	frame = new JFrame();
    	createBirdsImage();
    	createFoodRedKnotImage();
    	createMapRedKnotImage();
    	//frame.setContentPane(this);
    	
    	frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);	
    }
    
    
    
    public RedKnot getRedKnot() {
		return redKnot;
	}
	public void setRedKnot(RedKnot redKnot) {
		this.redKnot = redKnot;
	}


	public ArrayList<Items> getItems() {
		return items;
	}
	public void setItems(ArrayList<Items> items) {
		this.items = items;
	}

	public Map getMapRN() {
		return mapRN;
	}



	public void paintComponent(Graphics g) {
		if(this.gameStatus == GameStatus.RN) {
			iterator = items.iterator();
	    	while(iterator.hasNext()) {
	    		
	    		Items tempItem = iterator.next();
	    		g.drawImage(pic_food, tempItem.getX(), tempItem.getY(), Color.GRAY, this);
	    	}
	    	g.drawImage(pic_redKnot, redKnot.getX(), redKnot.getY(), Color.GRAY, this);
		}
    	
    	
    	
    }
    
    //repaint();
    //update birds location, items location, and score(status)
    public void update(RedKnot redKnot, GameStatus gameStatus) {
    	this.gameStatus = gameStatus;
    	if(this.gameStatus == GameStatus.RN) {
    		itemSpawnCounter++;
        	if(itemSpawnCounter >= 10) {
        		itemSpawnCounter = 0;
        		items.add(new Food(frameWidth, random.nextInt(frameHeight)));
        	}
        	this.redKnot.setX(redKnot.getX());
        	this.redKnot.setY(redKnot.getY());
    	}
    	
    	
    	frame.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    
    //
    //Create Images 
    //
    
    public void createBirdsImage() {
    	try {
    		pic_redKnot = ImageIO.read(new File("images/birds/Bird.png"));
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
    	
    }
    
    public void createMapRedKnotImage() {
    	try {
    		pic_map = ImageIO.read(new File("images/Components/Map.png"));
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    
    
    

}
