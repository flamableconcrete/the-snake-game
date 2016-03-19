package com.teamo.fun.games.thesnakegame.Screens;

import com.teamo.fun.games.framework.Game;
import com.teamo.fun.games.framework.Graphics;
import com.teamo.fun.games.framework.Graphics.PixmapFormat;
import com.teamo.fun.games.framework.Screen;
import com.teamo.fun.games.thesnakegame.Assets;
import com.teamo.fun.games.thesnakegame.Settings;

public class LoadingScreen extends Screen {
	
	public LoadingScreen(Game game) {
		super(game);
	}
	
	@Override
	public void update(float deltaTime) {
		
		Graphics g = game.getGraphics();
		
		PixmapFormat pf_background = PixmapFormat.RGB565;
		PixmapFormat pf_foreground = PixmapFormat.ARGB4444;
		
		String img = "images/";
		String sound = "sounds/";
		
		Assets.background = g.newPixmap(img + "background.png", pf_background);
		// Assets.background = g.newPixmap(img + "grid_100px.png", pf_background);
		Assets.logo = g.newPixmap(img + "logo.png", pf_foreground);
		Assets.mainMenu = g.newPixmap(img + "mainmenu.png", pf_foreground);
		Assets.buttons = g.newPixmap(img + "buttons.png", pf_foreground);
		Assets.help1 = g.newPixmap(img + "help1.png", pf_foreground);
		Assets.help2 = g.newPixmap(img + "help2.png", pf_foreground);
		Assets.help3 = g.newPixmap(img + "help3.png", pf_foreground);
		Assets.numbers = g.newPixmap(img + "numbers.png", pf_foreground);
		Assets.ready = g.newPixmap(img + "ready.png", pf_foreground);
		Assets.pause = g.newPixmap(img + "pause.png", pf_foreground);
		Assets.gameOver = g.newPixmap(img + "gameover.png", pf_foreground);
		Assets.headUp = g.newPixmap(img + "headup.png", pf_foreground);
		Assets.headDown = g.newPixmap(img + "headdown.png", pf_foreground);
		Assets.headLeft = g.newPixmap(img + "headleft.png", pf_foreground);
		Assets.headRight = g.newPixmap(img + "headright.png", pf_foreground);
		Assets.tail = g.newPixmap(img + "tail.png", pf_foreground);
		Assets.stain1 = g.newPixmap(img + "stain1.png", pf_foreground);
		Assets.stain2 = g.newPixmap(img + "stain2.png", pf_foreground);
		Assets.stain3 = g.newPixmap(img + "stain3.png", pf_foreground);
		
		Assets.click = game.getAudio().newSound(sound + "click.ogg");
		Assets.eat = game.getAudio().newSound(sound + "eat.ogg");
		Assets.bitten = game.getAudio().newSound(sound + "bitten.ogg");
		
		Settings.load(game.getFileIO());
		game.setScreen(new MainMenuScreen(game));
		
	}
	
	@Override
	public void present(float deltaTime) {
		
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
