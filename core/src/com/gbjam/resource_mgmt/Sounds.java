package com.gbjam.resource_mgmt;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	public static final int NO_SOUND = 0;
	public static final int GUN_SOUND = 1;
	public static final int FIRE_SOUND = 2;
	public static final int MACH_GUN_SOUND = 3;
	public static final int HEAL_SOUND = 4;

	static private Sound gunSound, fireSound, machineGunSound, healSound;
	static private Music music, musicIntro;
	static private Music boss;
	public static int[] weapons;

	static public void load() throws IOException {
		musicIntro = Gdx.audio.newMusic(Gdx.files.internal("sounds/songIntro.mp3"));
		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/songLoop.mp3"));
		
		boss = Gdx.audio.newMusic(Gdx.files.internal("sounds/BOSS.mp3"));
		
		gunSound = Gdx.audio.newSound(Gdx.files.internal("sounds/machine.mp3"));
		machineGunSound = Gdx.audio.newSound(Gdx.files.internal("sounds/machine.mp3"));
		fireSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fire.mp3"));
		healSound = Gdx.audio.newSound(Gdx.files.internal("sounds/heal.mp3"));
		
		weapons = new int[3];
		weapons[0] = MACH_GUN_SOUND;
		weapons[1] = FIRE_SOUND;
		weapons[2] = GUN_SOUND;
	}
	
	private static class IntroListener implements OnCompletionListener {
		@Override
		public void onCompletion(Music mus) {
			mus.stop();
			music.play();
		}
	}
	
	static public void startMusic() {
		musicIntro.play();
		musicIntro.setOnCompletionListener(new IntroListener());
	}
	
	static public void startBossMusic() {
		music.stop();
		musicIntro.stop();
		
		boss.play();
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
		case HEAL_SOUND:
			healSound.play();
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
		healSound.dispose();
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
