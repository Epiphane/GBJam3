package com.gbjam;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.gbjam.game_components.status.StatusComponent.StatusType;
import com.gbjam.resource_mgmt.EntityFactory;
import com.gbjam.resource_mgmt.GraphicsService;

public class GameScreen implements Screen {
	public static Entity player;
	
	private ArrayList<Entity> entities, newEntities;
	
	private class ExitCommand implements Command {
		public void execute(boolean press) {
			if(press) {
				Gdx.app.exit();
			}
		}
	}
	
	public void render(float delta) {
		GraphicsService.begin();
		
		// Add new entities to list of entities to compute
		// (To avoid Concurrent Modification)
		while(newEntities.size() > 0) {
			entities.add(newEntities.remove(0));
		}
		
		ArrayList<Entity> toRemove = new ArrayList<Entity>();
		Iterator<Entity> iterator = entities.iterator();
		while(iterator.hasNext()) {
			Entity entity = iterator.next();
			entity.update(delta, entities);
			if(entity.is(StatusType.DEAD)) {
				toRemove.add(entity);
			}
		}
		
		for(Entity e : toRemove) {
			entities.remove(e);
		}
		
		GraphicsService.end();
	}

	public void show() {
		InputService.setKeyCallback(Keys.ESCAPE, new ExitCommand());
		
		entities = new ArrayList<Entity>();
		newEntities = new ArrayList<Entity>();
		
		// Main Character
		Entity player = EntityFactory.generate("player", this);
		player.setX(19*8);
		player.setY(15*8);
		addEntity(player);

		Entity dragon = EntityFactory.generate("dragon", this);
		dragon.setX(96);
		dragon.setY(50);
		addEntity(dragon);
		
		TiledMap map = new TmxMapLoader().load("maps/new_random.tmx");
		
		MapGenerator.initSection(map, 0, 10);
		
		GraphicsService.loadMapRenderer(new MapRenderer(map, 1));
		GraphicsService.setMapWidth(((Integer) map.getProperties().get("width")) * 8);
		GraphicsService.setMapHeight(((Integer) map.getProperties().get("height")) * 8);

		Entity platform = EntityFactory.generate("platform", this);
		for(MapObject object : map.getLayers().get("Walls").getObjects()) {
			if(object instanceof PolygonMapObject) {
				platform.setPolygon(((PolygonMapObject) object).getPolygon());
				platform.setX(Float.parseFloat(object.getProperties().get("x").toString()));
				platform.setY(Float.parseFloat(object.getProperties().get("y").toString()));
				addEntity(platform.clone());
			}
		}
	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
