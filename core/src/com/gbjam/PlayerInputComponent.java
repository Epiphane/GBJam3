package com.gbjam;

import com.badlogic.gdx.Input.Keys;

public class PlayerInputComponent extends InputComponent {
	private Point movement;
	private class DirectionCommand implements Command {
		private int dir;
		
		DirectionCommand(int _dir) {
			dir = _dir;
		}

		public void execute(boolean press) {
			Point _movement = Utility.pointFromDir(dir);
			if(press)
				movement = movement.add(_movement);
			else
				movement = movement.sub(_movement);
		}
	}

	PlayerInputComponent() {
		InputService.setKeyCallback(Keys.DPAD_UP, new DirectionCommand(0));
		InputService.setKeyCallback(Keys.DPAD_RIGHT, new DirectionCommand(2));
		InputService.setKeyCallback(Keys.DPAD_DOWN, new DirectionCommand(4));
		InputService.setKeyCallback(Keys.DPAD_LEFT, new DirectionCommand(6));
		movement = new Point(0, 0);
	}
	
	public void update(Entity player) {
		player.setDX(movement.getX());
		player.setDY(movement.getY());
	}
}
