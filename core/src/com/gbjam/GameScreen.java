package com.gbjam;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.gbjam.game_components.*;
import com.gbjam.resource_mgmt.*;

public class GameScreen implements Screen {
	private ArrayList<Entity> entities, newEntities;
	
	private class ExitCommand implements Command {
		public void execute(boolean press) {
			if(press)
				Gdx.app.exit();
		}
	}
	
	public void render(float delta) {
		GraphicsService.begin();
		
		// Add new entities to list of entities to compute
		// (To avoid Concurrent Modification)
		while(newEntities.size() > 0) {
			entities.add(newEntities.remove(0));
		}
		
		Iterator<Entity> iterator = entities.iterator();
		while(iterator.hasNext()) {
			Entity entity = iterator.next();
			entity.update(delta, entities);
		}
		
		GraphicsService.end();
	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	public void show() {
		entities = new ArrayList<Entity>();
		newEntities = new ArrayList<Entity>();
		
		// Main Character
		Entity bullet = new Entity(new GraphicsComponent(Art.bullet), new BulletPhysicsComponent(),
				null, null);
		bullet.setDX(3);
		
		Entity player = new Entity(new PlayerGraphicsComponent(Art.character),
				new PlayerPhysicsComponent(), new PlayerInputComponent(), 
				new WeaponGeneratorComponent(this, bullet, 20));
		player.setY(14);
		addEntity(player);
		
		// Base platform
		Entity platform = new Entity(new GraphicsComponent(Art.platform),
				new PlatformPhysicsComponent(), null, null);
		addEntity(platform);
		
		InputService.setKeyCallback(Keys.ESCAPE, new ExitCommand());
	}
	
	public void addEntity(Entity entity) {
		newEntities.add(entity);
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
