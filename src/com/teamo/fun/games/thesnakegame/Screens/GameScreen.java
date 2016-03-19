package com.teamo.fun.games.thesnakegame.Screens;

import java.util.List;

import android.graphics.Color;

import com.teamo.fun.games.framework.Game;
import com.teamo.fun.games.framework.Graphics;
import com.teamo.fun.games.framework.Input.TouchEvent;
import com.teamo.fun.games.framework.Pixmap;
import com.teamo.fun.games.framework.Screen;
import com.teamo.fun.games.thesnakegame.Assets;
import com.teamo.fun.games.thesnakegame.Settings;
import com.teamo.fun.games.thesnakegame.World;
import com.teamo.fun.games.thesnakegame.Actors.Snake;
import com.teamo.fun.games.thesnakegame.Actors.SnakePart;
import com.teamo.fun.games.thesnakegame.Actors.Stain;

public class GameScreen extends Screen {
	
	enum GameState {
		Ready, Running, Paused, GameOver
	}
	
	GameState	state		= GameState.Ready;
	World		world;
	int			oldScore	= 0;
	String		score		= "0";
	
	public GameScreen(Game game) {
		super(game);
		world = new World();
	}
	
	@Override
	public void update(float deltaTime) {
		
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
		
	}
	
	private void updateReady(List<TouchEvent> touchEvents) {
		
		// touch anywhere to start!
		if (touchEvents.size() > 0)
			state = GameState.Running;
	}
	
	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			
			// pause button (top left corner)
			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x < 109 && event.y < 104) {
					if (Settings.soundEnabled)
						Assets.click.play(1);
					state = GameState.Paused;
					return;
				}
			}
			
			// left and right arrows
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (event.x < 109 && event.y > 696) {
					world.snake.turnLeft();
				}
				if (event.x > 371 && event.y > 696) {
					world.snake.turnRight();
				}
			}
		}
		
		world.update(deltaTime);
		if (world.gameOver) {
			if (Settings.soundEnabled)
				Assets.bitten.play(1);
			state = GameState.GameOver;
		}
		if (oldScore != world.score) {
			oldScore = world.score;
			score = "" + oldScore;
			if (Settings.soundEnabled)
				Assets.eat.play(1);
		}
	}
	
	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				
				// the width of the words
				if (event.x > 98 && event.x <= 382) {
					
					// height of "resume"
					if (event.y > 100 && event.y <= 154) {
						if (Settings.soundEnabled)
							Assets.click.play(1);
						state = GameState.Running;
						return;
					}
					// height of "quit"
					if (event.y > 154 && event.y < 208) {
						if (Settings.soundEnabled)
							Assets.click.play(1);
						game.setScreen(new MainMenuScreen(game));
						return;
					}
				}
			}
		}
	}
	
	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				
				// X button (to go back to main menu screen)
				if (event.x >= 185 && event.x <= 294 && event.y >= 200 && event.y <= 304) {
					if (Settings.soundEnabled)
						Assets.click.play(1);
					game.setScreen(new MainMenuScreen(game));
					return;
					
				}
			}
		}
	}
	
	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.background, 0, 0);
		drawWorld(world);
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();
		
		drawText(g, score, g.getWidth() / 2 - score.length() * 20 / 2, g.getHeight() - 42);
	}
	
	private void drawWorld(World world) {
		Graphics g = game.getGraphics();
		Snake snake = world.snake;
		SnakePart head = snake.parts.get(0);
		Stain stain = world.stain;
		
		Pixmap stainPixmap = null;
		if (stain.type == Stain.TYPE_1)
			stainPixmap = Assets.stain1;
		if (stain.type == Stain.TYPE_2)
			stainPixmap = Assets.stain2;
		if (stain.type == Stain.TYPE_3)
			stainPixmap = Assets.stain3;
		int x = stain.x * 32;
		int y = stain.y * 32;
		g.drawPixmap(stainPixmap, x, y);
		
		int len = snake.parts.size();
		for (int i = 1; i < len; i++) {
			SnakePart part = snake.parts.get(i);
			x = part.x * 32;
			y = part.y * 32;
			g.drawPixmap(Assets.tail, x, y);
		}
		
		Pixmap headPixmap = null;
		if (snake.direction == Snake.UP)
			headPixmap = Assets.headUp;
		if (snake.direction == Snake.LEFT)
			headPixmap = Assets.headLeft;
		if (snake.direction == Snake.DOWN)
			headPixmap = Assets.headDown;
		if (snake.direction == Snake.RIGHT)
			headPixmap = Assets.headRight;
		x = head.x * 32 + 16;
		y = head.y * 32 + 16;
		g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2, y - headPixmap.getHeight() / 2);
		
	}
	
	private void drawReadyUI() {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.ready, 23, 100);
		g.drawLine(0, 695, 480, 695, Color.BLACK);
	}
	
	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.buttons, 0, 0, 110, 210, 109, 104);		// pause button (upper left corner)
		g.drawLine(0, 695, 480, 695, Color.BLACK);					// line separating buttons from "world"
		g.drawPixmap(Assets.buttons, 0, 696, 110, 106, 109, 104);	// left arrow
		g.drawPixmap(Assets.buttons, 371, 696, 0, 104, 109, 104);	// right arrow
	}
	
	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.pause, 98, 100);		// "Resume" & "Quit"
		g.drawLine(0, 695, 480, 695, Color.BLACK);	// line that separates buttons
	}
	
	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.gameOver, 53, 100);						// "game over"
		g.drawPixmap(Assets.buttons, 185, 200, 0, 208, 109, 104);	// X button (to go back to the main menu screen)
		g.drawLine(0, 695, 480, 695, Color.BLACK);					// line that separates the buttons
	}
	
	private void drawText(Graphics g, String line, int x, int y) {
		int len = line.length();
		for (int i = 0; i < len; i++) {
			char character = line.charAt(i);
			
			if (character == ' ') {
				x += 20;
				continue;
			}
			
			int srcX = 0;
			int srcWidth = 0;
			
			switch (character) {
				case 0:
					srcX = 0;
					srcWidth = 39;
					break;
				case 1:
					srcX = 39;
					srcWidth = 27;
					break;
				case 2:
					srcX = 66;
					srcWidth = 43;
					break;
				case 3:
					srcX = 109;
					srcWidth = 44;
					break;
				case 4:
					srcX = 153;
					srcWidth = 50;
					break;
				case 5:
					srcX = 204;
					srcWidth = 45;
					break;
				case 6:
					srcX = 249;
					srcWidth = 44;
					break;
				case 7:
					srcX = 293;
					srcWidth = 37;
					break;
				case 8:
					srcX = 330;
					srcWidth = 36;
					break;
				case 9:
					srcX = 366;
					srcWidth = 44;
					break;
				case '.':
					srcX = 413;
					srcWidth = 18;
					break;
			}
			
			g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 50);
			x += srcWidth;
		}
	}
	
	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;
		
		if (world.gameOver) {
			Settings.addScore(world.score);
			Settings.save(game.getFileIO());
		}
	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
