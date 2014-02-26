package miniGame;
import java.awt.Color;
import java.awt.Graphics;
public class Point {
/*
 * class Point():
    def __init__(self,_x,_y):
        self.x = _x
        self.y = _y
    def display(self):
        pygame.draw.circle(screen,black,(self.x,self.y),4)
    def display3D(self):
        pygame.draw.circle(fp.image,black,(self.x,self.y),4)
    def update(self,_x,_y):
        self.x = _x
        self.y = _y
 */
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
