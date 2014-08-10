package com.gbjam.game_components.generator;

import com.gbjam.Entity;
import com.gbjam.game_components.status.StatusComponent.StatusType;
import com.gbjam.resource_mgmt.EntityFactory;
import com.gbjam.resource_mgmt.Sounds;
import com.gbjam.utility.Point;

public class WeaponGeneratorComponent extends MultiGeneratorComponent {
	public int activeWeapons[];
	
	public WeaponGeneratorComponent() {
		super();
		
		activeWeapons = new int[3];
		activeWeapons[0] = 0;
		activeWeapons[1] = 1;
		activeWeapons[2] = 2;
	}
	
	public void setTemplate(Entity e) {
		entities = new Entity[3];

		entities[0] = EntityFactory.generate("bullet", null);
		entities[1] = EntityFactory.generate("fire", null);
		entities[2] = EntityFactory.generate("bullet", null);
		
		template = entities[0];
		currentTemplate = 0;
	}
	
	public void setTemplate(int i) {
		if(i < 3)
			template = entities[activeWeapons[i]];
		
		this.setSoundToPlay(Sounds.weapons[activeWeapons[i]]);
		currentTemplate = i;
	}
	
	public int getTemplate() {
		return currentTemplate;
	}
	
	public void update(Entity other) {
		switch(activeWeapons[currentTemplate]) {
		case 0: // Machine gun
			other.getStatusComponent().statusTicks[StatusType.RECOIL.ordinal()] = 6;
			offset = new Point(10 + (other.getFacingRight() ? 5 : -5), 7); 
			break;
		case 1: // Flamethrower
			other.getStatusComponent().statusTicks[StatusType.RECOIL.ordinal()] = 2;
			template.setDX(other.getDX() + (other.getFacingRight() ? 1 : -1));
			template.setDY((float) Math.random() - 0.5f);
			offset = new Point(7 + (other.getFacingRight() ? 10 : -10), 4); 
			break;
		case 2: // Shotgun
			other.getStatusComponent().statusTicks[StatusType.RECOIL.ordinal()] = 25;
			offset = new Point(10 + (other.getFacingRight() ? 5 : -5), 7); 
			break;
		}
		
		if((other.getDX() > 0) != (template.getDX() > 0) && other.getDX() != 0) {
			template.setDX(template.getDX() * -1);
		}
		
		template.setX(other.getX());
		template.setY(other.getY());
		super.update(other);
	}
	
	@Override
	public void generate(Entity me) {
		super.generate(me);

		// Shotgun
		if(activeWeapons[currentTemplate] == 2) {
			Entity clone = template.clone();
			clone.setX(clone.getX() + offset.getX());
			clone.setY(clone.getY() + offset.getY());
			clone.setDY((float) (Math.random() * 2) - 1);
			world.addEntity(clone);
			
			Entity clone2 = template.clone();
			clone2.setX(clone2.getX() + offset.getX());
			clone2.setY(clone2.getY() + offset.getY());
			clone2.setDY((float) (Math.random() * 2) - 1);
			world.addEntity(clone2);
		}
	}
}
