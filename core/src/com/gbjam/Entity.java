package com.gbjam;

import java.util.ArrayList;

import com.badlogic.gdx.math.Polygon;
import com.gbjam.game_components.*;
import com.gbjam.utility.Point;

public class Entity {
	/** Components */
	private GraphicsComponent graphics;
	private PhysicsComponent physics;
	private InputComponent input;
	private GeneratorComponent generator;
	
	/** Physics-related values */
	private Polygon polygon;
	private float x, y, dx, dy;
	private Point size;
	
	/** Used by GeneratorComponent - do we need to generate an object? */
	private boolean generate;
	private int genTime;
	
	/** Is the user tryna jump? */
	private boolean jumping;
	private boolean canJump;
	
	public Entity(GraphicsComponent _graphics, PhysicsComponent _physics, InputComponent _input, 
			GeneratorComponent _generator) {
		graphics = _graphics;
		physics = _physics;
		input = _input;
		generator = _generator;

		generate = false;
		genTime = 0;
		x = y = 0;
		dx = dy = 0;
		size = graphics.getTextureSize();
	}
	
	public Entity clone() {
		Entity newEntity = new Entity(graphics, physics, input, generator);
		
		newEntity.x = x;
		newEntity.y = y;
		newEntity.dx = dx;
		newEntity.dy = dy;
		newEntity.size = size;
		newEntity.generate = generate;
		newEntity.genTime = genTime;
		
		return newEntity;
	}
	
	public void update(float delta, ArrayList<Entity> entities) {
		// Update according to input
		if(input != null)
			input.update(this);

		// Then game logic
		if(physics != null)
			physics.update(this, entities);
		
		// Then generate new things, if necessary
		if(generator != null)
			generator.update(this);
		
		// Then draw
		if(graphics != null)
			graphics.render(this);
	}

	public GraphicsComponent getGraphicsComponent() { return graphics; }
	public PhysicsComponent getPhysicsComponent() { return physics; }
	public InputComponent getInputComponent() { return input; }
	public GeneratorComponent getGeneratorComponent() { return generator; }
	
	public float getX() { return polygon != null ? polygon.getX() : x; }
	public float getY() { return polygon != null ? polygon.getY() : y; }
	public float getW() { return size.getW(); }
	public float getH() { return size.getH(); }
	public float getDX() { return dx; }
	public float getDY() { return dy; }
	public boolean generate() { return generate; }
	public boolean getCanJump() { return canJump; }

	public void setX(float _x) {
		x = _x; 
		if (polygon != null)
			polygon.setPosition(x, y);
	}
	public void setY(float _y) { 
		y = _y;
		if (polygon != null)
			polygon.setPosition(x, y);
	}
	public void setW(float _w) { size = new Point(_w, size.getH()); }
	public void setH(float _h) { size = new Point(size.getW(), _h); }
	public void setDX(float _dx) { dx = _dx; }
	public void setDY(float _dy) { dy = _dy; }
	public void setGenerate(boolean _generate) { generate = _generate; }
	public void setTrynaJump(boolean _jumping) { jumping = _jumping; }
	public void setCanJump(boolean _canJump) { canJump = _canJump; }

	public void setPolygon(Polygon poly) {
		this.polygon = poly;
		x = poly.getOriginX();
		y = poly.getOriginY();
		size = new Point(poly.getBoundingRectangle().width, poly.getBoundingRectangle().height);
	}

	public Polygon getPolygon() {
		return this.polygon;
	}
}
