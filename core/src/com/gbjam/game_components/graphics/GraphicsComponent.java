package com.gbjam.game_components.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.gbjam.Entity;
import com.gbjam.GBJam3;
import com.gbjam.resource_mgmt.AnimationSheet;
import com.gbjam.resource_mgmt.GraphicsService;
import com.gbjam.utility.Point;

public class GraphicsComponent {
	private TextureRegion[][] textures;
	/** Which frame the sprite is on. */
	private int frame = 0;
	/** Which 'state' the sprite is in - changes on direction change / hurt or not, etc */
	private int state = 0;
	protected int ticksSinceLastFrame = 0;
	
	/** How many frames make up each state of the player */
	private int[] numFrames;
	
	private Point textureOffset;
	
	/** This is just for EntityFactory */
	public GraphicsComponent() {
	}
	
	/** Same here. Cause JSON can't call special constructors */
	public void init(AnimationSheet sheet) {
		textures = sheet.textures;
		numFrames = sheet.numFrames;
		setTextureOffset(new Point(0, 0));
	}
	
	public Point getTextureSize() {
		return new Point(textures[0][0].getRegionWidth(), textures[0][0].getRegionHeight());
	}
	
	public void render(Entity object) {
		GraphicsService.draw(textures[frame][state], object.getX() - textureOffset.getX(), object.getY() - textureOffset.getY());
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
	
	/**
	 * Sets animation based on general state, and direction.
	 * Preserves direction if only state is sent and direction == 0
	 */
	public void setState(int state, int direction) {
		if(direction == 0) {
			direction = this.state % 2 == 1 ? 1 : -1;
		}
		
		// Prepare the modulo
		if(direction > 0) direction = 1;
		if(direction < 0) direction = 0;
		
		if (this.state != state * 2 + direction) {
			this.ticksSinceLastFrame = 0;
			this.state = state * 2 + direction;
			this.frame = 0;
		}
	}

	public Point getTextureOffset() {
		return textureOffset;
	}

	public void setTextureOffset(Point textureOffset) {
		this.textureOffset = textureOffset;
	}
}
