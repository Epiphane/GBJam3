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
import com.badlogic.gdx.math.Polygon;
import com.gbjam.game_components.AIInputComponent;
import com.gbjam.game_components.BulletPhysicsComponent;
import com.gbjam.game_components.CollisionComponent;
import com.gbjam.game_components.CollisionComponent.ColliderType;
import com.gbjam.game_components.GraphicsComponent;
import com.gbjam.game_components.PlatformPhysicsComponent;
import com.gbjam.game_components.PlayerCollisionComponent;
import com.gbjam.game_components.PlayerGraphicsComponent;
import com.gbjam.game_components.PlayerInputComponent;
import com.gbjam.game_components.PlayerPhysicsComponent;
import com.gbjam.game_components.WalkingCollisionComponent;
import com.gbjam.game_components.WeaponGeneratorComponent;
import com.gbjam.resource_mgmt.Art;
import com.gbjam.resource_mgmt.GraphicsService;
import com.gbjam.resource_mgmt.Sounds;
import com.gbjam.utility.Point;

public class GameScreen implements Screen {
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
			entities.add(0, newEntities.remove(0));
		}
		
		ArrayList<Entity> toRemove = new ArrayList<Entity>();
		Iterator<Entity> iterator = entities.iterator();
		while(iterator.hasNext()) {
			Entity entity = iterator.next();
			entity.update(delta, entities);
			if(entity.dead()) {
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
		Entity bullet = new Entity(new GraphicsComponent(Art.bullet), null, new BulletPhysicsComponent(),
				null, null);
		bullet.setDX(3);
		
		Entity player = new Entity(new PlayerGraphicsComponent(Art.character),
				new PlayerCollisionComponent(ColliderType.PLAYER),
				new PlayerPhysicsComponent(), new PlayerInputComponent(), 
				new WeaponGeneratorComponent(this, bullet, 20));
		player.getGeneratorComponent().setOffset(new Point(player.getW() / 2, 7));
		player.getGeneratorComponent().setSoundToPlay(Sounds.GUN_SOUND);
		player.setPolygon(new Polygon(new float[] {6, 0, 6, 20, 18, 20, 18, 0}));
		player.setX(50);
		player.setY(8.01f);
		addEntity(player);
		
		Entity slime = new Entity(new PlayerGraphicsComponent(Art.slime),
				new WalkingCollisionComponent(ColliderType.ENEMY),
				new PlayerPhysicsComponent(), new AIInputComponent(), null);
		slime.setPolygon(new Polygon(new float[] {1, 0, 1, 12, 15, 12, 15, 0}));
		slime.setX(130);
		slime.setY(8.01f);
		addEntity(slime);
		
		TiledMap map = new TmxMapLoader().load("maps/test.tmx");
		GraphicsService.loadMapRenderer(new MapRenderer(map, 1));
		
		Entity platform = new Entity(null, new CollisionComponent(ColliderType.PLATFORM), new PlatformPhysicsComponent(), null, null);
		for(MapObject object : map.getLayers().get(1).getObjects()) {
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
