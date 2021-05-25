package com.jiGM.EscapeDungeon.display.Users;

import java.util.ArrayList;
import java.util.Collections;

import com.jiGM.EscapeDungeon.display.Game;

public class UsernameBoard {
	
	public ArrayList<String> usernamesBoard = new ArrayList<String>();
	public String username = Game.usernameAnswer;
	public String secUsername = Game.secUserAnswer;
	
	public void sortUsernames() {
		updateUsers();
		
		Collections.sort(usernamesBoard);
		
		for (String i : usernamesBoard) {
			System.out.println(i);
		}
	}
	
	// Updates users everytime a new user joins
	public void updateUsers() {
		usernamesBoard.add(username + " on the board");
		usernamesBoard.add(secUsername + " on the board");
	}
}
