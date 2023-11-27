package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class FoodCollide extends Command {
	private GameWorld gw;
	private FoodStation foodStation;
	
	public FoodCollide(GameWorld gw) {
		super("Collide with Food Stations");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.foodCollide(foodStation);
	}
}
