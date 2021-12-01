package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class RightTurnCommand extends Command {

	private GameWorld gw;

	public RightTurnCommand(GameWorld gw) {
		super("right");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gw.turnRight();
		System.out.println("Right button pressed");
	}
}