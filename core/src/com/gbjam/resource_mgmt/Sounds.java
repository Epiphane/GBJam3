package com.gbjam.resource_mgmt;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	static private Sound gunSound;
	
	static public void load() throws IOException {
		gunSound = Gdx.audio.newSound(Gdx.files.internal("sounds/gun.mp3"));
		//gunSound.loop();
	}
	
	static public void playSound(int soundID) {
		gunSound.loop();
	}
}
