import java.awt.Rectangle;

public class Food extends Items{

	
	private int width;
	private int length;
	private int x;
	private int y;
	private int xVel;
	private int yVel;
	
	public Food(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
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
	
	public Rectangle bounds() {
		return (new Rectangle(x,y,32,32));
	}
	

}

