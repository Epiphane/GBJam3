package com.gbjam.game_components.input;

import com.badlogic.gdx.Input.Keys;
import com.gbjam.Command;
import com.gbjam.Entity;
import com.gbjam.InputService;
import com.gbjam.game_components.status.StatusComponent.StatusType;
import com.gbjam.utility.Point;
import com.gbjam.utility.Utility;

public class PlayerInputComponent extends InputComponent {
	private Point movement;
	private boolean shoot;
	private boolean jumping, switchWeapon;
	
	private class DirectionCommand implements Command {
		private int dir;
		
		DirectionCommand(int _dir) {
			dir = _dir;
		}

		@Override
		public void execute(boolean press) {
			Point _movement = Utility.pointFromDir(dir);
			if(press)
				movement = movement.add(_movement);
			else
				movement = movement.sub(_movement);
		}
	}
	
	private class JumpCommand implements Command {
		@Override
		public void execute(boolean press) {
			jumping = press;
		}
	}

	private class ShootCommand implements Command {
		public void execute(boolean press) {
			shoot = press;
		}
	}

	private class SwitchWeaponCommand implements Command {
		public void execute(boolean press) {
			if(press) {
				switchWeapon = true;
			}
		}
	}

	public PlayerInputComponent() {
		InputService.setKeyCallback(Keys.DPAD_UP, new DirectionCommand(0));
		InputService.setKeyCallback(Keys.DPAD_RIGHT, new DirectionCommand(2));
		InputService.setKeyCallback(Keys.DPAD_DOWN, new DirectionCommand(4));
		InputService.setKeyCallback(Keys.DPAD_LEFT, new DirectionCommand(6));
		InputService.setKeyCallback(Keys.X, new ShootCommand());
		InputService.setKeyCallback(Keys.Z, new JumpCommand());
		InputService.setKeyCallback(Keys.A, new SwitchWeaponCommand());
		movement = new Point(0, 0);
	}
	
	public void update(Entity player) {
		if(!player.is(StatusType.KNOCKBACK))
			player.setDX(movement.getX() * 2);
		
		player.setGenerate(shoot);
		
		if(switchWeapon) {
			int temp = player.getGeneratorComponent().getTemplate() + 1;
			if(temp == 3) temp = 0;	
			
			player.getGeneratorComponent().setTemplate(temp);
			
			switchWeapon = false;
		}
		
		if (jumping && player.getCanJump()) {
			player.setDY(6.2f);
		}
	}
}
