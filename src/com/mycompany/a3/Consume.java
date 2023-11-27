package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Consume extends Command {
	private GameWorld gw;
	
	public Consume(GameWorld gw) {
		super("Consume");
		
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.consume();
	}
}
