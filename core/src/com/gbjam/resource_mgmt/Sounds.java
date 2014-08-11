package com.gbjam.resource_mgmt;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	public static final int NO_SOUND = 0;
	public static final int GUN_SOUND = 1;
	public static final int FIRE_SOUND = 2;
	public static final int MACH_GUN_SOUND = 3;

	static private Sound gunSound, fireSound, machineGunSound;
	public static int[] weapons;

	static public void load() throws IOException {
		gunSound = Gdx.audio.newSound(Gdx.files.internal("sounds/machine.mp3"));
		machineGunSound = Gdx.audio.newSound(Gdx.files.internal("sounds/machine.mp3"));
		fireSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fire.wav"));
		
		weapons = new int[3];
		weapons[0] = MACH_GUN_SOUND;
		weapons[1] = FIRE_SOUND;
		weapons[2] = GUN_SOUND;
	}

	static public void playSound(int soundID) {
		switch(soundID) {
		case GUN_SOUND:
			gunSound.play();
			break;
		case FIRE_SOUND:
			fireSound.play();
			break;
		case MACH_GUN_SOUND:
			machineGunSound.play();
			break;
		default:
			// no sound
			break;
		}
	}

	static public void dispose() {
		gunSound.dispose();
		machineGunSound.dispose();
		fireSound.dispose();
	}

	public static int getSound(String soundName) {
		if (soundName.equals("GUN_SOUND")) {
			return GUN_SOUND;
		} else if (soundName.equals("FIRE_SOUND")) {
			return FIRE_SOUND;
		} else if (soundName.equals("MACH_GUN_SOUND")) {
			return MACH_GUN_SOUND;
		}
		else {
			System.out.println("OH NO UNDEFINED SOUND!!!!" + soundName);
			return 0;
		}
	}
}
