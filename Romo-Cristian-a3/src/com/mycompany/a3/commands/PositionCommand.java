package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;

public class PositionCommand extends Command{

	private Game g;
	public PositionCommand(Game g) {
		super("position");
		this.g=g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		g.pause();
		System.out.println("position");
	}
}
