package com.gbjam.game_components;

import com.gbjam.Entity;
import com.gbjam.GameScreen;
import com.gbjam.resource_mgmt.Sounds;

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
		world.addEntity(template.clone());
		timer = genTime;
	}
}
