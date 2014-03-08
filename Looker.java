package miniGame;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Graphics;

public class Looker {

	public static final int STOP = 0;
	public static final int RIGHT = 1;
	public static final int LEFT = -1;
	public static final int FORWARD = 1;
	public static final int BACK = -1;
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int CLOCKWISE = -1;
	public static final int CCLOCKWISE = 1;

	private int x;
	private int y;
	private int z;
	private double angleX;
	private double angleY;
	private double fovX; // field of view for calculating X
	private double fovY;
	private int maxXSpeed;
	private int maxYSpeed;
	private int maxZSpeed;
	private double xSpeed;
	private double ySpeed;
	private double zSpeed;
	private int xDirection;
	private int yDirection;
	private int zDirection;
	private int xRotDirection;
	private int yRotDirection;
	private boolean cameraLocked;// Determines whether or not movement is
									// dependent on camera
	private double accel;// acceleration

	public Looker() {
		this(0, 0, 0);
	}

	public Looker(int x, int y, int z) {
		this(x, y, z, Math.PI / 2, Math.PI / 2, Math.PI / 2, Math.PI / 3);
	}

	public Looker(int x, int y, int z, double angleX, double angleY,
			double fovX, double fovY) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.angleX = angleX;
		this.angleX = angleY;
		this.fovX = fovX;
		this.fovY = fovY;
		this.maxXSpeed = 7;
		this.maxYSpeed = 7;
		this.maxZSpeed = 7;
		this.xSpeed = 0;
		this.ySpeed = 0;
		this.zSpeed = 0;
		this.xDirection = Looker.STOP;
		this.yDirection = Looker.STOP;
		this.zDirection = Looker.STOP;
		this.xRotDirection = Looker.STOP;
		this.yRotDirection = Looker.STOP;
		this.accel = .8;
		this.cameraLocked = true;

	}

	public void paintArrow(Graphics g) {
		g.setColor(Color.GREEN);
		int[] xPoints = { x, (int) (x + 2000 * Math.cos(angleX + fovX / 2)),
				(int) (x + 2000 * Math.cos(angleX - fovX / 2)) };
		int[] yPoints = { y, (int) (y - 2000 * Math.sin(angleX + fovX / 2)),
				(int) (y - 2000 * Math.sin(angleX - fovX / 2)) };
		g.fillPolygon(new Polygon(xPoints, yPoints, 3));
		g.setColor(Color.BLUE);
		g.drawLine(x, y, (int) (x + 100 * Math.cos(angleX)),
				(int) (y - 100 * Math.sin(angleX)));
		g.drawLine(x, y, (int) (x + 50 * Math.cos(angleX + fovX / 2)),
				(int) (y - 50 * Math.sin(angleX + fovX / 2)));
		g.drawLine(x, y, (int) (x + 50 * Math.cos(angleX - fovX / 2)),
				(int) (y - 50 * Math.sin(angleX - fovX / 2)));
	}

	public void update() {
		movementCalc();
		calcXRotation();
		calcYRotation();
		move();
		if (angleX < 0)
			angleX = angleX % (2 * Math.PI) + (2 * Math.PI);
		angleX = angleX % (2 * Math.PI);
		if (angleY < 0)
			angleY = angleY % (2 * Math.PI) + (2 * Math.PI);
		angleY = angleY % (2 * Math.PI);
		// statusUpdate();

	}

	public void statusUpdate() {
		System.out.printf("\n(X,Y,Z) = (%d,%d,%d)\n", x, y, z);
	}

	public void calcXRotation() {
		if (xRotDirection == Looker.CLOCKWISE) {
			this.rotateX(Math.PI / 32);
		} else if (xRotDirection == Looker.CCLOCKWISE) {
			this.rotateX(-Math.PI / 32);
		}
	}

	public void calcYRotation() {
		if (yRotDirection == Looker.CLOCKWISE) {
			this.rotateY(Math.PI / 32);
		} else if (yRotDirection == Looker.CCLOCKWISE) {
			this.rotateY(-Math.PI / 32);
		}
	}

	public void rotateX(double rotation) {
		angleX += rotation;
	}

	public void rotateY(double rotation) {
		angleY += rotation;
	}

	public void move(){
		if(cameraLocked){
			//Finish to include z motion
			x += xSpeed*Math.sin(angleX) - ySpeed*Math.cos(angleX);
			y += ySpeed*Math.sin(angleX) + xSpeed*Math.cos(angleX) + zSpeed*Math.sin(angleY);
			z += zSpeed*Math.cos(angleY) - ySpeed*Math.sin(angleY);
			//Rotation Mode (glitchy)
			//angleX = Math.PI - Math.atan2(y - 100 ,x + 400);;
		}else{
			x += xSpeed;
			y += ySpeed;
			z += zSpeed;
		}
	}

	public void movementCalc() {
		moveX();
		moveY();
		moveZ();
	}

	public void moveX() {
		if (xDirection == Looker.STOP) {
			if (xSpeed > 1) {
				xSpeed -= accel;
			} else if (xSpeed < -1) {
				xSpeed += accel;
			} else {
				xSpeed = 0;
			}
		} else {
			if (xDirection == Looker.RIGHT) {
				if (xSpeed < maxXSpeed) {
					xSpeed += accel;
				}
			} else if (xDirection == Looker.LEFT) {
				if (-xSpeed < maxXSpeed) {
					xSpeed -= accel;
				}
			}
		}
	}

	public void moveY() {
		if (yDirection == Looker.STOP) {
			if (ySpeed > 1) {
				ySpeed -= accel;
			} else if (ySpeed < -1) {
				ySpeed += accel;
			} else {
				ySpeed = 0;
			}
		} else {
			if (yDirection == Looker.BACK) {
				if (ySpeed < maxYSpeed) {
					ySpeed += accel;
				}
			} else if (yDirection == Looker.FORWARD) {
				if (-ySpeed < maxYSpeed) {
					ySpeed -= accel;
				}
			}
		}
	}

	public void moveZ() {
		if (zDirection == Looker.STOP) {
			if (zSpeed > 1) {
				zSpeed -= accel;
			} else if (zSpeed < -1) {
				zSpeed += accel;
			} else {
				zSpeed = 0;
			}
		} else {
			if (zDirection == Looker.DOWN) {
				if (-zSpeed < maxZSpeed) {
					zSpeed -= accel;
				}
			} else if (zDirection == Looker.UP) {
				if (zSpeed < maxZSpeed) {
					zSpeed += accel;
				}
			}
		}
	}

	// Getters and Setters
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

	public double getFovX() {
		return fovX;
	}

	public void setFovX(double fovX) {
		this.fovX = fovX;
	}

	public int getMaxXSpeed() {
		return maxXSpeed;
	}

	public void setMaxXSpeed(int maxXSpeed) {
		this.maxXSpeed = maxXSpeed;
	}

	public int getMaxYSpeed() {
		return maxYSpeed;
	}

	public void setMaxYSpeed(int maxYSpeed) {
		this.maxYSpeed = maxYSpeed;
	}

	public double getxSpeed() {
		return xSpeed;
	}

	public double getySpeed() {
		return ySpeed;
	}

	public int getxDirection() {
		return xDirection;
	}

	public void setxDirection(int xDirection) {
		this.xDirection = xDirection;
	}

	public int getyDirection() {
		return yDirection;
	}

	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public double getAngleX() {
		return angleX;
	}

	public void setAngleX(double angleX) {
		this.angleX = angleX;
	}

	public double getAngleY() {
		return angleY;
	}

	public void setAngleY(double angleY) {
		this.angleY = angleY;
	}

	public double getFovY() {
		return fovY;
	}

	public void setFovY(double fovY) {
		this.fovY = fovY;
	}

	public int getMaxZSpeed() {
		return maxZSpeed;
	}

	public void setMaxZSpeed(int maxZSpeed) {
		this.maxZSpeed = maxZSpeed;
	}

	public double getzSpeed() {
		return zSpeed;
	}

	public int getzDirection() {
		return zDirection;
	}

	public void setzDirection(int zDirection) {
		this.zDirection = zDirection;
	}

	public double getAccel() {
		return accel;
	}

	public void setAccel(double accel) {
		this.accel = accel;
	}

	public int getxRotDirection() {
		return xRotDirection;
	}

	public void setxRotDirection(int xRotDirection) {
		this.xRotDirection = xRotDirection;
	}

	public int getyRotDirection() {
		return yRotDirection;
	}

	public void setyRotDirection(int yRotDirection) {
		this.yRotDirection = yRotDirection;
	}

	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	public void setzSpeed(double zSpeed) {
		this.zSpeed = zSpeed;
	}

	public boolean isCameraLocked() {
		return cameraLocked;
	}

	public void setCameraLocked(boolean cameraLocked) {
		this.cameraLocked = cameraLocked;
	}

	// End Getters and Setters
}
