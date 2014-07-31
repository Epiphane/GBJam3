package com.gbjam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GraphicsService {
	private static SpriteBatch batch;
	
	public static void load() {
		batch = new SpriteBatch(10);
	}
	
	public static void begin() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
	}
	
	public static void draw(Texture texture, int x, int y) {
		if(texture != null) {
			batch.draw(texture, x, y);
		}
	}
	
	public static void draw(Texture texture, float x, float y) {
		if(texture != null) {
			batch.draw(texture, x, y);
		}
	}

	public static void end() {
		batch.end();
	}
}
