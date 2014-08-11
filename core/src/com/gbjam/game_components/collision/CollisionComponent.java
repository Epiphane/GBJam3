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
		ENEMY_BULLET,
		PLAYER,
		POWERUP,
		BOSS,
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
	
	public CollisionComponent clone() {
		CollisionComponent newComponent;
		try {
			newComponent = this.getClass().newInstance();
			
			newComponent.blockedLeft = blockedLeft;
			newComponent.blockedRight = blockedRight;
			newComponent.type = type;
			newComponent.filter = filter;
			
			return newComponent;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void update(Entity entity, ArrayList<Entity>entities) {
		if(GBJam3.DEBUG) {
			Polygon polygon = entity.getPolygon();
			float v[] = polygon.getTransformedVertices();
			for(int i = 0; i < v.length; i += 2) {
				float vertices[];
				if(i < v.length - 2)
					vertices = new float[] { v[i], v[i + 1], v[i + 2], v[i + 3] };
				else
					vertices = new float[] { v[i], v[i + 1], v[0], v[1] };
				
				GraphicsService.drawLine(vertices);
			}
			//float vertices[] = new float[] { v[0], v[1], v[4], v[5] };
			//GraphicsService.drawRect(vertices);
		}
		
		// do nothing, liek i said
	}
}
