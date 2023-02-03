import java.lang.Math;   
public class Apple {
	
	private int x;
	private int y;
	
	
	public Apple() {
		
		moveApple();
		
		
	}

	public int getX() {
		return x*25;
	}
	public int getY() {
		return y*25;
	}
	public void moveApple() {
		x = (int) Math.floor(Math.random()*34)+2;
		y = (int) Math.floor(Math.random()*34)+2;
		System.out.println("{" + x + "," + y + ")");

	}
}
