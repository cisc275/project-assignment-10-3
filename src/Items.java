
public abstract class Items {
	private int width;
	private int length;
	private int x;
	private int y;
	private int xVel;
	private int yVel;
	
	public Items() {
		
	}
	
	public abstract boolean beHit();
	public abstract void exit();
}
