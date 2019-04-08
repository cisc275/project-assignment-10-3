
public abstract class Items {
	private int width;
	private int length;
	
	public Items(int width, int length) {
		this.width = width;
		this.length = length;
	}
	
	public abstract boolean beHit();
	public abstract void exit();
}
