import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ModelTest {
	View view;
	Model model;
	
	public void setUp() {
		view = new View();
		model = new Model(view.getRedKnot(), view.getClapperRail(), view.getMapRN(), view.getItems(), view.getCRitems(), view.getScoreBoard());
	}
	
	@Test
	void UpdateLocationTest() {
		fail("Not yet implemented");
	}

	@Test
	void endGameTest() {
		fail("Not yet implemented");
	}
	
	@Test
	void goodCollisionTest() {
		fail("Not yet implemented");
	}
	
	@Test
	void screenTimeTest() {
		setUp();
		model.setScreenTime(10);
		model.screenTime();
		assertEquals(11,model.getScreenTime());
	}
	
	
	

}
