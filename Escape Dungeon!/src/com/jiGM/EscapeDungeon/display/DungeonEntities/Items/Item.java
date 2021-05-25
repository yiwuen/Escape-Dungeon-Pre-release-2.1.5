package com.jiGM.EscapeDungeon.display.DungeonEntities.Items;

public class Item {
	
	public int itemID;
	
	public int bookID = 376480;
	public int keyID = 728631;
	
	private ItemManager riManager = new ItemManager();
	
	// Used for creating new instance of this class
	public Item() {
		this.itemID = 0;
	}
	
	// Used for creating new item
	public Item(int itemID) {
		this.itemID = itemID;
		if (this.itemID == bookID) { // change this to this.itemID or itemID if needed
			this.riManager.generateItem(bookID);
			System.out.println("Generated ID: " + bookID);
			int randomID = riManager.generateRandomID(riManager.randomNumber(420) + (riManager.randomNum - 1));
			System.out.println(randomID);
		}
		if (this.itemID == keyID) { // change itemID to this.itemID if needed
			this.riManager.generateItem(keyID);
			System.out.println("Generated ID: " + keyID);
		}
	}
	
	public int getItemID() {
		return itemID;
	}
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
}
