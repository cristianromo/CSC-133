package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AboutCommand extends Command{

	public AboutCommand(GameWorld gw) {
		super("about game");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Dialog.show("About Program",
				"This program was created for Assignment 2 \n Made by Cristian Romo for CSC 133 - Gordon \n"
				+ "The purpose of the game is to get to all bases in sequential order \n"
				+ "and not run out of energy while doing so! Be sure not to hit any \n"
				+ "drones or other NPCs. Good Luck!", "OK", null);
	}
}
