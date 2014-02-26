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
		thetaX = Math.PI - Math.atan2(look.getY()-point.getY(),look.getX()-point.getX());
		betaX = alphaX +(look.getFovX())/2 - thetaX;
		if (betaX < 0)
			betaX = betaX%(2*Math.PI) + (2*Math.PI) ;
		betaX = betaX%(2*Math.PI);
		//System.out.printf("\nAlphaX: %f\nthetaX: %f\nbetaX: %f",alphaX,thetaX,betaX);
	}
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
