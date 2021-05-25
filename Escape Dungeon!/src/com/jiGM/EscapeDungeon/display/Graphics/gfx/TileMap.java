package com.jiGM.EscapeDungeon.display.Graphics.gfx;

import java.awt.Graphics;

public class TileMap {
	
	// UNUSED... yet or UNFINISHED
	
	private int[][] tileMap = new int[10][10];
	
	public TileMap() {
		tileMap[0][0] = 0;
	}
	
	public TileMap(Graphics g) {
		for (int row = 0; row < tileMap.length; row++) {
			for (int col = 0; col < 10; col++) {
				tileMap[row][col] = 5;
				g.drawRect(10, 10, 55, 55);
			}
		}
	}
	
}
