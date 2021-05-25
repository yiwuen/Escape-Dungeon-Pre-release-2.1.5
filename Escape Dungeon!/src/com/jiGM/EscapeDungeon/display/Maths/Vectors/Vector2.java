package com.jiGM.EscapeDungeon.display.Maths.Vectors;

public class Vector2 {
	
	public double x, y;
	
	public Vector2() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void translate(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	public boolean equals(Vector2 other) {
        return (this.x == other.x && this.y == other.y);
    }
	
}
