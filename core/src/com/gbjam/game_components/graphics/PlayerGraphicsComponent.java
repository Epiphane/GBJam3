package com.gbjam.game_components.graphics;

import com.gbjam.Entity;
import com.gbjam.game_components.status.StatusComponent.StatusType;

public class PlayerGraphicsComponent extends GraphicsComponent {
	private static final int TICKS_PER_WALK = 7;
	private static final int TICKS_PER_IDLE = 14;

	@Override
	public void render(Entity object) {
		// Do check here to see what state the entity is in (hurt, moving, star-power, whatever)
		super.render(object);

		if (object.getDX() == 0) {
			setState(0, (int) object.getDX());
			if (this.ticksSinceLastFrame >= TICKS_PER_IDLE)
				incrementFrame();
		}
		else if (object.getDX() > 0) {
			setState(1, (int) object.getDX());
			if (this.ticksSinceLastFrame >= TICKS_PER_WALK)
				incrementFrame();
		}
		else if (object.getDX() < 0) {
			setState(1, (int) object.getDX());
			if (this.ticksSinceLastFrame >= TICKS_PER_WALK) 
				incrementFrame();
		}
		
		if(object.is(StatusType.RECOIL)) {
			setState(2, (int) object.getDX());
		}
	}
}
