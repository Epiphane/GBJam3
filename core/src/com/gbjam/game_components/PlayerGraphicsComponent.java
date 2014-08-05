package com.gbjam.game_components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gbjam.Entity;

public class PlayerGraphicsComponent extends GraphicsComponent {
	private static final int TICKS_PER_WALK = 2;
	private static final int TICKS_PER_IDLE = 10;
	
	public PlayerGraphicsComponent(TextureRegion[][] _texture, int[] _numFrames) {
		super(_texture, _numFrames);
	}

	@Override
	public void render(Entity object) {
		// Do check here to see what state the entity is in (hurt, moving, star-power, whatever)
		
		if (object.getDX() == 0) {
			setState(0);
			
			if (this.ticksSinceLastFrame == TICKS_PER_IDLE)
				incrementFrame();
		}
		else if (object.getDX() > 0) {
			setState(1);
			
			if (this.ticksSinceLastFrame == TICKS_PER_WALK)
				incrementFrame();
		}
		else if (object.getDX() < 0) {
			setState(0);
			
			if (this.ticksSinceLastFrame == TICKS_PER_WALK) 
				incrementFrame();
		}
	}
}
