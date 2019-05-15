
import java.io.Serializable;

public class StatusBar implements Serializable{
	public static int x;
	public static int y;
	public final static int length = 64;
	private int status = 10;
	
	public StatusBar(int x, int y) {
		StatusBar.x = x;
		StatusBar.y = y;

	}

	//getters & setters
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
