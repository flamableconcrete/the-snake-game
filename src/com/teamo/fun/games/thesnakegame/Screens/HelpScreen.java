package com.teamo.fun.games.thesnakegame.Screens;

import java.util.List;

import com.teamo.fun.games.framework.Game;
import com.teamo.fun.games.framework.Graphics;
import com.teamo.fun.games.framework.Input.TouchEvent;
import com.teamo.fun.games.framework.Screen;
import com.teamo.fun.games.thesnakegame.Assets;
import com.teamo.fun.games.thesnakegame.Settings;

public class HelpScreen extends Screen {
	
	public HelpScreen(Game game) {
		super(game);
	}
	
	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.x > 371 && event.y > 696) {
					game.setScreen(new HelpScreen2(game));
					if (Settings.soundEnabled)
						Assets.click.play(1);
					return;
				}
			}
		}
	}
	
	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);	// background
		g.drawPixmap(Assets.help1, 50, 100);	// help screen 1
		g.drawPixmap(Assets.buttons, 371, 696, 0, 104, 109, 104);	// right arrow
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
