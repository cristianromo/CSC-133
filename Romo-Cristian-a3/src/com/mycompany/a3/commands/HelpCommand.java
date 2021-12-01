package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class HelpCommand extends Command{

	
	public HelpCommand(GameWorld gw) {
		super("help");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Dialog.show("Help", "here are a list of instructions: \n "
				+ " a: accelerate player cyborg \n"
				+ " b: slow down player cyborg \n"
				+ " l: turn left \n"
				+ " r: turn right \n"
				+ " e: simulate a collision with an energy station \n"
				+ " g: simulate a collision with a drone \n"
				+ " t: tick the game clock and advance time \n"
				+ " m: print out text version of map to keep track of objects", "Ok", null);
		System.out.println("help button pressed");
	}

}