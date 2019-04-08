
public class Food extends Items{

	public Food(int width, int length) {
		super(width, length);
		
	}
	/*
	 * Return: boolean 
	 * Description: return true if food collides with bird and calls exit();
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
