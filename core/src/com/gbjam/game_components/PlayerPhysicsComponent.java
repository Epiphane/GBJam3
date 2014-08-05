package com.gbjam.game_components;

import java.util.ArrayList;
import java.util.Iterator;

import com.gbjam.Entity;

public class PlayerPhysicsComponent extends PhysicsComponent {
	public void update(Entity object, ArrayList<Entity> entities) {
		object.setX(object.getX() + object.getDX());
		object.setY(object.getY() + object.getDY());
		
		boolean canMove = true;
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()) {
			Entity other = it.next();
			if(other.getPhysicsComponent().collide(object, other)) {
				canMove = false;
			}
		}
	}
}
