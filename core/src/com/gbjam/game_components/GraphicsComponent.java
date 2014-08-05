package com.gbjam.game_components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gbjam.Entity;
import com.gbjam.resource_mgmt.AnimationSheet;
import com.gbjam.resource_mgmt.GraphicsService;

public class GraphicsComponent {
	private TextureRegion[][] textures;
	/** Which frame the sprite is on. */
	private int frame = 0;
	/** Which 'state' the sprite is in - changes on direction change / hurt or not, etc */
	private int state = 0;
	protected int ticksSinceLastFrame = 0;
	
	/** How many frames make up each state of the player */
	private int[] numFrames;
	
	public GraphicsComponent(AnimationSheet sheet) {
		textures = sheet.textures;
		numFrames = sheet.numFrames;
	}
	
	public void render(Entity object) {
		GraphicsService.draw(textures[frame][state], object.getX(), object.getY());
		ticksSinceLastFrame++;
	}
	
	public void incrementFrame() {
		ticksSinceLastFrame = 0;
		this.frame++;
		this.frame %= this.numFrames[state];
	}
	
	public void setFrame(int frame) {
		if (this.frame != frame) {
			this.ticksSinceLastFrame = 0;
			this.frame = frame;
		}
	}
	
	public void setState(int state) {
		if (this.state != state) {
			this.ticksSinceLastFrame = 0;
			this.state = state;
			this.frame = 0;
		}
	}
}
