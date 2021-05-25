package com.jiGM.EscapeDungeon.display.Players;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

import com.jiGM.EscapeDungeon.display.Game;
import com.jiGM.EscapeDungeon.display.DungeonEntities.Containers.Chest;

public class SecondPlayer extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Font playerFont = new Font("Verdana", Font.BOLD, 14);
	
	public int xPosition = 19;
	public int yPosition = 485;
	private double playerSpeed = 17.0;
	
	public void paint(Graphics g) {
		
		// Second player display
		g.setColor(Color.BLUE);
		g.fillRect(xPosition, yPosition, 75, 75);
		
		// Second player tag...
		g.setColor(Color.WHITE);
		g.setFont(playerFont);
		g.drawString(Game.secUserAnswer, xPosition, yPosition + 1);
		
		// Player position calculation... (took a while to calculate player position to open chest)
		if (xPosition > 280 && xPosition < 415 && yPosition < 75 && yPosition > 45) {
			Chest.openChest(g);
		}
		if (Game.secUserAnswer.contains(" ") || Game.secUserAnswer.isBlank()) {
			hidePlayer(true);
		}
		
		repaint();
	}
	
	public void hidePlayer(boolean hide) {
		if (hide == true) {
			xPosition -= 10000;
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
	
}
