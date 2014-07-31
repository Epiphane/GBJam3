package com.gbjam;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
	private SpriteBatch batch;
	private ArrayList<GameObject> entities;
	
	public void render(float delta) {
		batch.begin();
		
		Iterator<GameObject> iterator = entities.iterator();
		while(iterator.hasNext()) {
			iterator.next().update(delta);
		}
		
		batch.end();
	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	public void show() {
		batch = new SpriteBatch(10);
		
		entities = new ArrayList<GameObject>();
		entities.add(new GameObject(new GraphicsComponent(batch, Art.character)));
		
		Gdx.app.log("Show", "GameScreen");
	}

	public void hide() {
		// TODO Auto-generated method stub

	}

	public void pause() {
		// TODO Auto-generated method stub

	}

	public void resume() {
		// TODO Auto-generated method stub

	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

}
