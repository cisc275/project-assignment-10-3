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
	ScoreBoard sb = new ScoreBoard();
	
	public void setUp() {
		RK = new RedKnot(2);
		CR = new ClapperRail(5, 20);
		items.add(new Food(5,5,ItemsID.Food));
		items.add(new Food(6,6,ItemsID.Food));
		items.add(new Food(7,7,ItemsID.Food));
		
		CRitems.add(new Food(5,5,ItemsID.Food));
		CRitems.add(new Food(6,6,ItemsID.Food));
		CRitems.add(new Food(7,7,ItemsID.Food));

		model = new Model(10000, 10000, RK, CR, map, items, CRitems, sb, null);

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
	void goodCollisionTest1() {
		setUp();
		model.iterator = items.iterator();
		model.iterator.next();
		//model.goodCollision(new Food(0,2,ItemsID.Food), RK);
		
		assertEquals(1,sb.getScore());
		
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
		System.out.println(model.getScreenTime());
		assertEquals(0,model.getScreenTime());
	}
	
	@Test
	void geterTest() {
		setUp();
		model.getScoreBoard();
		model.getClapperrail();
		model.getMapRN();
		model.getGamestatus();
		model.getRedKnot();
		
	}
	
	
	

}
