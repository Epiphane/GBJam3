package com.gbjam.game_components.graphics;

import com.gbjam.Entity;
import com.gbjam.game_components.status.StatusComponent.StatusType;

public class PlayerGraphicsComponent extends GraphicsComponent {
	protected static final int TICKS_PER_WALK = 7;
	protected static final int TICKS_PER_IDLE = 14;
	protected static final int TICKS_PER_HURT = 5;

	@Override
	public void render(Entity object) {
		// Do check here to see what state the entity is in (hurt, moving, star-power, whatever)
		super.render(object);

		if(object.is(StatusType.HURT)) {
			setState(3, (int) object.getDX());
			if (this.ticksSinceLastFrame >= TICKS_PER_HURT) {
				incrementFrame();
			}
		}
		else {
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
				incrementFrame();
			}
		}
	}
	
	public GraphicsComponent clone() {
		PlayerGraphicsComponent newComponent = new PlayerGraphicsComponent();
		
		newComponent.ticksSinceLastFrame = ticksSinceLastFrame;
		newComponent.textures = textures;
		newComponent.frame = frame;
		newComponent.state = state;
		newComponent.numFrames = numFrames;
		newComponent.textureOffset = textureOffset;
		
		return newComponent;
	}
}
