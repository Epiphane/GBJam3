package com.gbjam;

import java.util.ArrayList;

import com.gbjam.game_components.*;
import com.gbjam.utility.Point;

public class Entity {
	private GraphicsComponent graphics;
	private PhysicsComponent physics;
	private InputComponent input;
	private float x, y, dx, dy;
	private Point size;
	
	Entity(GraphicsComponent _graphics, PhysicsComponent _physics, InputComponent _input) {
		graphics = _graphics;
		physics = _physics;
		input = _input;

		x = y = 0;
		dx = dy = 0;
		size = graphics.getTextureSize();
	}
	
	public void update(float delta, ArrayList<Entity> entities) {
		// Update according to input
		if(input != null)
			input.update(this);

		// Then game logic
		if(physics != null)
			physics.update(this, entities);
		
		// Then draw
		if(graphics != null)
			graphics.render(this);
	}

	public GraphicsComponent getGraphicsComponent() { return graphics; }
	public PhysicsComponent getPhysicsComponent() { return physics; }
	public InputComponent getInputComponent() { return input; }
	
	public float getX() { return x; }
	public float getY() { return y; }
	public float getW() { return size.getW(); }
	public float getH() { return size.getH(); }
	public float getDX() { return dx; }
	public float getDY() { return dy; }

	public void setX(float _x) { x = _x; }
	public void setY(float _y) { y = _y; }
	public void setW(float _w) { size = new Point(_w, size.getH()); }
	public void setH(float _h) { size = new Point(size.getW(), _h); }
	public void setDX(float _dx) { dx = _dx; }
	public void setDY(float _dy) { dy = _dy; }
}
