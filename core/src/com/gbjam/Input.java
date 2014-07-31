package com.gbjam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class Input implements InputProcessor {
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int ACTION = 4;
	public static final int RUN = 5;
	
	// Callback hash
	public Command[] keyCallbacks = new Command[255];
	
	public void setKeyCallback(int key, Command callback) {
		if(keyCallbacks[key] != null) {
			Gdx.app.log("WARNING", "Overwriting callback for key " + key);
		}
			
		keyCallbacks[key] = callback;
	}
	
	/**
	 * Sets button in the array to state of the key
	 * 
	 * @param key
	 * @param down
	 */
	public void set(int key, boolean down) {
		if(keyCallbacks[key] != null) {
			keyCallbacks[key].execute(down);
		}
	}

	public boolean keyDown(int keycode) {
		set(keycode, true);
		return false;
	}

	public boolean keyUp(int keycode) {
		set(keycode, false);
		return false;
	}

	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
