import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ModelTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void UpdateLocationTest() {
		fail("Not yet implemented");
	}
	
	@Test
	void powerUpTest() {
		ScoreBoard scoreboard = new ScoreBoard(0,0,0);
		Model m = new Model(scoreboard);
		
		m.powerUp();
		assertEquals(5,scoreboard.getScore());
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
	void badCollisionTest(){
		fail("Not yet implemented");
	}
	
	
	

}
