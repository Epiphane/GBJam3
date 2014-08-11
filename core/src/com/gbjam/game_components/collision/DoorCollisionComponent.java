package com.gbjam.game_components.collision;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Polygon;
import com.gbjam.Entity;
import com.gbjam.GameScreen;
import com.gbjam.resource_mgmt.EntityFactory;
import com.gbjam.resource_mgmt.GraphicsService;

public class DoorCollisionComponent extends DynamicCollisionComponent {

	public void init(ColliderType _type) {
		super.init(_type);
		filter[ColliderType.PLAYER.ordinal()] = true;
	}
	
	@Override
	protected void collideAnyDir(Polygon myPolygon, Entity entity,
			Entity collider) {
		if (collider.getCollisionComponent().type == ColliderType.PLAYER) {
			GameScreen.sharedScreen.goToBoss();
		}
		
	}

	
}
