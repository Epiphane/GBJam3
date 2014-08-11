package com.gbjam.game_components.collision;

import com.badlogic.gdx.math.Polygon;
import com.gbjam.Entity;
import com.gbjam.GameScreen;
import com.gbjam.game_components.collision.CollisionComponent.ColliderType;
import com.gbjam.game_components.status.AttributeComponent.AttribType;
import com.gbjam.game_components.status.StatusComponent.StatusType;
import com.gbjam.resource_mgmt.Sounds;

public class HeartCollisionComponent extends DynamicCollisionComponent {
	public void init(ColliderType _type) {
		super.init(_type);
		filter[ColliderType.PLAYER.ordinal()] = true;
		filter[ColliderType.PLATFORM.ordinal()] = false;
	}
	
	protected void collideAnyDir(Polygon myPolygon, Entity me, Entity collider) {
		if(collider.getCollisionComponent().type == ColliderType.PLAYER) {
			int health = GameScreen.player.getAttribute(AttribType.HEALTH);
			GameScreen.player.setAttribute(AttribType.HEALTH, health + 10);
			Sounds.playSound(Sounds.HEAL_SOUND);
			
			me.setStatus(StatusType.DEAD, true);
		}
	}
}
