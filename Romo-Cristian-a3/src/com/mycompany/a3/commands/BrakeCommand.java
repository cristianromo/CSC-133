package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class BrakeCommand extends Command{

	private GameWorld gw;
	
	public BrakeCommand(GameWorld gw) {
		super("brake");
		this.gw=gw;
		// TODO Auto-generated constructor stub
	}
	@Override	
	public void actionPerformed(ActionEvent e) {
		gw.brake();
		System.out.println("Brake button pushed");
	}
}