package com.gbjam.resource_mgmt;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	public static final int NO_SOUND = 0;
	public static final int GUN_SOUND = 1;
	public static final int FIRE_SOUND = 2;

	static private Sound gunSound, fireSound;

	static public void load() throws IOException {
		gunSound = Gdx.audio.newSound(Gdx.files.internal("sounds/gun.mp3"));
		fireSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fire.wav"));
	}

	static public void playSound(int soundID) {
		switch(soundID) {
		case GUN_SOUND:
			gunSound.play();
			break;
		case FIRE_SOUND:
			fireSound.play();
			break;
		default:
			// no sound
			break;
		}
	}

	static public void dispose() {
		gunSound.dispose();
	}

	public static int getSound(String soundName) {
		if (soundName.equals("GUN_SOUND")) {
			return GUN_SOUND;
		} else if (soundName.equals("FIRE_SOUND")) {
			return FIRE_SOUND;
		}
		else {
			System.out.println("OH NO UNDEFINED SOUND!!!!" + soundName);
			return 0;
		}
	}
}
