
public class Obstacle extends Items{
	public Obstacle(int width, int length) {
		super(width, length);
		// TODO Auto-generated constructor stub
	}


	private int width;
	private int length;

	/*
	 * Return: Boolean
	 * Description: This method detects if the bird is get hit or not by the obstacle
	 * 				returns TRUE if hits,
	 * 						else FALSE
	 * 	
	 */
	public boolean beHit() {
		return false;
	}
	
	
	/*
	 * Description: THe method will be called if beHit() == TRUE;
	 * 
	 * Effects: Obstacles disappear from the screen
	 */
	public void exit() {
		
	}
}
