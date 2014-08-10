package com.gbjam.game_components.collision;

import com.badlogic.gdx.math.Polygon;
import com.gbjam.Entity;
import com.gbjam.game_components.status.AttributeComponent.AttribType;
import com.gbjam.game_components.status.StatusComponent.StatusType;

public class BulletCollisionComponent extends DynamicCollisionComponent {
	
	public void init(ColliderType type_) {
		super.init(type_);
		filter[ColliderType.ENEMY.ordinal()] = true;
		filter[ColliderType.BOSS.ordinal()] = true;
	}
	
	@Override
	protected void collideAnyDir(Polygon myPolygon, Entity me, Entity collider) {
		if(collider.getCollisionComponent().type == ColliderType.ENEMY ||
				collider.getCollisionComponent().type == ColliderType.BOSS) {
			collider.inflictStatus(StatusType.HURT, me.getAttribute(AttribType.ATTACK));
		}
		me.setStatus(StatusType.DEAD, true);
	}
}
