package com.jiGM.EscapeDungeon.display.Players;

import java.awt.Color;
import java.awt.Font;
//import java.awt.GradientPaint;
import java.awt.Graphics2D;
//import java.awt.Paint;

import javax.swing.JPanel;

import com.jiGM.EscapeDungeon.display.Game;
import com.jiGM.EscapeDungeon.display.DungeonEntities.Containers.Chest;
import com.jiGM.EscapeDungeon.display.InventorySystem.Inventory;

public class Player extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
//	private static final Color color1 = Color.BLUE;
//	private static final Color color2 = Color.MAGENTA;
//	private static final Paint gradientColor = new GradientPaint(0, 0, color1, 200, 150, color2, true);
	private static Color playerColor = Color.RED;
	
	public int xPosition = 20;
	public int yPosition = 20;
	public double playerSpeed = 17.0;
	
	private boolean openedInventory = false;
	
	public static Font playerFont = new Font("Verdana", Font.BOLD, 14);
	
	public void paintComponent(Graphics2D g) {
		
		// Player Label
		g.setColor(Color.WHITE);
		g.setFont(playerFont);
		g.drawString("" + Game.usernameAnswer + " (you)", xPosition, yPosition + -3);
		
		// Player
		g.setPaint(playerColor);
		g.fillRect(xPosition, yPosition, 75, 75);
		
		// Player position calculation... (took a while to calculate player position to open chest)
		// Or is touching chest
		if (xPosition > 280 && xPosition < 415 && yPosition < 75 && yPosition > 45) {
			Chest.openChest(g);
		}
		
		repaint();
	}
	
	public void openInventory() {
		this.openedInventory = true;
		
		if (this.openedInventory == true) {
			Inventory.openInventory(true);
		}
	}
	
	public void updateMovement() {
		moveUp();
		moveDown();
		moveLeft();
		moveRight();
	}
	
	public void closeInventory() {
		this.openedInventory = false;
		
		if (this.openedInventory == false) {
			Inventory.closeInventory(true);
		}
	}
	
	public void moveUp() {
		yPosition -= playerSpeed;
	}
	
	public void moveDown() {
		yPosition += playerSpeed;
	}
	
	public void moveLeft() {
		xPosition -= playerSpeed;
	}
	
	public void moveRight() {
		xPosition += playerSpeed;
	}
	
	public void translate(int x, int y) {
		xPosition = x;
		yPosition = y;
	}
	
}
