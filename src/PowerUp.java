public class PowerUp extends Items{
	

	private int width;
	private int length;
	private int x;
	private int y;
	private int xVel;
	private int yVel;
	private ItemsID itemID;
	
	public PowerUp(int x, int y, ItemsID itemID) {
		super(x, y, itemID);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Return: boolean 
	 * Description: return true if PowerUp collides with bird and calls exit();
	 */
	@Override
	public boolean beHit() {
		return false;
	}

	/*
	 * Description: makes the food disappear on the screen
	 */
	@Override
	public void exit() {
		
	}

}
