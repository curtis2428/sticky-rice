package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class About extends Command {
	public About()
    {
        super("About");
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String info = "Welcome to The Journey\n"
        		+ "Professor Muyan\n"
        		+"Author: Curtis Berry\n"
        		+ "Assignment #2";
        Dialog.show("About",info,"Ok",null);
    }

}
