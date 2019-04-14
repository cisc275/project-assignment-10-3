
public class PowerUp extends Items{
	

	private int width;
	private int length;
	private int x;
	private int y;
	private int xVel;
	private int yVel;
	
	public PowerUp(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Return: boolean 
	 * Description: return true if PowerUp collides with bird and calls exit();
	 */
	@Override
	public boolean beHit() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * Description: makes the food disappear on the screen
	 */
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

}
