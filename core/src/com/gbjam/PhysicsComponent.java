package com.gbjam;

import com.badlogic.gdx.Gdx;

public class PhysicsComponent {
	public void update(Entity object) {
		Gdx.app.log("ERROR", "Physics not implemented!");
	}
	
	public boolean collide(Entity object, Entity other) {
		return false;
	}
}
