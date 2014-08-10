package com.gbjam.game_components.generator;

import com.gbjam.Entity;
import com.gbjam.GameScreen;
import com.gbjam.game_components.status.StatusComponent.StatusType;
import com.gbjam.resource_mgmt.EntityFactory;
import com.gbjam.resource_mgmt.RandomizerService;

public class DragonGeneratorComponent extends MultiGeneratorComponent {
	@Override
	public void setTemplate(Entity e) {
		entities = new Entity[2];
		
		entities[0] = EntityFactory.generate("enemyFire", null);
		entities[1] = EntityFactory.generate("enemyBullet", null);
		
		template = entities[0];
	}
	
	public void setTemplate(int i) {
		super.setTemplate(i);
	}
	
	@Override
	public void update(Entity other) {
		super.update(other);
		
		if(currentTemplate == 0) {
			other.getStatusComponent().statusTicks[StatusType.RECOIL.ordinal()] = 3;
			float distX = GameScreen.player.getX() - template.getX();
			template.setDX(distX / 20);
			float distY = GameScreen.player.getY() - template.getY();
			if(distY < -0.2f)
				template.setDY(distY / 20);
		}
		else
			other.getStatusComponent().statusTicks[StatusType.RECOIL.ordinal()] = 1;
		
		template.setX(other.getX());
		template.setY(other.getY());
	}

	@Override
	public void generate(Entity other) {
		float y = RandomizerService.random() * 6 + 1;
		template.setY(other.getY() + y);
		
		super.generate(other);
	}
}
