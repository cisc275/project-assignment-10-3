import java.awt.Rectangle;

public class RedKnot{
	private int x = 0;
	private int y;
	private int length = 10;
	private int width = 10;
	private int xVel = 0;
	private int yVel = 0;
	
	
	/*
	 * Constructor 
	 */
	public RedKnot(int y) {
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


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
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
	
	public Rectangle bounds() {
		return (new Rectangle(x,y,180,180));
	}

}
