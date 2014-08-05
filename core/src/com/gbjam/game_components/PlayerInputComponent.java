package com.gbjam.game_components;

import com.badlogic.gdx.Input.Keys;
import com.gbjam.Command;
import com.gbjam.GameObject;
import com.gbjam.InputService;
import com.gbjam.utility.Point;
import com.gbjam.utility.Utility;

public class PlayerInputComponent extends InputComponent {
	private Point movement;
	private class DirectionCommand implements Command {
		private int dir;
		
		DirectionCommand(int _dir) {
			dir = _dir;
		}

		public void execute(boolean press) {
			Point _movement = Utility.pointFromDir(dir);
			movement = movement.add(_movement);
		}
	}

	PlayerInputComponent() {
		InputService.setKeyCallback(Keys.DPAD_UP, new DirectionCommand(0));
		InputService.setKeyCallback(Keys.DPAD_RIGHT, new DirectionCommand(2));
		InputService.setKeyCallback(Keys.DPAD_DOWN, new DirectionCommand(4));
		InputService.setKeyCallback(Keys.DPAD_LEFT, new DirectionCommand(6));
		movement = new Point(0, 0);
	}
	
	public void update(GameObject player) {
		player.setX(player.getX() + movement.getX());
		player.setY(player.getY() + movement.getY());
		movement = new Point(0, 0);
	}
}
