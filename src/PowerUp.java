
public class PowerUp extends Items{

	public PowerUp(int width, int length) {
		super(width, length);
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
