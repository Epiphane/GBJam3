package com.gbjam.resource_mgmt;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.gbjam.GBJam3;
import com.gbjam.MapRenderer;

public class GraphicsService {
	private static SpriteBatch batch;
	
	private static OrthographicCamera camera;
	private static MapRenderer renderer;
	private static int mapWidth, mapHeight;
	private static ShapeRenderer shapeRenderer;
	
	public static void load() {
		batch = new SpriteBatch(10);
		Gdx.gl.glClearColor(0.87843137254f, 0.9725490196f, 0.81568627451f, 1);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GBJam3.GAME_WIDTH, GBJam3.GAME_HEIGHT);
		camera.update();
		
		mapWidth = 1600;
		mapHeight = 16*14;

		shapeRenderer = new ShapeRenderer(20);
		shapeRenderer.setColor(1, 0, 0, 1);
	}
	
	public static void setMapWidth(int _mapWidth) {
		mapWidth = _mapWidth;
	}
	
	public static void setMapHeight(int _mapHeight) {
		mapHeight = _mapHeight;
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
		camera.update();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.setView(camera);
		renderer.render();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		renderer.setView(camera);
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);
	}
	
	public static void draw(Texture healthbar, float x, float y) {
		if(healthbar != null) {
			batch.draw(healthbar, x, y);
		}
	}
	
	public static void draw(TextureRegion texture, float x, float y) {
		if(texture != null) {
			batch.draw(texture, x, y);
		}
	}
	
	public static void draw(Texture texture, int x, int y) {
		if(texture != null) {
			batch.draw(texture, x, y);
		}
	}
	
	public static void drawRect(float[] v) {
		shapeRenderer.rect(v[0], v[1], v[2] - v[0], v[3] - v[1]);
	}
	
	public static OrthographicCamera getCamera() {
		return camera;
	}

	public static void setCamera(float x, float y) {
		camera.position.x = x;
		camera.position.y = y;
		
		if(camera.position.x < GBJam3.GAME_WIDTH / 2)
			camera.position.x = GBJam3.GAME_WIDTH / 2;
		if(camera.position.x > mapWidth - GBJam3.GAME_WIDTH / 2)
			camera.position.x = mapWidth - GBJam3.GAME_WIDTH / 2;
		if(camera.position.y < GBJam3.GAME_HEIGHT / 2)
			camera.position.y = GBJam3.GAME_HEIGHT / 2;
		if(camera.position.y > mapHeight - GBJam3.GAME_HEIGHT / 2)
			camera.position.y = mapHeight - GBJam3.GAME_HEIGHT / 2;
	}

	public static void end() {
		batch.end();
		shapeRenderer.end();
	}

	public static void drawLine(float[] vertices) {
		shapeRenderer.line(vertices[0], vertices[1], vertices[2], vertices[3]);
	}
}

