package com.gbjam.resource_mgmt;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gbjam.GBJam3;
import com.gbjam.MapRenderer;

public class GraphicsService {
	private static SpriteBatch batch;
	
	private static OrthographicCamera camera;
	private static MapRenderer renderer;
	
	public static void load() {
		batch = new SpriteBatch(10);
		Gdx.gl.glClearColor(0.87843137254f, 0.9725490196f, 0.81568627451f, 1);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GBJam3.GAME_WIDTH, GBJam3.GAME_HEIGHT);
		camera.update();
	}
	
	public static void loadMapRenderer(MapRenderer _renderer) {
		renderer = _renderer;
		if(renderer != null) {
			batch = (SpriteBatch) renderer.getSpriteBatch();
		}
		else {
			batch = new SpriteBatch(10);
		}
	}
	
	public static void begin() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.setView(camera);
		renderer.render();
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
	
	public OrthographicCamera getCamera() {
		return camera;
	}

	public static void end() {
		batch.end();
	}
}

