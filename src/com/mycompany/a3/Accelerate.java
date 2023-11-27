package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class Accelerate extends Command {
private GameWorld gw;
	
    public Accelerate(GameWorld gw) {   	
        super("Accelerate");
       
        this.gw = gw;      
    }
       
    @Override
    public void actionPerformed(ActionEvent e) {
    	
        gw.accelerate();        
    }
}
