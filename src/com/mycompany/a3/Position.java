package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Position extends Command {
	
	// Create a private GameWorld object so we can manipulate the one that is given in the constructor
	private GameWorld gw;

	
	// Constructor
	public Position(GameWorld gw) {
		
		// Pass the overridden text to the parent constructor
		super("Position");
		
		// Set the local GameWorld (gw) object to the one that was given in the constructor
		this.gw = gw;
		
	}

	// This action will be performed when the command is pushed
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Check to see if game is currently paused
		if (gw.getPaused()) {
			
			// Revert the position variable to the opposite of what it currently was
			gw.revertPosition();
			//gw.getCurrentClock();
			
		}
		
	}
	
}