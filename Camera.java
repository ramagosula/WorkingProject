package miniGame;

public class Camera {
	private Looker look;
	private double alphaX;
	private double betaX;
	private double thetaX;
	
	public Camera(Looker look){
		this(look,0,0,0);
	}
	public Camera(Looker look, double alphaX, double thetaX, double betaX){
		this.look = look;
		this.alphaX = alphaX;
		this.thetaX = thetaX;
		this.betaX = betaX;
	}
	public void update(Point point){
		alphaX = look.getAngleX();
		//VERSION 1: This version is consistent, but only if seen straight on
		thetaX = Math.PI - Math.atan2(look.getY() -point.getY(),look.getX()-point.getX());
 
		//VERSION 2: This version is warped-looking, but works from all angles
//		double r = Math.sqrt((look.getY() - point.getY())*(look.getY() - point.getY()) + (look.getZ() - ((Point3D)(point)).getZ())*(look.getZ() - ((Point3D)(point)).getZ()));
//		thetaX = Math.PI - Math.atan2(r,look.getX() - point.getX());
		
		//thetaX = calcThetaX(point);
		betaX = alphaX +(look.getFovX())/2 - thetaX;
		if (betaX < 0)
			betaX = betaX%(2*Math.PI) + (2*Math.PI) ;
		betaX = betaX%(2*Math.PI);
		//System.out.printf("\nAlphaX: %f\nthetaX: %f\nbetaX: %f",alphaX,thetaX,betaX);
	}
	//Doesn't seem to work
//	private double calcThetaX(Point point){
//		double theta = 0;
//		if(point.getX() - look.getX() > 0){
//			if(point.getY() - look.getY() > 0){
//				theta = Math.atan2(point.getY()-look.getY(),point.getX()- look.getX());
//			}else if(point.getY() - look.getY() < 0){
//				theta = Math.PI - Math.atan2(point.getY()-look.getY(),look.getX()-point.getX());
//			}else{
//				theta = 0;
//			}
//		}else if(point.getX() - look.getX() < 0){
//			if(point.getY() - look.getY() > 0){
//				theta = Math.PI + Math.atan2(look.getY() - point.getY(),look.getX()-point.getX());
//			}else if(point.getY() - look.getY() < 0){
//				theta = Math.PI*2 - Math.atan2(look.getY() - point.getY(),point.getX()-look.getX());
//			}else{
//				theta = Math.PI;
//			}
//		}else{
//			if(point.getY() - look.getY() > 0){
//				theta = Math.PI/2;
//			}else{
//				theta = Math.PI*3/2;
//			}
//		}
//		return theta;
//		
//	}
	public boolean pointVisibleX(Point point){
		update(point);
		boolean pointVisible = false;
		if (betaX > 0 & betaX < look.getFovX()){
            pointVisible = true;
		}
        return pointVisible;
	}
	public int calcScreenX(Point point){
		update(point);
		double distanceX = Math.sqrt((look.getX() - point.getX())*(look.getX() - point.getX()) + (look.getY() - point.getY())*(look.getY() - point.getY()));
		double fX = distanceX*Math.sin(look.getFovX() - betaX);
		double gX = distanceX*Math.sin(betaX);
		int displayDistX = (int) (GameRunner.WIDTH*(gX/(fX + gX)));
		return displayDistX;
	}
	public Looker getLook() {
		return look;
	}
	public void setLook(Looker look) {
		this.look = look;
	}
	public double getAlphaX() {
		return alphaX;
	}
	public void setAlphaX(double alphaX) {
		this.alphaX = alphaX;
	}
	public double getBetaX() {
		return betaX;
	}
	public void setBetaX(double betaX) {
		this.betaX = betaX;
	}
	public double getThetaX() {
		return thetaX;
	}
	public void setThetaX(double thetaX) {
		this.thetaX = thetaX;
	}
	
}
