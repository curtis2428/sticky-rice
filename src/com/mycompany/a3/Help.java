package com.mycompany.a3;


import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class Help extends Command {
	 public Help()
	    {
	        super("Help");
	    }
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	        
	    	String info = "Press these keys for desired action:\n" +
	    				  "A: Acceleration\n"+
	        			  "B: Brake\n"+
	        			  "E: Collide with Food Station\n"+
	        			  "G: Collide with Spider\n"+
	        		      "L: Turn Left\n"+
	        			  "R: Turn Right\n"+
	        		      "T: Tick\n"   
	        		       	+ "";
	        Dialog.show("Help Command",info,"Ok",null);
	    }

}
