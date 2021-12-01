package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CollideNPCCommand extends Command {

	private GameWorld gw;

	public CollideNPCCommand(GameWorld gw) {
		super("collide with NPC");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gw.cyborgCollidesCyborg();
		System.out.println("Collided with NPC");
	}
}
