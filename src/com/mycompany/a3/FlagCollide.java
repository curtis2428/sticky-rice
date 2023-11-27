package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class FlagCollide extends Command {
	private GameWorld gw;
	
	public FlagCollide(GameWorld gw) {
		super("Collide with Flag");
		
		this.gw = gw;
	}
	
	@Override
	 public void actionPerformed(ActionEvent e) {
	              
        try {        	      	
        	TextField userInput = new TextField();        	
        	
            Dialog.show("Enter the flag sequence number:", userInput, new Command("OK"));
        	        	
            int intInput = Integer.parseInt(userInput.getText().toString());      	
        	
        	if (intInput > 0 && intInput < 5) {        		        		
        		gw.flagCollide(intInput);        		
        	} 
        	else {        		
        		Dialog.show("Error (invalid number)", "Please enter a valid flag sequence number between 1 and 4." , "OK", null);       		
        	}        	
        } 
        catch (NumberFormatException e1) {        	
        	Dialog.show("Error (letter)", "Please enter a valid flag sequence number between 1 and 4.", "OK", null);        	
        }        
    }
}
