package com.gbjam.game_components;

import java.util.ArrayList;
import java.util.Arrays;

import com.gbjam.Entity;

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
		POWERUP
	}
	
	protected boolean[] filter;
	
	public ColliderType type;
	
	public CollisionComponent(ColliderType type_) {
		type = type_;
		filter = new boolean[ColliderType.values().length];
		// By default, a component collides with everything! yayyyy
		Arrays.fill(filter, true);
	}
	
	public void update(Entity entity, ArrayList<Entity>entities) {
		// do nothing, liek i said
	}
}
