package miniGame;

public class Camera3D extends Camera {
	private double alphaY;
	private double betaY;
	private double thetaY;
	
	public Camera3D(Looker look, double alphaX, double thetaX, double betaX, double alphaY, double thetaY, double betaY) {
		super(look, alphaX, thetaX, betaX);
		this.alphaY = alphaY;
		this.betaY = betaY;
		this.thetaY = thetaY;
	}
	public Camera3D(Looker look){
		super(look);
	}
	public void update(Point3D point){
		super.update(point);
		alphaY = super.getLook().getAngleY();
		
		//VERSION 1: This version is consistent, but only if seen straight on
//		thetaY = Math.PI - Math.atan2(super.getLook().getZ()-point.getZ(),super.getLook().getY()-point.getY());
		
		//VERSION 2: This version is warped-looking, but works from all angles
		double dist = Math.sqrt((super.getLook().getY() - point.getY())*(super.getLook().getY() - point.getY()) + (super.getLook().getX() - point.getX())*(super.getLook().getX() - point.getX()));
		double yPrime = dist*Math.tan(Math.PI/4 + super.getLook().getAngleX());
//		double zPrime = dist*Math.tan(Math.PI/4 + super.getLook().getAngleY());
//		thetaY = Math.PI - Math.atan2(super.getLook().getZ()-point.getZ(),yPrime);
		thetaY = Math.PI - Math.atan2(super.getLook().getZ()-point.getZ(),yPrime);
		
		betaY = alphaY +(super.getLook().getFovY())/2 - thetaY;
		if (betaY < 0)
			betaY = betaY%(2*Math.PI) + (2*Math.PI) ;
		betaY = betaY%(2*Math.PI);
		//System.out.printf("\nAlphaY: %f\nthetaY: %f\nbetaY: %f",alphaY,thetaY,betaY);
	}
	public boolean pointVisibleY(Point3D point){
		update(point);
		boolean pointVisible = false;
		//System.out.println("BetaY = " + betaY);
		//System.out.println("FovY = " + super.getLook().getFovY());
		//TODO HERE LIES THE PROBLEM
		//ALSO alphaX isn't calculating properly
		//Beta is never less than fovY 
		if (betaY > 0 && betaY < super.getLook().getFovY()){
            pointVisible = true;
		}
		if (betaY > 0 && betaY < 5.37){
            pointVisible = true;
		}
        return pointVisible;
	}
	public int calcScreenY(Point3D point){
		update(point);		
		double distanceY = Math.sqrt((super.getLook().getY() - point.getY())*(super.getLook().getY() - point.getY()) + (super.getLook().getZ() - point.getZ())*(super.getLook().getZ() - point.getZ()));
		double fY = distanceY*Math.sin(super.getLook().getFovY() - betaY);
		double gY = distanceY*Math.sin(betaY);
		int displayDistY = (int) (GameRunner.WIDTH*2*(gY/(3*(fY + gY))));
		return displayDistY;
	}
	public int calcScreenYDebug(Point3D point){
		update(point);
		double distanceY = Math.sqrt((super.getLook().getY() - point.getY())*(super.getLook().getY() - point.getY()) + (super.getLook().getZ() - point.getZ())*(super.getLook().getZ() - point.getZ()));
		double fY = distanceY*Math.sin(super.getLook().getFovY() - betaY);
		double gY = distanceY*Math.sin(betaY);
		int displayDistY = (int) (GameRunner.WIDTH*2*(gY/(3*(fY + gY))));
		System.out.printf("DistanceY:%2f\nfY:%2f\ngY:%2f\nDisplayDistY:%2d\n",distanceY,fY,gY,displayDistY);

		return displayDistY;
	}
	public double calcDistance(Point3D point){
		double distance = Math.sqrt((super.getLook().getY() - point.getY())*(super.getLook().getY() - point.getY()) + (super.getLook().getZ() - point.getZ())*(super.getLook().getZ() - point.getZ()));
			return distance;
	}

}
