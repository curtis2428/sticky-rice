package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ClockTick extends Command {
	private GameWorld gw;
	
    public ClockTick(GameWorld gw) { 	
        super("Tick");
        
        this.gw = gw;      
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        gw.tick();    
    }   
}
