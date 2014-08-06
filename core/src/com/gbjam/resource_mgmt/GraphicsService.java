package com.gbjam.resource_mgmt;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gbjam.GBJam3;

public class GraphicsService {
	private static SpriteBatch batch;
	
	public static void load() {
		batch = new SpriteBatch(10);
		Gdx.gl.glClearColor(0.87843137254f, 0.9725490196f, 0.81568627451f, 1);
	}
	
	public static void begin() {
		Gdx.gl.glViewport(0, 0, GBJam3.GAME_WIDTH * 16, GBJam3.GAME_HEIGHT * 16);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
	}
	
	public static void draw(TextureRegion texture, int x, int y) {
		if(texture != null) {
			batch.draw(texture, x, y);
		}
	}
	
	public static void draw(TextureRegion texture, float x, float y) {
		if(texture != null) {
			batch.draw(texture, x, y);
		}
	}

	public static void end() {
		batch.end();
	}
}