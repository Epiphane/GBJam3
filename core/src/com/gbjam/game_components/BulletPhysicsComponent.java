package com.gbjam.game_components;

import java.util.ArrayList;

import com.gbjam.Entity;

public class BulletPhysicsComponent extends PhysicsComponent {
	public void update(Entity object, ArrayList<Entity> entities) {
		object.setX(object.getX() + object.getDX());
	}
	
	public boolean collide(Entity object, Entity other) {
		return false;
	}
}
