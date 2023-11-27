package com.mycompany.a3;


import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class Quit extends Command {
	private GameWorld gw;
	
	public Quit(GameWorld gw){
        super("Quit");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent e){
    	Command yes = new Command("Yes");
    	Command no = new Command("No");
    	Command []cmds = new Command[] {yes,no};
        Command c = Dialog.show("Quit","Do you want to quit?",cmds);
        if (c == yes)
            gw.quit();   	
    }
}
