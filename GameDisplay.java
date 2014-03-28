/*
 * Creator Rama Gosula
 * Controls:
 * Forward = w
 * back = s
 * left = a
 * right = d
 * up = e
 * down = q
 * 
 * It glitches out when you rotate, but if you want:
 * clockwise = ->
 * counterclockwise = <-
 * 
 * Feel free to mess around with adding objects to the scene do this below
 * ctrl-f "#" to find where to do that
 * 
 * It will take to to
 * -here
 * -where you add objects
 * -where you can create new methods for adding objects
 * 
 */
package miniGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.sound.sampled.Line;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameDisplay extends JPanel implements KeyListener {
	private static boolean LEFTDOWN;
	private static boolean RIGHTDOWN;
	private static boolean BACKDOWN;
	private static boolean FORWARDDOWN;
	private static boolean UPDOWN;
	private static boolean DOWNDOWN;
	private static boolean CLOCKWISEDOWNX;
	private static boolean COUNTERCLOCKWISEDOWNX;
	private static boolean CLOCKWISEDOWNY;
	private static boolean COUNTERCLOCKWISEDOWNY;
	private static Looker look = new Looker(300, 600, 0);
	private static Looker look2 = new Looker(250, 600, 0);
	private static Camera3D camera = new Camera3D(look);
	private static Camera3D camera2 = new Camera3D(look2);
	
	private Random rand = new Random();
	private ArrayList<Point> pointList = new ArrayList<Point>();
	private ArrayList<Point3D> pointList3D = new ArrayList<Point3D>();
	private ArrayList<Point> pointDispList = new ArrayList<Point>();
	private ArrayList<Line3D> lineList = new ArrayList<Line3D>();
	private ArrayList<Line3D> axes = new ArrayList<Line3D>();
	
	public GameDisplay() {// Initialization of panel
		super();
		LEFTDOWN = false;
		RIGHTDOWN = false;
		BACKDOWN = false;
		FORWARDDOWN = false;
		UPDOWN = false;
		DOWNDOWN = false;
		CLOCKWISEDOWNX = false;
		CLOCKWISEDOWNY = false;
		COUNTERCLOCKWISEDOWNX = false;
		COUNTERCLOCKWISEDOWNY = false;
		
		setBackground(Color.WHITE);
		
		/*
		 * Here is where you insert the shapes#
		 */
		
//		addSquare(300, 200);
//		addSquare(400, 100);
		
//		addSquare3D(100,400,0);
//		addBuilding(400, 100, 0, 5);
//		addRandomBox(0, 0, 0, 10,50);
//		addRandomBox(0, 0, 0, 5,10);
//		addRandomBox(-1000, 0, 0, 10,50);


		//addBox(-700, 0, 0, 10,50);
//		addSphere(500, 0, 0, 50, 100);
//		addSpiral(-500,0,0);
//		addCone(0, 0, 0);
//		addLineCone(0, 0, 0);
//		addLineSpiral(-500, 0, 0);
//		addLineSphere(500,0,0,50,100);
//		addGrid(0, 0, 0, 50, 50, 10);
		
//		addGrid(0, 0, 0, 50, 50, 10);
		addAxes(0, 0, 0) ;
//		pointList3D.add(new Point3D());

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Camera camera = new Camera(look);
		look.update();
		look2.setxDirection(look.getxDirection());
		look2.setyDirection(look.getyDirection());
		look2.setzDirection(look.getzDirection());
		look2.setxRotDirection(look.getxRotDirection());
		look2.setyRotDirection(look.getyRotDirection());
		look2.setCameraLocked(look.isCameraLocked());
		look2.update();
		//look.paintArrow(g);
		
		pointDispList.clear();
		
		//2D
//		for (int i = 0; i < pointList.size(); i++) {
//			pointList.get(i).display(g);
//			if (camera.pointVisibleX(pointList.get(i))){
//				pointDispList.add(new Point(camera.calcScreenX(pointList.get(i)),50));
//				
//			}
//		}	
		//flyMovements();
		//3D
		for (int i = 0; i < pointList3D.size(); i++) {
			//pointList3D.get(i).display(g);
			if (camera.pointVisibleX(pointList3D.get(i)) && camera.pointVisibleY(pointList3D.get(i))){
				pointDispList.add(new Point(camera.calcScreenX(pointList3D.get(i)),camera.calcScreenY(pointList3D.get(i))));
			}
		}	

		
//		int moveLeft = -500;
//
//		drawPlane(moveLeft, 0, 0, 100, camera, g,new Color(255,255,255));
//		drawPlane(moveLeft, 0, 100, 100, camera, g,new Color(255,255,255));
//		drawVPlane(moveLeft, 0, 0, 100, camera, g,new Color(240,240,255));
//		drawVPlane(moveLeft+ 100, 0, 0, 100, camera, g,new Color(240,240,255));
		
		//second view
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, GameRunner.WIDTH, GameRunner.HEIGHT);
		g.fillRect(0, 0, GameRunner.WIDTH, 100);
		for (int i = 0; i < pointDispList.size(); i++){
			pointDispList.get(i).display(g);
		}
		
		Line3D.setCamera(camera);
		
		for (int i = 0; i < lineList.size(); i++){
			lineList.get(i).display(g,Color.blue);
		}
		drawAxes(g); 
		
//		drawAxes(300, 300, 20, camera, g);//This draws the coordinate axes
		

		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(GameRunner.WIDTH, 0, GameRunner.WIDTH, GameRunner.HEIGHT);
		g.fillRect(GameRunner.WIDTH, 0, GameRunner.WIDTH, 100);
		
		pointDispList.clear();
		
		for (int i = 0; i < pointList3D.size(); i++) {
			//pointList3D.get(i).display(g);
			if(i != 0){
				if (camera2.pointVisibleX(pointList3D.get(i)) && camera2.pointVisibleY(pointList3D.get(i))){
					pointDispList.add(new Point(camera2.calcScreenX(pointList3D.get(i))+ GameRunner.WIDTH,camera2.calcScreenY(pointList3D.get(i))));
				}
			}else{
				if (camera2.pointVisibleX(pointList3D.get(i)) && camera2.pointVisibleY(pointList3D.get(i))){
					pointDispList.add(new Point(camera2.calcScreenX(pointList3D.get(i))+ GameRunner.WIDTH,camera2.calcScreenY(pointList3D.get(i))));
				}
			}
		}	
		for (int i = 0; i < pointDispList.size(); i++){
			pointDispList.get(i).display(g);
		}
		
		Line3D.setCamera(camera2);
		
		for (int i = 0; i < lineList.size(); i++){
			lineList.get(i).display(g,Color.blue,GameRunner.WIDTH);
		}
		drawAxes(g,GameRunner.WIDTH); 


		
//		drawLines(g);
		
	} // end method paintComponent

	
	//#
	public void addSquare(int x, int y, int size){
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				pointList.add(new Point(x + i * GameRunner.WIDTH / size, y + j* GameRunner.HEIGHT / size));
			}
		}
	}
	public void addSquare(int x, int y){
		addSquare(x,y,10);
	}
	public void addSquare3D(int x, int y,int z, int size){
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				pointList3D.add(new Point3D(x + i * GameRunner.WIDTH / size, y + j* GameRunner.HEIGHT / size,z));
			}
		}
	}
	public void addSquare3D(int x, int y, int z){
		addSquare3D(x,y,z,10);
	}
	public void addGrid(int x, int y, int z, int length, int width, int spacing){
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				pointList3D.add(new Point3D(x + i * (GameRunner.WIDTH / spacing), y + j* (GameRunner.HEIGHT / spacing),z));
			}
		}
	}
	public void addBuilding(int x, int y, int z, int floors){
		for (int i = 0; i < floors; i++){
			addSquare3D(x,y,z + 10*i);
		}
	}
	public void addBox(int x, int y, int z, int size, int spacing){
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++){
					pointList3D.add(new Point3D(x + i*spacing , y + j*spacing,z + k*spacing));
				}
			}
		}
	}
	public void addLineBox(int x, int y, int z, int size, int spacing){
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++){
					Point3D point = new Point3D(x + i*spacing , y + j*spacing,z + k*spacing);
					lineList.add(new Line3D(point,point));

				}
			}
		}
	}
	public void addRandomBox(int x, int y, int z, int size, int spacing){
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++){
					pointList3D.add(new Point3D(x + i*spacing + rand.nextInt(spacing/2), y + j*spacing +rand.nextInt(spacing/2),z + k*spacing + rand.nextInt(spacing/2)));
				}
			}
		}
	}
	public void addSphere(int x, int y, int z, int radius, int approximation){
		for (double i = 0; i < Math.PI; i += approximation*.001 ){
			for (double j = 0; j < 2*Math.PI; j += approximation*.001){
				int xNew = (int) (x + radius*Math.sin(i)*Math.sin(j));
				int yNew = (int) (y + radius*Math.sin(i)*Math.cos(j));
				int zNew = (int) (z + radius*Math.cos(i));
				pointList3D.add(new Point3D(xNew,yNew,zNew));
			}
		}
	}
	public void addSpiral(int x, int y, int z, double density){
		for(double t = 0; t < 100; t += density){
			int xNew = (int) (x + 100*Math.cos(t));
			int yNew = (int) (y + 100*Math.sin(t));
			int zNew = (int) (z + 10*t);
			pointList3D.add(new Point3D(xNew,yNew,zNew));
		}
	}
	public void addSpiral(int x, int y, int z){
		addSpiral(x, y, z, .2);
	}
	public void addCone(int x, int y, int z){
		for(double t = -100; t < 100; t += .2){
			int xNew = (int) (x + t*Math.cos(t));
			int yNew = (int) (y + t*Math.sin(t));
			int zNew = (int) (z + t);
			pointList3D.add(new Point3D(xNew,yNew,zNew));
		}
	}
	public void addLineGrid(int x, int y, int z, int length, int width, int spacing){
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				Point3D startPoint = new Point3D(x + i * (GameRunner.WIDTH / spacing), y + j* (GameRunner.HEIGHT / spacing),z);
				Point3D endPoint = new Point3D(x + i * (GameRunner.WIDTH / spacing), y + j* (GameRunner.HEIGHT / spacing),z + 0);
				lineList.add(new Line3D(startPoint,endPoint));
			}
		}
	}
	public void addLineGridVertical1(int x, int y, int z, int length, int width, int spacing){
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				Point3D startPoint = new Point3D(x, y + j* (GameRunner.HEIGHT / spacing),z + i*(GameRunner.WIDTH / spacing));
				lineList.add(new Line3D(startPoint,startPoint));
			}
		}
	}
	public void addLineGridRandom(int x, int y, int z, int length, int width, int spacing){
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				Point3D startPoint = new Point3D(x + i * (GameRunner.WIDTH / spacing), y + j* (GameRunner.HEIGHT / spacing),z);
				Point3D endPoint = new Point3D(x + i * (GameRunner.WIDTH / spacing), y + j* (GameRunner.HEIGHT / spacing),(int)(z + 100*rand.nextDouble()));
				lineList.add(new Line3D(startPoint,endPoint));
			}
		}
	}
	public void addLineCone(int x, int y, int z){
		double increment = .2;
		for(double t = -100; t < 100 - increment; t += increment){
			int xNew = (int) (x + t*Math.cos(t));
			int yNew = (int) (y + t*Math.sin(t));
			int zNew = (int) (z + t);
			
			Point3D startPoint = new Point3D(xNew,yNew,zNew);
			double t2 = t + increment;
			xNew = (int) (x + (t2)*Math.cos(t2));
			yNew = (int) (y + (t2)*Math.sin(t2));
			zNew = (int) (z + t2);
			
			Point3D endPoint = new Point3D(xNew,yNew,zNew);
			
			lineList.add(new Line3D(startPoint, endPoint));
		}
	}
	public void addLineSpiral(int x, int y, int z){
		double increment = .2;
		for(double t = 0; t < 100 - increment; t += increment){
			int xNew = (int) (x + 100*Math.cos(t));
			int yNew = (int) (y + 100*Math.sin(t));
			int zNew = (int) (z + 10*t);
			
			Point3D startPoint = new Point3D(xNew,yNew,zNew);
			
			double t2 = t + increment;
			xNew = (int) (x + 100*Math.cos(t2));
			yNew = (int) (y + 100*Math.sin(t2));
			zNew = (int) (z + 10*t2);
			Point3D endPoint = new Point3D(xNew,yNew,zNew);
			lineList.add(new Line3D(startPoint, endPoint));
		}
	}
	public void addLineSphere(int x, int y, int z, int radius, int approximation){
		double increment = approximation*.001;
		for (double i = 0; i < Math.PI; i += approximation*.001 ){
			for (double j = 0; j < 2*Math.PI ; j += increment){
				int xNew = (int) (x + radius*Math.sin(i)*Math.sin(j));
				int yNew = (int) (y + radius*Math.sin(i)*Math.cos(j));
				int zNew = (int) (z + radius*Math.cos(i));
				
				Point3D startPoint = new Point3D(xNew,yNew,zNew);
				double t = j + increment;
				
				xNew = (int) (x + radius*Math.sin(i)*Math.sin(t));
				yNew = (int) (y + radius*Math.sin(i)*Math.cos(t));
				zNew = (int) (z + radius*Math.cos(i));
				Point3D endPoint = new Point3D(xNew,yNew,zNew);

				lineList.add(new Line3D(startPoint, endPoint));
			}
		}
	}
	public void drawAxes(int x, int y, int z, Camera3D camera, Graphics g){
		Point3D center = new Point3D(x, y, z);
		Point3D line2 = new Point3D(x,y,z+50);
		g.setColor(Color.BLUE);
		g.drawLine(camera.calcScreenX(center),camera.calcScreenY(center),camera.calcScreenX(line2),camera.calcScreenY(line2));
		g.setColor(Color.RED);
		line2.setPosition(x+50, y, z);
		g.drawLine(camera.calcScreenX(center),camera.calcScreenY(center),camera.calcScreenX(line2),camera.calcScreenY(line2));
		g.setColor(Color.GREEN);
		line2.setPosition(x, y+50, z);
		g.drawLine(camera.calcScreenX(center),camera.calcScreenY(center),camera.calcScreenX(line2),camera.calcScreenY(line2));

	}
	public void drawAxes(Graphics g){
		drawAxes(g,0);
	}
	public void drawAxes(Graphics g,int offset){
		axes.get(0).display(g, Color.BLUE,offset);
		axes.get(1).display(g,Color.RED,offset);
		axes.get(2).display(g,Color.GREEN,offset);
	}
	public void addAxes(int x, int y, int z){
		Point3D center = new Point3D(x, y, z);
		axes.add(new Line3D(center, new Point3D(x,y,z+50)));
		axes.add(new Line3D(center, new Point3D(x+ 50,y,z)));
		axes.add(new Line3D(center, new Point3D(x,y+50,z)));
	}
	
	public void drawPlane(int x, int y, int z,int size, Camera3D camera, Graphics g, Color c){
		Point3D leftTop = new Point3D(x,y,z);
		Point3D leftBottom = new Point3D(x,y + size,z);
		Point3D rightTop = new Point3D(x+ size,y,z);
		Point3D rightBottom = new Point3D(x+size,y+size,z);
		Point3D points[] = {leftTop,leftBottom,rightBottom,rightTop};
		int xPoints[] = new int[4];
		int yPoints[] = new int[4];
		for (int i = 0; i < 4; i++){
			xPoints[i] = camera.calcScreenX(points[i]);
			yPoints[i] = camera.calcScreenY(points[i]);
		}
		g.setColor(c);
		g.fillPolygon(xPoints, yPoints, 4);
		
	}

	public void drawVPlane(int x, int y, int z,int size, Camera3D camera, Graphics g, Color c){
		Point3D leftTop = new Point3D(x,y,z);
		Point3D leftBottom = new Point3D(x,y + size,z);
		Point3D rightTop = new Point3D(x,y,z +size);
		Point3D rightBottom = new Point3D(x,y+size,z+size);
		Point3D points[] = {leftTop,leftBottom,rightBottom,rightTop};
		int xPoints[] = new int[4];
		int yPoints[] = new int[4];
		for (int i = 0; i < 4; i++){
			xPoints[i] = camera.calcScreenX(points[i]);
			yPoints[i] = camera.calcScreenY(points[i]);
		}
		g.setColor(c);
		g.fillPolygon(xPoints, yPoints, 4);
		
	}
	public void flyMovements(){
		for (int i = 0; i < pointList3D.size(); i++) {
			pointList3D.get(i).changeX(rand.nextInt(5)-2);
			pointList3D.get(i).changeY(rand.nextInt(5)-2);
			pointList3D.get(i).changeZ(rand.nextInt(5)-2);
		}	
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			look.setCameraLocked(!look.isCameraLocked());
			break;
		case KeyEvent.VK_W:
			//forward
			FORWARDDOWN = true;
			if (BACKDOWN){
				look.setyDirection(Looker.STOP);
			}else{
				look.setyDirection(Looker.FORWARD);
			}
			//System.out.println("W - forward");
			break;

		case KeyEvent.VK_S:
			//back
			BACKDOWN = true;
			if (FORWARDDOWN){
				look.setyDirection(Looker.STOP);
			}else{
				look.setyDirection(Looker.BACK);
			}
			//System.out.println("S - back");
			break;
		case KeyEvent.VK_A:
			//Left
			LEFTDOWN = true;
			if (RIGHTDOWN){
				look.setxDirection(Looker.STOP);
			}else{
				look.setxDirection(Looker.LEFT);
			}
			//System.out.println("A - left");
			break;
		case KeyEvent.VK_D:
			RIGHTDOWN = true;
			if (LEFTDOWN){
				look.setxDirection(Looker.STOP);
			}else{
				look.setxDirection(Looker.RIGHT);
			}
			//System.out.println("D - right");
			break;
		case KeyEvent.VK_E:
			UPDOWN = true;
			if (DOWNDOWN){
				look.setzDirection(Looker.STOP);
			}else{
				look.setzDirection(Looker.UP);
			}
			//System.out.println("E - up");
			break;
		case KeyEvent.VK_Q:
			DOWNDOWN = true;
			if (UPDOWN){
				look.setzDirection(Looker.STOP);
			}else{
				look.setzDirection(Looker.DOWN);
			}
			//System.out.println("Q - down");
			break;
		case KeyEvent.VK_UP:
			COUNTERCLOCKWISEDOWNY = true;
			if (CLOCKWISEDOWNY){
				look.setyRotDirection(Looker.STOP);
			}else{
				look.setyRotDirection(Looker.CLOCKWISE);
			}
			//System.out.println("UP - pitch forward");
			break;
		case KeyEvent.VK_DOWN:
			CLOCKWISEDOWNY = true;
			if (COUNTERCLOCKWISEDOWNY){
				look.setyRotDirection(Looker.STOP);
			}else{
				look.setyRotDirection(Looker.CCLOCKWISE);
			}
			//System.out.println("DOWN - pitch back");
			break;
		case KeyEvent.VK_LEFT:
			COUNTERCLOCKWISEDOWNX = true;
			if (CLOCKWISEDOWNX){
				look.setxRotDirection(Looker.STOP);
			}else{
				look.setxRotDirection(Looker.CLOCKWISE);
			}
			//System.out.println("LEFT - counterclockwise");
			break;
		case KeyEvent.VK_RIGHT:
			CLOCKWISEDOWNX = true;
			if (COUNTERCLOCKWISEDOWNX){
				look.setxRotDirection(Looker.STOP);
			}else{
				look.setxRotDirection(Looker.CCLOCKWISE);
			}
			//System.out.println("RIGHT - clockwise");
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			FORWARDDOWN = false;
			if (BACKDOWN){
				look.setyDirection(Looker.BACK);
			}else{
				look.setyDirection(Looker.STOP);
			}
			//System.out.println("W - forward");
			break;
		case KeyEvent.VK_S:
			BACKDOWN = false;
			if (FORWARDDOWN){
				look.setyDirection(Looker.FORWARD);
			}else{
				look.setyDirection(Looker.STOP);
			}
			//System.out.println("S - back");
			break;
		case KeyEvent.VK_A:
			LEFTDOWN = false;
			if (RIGHTDOWN){
				look.setxDirection(Looker.RIGHT);
			}else{
				look.setxDirection(Looker.STOP);
			}
			//System.out.println("A - left");
			break;
		case KeyEvent.VK_D:
			RIGHTDOWN = false;
			if (LEFTDOWN){
				look.setxDirection(Looker.LEFT);
			}else{
				look.setxDirection(Looker.STOP);
			}
			//System.out.println("D - right");
			break;
		case KeyEvent.VK_E:
			UPDOWN = false;
			if (DOWNDOWN){
				look.setzDirection(Looker.DOWN);
			}else{
				look.setzDirection(Looker.STOP);
			}
			//System.out.println("E - up");
			break;
		case KeyEvent.VK_Q:
			DOWNDOWN = false;
			if (UPDOWN){
				look.setzDirection(Looker.UP);
			}else{
				look.setzDirection(Looker.STOP);
			}
			//System.out.println("Q - down");
			break;
		case KeyEvent.VK_UP:
			COUNTERCLOCKWISEDOWNY = false;
			if (CLOCKWISEDOWNY){
				look.setyRotDirection(Looker.CLOCKWISE);
			}else{
				look.setyRotDirection(Looker.STOP);
			}
			//System.out.println("UP - pitch forward");
			break;
		case KeyEvent.VK_DOWN:
			CLOCKWISEDOWNY = false;
			if (COUNTERCLOCKWISEDOWNY){
				look.setyRotDirection(Looker.CCLOCKWISE);
			}else{
				look.setyRotDirection(Looker.STOP);
			}
			//System.out.println("DOWN - pitch back");
			break;
		case KeyEvent.VK_LEFT:
			COUNTERCLOCKWISEDOWNX = false;
			if (CLOCKWISEDOWNX){
				look.setxRotDirection(Looker.CLOCKWISE);
			}else{
				look.setxRotDirection(Looker.STOP);
			}
			//System.out.println("LEFT - counterclockwise");
			break;
		case KeyEvent.VK_RIGHT:
			CLOCKWISEDOWNX = false;
			if (COUNTERCLOCKWISEDOWNX){
				look.setxRotDirection(Looker.CCLOCKWISE);
			}else{
				look.setxRotDirection(Looker.STOP);
			}
			//System.out.println("RIGHT - clockwise");
			break;
		}
	}

}