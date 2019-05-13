public class StatusBar {
	private int x;
	private int y;
	private final int width = 300;
	private final int length = 32;
	private int status = 0;
	
	
	public StatusBar(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}

	public int getLength() {
		return length;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
