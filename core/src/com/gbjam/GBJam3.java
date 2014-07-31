package com.gbjam;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class GBJam3 extends Game {
	// Keep this constant since it's a gameboy and stuff...
	public static final int GAME_WIDTH = 160;
	public static final int GAME_HEIGHT = 144;
	
	@Override
	public void create() {
		try {
			Art.load();
			Sounds.load();
			GraphicsService.load();
			InputService.load();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		setScreen(new GameScreen());
	}
}
