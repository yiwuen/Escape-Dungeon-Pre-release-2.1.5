package com.jiGM.EscapeDungeon.display.InventorySystem;

import java.util.ArrayList;

public class Inventory {
	
	// Inventory still in progress... Does not work yet!
	
	public ArrayList<String> inventory = new ArrayList<String>();
	
	public static boolean openedInventory = false;
	
	public static void openInventory(boolean open) {
		openedInventory = true;
		
		if (open == true) {
			if (openedInventory == true) {
				System.out.println("Opened inventory");
			}
		}
	}
	
	public static void closeInventory(boolean close) {
		openedInventory = false;
		
		if (close == true) {
			if (openedInventory == false) {
				System.out.println("Closed inventory");
			}	
		}
	}
}
