package com.gbjam.game_components;

import java.util.ArrayList;
import java.util.Iterator;

import com.gbjam.Entity;

public class PlayerPhysicsComponent extends CharacterPhysicsComponent {
	public void update(Entity object, ArrayList<Entity> entities) {
		object.setX(object.getX() + object.getDX());
		object.setY(object.getY() + object.getDY());
		
		Entity collided = null;
		Iterator<Entity> it = entities.iterator();
		while(collided == null && it.hasNext()) {
			Entity other = it.next();
			if(object != other && other.getPhysicsComponent().collide(object, other)) {
				collided = other;
			}
		}
		
		// Step back 
		if(collided != null) {
			while(collided.getPhysicsComponent().collide(object, collided)) {
				if (object.getDX() == 0 && object.getDY() == 0) 
					break; // You're going nowhere sonny
				
				object.setX(object.getX() - object.getDX() * 0.05f);
				object.setY(object.getY() - object.getDY() * 0.05f);
			}
		}
	}
}
