package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;

public class PauseCommand extends Command{

	private Game g;
	public PauseCommand(Game g) {
		super("pause/play");
		this.g=g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		g.pause();
		System.out.println("pause/play");
	}
}
