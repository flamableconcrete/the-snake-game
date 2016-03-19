package com.teamo.fun.games.thesnakegame.Screens;

import java.util.List;

import com.teamo.fun.games.framework.Game;
import com.teamo.fun.games.framework.Graphics;
import com.teamo.fun.games.framework.Input.TouchEvent;
import com.teamo.fun.games.framework.Screen;
import com.teamo.fun.games.thesnakegame.Assets;
import com.teamo.fun.games.thesnakegame.Settings;

public class MainMenuScreen extends Screen {
	
	private static final String	TAG	= MainMenuScreen.class.getSimpleName();
	
	public MainMenuScreen(Game game) {
		super(game);
	}
	
	@Override
	public void update(float deltaTime) {
		
		Graphics g = game.getGraphics();
		
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		
		for (int i = 0; i < len; i++) {
			
			TouchEvent event = touchEvents.get(i);
			
			if (event.type == TouchEvent.TOUCH_UP) {
				
				// Toggle sound
				if (inBounds(event, 0, g.getHeight() - 104, 109, 104)) {
					Settings.soundEnabled = !Settings.soundEnabled;
					if (Settings.soundEnabled)
						Assets.click.play(1);
				}
				
				// Play game!
				if (inBounds(event, 42, 220, 396, 48)) {
					game.setScreen(new GameScreen(game));
					if (Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
				
				// High scores
				if (inBounds(event, 42, 220 + 48, 396, 63)) {
					game.setScreen(new HighscoreScreen(game));
					if (Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
				
				// Help
				if (inBounds(event, 42, 220 + 111, 396, 56)) {
					game.setScreen(new HelpScreen(game));
					if (Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
				
			}
			
		}
		
	}
	
	private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1)
			return true;
		return false;
	}
	
	@Override
	public void present(float deltaTime) {
		
		Graphics g = game.getGraphics();
		
		// Log.d(TAG, "About to draw Pixmap\n-------------------------------");
		g.drawPixmap(Assets.background, 0, 0); 		// background
		// Log.d(TAG, "Pixmap is drawn\n---------------------------------");
		g.drawPixmap(Assets.logo, 112, 20);			// logo
		g.drawPixmap(Assets.mainMenu, 42, 220);		// the whole main menu
		
		if (Settings.soundEnabled)
			g.drawPixmap(Assets.buttons, 0, 696, 0, 0, 109, 104);		// sound on
		else if (!Settings.soundEnabled)
			g.drawPixmap(Assets.buttons, 0, 696, 110, 0, 109, 104);		// sound off
			
		// g.drawPixmap(Assets.buttons, 0, 696, 0, 0, 109, 104); // sound on
		// g.drawPixmap(Assets.buttons, 0, 416, 110, 0, 109, 104); // sound off
		
	}
	
	@Override
	public void pause() {
		Settings.save(game.getFileIO());
	}
	
	@Override
	public void resume() {
		
	}
	
	@Override
	public void dispose() {
		
	}
	
}
