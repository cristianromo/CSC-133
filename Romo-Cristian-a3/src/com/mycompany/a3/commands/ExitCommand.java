package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ExitCommand extends Command {

	public ExitCommand(GameWorld gw) {
		super("exit");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Exit button pushed");
		Boolean bOk = Dialog.show("Confirm quit", "Are you sure you want to exit?", "Ok", "Cancel");
		if (bOk) {
			Display.getInstance().exitApplication();
		}
	}
}
