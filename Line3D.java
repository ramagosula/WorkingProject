package miniGame;

import java.awt.Color;
import java.awt.Graphics;

public class Line3D {
	private Point3D startPoint;
	private Point3D endPoint;
	private Camera3D camera;
	public Line3D(Point3D startPoint, Point3D endPoint, Camera3D camera){
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.camera = camera;
	}
	public void TranslateX(int translation){
		startPoint.changeX(translation);
		endPoint.changeX(translation);
	}
	public void TranslateY(int translation){
		startPoint.changeY(translation);
		endPoint.changeY(translation);
	}
	public void TranslateZ(int translation){
		startPoint.changeZ(translation);
		endPoint.changeZ(translation);
	}
	public void display(Graphics g){
		g.drawLine(camera.calcScreenX(startPoint),camera.calcScreenY(startPoint),camera.calcScreenX(endPoint),camera.calcScreenY(endPoint));
		
	}
	public void display(Graphics g, Color c){
		g.setColor(c);
		g.drawLine(camera.calcScreenX(startPoint),camera.calcScreenY(startPoint),camera.calcScreenX(endPoint),camera.calcScreenY(endPoint));
		
	}
	public double length(){
		return Math.sqrt(Math.pow(endPoint.getX() -startPoint.getX(), 2) +Math.pow(endPoint.getY() -startPoint.getY(), 2) +Math.pow(endPoint.getZ() -startPoint.getZ(), 2) );
	}
	public Point3D getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point3D startPoint) {
		this.startPoint = startPoint;
	}
	public Point3D getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point3D endPoint) {
		this.endPoint = endPoint;
	}
	public Camera3D getCamera() {
		return camera;
	}
	public void setCamera(Camera3D camera) {
		this.camera = camera;
	}
	
}
