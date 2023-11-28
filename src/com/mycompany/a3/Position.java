package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Position extends Command {
	
	private GameWorld gw;

	
	public Position(GameWorld gw) {		
		super("Position");		
		this.gw = gw;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {		
		if (gw.getPaused()) {			
			gw.revertPosition();
			//gw.getCurrentClock();
			
		}
		
	}
	
}
