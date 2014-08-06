package com.gbjam.game_components;

import com.gbjam.Entity;
import com.gbjam.GameScreen;

/**
 * Generator Component
 * 
 * Useful for Entity's that "generate" other entities,
 * like the character's gun that spawns bullets.
 */
public class GeneratorComponent {
	private GameScreen world;
	protected Entity template;
	private int genTime, timer;
	
	/**
	 * Stores two references: the world that we'll be generating entities to,
	 * and a "template entity" that we will clone.
	 */
	public GeneratorComponent(GameScreen _world, Entity _template, int _genTime) {
		world = _world;
		template = _template;
		genTime = _genTime;
		timer = 0;
	}
	
	public void update(Entity other) {
		if(other.generate()) {
			generate();
		}
		else {
			timer = 0;
		}
	}
	
	public void generate() {
		if(timer <= 0) {
			world.addEntity(template.clone());
			timer = genTime;
		}
		timer --;
	}
}
