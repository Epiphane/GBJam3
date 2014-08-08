package com.gbjam.game_components.collision;

import java.util.ArrayList;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.gbjam.Entity;
import com.gbjam.GBJam3;
import com.gbjam.resource_mgmt.GraphicsService;

/***
 * WOAH HUGE NOTE HERE:
 * 
 * This component DOES NOT LOOK FOR OTHER THINGS TO COLLIDE WITH.
 * All it does is wait to be collided WITH.
 * 
 * If you want something to check for collisions and respond to them,
 * use the subclass DynamicCollisionComponent.
 * 
 * This class is for platforms that get walked all over but don't respond to
 * it at all.
 */
public class CollisionComponent {
	public enum ColliderType {
		PLATFORM,
		ENEMY,
		PLAYER,
		POWERUP,
		BULLET
	}
	
	protected boolean[] filter;
	
	public ColliderType type;

	public boolean blockedLeft, blockedRight;
	
	public void init(ColliderType type_) {
		type = type_;
		filter = new boolean[ColliderType.values().length];
		// By default, a component collides with just platforms
		filter[ColliderType.PLATFORM.ordinal()] = true;
	}
	
	public void update(Entity entity, ArrayList<Entity>entities) {
		if(GBJam3.DEBUG) {
			Polygon polygon = entity.getPolygon();
			float v[] = polygon.getTransformedVertices();
			float vertices[] = new float[] { v[0], v[1], v[4], v[5] };
			GraphicsService.drawRect(vertices);
		}
		
		// do nothing, liek i said
	}
}
