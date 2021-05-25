package com.jiGM.EscapeDungeon.display.DungeonEntities.Items;

import java.util.ArrayList;
import java.util.Random;

public class ItemManager {
	
	private Item item;
	public int randomID;
	public int randomNum;
	
	public ItemTypes itemTypes;
	
	public ArrayList<Integer> itemsList = new ArrayList<Integer>();
	
	public ItemManager() {
		this.item = null;
	}
	
	public ItemManager(String itemName) {
		if (itemName.equals("Book") || itemName.equals("Key")) {
			System.out.println(itemName);
		}
	}
	
	public ItemManager(String itemName, Item item) {
		this.item = item;
	}
	
	public void generateItem(int itemID) {
		this.item = new Item(itemID);
		this.itemsList.add(itemID);
//		System.out.println(itemID);
	}
	
	public int generateRandomID(int randomID) {
		this.randomID = randomID;
		Random random = new Random();
		int int_random = random.nextInt(randomID);
		System.out.println("Random ID: " + (randomID - 1) + int_random);
		return randomID;
	}
	
	public int randomNumber(int randomNum) {
		this.randomNum = randomNum;
		Random random = new Random();
		int int_random = random.nextInt(randomNum);
		return randomNum;
	}
	
}
