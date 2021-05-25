package com.jiGM.EscapeDungeon.display.Maths;

public class Matrix {
	
	// not used.
	
	private int[][] matrixA = {
			{0, 0, 0, 0},
			{1, 1, 1, 1},
			{0, 0, 0, 0},
			{1, 1, 1, 1},
	};
	
	private int[][] matrixB = {
			{1, 1, 1, 1},
			{0, 0, 0, 0},
			{1, 1, 1, 1},
			{0, 0, 0, 0},
	};
	
	private int matrixC[][] = new int[4][4];
	
	public void addMatrix(int x, int y) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				matrixC[i][j] = (this.matrixA[i][j] + x) + (this.matrixB[i][j] + y);
			}
		}
	}
	
	public void removeMatrix() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				matrixC[i][j] = this.matrixA[i][j] - this.matrixB[i][j];
			}
		}
	}
	
}
