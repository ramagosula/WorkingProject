package miniGame;

public class Point3D extends Point{
	private int z;
	public Point3D(int x, int y, int z){
		super( x, y);
		this.z = z;
	}
	public Point3D(){
		super();
		z = 0;
	}
	
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public void changeZ(int change){
		z += change;
	}
	public void setPosition(int x, int y, int z){
		super.setPosition(x, y);
		this.z = z;
	}
	
}
