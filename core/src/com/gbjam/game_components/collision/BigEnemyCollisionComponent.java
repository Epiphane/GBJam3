package com.gbjam.game_components.collision;

import com.badlogic.gdx.math.Polygon;
import com.gbjam.Entity;
import com.gbjam.game_components.collision.CollisionComponent.ColliderType;
import com.gbjam.game_components.status.AttributeComponent.AttribType;
import com.gbjam.game_components.status.StatusComponent.StatusType;

public class BigEnemyCollisionComponent extends EnemyCollisionComponent {
	protected void collideAnyDir(Polygon myPolygon, Entity me, Entity collider) {
		if(!collider.is(StatusType.HURT) && collider.getCollisionComponent().type == ColliderType.PLAYER) {
			collider.inflictStatus(StatusType.HURT, me.getAttribute(AttribType.ATTACK));
			collider.inflictStatus(StatusType.KNOCKBACK, -1);
		}
	}
	
	protected void stomped(Entity me, Entity stompedGuy) {
		// override me!
		System.out.println("hit butt");
	}
}
