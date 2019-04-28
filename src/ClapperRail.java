import java.awt.Rectangle;

public class ClapperRail {
	private int x;
	private int y;
	private final int length = 32;
	private final int width = 32;
	private int xVel = 0;
	private int yVel = 0;
	
	/*
	 * Constructor
	 */
	public ClapperRail(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxVel() {
		return xVel;
	}

	public void setxVel(int xVel) {
		this.xVel = xVel;
	}

	public int getyVel() {
		return yVel;
	}

	public void setyVel(int yVel) {
		this.yVel = yVel;
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}
	public Rectangle bounds() {
		return (new Rectangle(x,y,32,32));
	}	
}
