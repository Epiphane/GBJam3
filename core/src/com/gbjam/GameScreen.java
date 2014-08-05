package com.gbjam;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
	private ArrayList<Entity> entities;
	
	private class ExitCommand implements Command {
		public void execute(boolean press) {
			if(press)
				Gdx.app.exit();
		}
	}
	
	public void render(float delta) {
		GraphicsService.begin();
		
		Iterator<Entity> iterator = entities.iterator();
		while(iterator.hasNext()) {
			iterator.next().update(delta);
		}
		
		GraphicsService.end();
	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	public void show() {
		entities = new ArrayList<Entity>();
		entities.add(new Entity(new GraphicsComponent(Art.character),
				new PlayerPhysicsComponent(), new PlayerInputComponent()));
		
		InputService.setKeyCallback(Keys.ESCAPE, new ExitCommand());
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
