package com.jiGM.EscapeDungeon.display.DungeonEntities;

import com.jiGM.EscapeDungeon.display.DungeonEntities.Containers.Chest;
import com.jiGM.EscapeDungeon.display.DungeonEntities.Items.Item;
import com.jiGM.EscapeDungeon.display.DungeonEntities.Items.ItemManager;

public class DungeonManager {
	
	// TODO add code here to manager all dungeon stuff
	// uses for managing stuff in dungeons
	
	public Item item;
	public ItemManager itemManager;
	public Chest chest;
	
	public DungeonManager(Item item, ItemManager itemManager, Chest chest) {
		this.item = item;
		this.itemManager = itemManager;
		this.chest = chest;
	}
}
