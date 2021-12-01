package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AccelerateCommand extends Command{

	private GameWorld gw;
	
	public AccelerateCommand(GameWorld gw) {
		super("accelerate");
		this.gw=gw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.accelerate();
		System.out.println("Accelerate button pushed");
	}
}
