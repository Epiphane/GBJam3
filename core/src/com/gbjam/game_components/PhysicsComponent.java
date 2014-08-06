package com.gbjam.game_components;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.gbjam.Entity;

public class PhysicsComponent {
	public void update(Entity object, ArrayList<Entity> entities) {
		Gdx.app.log("ERROR", "Physics not implemented!");
	}
	
	public boolean collide(Entity object, Entity other) {
		return Intersector.overlapConvexPolygons(object.getPolygon(), other.getPolygon());
	}
	
	public boolean bottomFoot(Entity object) {
		return false;
	}
}
