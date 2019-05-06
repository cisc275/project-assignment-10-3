import java.awt.Rectangle;

import javax.imageio.ImageIO;

public abstract class Items {
	private int width;
	private int length;
	private int x;
	private int y;
	private int xVel = -8;
	private int yVel = 0;
	private ItemsID itemID;
	
	public Items(int x, int y, int width, int length, ItemsID itemID) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.length = length;
		this.itemID = itemID;
	}
	
	
	
	public ItemsID getItemID() {
		return itemID;
	}



	public int getWidth() {
		return width;
	}



	public void setWidth(int width) {
		this.width = width;
	}



	public int getLength() {
		return length;
	}



	public void setLength(int length) {
		this.length = length;
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
	
	public Rectangle bounds() {
		return (new Rectangle(x,y,getLength(),getWidth()));
	}



	public abstract boolean beHit();
	public abstract void exit();
}
