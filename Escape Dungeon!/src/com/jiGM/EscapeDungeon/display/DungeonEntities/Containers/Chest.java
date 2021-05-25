package com.jiGM.EscapeDungeon.display.DungeonEntities.Containers;

import javax.swing.*;
import java.awt.*;

public class Chest extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private int x = 370;
	private int y = 85;
	private int chestLabelXPosition = 370;
	private int chestLabelYPosition = 65;
	
	@Override
	public void paintComponent(Graphics g) {
		
		// Chest
		g.setColor(new Color(156, 82, 39));
		g.fillRect(x, y, 45, 45);
		
		// Chest Label
		g.setColor(Color.WHITE);
		g.drawString("Chest", chestLabelXPosition, chestLabelYPosition);
		
		// Chest black outline part
		g.setColor(Color.BLACK);
		g.fillRect(x, y + 9, 45, 5);
		// White square in the middle of chest
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x + 19, y + 9, 7, 10);
		
	}
	
	public static void openChest(Graphics g) {
		
		// Chest Inside Container
		g.setColor(new Color(156, 82, 39));
		g.fillRect(200 + 85, 182, 214, 145);
		
		// Chest Top Label Inside
		g.setColor(Color.WHITE);
		g.drawString("Chest Items", 200 + 85, 172);
		
		// Chest Grids
		g.setColor(Color.WHITE);
		// 1
		g.drawRect(200 + 85, 183, 70, 70);
		// 2
		g.drawRect(200 + 71 + 85, 183, 70, 70);
		// 3
		g.drawRect(271 + 71 + 85, 183, 70, 70);
		// 4
		g.drawRect(200 + 85, 183 + 71, 70, 70);
		// 5
		g.drawRect(271 + 85, 254, 70, 70);
		// 6
		g.drawRect(271 + 71 + 85, 254, 70, 70);
	}
}
