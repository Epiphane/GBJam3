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
import com.gbjam.game_components.BulletPhysicsComponent;
import com.gbjam.game_components.GraphicsComponent;
import com.gbjam.game_components.PlatformPhysicsComponent;
import com.gbjam.game_components.PlayerGraphicsComponent;
import com.gbjam.game_components.PlayerInputComponent;
import com.gbjam.game_components.PlayerPhysicsComponent;
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
			entities.add(newEntities.remove(0));
		}
		
		Iterator<Entity> iterator = entities.iterator();
		while(iterator.hasNext()) {
			Entity entity = iterator.next();
			entity.update(delta, entities);
		}
		
		GraphicsService.end();
	}

	public void show() {
		InputService.setKeyCallback(Keys.ESCAPE, new ExitCommand());
		
		entities = new ArrayList<Entity>();
		newEntities = new ArrayList<Entity>();
		
		// Main Character
		Entity bullet = new Entity(new GraphicsComponent(Art.bullet), new BulletPhysicsComponent(),
				null, null);
		bullet.setDX(3);
		
		Entity player = new Entity(new PlayerGraphicsComponent(Art.character),
				new PlayerPhysicsComponent(), new PlayerInputComponent(), 
				new WeaponGeneratorComponent(this, bullet, 20));
		player.getGeneratorComponent().setOffset(new Point(player.getW() / 2, 7));
		player.getGeneratorComponent().setSoundToPlay(Sounds.GUN_SOUND);
		addEntity(player);
		
		Polygon debugPolyPlayer = new Polygon(new float[]{0, 0, 19, 0, 19, 27, 0, 27});
		debugPolyPlayer.setOrigin(0, 0);
		player.setPolygon(debugPolyPlayer);
		player.setY(18);
		
		// Base platform
		Entity platform = new Entity(new GraphicsComponent(Art.platform),
				new PlatformPhysicsComponent(), null, null);
		//addEntity(platform);
		
		Polygon debugPolyPlatform = new Polygon(new float[]{0, 0, 160, 0, 160, 14, 0, 14});
		debugPolyPlatform.setOrigin(0, 0);
		platform.setPolygon(debugPolyPlatform);
		platform.setY(0);
		
		TiledMap map = new TmxMapLoader().load("maps/test.tmx");
		GraphicsService.loadMapRenderer(new MapRenderer(map, 1));
		
		platform = new Entity(null, new PlatformPhysicsComponent(), null, null);
		for(MapObject object : map.getLayers().get(1).getObjects()) {
			if(object instanceof PolygonMapObject) {
				platform.setPolygon(((PolygonMapObject) object).getPolygon());
				addEntity(platform);
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
