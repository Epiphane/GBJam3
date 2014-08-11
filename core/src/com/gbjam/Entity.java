package com.gbjam;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Polygon;
import com.gbjam.game_components.collision.CollisionComponent;
import com.gbjam.game_components.generator.GeneratorComponent;
import com.gbjam.game_components.graphics.GraphicsComponent;
import com.gbjam.game_components.input.InputComponent;
import com.gbjam.game_components.physics.PhysicsComponent;
import com.gbjam.game_components.status.AttributeComponent;
import com.gbjam.game_components.status.AttributeComponent.AttribType;
import com.gbjam.game_components.status.StatusComponent;
import com.gbjam.game_components.status.StatusComponent.StatusType;
import com.gbjam.utility.Point;

public class Entity {
	/** Components */
	private GraphicsComponent graphics;
	private CollisionComponent collision;
	private PhysicsComponent physics;
	private StatusComponent status;
	private AttributeComponent attributes;
	private InputComponent input;
	private GeneratorComponent generator;
	
	/** For graphics */
	private boolean facingRight;

	/** Physics-related values */
	private Polygon polygon;
	private float x, y, dx, dy;

	/** Used by GeneratorComponent - do we need to generate an object? */
	private boolean generate;
	private int genTime;

	/** Is the user tryna jump? */
	private boolean jumping;
	private boolean canJump;

	/** Standing on platform (so not affected by gravity) */
	private boolean onGround;
	
	private boolean initialized;
	
	public Entity() {
	}

	public void init(GraphicsComponent _graphics, CollisionComponent _collision,
			PhysicsComponent _physics, InputComponent _input, StatusComponent _status, AttributeComponent _attributes,
			GeneratorComponent _generator) {
		graphics = _graphics;
		collision = _collision;
		physics = _physics;
		status = _status;
		attributes = _attributes;
		input = _input;
		generator = _generator;

		if (graphics != null && polygon == null) {
			Point size = graphics.getTextureSize();
			polygon = new Polygon(new float[] { 0, 0, size.getW(), 0,
					size.getW(), size.getH(), 0, size.getH() });
		}
	
		initialized = true;
	}
	
	public boolean initialized() { return initialized; }

	public Entity clone() {
		Entity newEntity = new Entity();
		newEntity.init(graphics, collision, physics, input, status, attributes, generator);

		if(graphics != null) // Clone one for myself
			graphics = graphics.clone();
		if(input != null) // Clone one for myself
			input = input.clone();
		if(collision != null) // Clone one for myself
			collision = collision.clone();
		if(status != null) // Clone one for myself
			status = status.clone();
		if(attributes != null)
			attributes = attributes.clone();

		newEntity.x = x;
		newEntity.y = y;
		newEntity.dx = dx;
		newEntity.dy = dy;
		newEntity.generate = generate;
		newEntity.genTime = genTime;
		if(polygon != null) {
			newEntity.polygon = new Polygon(polygon.getVertices());
			newEntity.polygon.setPosition(x, y);
		}

		return newEntity;
	}

	public void update(float delta, ArrayList<Entity> entities) {
		// Update according to input
		if (status != null)
			status.update(this);
		if (input != null)
			input.update(this);

		// Then game logic
		if (physics != null)
			physics.update(this);

		// Play bumper cars
		if (collision != null)
			collision.update(this, entities);

		// Then generate new things, if necessary
		if (generator != null)
			generator.update(this);

		// Then draw
		if (graphics != null)
			graphics.render(this);
	}

	public GraphicsComponent getGraphicsComponent() {
		return graphics;
	}

	public PhysicsComponent getPhysicsComponent() {
		return physics;
	}

	public InputComponent getInputComponent() {
		return input;
	}

	public GeneratorComponent getGeneratorComponent() {
		return generator;
	}

	public StatusComponent getStatusComponent() {
		return status;
	}

	public CollisionComponent getCollisionComponent() {
		return collision;
	}

	public float getX() {
		return polygon != null ? polygon.getX() : x;
	}

	public float getY() {
		return polygon != null ? polygon.getY() : y;
	}

	public float getDX() {
		return dx;
	}

	public float getDY() {
		return dy;
	}

	public boolean generate() {
		return generate;
	}

	public boolean getCanJump() {
		return canJump;
	}

	public boolean getOnGround() {
		return onGround;
	}

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

	public void setDX(float _dx) {
		dx = _dx;
	}

	public void setDY(float _dy) {
		dy = _dy;
	}

	public void setGenerate(boolean _generate) {
		generate = _generate;
	}

	public void setTrynaJump(boolean _jumping) {
		jumping = _jumping;
	}

	public void setCanJump(boolean _canJump) {
		canJump = _canJump;
	}

	public void setOnGround(boolean _onGround) {
		onGround = _onGround;
		canJump = true;
	}

	public void setPolygon(Polygon poly) {
		this.polygon = poly;

		if(graphics != null) {
			float vertices[] = poly.getVertices();
			graphics.setTextureOffset(new Point(vertices[0], vertices[1]));
		}
	}

	public Polygon getPolygon() {
		return this.polygon;
	}

	public void inflictStatus(StatusType status, int extent) {
		if(this.status != null) {
			switch(status) {
			case HURT:
				int health = attributes.getAttribute(AttribType.HEALTH) - extent;
				attributes.setAttribute(AttribType.HEALTH, health);
				break;
			case KNOCKBACK:
				setDX(3 * extent);
				setDY(dy + 2);
				break;
			}
		}
		
		if(attributes != null && attributes.getAttribute(AttribType.HEALTH) <= 0)
			this.status.setStatus(StatusType.DEAD, true);
		
		this.status.setStatus(status, true);
	}
	
	public boolean is(StatusType _status) {
		return status != null ? status.is(_status) : false;
	}
	
	public void setStatus(StatusType _status, boolean state) {
		if(status != null) {
			status.setStatus(_status, state);
		}
	}

	public int getAttribute(AttribType attrib) {
		if(attributes != null)
			return attributes.getAttribute(attrib);
		else {
			Gdx.app.log("WARNING", "Getting attribute " + attrib + ", but I have no AttributeComponent!");
			return 0;
		}
	}

	public void setAttribute(AttribType attrib, int val) {
		if(attributes != null)
			attributes.setAttribute(attrib, val);
		else
			Gdx.app.log("WARNING", "Setting attribute " + attrib + ", but I have no AttributeComponent!");
	}

	public boolean getFacingRight() {
		return facingRight;
	}

	public void setFacingRight(boolean faceRight) {
		this.facingRight = faceRight;
	}
}
