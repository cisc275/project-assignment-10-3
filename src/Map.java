public class Map {
	
	//private final int WIDTH = 64;
	//private final int LENGTH = 64;
	private int x;//for current location on map 
	private final int y = 20;
	private int status;
	private int status_Y = 30;
	
	// constructor
	public Map(int x, int status) {
		this.x = x;
		this.status = status;
	}
	
	// getters and setters
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
	
	public int getStatus_Y() {
		return status_Y;
	}
	public void setStatus_Y(int status_Y) {
		this.status_Y = status_Y;
	}		
}
