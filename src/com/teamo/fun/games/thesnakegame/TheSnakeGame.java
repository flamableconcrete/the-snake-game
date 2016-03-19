package com.teamo.fun.games.thesnakegame;

import android.annotation.TargetApi;

import com.teamo.fun.games.framework.Screen;
import com.teamo.fun.games.framework.impl.AndroidGame;
import com.teamo.fun.games.thesnakegame.Screens.LoadingScreen;

@TargetApi(10)
public class TheSnakeGame extends AndroidGame {
	
	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}
}