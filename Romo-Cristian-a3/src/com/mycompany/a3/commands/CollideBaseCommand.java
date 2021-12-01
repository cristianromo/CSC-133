package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CollideBaseCommand extends Command {

	private GameWorld gw;

	public CollideBaseCommand(GameWorld gw) {
		super("collide with Base");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//gw.cyborgCollidesBase();
		System.out.println("Collided with Base button pressed");
	}
}