package com.jiGM.EscapeDungeon.display.World.Camera;

import com.jiGM.EscapeDungeon.display.Maths.Matrix;
import com.jiGM.EscapeDungeon.display.Maths.Vectors.Vector2;

public class Camera2D {
	
	public double xCam, yCam;
	private double xOffset, yOffset;
	
	private boolean reset = false;
	
	private Matrix matrix = new Matrix();
	private Vector2 vec2 = new Vector2(0, 0);
	
	public Camera2D(double x, double y) {
		this.xCam = x;
		this.yCam = y;
	}
	
	public void translate(double x, double y) {
		this.xCam += x;
		this.yCam += y;
		this.vec2.translate(this.xCam, this.yCam);
		this.matrix.addMatrix(5, 10);
	}
	
	public void resetCamera(boolean reset) {
		this.reset = true;
		if (reset == true || this.reset == true) {
			this.xCam = 0;
			this.yCam = 0;
		}
	}
	
	public double getX() {
		return this.xCam;
	}
	
	public double getY() {
		return this.yCam;
	}
	
	public void updateCamera(double xUpdate, double yUpdate) {
		this.xOffset = xUpdate / xCam - 2;
		this.yOffset = yUpdate / yCam - 2;
		this.translate(xCam + xOffset, yCam + yOffset);
	}

}
