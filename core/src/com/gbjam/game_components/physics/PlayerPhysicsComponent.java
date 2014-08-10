package com.gbjam.game_components.physics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gbjam.Entity;
import com.gbjam.resource_mgmt.GraphicsService;

public class PlayerPhysicsComponent extends CharacterPhysicsComponent {
	public void update(Entity object) {
		super.update(object);
		OrthographicCamera cam = GraphicsService.getCamera();
		cam.position.x = object.getX();
		cam.position.y = object.getY();
	}
}
