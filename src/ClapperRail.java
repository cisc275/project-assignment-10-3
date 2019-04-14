import java.awt.Rectangle;

public class ClapperRail {
	private int x;
	private int y;
	private int length;
	private int width;
	private int xVel;
	private int yVel;
	
	/*
	 * Constructor
	 */
	public ClapperRail() {
		
	}
	public Rectangle bounds() {
		return (new Rectangle(x,y,32,32));
	}
	
	
	
	
}
