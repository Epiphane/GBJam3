package com.gbjam.resource_mgmt;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	public static final int GUN_SOUND = 0;
	
	static private Sound gunSound;
	
	static public void load() throws IOException {
		gunSound = Gdx.audio.newSound(Gdx.files.internal("sounds/gun.mp3"));
	}
	
	static public void playSound(int soundID) {
		gunSound.play();
	}
	
	static public void dispose() {
		gunSound.dispose();
	}
}
