package com.gbjam;

import com.badlogic.gdx.Gdx;

public class InputService {
	private static Input input;
	
	public static void load() {
		input = new Input();
		Gdx.input.setInputProcessor(input);
	}
	
	public static void setKeyCallback(int key, Command callback) {
		input.setKeyCallback(key, callback);
	}
}
