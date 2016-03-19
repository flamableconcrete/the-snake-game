package com.teamo.fun.games.thesnakegame.Screens;

import java.util.List;

import com.teamo.fun.games.framework.Game;
import com.teamo.fun.games.framework.Graphics;
import com.teamo.fun.games.framework.Input.TouchEvent;
import com.teamo.fun.games.framework.Screen;
import com.teamo.fun.games.thesnakegame.Assets;
import com.teamo.fun.games.thesnakegame.Settings;

public class HighscoreScreen extends Screen {
	
	String	lines[]	= new String[5];
	
	public HighscoreScreen(Game game) {
		super(game);
		for (int i = 0; i < 5; i++)
			lines[i] = "" + (i + 1) + ". " + Settings.highscores[i];
	}
	
	@Override
	public void update(float deltaTime) {
		
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			
			// Back to Main Menu
			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x < 109 && event.y > 696) {
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
		
		g.drawPixmap(Assets.background, 0, 0); 					// background
		g.drawPixmap(Assets.mainMenu, 42, 20, 0, 54, 396, 63); 	// Highscores label
		
		int y = 100;
		for (int i = 0; i < 5; i++) {
			drawText(g, lines[i], 20, y);
			y += 50;
		}
		
		g.drawPixmap(Assets.buttons, 0, 696, 110, 106, 109, 104);	// left arrow
		
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
				case '0':
					srcX = 0;
					srcWidth = 39;
					break;
				case '1':
					srcX = 39;
					srcWidth = 27;
					break;
				case '2':
					srcX = 66;
					srcWidth = 43;
					break;
				case '3':
					srcX = 109;
					srcWidth = 44;
					break;
				case '4':
					srcX = 153;
					srcWidth = 50;
					break;
				case '5':
					srcX = 204;
					srcWidth = 45;
					break;
				case '6':
					srcX = 249;
					srcWidth = 44;
					break;
				case '7':
					srcX = 293;
					srcWidth = 37;
					break;
				case '8':
					srcX = 330;
					srcWidth = 36;
					break;
				case '9':
					srcX = 366;
					srcWidth = 44;
					break;
				case '.':
					srcX = 413;
					srcWidth = 18;
					break;
			}
			
			g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 50);	// draw the particular number
			x += srcWidth;
		}
		
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}
	
	@Override
	public void dispose() {
		
	}
	
}
