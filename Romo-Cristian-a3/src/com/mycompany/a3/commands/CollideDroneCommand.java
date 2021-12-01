package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CollideDroneCommand extends Command{

	private GameWorld gw;
	public CollideDroneCommand(GameWorld gw) {
		super("collide with Drone");
		this.gw=gw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gw.droneCollidesCyborg();
		System.out.println("Collided with Drone");
	}
}
