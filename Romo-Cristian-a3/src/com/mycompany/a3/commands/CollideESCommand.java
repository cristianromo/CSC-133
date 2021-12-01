package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CollideESCommand extends Command{

	private GameWorld gw;
	public CollideESCommand(GameWorld gw) {
		super("collide with Energy Station");
		this.gw=gw;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gw.cyborgCollidesEnergy();
		System.out.println("Collided with an Energy Station");
	}
}
