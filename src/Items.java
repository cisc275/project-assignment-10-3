import java.awt.Rectangle;

import javax.imageio.ImageIO;

public abstract class Items {
	private int width;
	private int length;
	private int x;
	private int y;
	public static final int X_VEL = -8;
	public static final int Y_VEL = 0;
	private ItemsID itemID;
	
	//constructor
	public Items(int x, int y, int width, int length, ItemsID itemID) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.length = length;
		this.itemID = itemID;
	}
	
	
	// getters and setters
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
	
	/**
	 *  created rectangle around the item (hitbox)
	 */
	public Rectangle bounds() {
		return (new Rectangle(x,y,getLength(),getWidth()));
	}

	//abstract methods to be implemented by subclasses
	public abstract boolean beHit();
	public abstract void exit();
}
