package com.jiGM.EscapeDungeon.display;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
//import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.jiGM.EscapeDungeon.display.DungeonEntities.Containers.Chest;
import com.jiGM.EscapeDungeon.display.DungeonEntities.Items.Item;
import com.jiGM.EscapeDungeon.display.Inputs.Mouse;
import com.jiGM.EscapeDungeon.display.Players.Player;
import com.jiGM.EscapeDungeon.display.Players.SecondPlayer;
import com.jiGM.EscapeDungeon.display.Users.UsernameBoard;
import com.jiGM.EscapeDungeon.display.World.Camera.Camera2D;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 850;
	private static final int HEIGHT = 620;
	private static final String gameTitle = "Escape Dungeon";
//	private BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private boolean running = false;
	
	private static float radius = 340f; // radius = 400f;
	public static float xLighting = 420f; // 420f
	public static float yLighting = 265f; // 265f
	private static float velX = 0.3f; // 0.3f
	private static float velY = 0.3f; // 0.3f
	
	public static long timer = 0;
	public static double delta = 0;
	
	private JFrame frame;
	private static JOptionPane gameDialog;
	private JMenuBar menuBar;
	private JMenuItem quitButton;
	
	private static Camera2D camera;
	private static Item item;
	
	public static String usernameAnswer;
	public static String secUserAnswer;
	
	private static Player player = new Player();
	private static SecondPlayer secondPlayer = new SecondPlayer();
	private static Chest chest = new Chest();
	
	private static UsernameBoard usernameBoard;
	
	private static Thread thread;
	
	public int mouseX = -1;
	public int mouseY = -1;
	
	private static Mouse mouse = new Mouse();
	
	// 0: success, 1: failed
	private int exitStatus = 0;
	private static int joinStatus = 0;
	
	// **REMINDER** stuff (like frame, menu, objects, etc) visible in constructor
	public Game() {
		this.frame = new JFrame();
		gameDialog = new JOptionPane();
		this.menuBar = new JMenuBar();
		this.quitButton = new JMenuItem("Quit Game");
		this.menuBar.add(quitButton);
        this.quitButton.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent event) {
        		int quitAnswer = JOptionPane.showConfirmDialog(gameDialog, "Are you sure you want to quit?");
        		if (quitAnswer == JOptionPane.YES_OPTION) {
        			exitStatus = 0;
    				System.exit(exitStatus);
    				System.out.println("You quited the game with exit status: " + exitStatus);
        		}
			}
        });
        
		JOptionPane.showMessageDialog(gameDialog,
				"Please read How to Play: \n"
				+ "- WASD to move around \n"
				+ "- Touch a container like chests \n"
				+ "	 to open them. \n"
				+ "- Do not click on the game screen as it \n"
				+ "  will freeze everything");
		
		usernameAnswer = JOptionPane.showInputDialog(gameDialog, "Please enter a username: (8 characters limit and no space) ");
		if (usernameAnswer != null) {
			if (usernameAnswer.length() > 8) {
				JOptionPane.showMessageDialog(gameDialog, "Typing a username over 8 characters could cause your username to display wrong.\n"
						+ "Either retype your username with 8 characters (or less) or continue.");
			}
		}
		secUserAnswer = JOptionPane.showInputDialog(gameDialog, "Enter username for second user: (8 character limit and no space) ");
		if (secUserAnswer.contains(" ") || secUserAnswer.isBlank() || secUserAnswer.isEmpty()) {
			JOptionPane.showMessageDialog(gameDialog, "Second player cannot contain nothing (or space) \n"
					+ "or it will not display (or display wrong)");
		}
		if (secUserAnswer != null) {
			if (secUserAnswer.length() > 8) {
				JOptionPane.showMessageDialog(gameDialog, "You can only have 8 characters in your username");
			}
		}
		
		if (usernameAnswer.contains(" ") || usernameAnswer.isBlank()) {
			JOptionPane.showMessageDialog(gameDialog, "Username cannot contain a space (or nothing).\n"
					+ "Continuing with a username with a space "
					+ "may result in game errors. \n");	
		}
		
		if (usernameAnswer.contains(secUserAnswer) && secUserAnswer.contains(usernameAnswer)) {
			JOptionPane.showMessageDialog(gameDialog, "Cannot have the same username as other player \n" + 
		"Continuing with this username '" + usernameAnswer + "' will cause errors.");
		}
		
		int dialogAnswer = JOptionPane.showConfirmDialog(gameDialog, "Ready to join the game? ");
		
		if (dialogAnswer == JOptionPane.YES_OPTION) {
//			System.out.println(usernameAnswer + " has joined the game");
//			joinStatus = 0;
//			System.out.println("Join status: " + joinStatus);
			
			try {
				System.out.println(usernameAnswer + " has joined the game");
				System.out.println(secUserAnswer + " has joined the game");
				joinStatus = 0;
				System.out.println("Join status: " + joinStatus);
			} catch (Exception e) {
				System.err.println("Unable to join game. Join status: " + joinStatus); // this means not good... :(
				getErrorMessage();
			}
			
			// This does nothing right now...
			if (joinStatus == 1 || joinStatus > 0) {
				System.exit(joinStatus);
			}
		}
		
		else if (dialogAnswer == JOptionPane.NO_OPTION) {
			System.out.println(usernameAnswer + " cannot connect or join the game");
			exitStatus = 0;
			System.out.println("Exit status: " + exitStatus);
			System.exit(exitStatus);
		}
		else if (dialogAnswer == JOptionPane.CANCEL_OPTION) {
			System.out.println(usernameAnswer + " cannot connect or join the game");
			exitStatus = 1;
			System.out.println("Exit status: " + exitStatus);
			System.exit(exitStatus);
		}
		
		Dimension winSize = new Dimension(WIDTH, HEIGHT);
		this.frame.setSize(winSize);
		this.frame.setBackground(Color.BLACK);
		this.frame.setJMenuBar(menuBar);
		
		this.frame.addMouseListener(mouse);
		this.frame.addMouseMotionListener(mouse);
	}
	
	public void start() {
		this.running = true;
		
		Game.thread = new Thread(this, "Game");
		Game.thread.start();
	}
	
	public void stop() {
		this.running = false;
		
		try {
			Game.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long startTime = System.currentTimeMillis();
//		long timer = 0;
//		long waitTimer = 1;
		final double ns = 1000000000.0 / 60.0;
//		double delta = 0;
		int fpsCount = 0;
		
		while (this.running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				lightingUpdate(false);
				render();
				delta--;
				fpsCount++;
			}
			
			if (System.currentTimeMillis() - startTime > 1000) {
//				timer += 2 - waitTimer;
				timer++;
				startTime += 1000;
				this.frame.setTitle(gameTitle + " | " + "Timer: " + timer + " seconds | " + fpsCount + " FPS");
				fpsCount = 0;
				exitStatus = 127;
			}
			if (timer == 60 || timer > 59) {
				// TODO convert seconds to one minute...
			}
		}
		
		stop();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Second user with absolute no control...
//		int xPosition = 19;
//		int yPosition = 485;
//		g.setColor(Color.BLUE);
//		g.fillRect(xPosition, yPosition, 75, 75);
//	
		// Second user tag...
//		g.setColor(Color.WHITE);
//		g.setFont(font);
//		g.drawString(secUserAnswer, xPosition, yPosition + 1);
		
		player.paintComponent(g);
		chest.paintComponent(g);
		secondPlayer.paint(g);
		
		renderLighting(true, g);
		
		g.dispose();
		bs.show();
	}
	
	public void renderLighting(boolean render, Graphics2D g) {
		if (render == true) {
			Point2D center = new Point2D.Float(player.xPosition, player.yPosition); // xLighting, yLighting or current
			float[] dist = {0.0f, 1.0f};
			Color[] colors1 = {new Color(1.0f, 1.0f, 1.0f, 1.0f), Color.BLACK};
			RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors1);
			g.setPaint(p);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .45f)); // last number, changes the lightness or darkness
			g.fillRect(0, 0, 800 * 2, 800 * 2);
		}
	}
	
	public void update() {
		player.updateMovement();
		usernameBoard.updateUsers(); // TODO may need to have...
	}
	
	public void lightingUpdate(boolean lightMove) {
		if (lightMove == true) {
			xLighting += velX;
			yLighting += velY;
			
			if (xLighting <= 0 || xLighting >= 640) {
				velX *= -1;
			}
			if (yLighting <= 0 || xLighting >= 480) {
				velY *= -1;
			}
		}
	}
	
	public String getErrorMessage() {
		joinStatus = joinStatus + 1;
		System.exit(joinStatus);
		return "Error message: Cannot join the game";
	}
	
	public int getX() {
		return this.mouseX;
	}
	
	public int getY() {
		return this.mouseY;
	}
	
	public static void resetGame() {
		timer = -1;
		
		player.xPosition = 15;
		player.yPosition = 15;
		
		secondPlayer.xPosition = 19;
		secondPlayer.yPosition = 485;
	}
	
	public static void main(String[] args) throws Exception, IOException {
		Game game = new Game();
		
		usernameBoard = new UsernameBoard();
		usernameBoard.sortUsernames();
		
		item = new Item();
		if (item.bookID == 376480) {
			item.getItemID();
		}
		if (item.keyID == 728631) {
			item.getItemID();
		}
		
		camera = new Camera2D(0, 0);
		camera.translate(player.xPosition, player.yPosition);
		
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setResizable(false);
		game.frame.add(game);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent event) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent event) {
				
				// Main player inputs (you)
				if (event.getKeyCode() == KeyEvent.VK_W) {
					player.moveUp();
					camera.translate(0, -player.yPosition);
				}
				if (event.getKeyCode() == KeyEvent.VK_S) {
					player.moveDown();
					camera.translate(0, player.yPosition);
				}
				if (event.getKeyCode() == KeyEvent.VK_A) {
					player.moveLeft();
					camera.translate(-player.xPosition, 0);
				}
				if (event.getKeyCode() == KeyEvent.VK_D) {
					player.moveRight();
					camera.translate(player.xPosition, 0);
				}
				if (event.getKeyCode() == KeyEvent.VK_E) {
					player.openInventory();
				}
				
				if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
					player.closeInventory();
				}
				
				if (event.getKeyCode() == KeyEvent.VK_Q) {
					int quitGame = JOptionPane.showConfirmDialog(gameDialog, "Are you sure you want to quit?");
					if (quitGame == JOptionPane.YES_OPTION) {
						System.exit(joinStatus);
					}
				}
				
				if (event.getKeyCode() == KeyEvent.VK_R) {
					System.out.println("Reseted game.");
					resetGame();
				}
				
				// Second player inputs
				if (event.getKeyCode() == KeyEvent.VK_UP) {
					secondPlayer.moveUp();
				}
				if (event.getKeyCode() == KeyEvent.VK_DOWN) {
					secondPlayer.moveDown();
				}
				if (event.getKeyCode() == KeyEvent.VK_LEFT) {
					secondPlayer.moveLeft();
				}
				if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
					secondPlayer.moveRight();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent event) {
				camera.resetCamera(true);
			}
			
		});
		
		game.start();
	}
}
