package com.gbjam.game_components.collision;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gbjam.Entity;
import com.gbjam.resource_mgmt.GraphicsService;

public class PlayerCollisionComponent extends WalkingCollisionComponent {
	protected boolean stomped(Entity me, Entity stompedGuy) {
		if(stompedGuy.getCollisionComponent().type == ColliderType.ENEMY) {
			me.setDY(5);
			return true;
		}

		return super.stomped(me, stompedGuy);
	}
	
	public void update(Entity me, ArrayList<Entity> entities) {
		super.update(me, entities);

		OrthographicCamera cam = GraphicsService.getCamera();
		cam.position.x = me.getX();
		cam.position.y = me.getY();
	}
}
