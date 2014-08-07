package com.gbjam.game_components;

import com.gbjam.Entity;
import com.gbjam.resource_mgmt.RandomizerService;

public class EnemyInputComponent extends InputComponent {
	public int restTimer, walkTimer;
	public int timer;
	public int walking;
	
	public EnemyInputComponent() {
		restTimer = 80;
		walkTimer = 50;
		timer = restTimer;
		walking = 0;
	}
	
	public void update(Entity object) {
		object.setDX(walking * 0.5f);
		if(--timer <= 0) {
			System.out.println(walking);
			if(walking == 0) {
				walking = (RandomizerService.random() > 0.5f) ? 1 : -1;
				timer = walkTimer;
			}
			else {
				walking = 0;
				timer = restTimer;
			}
		}
	}
}