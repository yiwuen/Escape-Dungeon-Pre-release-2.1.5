package com.jiGM.EscapeDungeon.display.Users;

public class UserBoardManager implements IUserBoard {
	
	public static int maxUsers = 5;
	
	private static UsernameBoard usernameBoard = new UsernameBoard();
	
	public static void getMaxUsers(int userIndex) {
		usernameBoard.usernamesBoard.remove(userIndex);
	}

	@Override
	public void kickPlayer() {
		if (maxUsers > 5) {
			getMaxUsers(1);
		}
	}

	@Override
	public void resetPlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		
	}
}
