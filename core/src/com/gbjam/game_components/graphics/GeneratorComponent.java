package com.gbjam.game_components.graphics;

import com.gbjam.Entity;
import com.gbjam.GameScreen;
import com.gbjam.resource_mgmt.Sounds;
import com.gbjam.utility.Point;

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
	private int soundToPlay;
	private Point offset;
	
	/**
	 * Stores two references: the world that we'll be generating entities to,
	 * and a "template entity" that we will clone.
	 */
	public GeneratorComponent(GameScreen _world, Entity _template, int _genTime) {
		this(_world, _template, _genTime, Sounds.NO_SOUND);
	}
	
	public GeneratorComponent(GameScreen _world, Entity _template, int _genTime, int _soundToPlay) {
		world = _world;
		template = _template;
		genTime = _genTime;
		timer = 0;
		soundToPlay = _soundToPlay;
	}
	
	public void setSoundToPlay(int _soundToPlay) {
		soundToPlay = _soundToPlay;
	}
	
	public void setOffset(Point _offset) {
		offset = _offset;
	}
	
	public void update(Entity other) {
		if(other.generate()) {
			if(timer <= 0) {
				generate();
			}
			timer --;
		}
		else {
			timer = 0;
		}
	}
	
	public void generate() {
		if(soundToPlay != Sounds.NO_SOUND) {
			Sounds.playSound(soundToPlay);
		}
		Entity clone = template.clone();
		clone.setX(clone.getX() + offset.getX());
		clone.setY(clone.getY() + offset.getY());
		world.addEntity(clone);
		timer = genTime;
	}
}
