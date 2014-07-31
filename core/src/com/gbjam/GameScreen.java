package com.gbjam;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
	private ArrayList<GameObject> entities;
	
	public void render(float delta) {
		GraphicsService.begin();
		
		Iterator<GameObject> iterator = entities.iterator();
		while(iterator.hasNext()) {
			iterator.next().update(delta);
		}
		
		GraphicsService.end();
	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	public void show() {
		entities = new ArrayList<GameObject>();
		entities.add(new GameObject(new GraphicsComponent(Art.character),
				new PhysicsComponent(), new PlayerInputComponent()));
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
