package com.gbjam.game_components.input;

import com.gbjam.Entity;
import com.gbjam.utility.Utility;

public class AIInputComponent extends InputComponent {
	public int restTimer, walkTimer;
	public int timer;
	public int walking;
	
	public AIInputComponent() {
		restTimer = 80;
		walkTimer = 50;
		timer = restTimer;
		walking = 0;
	}
	
	public InputComponent clone() {
		AIInputComponent newComponent = new AIInputComponent();
		
		return newComponent;
	}
	
	public void update(Entity object) {
		object.setDX(walking * 0.5f);
		
		if(--timer <= 0) {
			if(walking == 0) {
				walking = (Utility.random(0, 1) == 0) ? 1 : -1;
				timer = walkTimer;
			}
			else {
				walking = 0;
				timer = restTimer;
			}
		}
		
		if(walking > 0 && object.getCollisionComponent().blockedRight
				|| walking < 0 && object.getCollisionComponent().blockedLeft)
			walking *= -1;
	}
}