public class PowerUp extends Items{
	
	public PowerUp(int x, int y, int width, int length, ItemsID itemID) {
		super(x, y, width, length, itemID);
	}
	
	/**
	 *  return true if PowerUp collides with bird and calls exit();
	 */
	@Override
	public boolean beHit() {
		return false;
	}

	/*
	 * Description: makes the food disappear on the screen
	 */
	@Override
	public void exit() {}

}
