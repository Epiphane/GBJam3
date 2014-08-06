package com.gbjam.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gbjam.GBJam3;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GBJam3.GAME_WIDTH * 4;
		config.height = GBJam3.GAME_HEIGHT * 4;
		config.resizable = false;
		new LwjglApplication(new GBJam3(), config);
	}
}
