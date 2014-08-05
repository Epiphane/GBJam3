package com.gbjam.game_components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gbjam.Entity;
import com.gbjam.resource_mgmt.GraphicsService;
import com.gbjam.utility.Point;

public class GraphicsComponent {
	private TextureRegion[][] texture;
	/** Which frame the sprite is on. */
	private int frame = 0;
	/** Which 'state' the sprite is in - changes on direction change / hurt or not, etc */
	private int state = 0;
	private int ticksSinceLastFrame = 0;
	
	public GraphicsComponent(TextureRegion[][] _texture) {
		texture = _texture;
	}
	
	public Point getTextureSize() {
		return new Point(texture.getWidth(), texture.getHeight());
	}
	
	public void render(Entity object) {
		GraphicsService.draw(texture[frame][state], object.getX(), object.getY());
		ticksSinceLastFrame++;
	}
}
