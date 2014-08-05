package com.gbjam;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.gbjam.game_components.*;
import com.gbjam.resource_mgmt.*;

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
			iterator.next().update(delta, entities);
		}
		
		GraphicsService.end();
	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	public void show() {
		entities = new ArrayList<Entity>();
		
		// Main Character
		Entity player = new Entity(new GraphicsComponent(Art.character),
				new PlayerPhysicsComponent(), new PlayerInputComponent());
		player.setY(14);
		entities.add(player);
		
		// Base platform
		Entity platform = new Entity(new GraphicsComponent(Art.platform),
				new PlatformPhysicsComponent(), null);
		entities.add(platform);
		
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
