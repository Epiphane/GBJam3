package com.gbjam.game_components.collision;

import com.gbjam.Entity;
import com.gbjam.game_components.status.AttributeComponent.AttribType;
import com.gbjam.game_components.status.StatusComponent.StatusType;

public class EnemyCollisionComponent extends WalkingCollisionComponent {
	protected void bumpedWithLeftSide(Entity me, Entity collider) {
		// HAH. DIE, PLAYER SCUM
		if(collider.getCollisionComponent().type == ColliderType.PLAYER) {
			collider.inflictStatus(StatusType.HURT, me.getAttribute(AttribType.ATTACK));
		}
	}
}
