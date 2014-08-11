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
import com.gbjam.game_components.status.AttributeComponent.AttribType;
import com.gbjam.game_components.status.StatusComponent.StatusType;
import com.gbjam.resource_mgmt.Art;
import com.gbjam.resource_mgmt.EntityFactory;
import com.gbjam.resource_mgmt.GraphicsService;
import com.gbjam.resource_mgmt.Sounds;
import com.gbjam.utility.PointM;

public class GameScreen implements Screen {
	public static Entity player;
	public static GameScreen sharedScreen;
	private boolean goToBoss;
	
	private ArrayList<Entity> entities, newEntities;
	
	private class ExitCommand implements Command {
		public void execute(boolean press) {
			if(press) {
				Gdx.app.exit();
			}
		}
	}
	
	public void render(float delta) {
		if(goToBoss) {
			initBoss();
			goToBoss = false;
			return;
		}
		
		GraphicsService.begin();
		
		float offsetX = GraphicsService.getCamera().position.x;
		float offsetY = GraphicsService.getCamera().position.y;
		
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
			if(e.getAttribute(AttribType.HEART) > 0) {
				Entity heart = EntityFactory.generate("heart", this);
				heart.setX(e.getX() + 4);
				heart.setY(e.getY() + 4);
				addEntity(heart);
			}
			
			entities.remove(e);
		}
		
		GraphicsService.draw(Art.healthbar, offsetX - 80, 64 + offsetY);
		for(int i = 0; i < 30 && i * 3.5f < player.getAttribute(AttribType.HEALTH); i ++)
			GraphicsService.draw(Art.health, offsetX - 66 + i * 5, 66 + offsetY);

		GraphicsService.draw(Art.weapons[player.getGeneratorComponent().getTemplate()], offsetX - 80, 52 + offsetY);
		
		GraphicsService.end();
	}

	public void show() {
		InputService.setKeyCallback(Keys.ESCAPE, new ExitCommand());

		TiledMap map = new TmxMapLoader().load("maps/corridor.tmx");
		
		entities = new ArrayList<Entity>();
		newEntities = new ArrayList<Entity>();
		
		Entity endDoor = EntityFactory.generate("door", this);

		for(MapObject object : map.getLayers().get("Door").getObjects()) {
			if(object instanceof PolygonMapObject) {
				endDoor.setPolygon(((PolygonMapObject) object).getPolygon());
				endDoor.setX(Float.parseFloat(object.getProperties().get("x").toString()));
				endDoor.setY(Float.parseFloat(object.getProperties().get("y").toString()));
				addEntity(endDoor);
			}
		}
		
		// Main Character
		player = EntityFactory.generate("player", this);
		player.setX(1*16);
		player.setY(1*16);
		addEntity(player);
		
		MapGenerator.initSection(map, 1, 3, 98, 11, new PointM((int) player.getX() / 16, (int) player.getY() / 16), this, false);
		
		GraphicsService.loadMapRenderer(new MapRenderer(map, 1));
		GraphicsService.setMapWidth(((Integer) map.getProperties().get("width")) * 16);
		GraphicsService.setMapHeight(((Integer) map.getProperties().get("height")) * 16);

		Entity platform = EntityFactory.generate("platform", this);
		for(MapObject object : map.getLayers().get("Walls").getObjects()) {
			if(object instanceof PolygonMapObject) {
				platform.setPolygon(((PolygonMapObject) object).getPolygon());
				platform.setX(Float.parseFloat(object.getProperties().get("x").toString()));
				platform.setY(Float.parseFloat(object.getProperties().get("y").toString()));
				addEntity(platform.clone());
			}
		}
		
		sharedScreen = this;
		
		Sounds.startMusic();
	}

	public void goToBoss() {
		goToBoss = true;
	}
	
	public void initBoss() {
		entities = new ArrayList<Entity>();
		newEntities = new ArrayList<Entity>();

		player.setX(2*16);
		addEntity(player);

		// Main Character
		Entity dragon = EntityFactory.generate("dragon", this);
		dragon.setX(98);
		dragon.setY(30);
		addEntity(dragon);
		
		TiledMap map = new TmxMapLoader().load("maps/boss_random.tmx");
Sounds.startBossMusic();
		MapGenerator.initSection(map, 1, 3, 6, 14, new PointM((int) player.getX() / 16, (int) player.getY() / 16), this, true);

		GraphicsService.loadMapRenderer(new MapRenderer(map, 1));
		GraphicsService.setMapWidth(((Integer) map.getProperties().get("width")) * 16);
		GraphicsService.setMapHeight(((Integer) map.getProperties().get("height")) * 16);

		Entity platform = EntityFactory.generate("platform", GameScreen.sharedScreen);
		for(MapObject object : map.getLayers().get("Walls").getObjects()) {
			if(object instanceof PolygonMapObject) {
				platform.setPolygon(((PolygonMapObject) object).getPolygon());
				platform.setX(Float.parseFloat(object.getProperties().get("x").toString()));
				platform.setY(Float.parseFloat(object.getProperties().get("y").toString()));
				GameScreen.sharedScreen.addEntity(platform.clone());
			}
		}
	}
	
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}
	
	public void addEntity(Entity entity) {
		if(entity != null)
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
