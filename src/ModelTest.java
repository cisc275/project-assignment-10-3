import static org.junit.jupiter.api.Assertions.*;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

class ModelTest {
	Model model;
	RedKnot RK;
	ClapperRail CR;
	Map map = new Map(20,1);
	ArrayList<Items> items = new ArrayList<>();
	ArrayList<Items> CRitems = new ArrayList<>();
	ArrayList<Integer> background=new ArrayList<>();
	ScoreBoard sb = new ScoreBoard();
	StatusBar SB=new StatusBar(10,10);
	Quiz RK_Quiz;
	Quiz CR_Quiz;
	Boolean [] tutorialHitFlag;
	int tutorialLevel=1;
	
	public void setUp() {
		RK = new RedKnot(2);
		CR = new ClapperRail(5, 20);
		items.add(new Food(5,5,5,5, 10, ItemsID.PowerUp));
		items.add(new Food(6,6,6,6, 10, ItemsID.Fly));
		items.add(new Food(7,7,7,7,10,ItemsID.Snail));
		items.add(new Obstacle(7,7,7,7, 10, ItemsID.Plane));
		
		
		CRitems.add(new Food(5,5,5,5,10, ItemsID.Food));
		CRitems.add(new Food(6,6,6,6,10,ItemsID.Food));
		CRitems.add(new Food(7,7,7,7,10, ItemsID.Food));
		
		
		RK_Quiz=new Quiz("RNQuiz.txt");
		CR_Quiz=new Quiz("CRQuiz.txt");
		
		

		model = new Model(10000, 10000, RK, CR, map, items, CRitems, sb, SB, RK_Quiz, CR_Quiz, true, false, background, tutorialLevel, tutorialHitFlag );

	}
	
	@Test
	void UpdateLocationTest() {
		setUp();
		model.setGamestatus(GameStatus.RN);
		
		model.updateLocation();
		assertEquals(0,RK.getX());
		
		model.setGamestatus(GameStatus.CR);
		model.updateLocation();
		assertEquals(0,model.getScreenTime());
	}
	
	@Test
	void BirdOutOfBoundsTest() {
		setUp();
		RK.setX(-1);
		RK.setY(-1);
		model.birdOutOfBounds(RK);
		assertEquals(0,RK.getX());
		assertEquals(0,RK.getY());
		RK.setX(9999);
		RK.setY(9999);
		model.birdOutOfBounds(RK);
		
	}
	
	@Test 
	void itemsOutOfBoundsTest() {
		setUp();
		items.get(0).setX(500);
		model.itemsOutOfBounds(items.get(0));
		

		items.get(1).setX(-15);
		items.get(1).setLength(5);
		model.iterator=items.iterator();
		model.iterator.next();
		model.itemsOutOfBounds(items.get(1));
	}
	
	@Test
	void collisionRKTest() {
		setUp();
		model.iterator=items.iterator();
		model.iterator.next();
		model.collisionRK(items.get(0), RK);
		
		items.get(0).setX(130);
		items.get(0).setY(130);
		model.collisionRK(items.get(0), RK);
		model.iterator.next();
		items.get(1).setX(130);
		items.get(1).setY(130);
		model.collisionRK(items.get(1), RK);
		model.iterator.next();
		items.get(2).setX(130);
		items.get(2).setY(130);
		model.collisionRK(items.get(2), RK);
		
		
		
	}

	@Test
	void goodCollisionTest1() {
		setUp();
		model.iterator = items.iterator();
		model.iterator.next();
		//model.goodCollision(new Food(0,2,ItemsID.Food), RK);
		
		assertEquals(0,sb.getScore());
		//System.out.println(sb.getScore());
	}
	
	@Test
	void goodCollisionTest2() {
		setUp();
		model.iterator = CRitems.iterator();
		model.iterator.next();
		//assertEquals(true,model.goodCollision(new Food(5,20), CR));
	}
	
	@Test
	void screenTimeTest() {
		setUp();
		model.iterator = CRitems.iterator();
		model.iterator.next();
		model.setScreenTime(19);
		
		model.screenTime();
		//System.out.println(model.getScreenTime());
		assertEquals(20,model.getScreenTime());
		
		model.setScreenTime(100);
		model.screenTime();
	}
	
	
	@Test
	void geterTest() {
		setUp();
		model.getScoreBoard();
		model.getClapperrail();
		model.getMapRN();
		model.getGamestatus();
		model.getRedKnot();
		model.getQuiz_RN();
		model.getQuiz_CR();
		model.getBackground();
		model.getCRitems();
		model.getTutorialHitFlag();
		model.getTutorialLevel();
		model.getItems();
		model.getStatusBar();
		
	}
	
	
	

}
