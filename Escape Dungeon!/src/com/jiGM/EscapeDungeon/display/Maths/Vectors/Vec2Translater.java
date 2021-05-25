package com.jiGM.EscapeDungeon.display.Maths.Vectors;

public class Vec2Translater {
	
	private Vector2 vec2;
	private double xTranslate, yTranslate;
	
	public Vec2Translater(Vector2 vec2, double xTranslate, double yTranslate) {
		this.vec2 = new Vector2();
		this.xTranslate = xTranslate;
		this.yTranslate = yTranslate;
		this.vec2.translate(this.xTranslate, this.yTranslate);
	}
	
}
