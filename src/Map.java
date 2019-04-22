
public class Map {
	private final int width = 64;
	private final int length = 64;
	private int x;//for current location on map 
	private final int y = 10;
	private int status = 440;// 0-50
	private int status_Y = 20;
	
	
	public Map(int x) {
		this.x = x;
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

	


	/*
	 * Parameter: 
	 * 			int status: the percentage of completeness
	 * 
	 * Description: this function calculate the current location based on the status;
	 */
	public int getStatus_Y() {
		return status_Y;
	}
	public void setStatus_Y(int status_Y) {
		this.status_Y = status_Y;
	}		
}
