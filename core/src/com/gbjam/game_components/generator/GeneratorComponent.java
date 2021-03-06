package com.gbjam.game_components.generator;

import com.gbjam.Entity;
import com.gbjam.GameScreen;
import com.gbjam.game_components.status.StatusComponent.StatusType;
import com.gbjam.resource_mgmt.Sounds;
import com.gbjam.utility.Point;

/**
 * Generator Component
 * 
 * Useful for Entity's that "generate" other entities,
 * like the character's gun that spawns bullets.
 */
public class GeneratorComponent {
	protected GameScreen world;
	protected Entity template;
	protected int soundToPlay;
	public String soundName;
	protected Point offset;
	
	/**
	 * Stores two references: the world that we'll be generating entities to,
	 * and a "template entity" that we will clone.
	 */
	public GeneratorComponent() {
		soundToPlay = Sounds.NO_SOUND;
	}

	public void setWorld(GameScreen _world) {
		world = _world;
		if(template != null && template.getGeneratorComponent() != null) {
			template.getGeneratorComponent().setWorld(_world);
		}
	}

	public void setTemplate(Entity _template) {
		template = _template;
	}
	
	public void setTemplate(int i) {
		// Override me!
	}
	
	public int getTemplate() { 
		return 0; 
	}
	
	public void setSoundToPlay(int _soundToPlay) {
		soundToPlay = _soundToPlay;
	}
	
	public void setOffset(Point _offset) {
		offset = _offset;
	}
	
	public Point getOffset() {
		return offset;
	}
	
	public GeneratorComponent clone() {
		GeneratorComponent newComponent;
		try {
			newComponent = this.getClass().newInstance();
			
			newComponent.template = this.template.clone();
			newComponent.world = this.world;
			newComponent.soundToPlay = this.soundToPlay;
			newComponent.soundName = this.soundName;
			newComponent.offset = this.offset;
			
			return newComponent;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void update(Entity other) {
		if(other.generate()) {
			if(!other.is(StatusType.RECOIL)) {
				if((other.getDX() > 0) != (template.getDX() > 0) && other.getDX() != 0) {
						template.setDX(template.getDX() * -1);
				}
				
				generate(other);
				other.setStatus(StatusType.RECOIL, true);
			}
		}
		
		template.setX(other.getX());
		template.setY(other.getY());
	}
	
	public void generate(Entity other) {
		if(soundToPlay != Sounds.NO_SOUND) {
			Sounds.playSound(soundToPlay);
		}
		
		Entity clone = template.clone();
			
		clone.setX(clone.getX() + offset.getX());
		clone.setY(clone.getY() + offset.getY());
		world.addEntity(clone);
	}
}
