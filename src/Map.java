
public class Map {
	private final int width = 64;
	private final int length = 64;
	private final int x = 430;//for current location on map 
	private final int y = 10;
	private int status = 0;// 0-100%
	
	
	
	
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
	public void updateLocation(int status) {
		
	}
	
	
	
	
}

