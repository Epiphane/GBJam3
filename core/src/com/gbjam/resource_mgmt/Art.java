package com.gbjam.resource_mgmt;

import java.io.IOException;

import com.badlogic.gdx.graphics.Texture;

public class Art {
	public static Texture character;
	
	static public void load() throws IOException {
		character = new Texture("character.png");
	}
}