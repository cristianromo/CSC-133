package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TickCommand extends Command{

	private GameWorld gw;
	public TickCommand(GameWorld gw) {
		super("tick");
		this.gw=gw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//gw.tick();
		System.out.println("tick button pressed");
	}
}
