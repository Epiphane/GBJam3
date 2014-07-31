package com.gbjam;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GraphicsComponent {
	private SpriteBatch batch;
	private Texture texture;
	
	GraphicsComponent(SpriteBatch _batch, Texture _texture) {
		batch = _batch;
		texture = _texture;
	}
	
	public void render() {
		batch.draw(texture, 0, 0, 100, 100);
	}
}
