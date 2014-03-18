package miniGame;
import java.awt.Color;
import java.awt.Graphics;
public class Point {

	private int x;
	private int y;
	
	public Point(int x, int y){
		this.x= x;
		this.y =y;
	}
	public Point(){
		x = 0;
		y = 0;
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
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void changeX(int change){
		x += change;
	}public void changeY(int change){
		y += change;
	}
	public void display(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 6, 6);
	}
	public void display(Graphics g, Color c){
		g.setColor(c);
		g.fillRect(x, y, 1, 1);
	}
}
