package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {

		private GameWorld gw;
		
		private Label healthLevel;
		private Label sound;
		private Label lives;
		private Label clock;
		private Label lastFlagReached;
		private Label foodLevel;
		
		
		public ScoreView(GameWorld gw) {

			this.gw = gw;

			this.setLayout(new FlowLayout(Component.CENTER));
			
			//Creating all the labels for top display
			Label timerLabel = new Label("Time:");
			clock = new Label();
			timerLabel.getAllStyles().setFgColor(ColorUtil.BLUE);		
			this.add(timerLabel);
			this.add(clock);

			Label livesLabel = new Label("Lives Left:");
			lives = new Label();
			livesLabel.getAllStyles().setFgColor(ColorUtil.BLUE);		
			this.add(livesLabel);
			this.add(lives);
		
			Label lastFlagReachedLabel = new Label("Last Flag Reached:");
			lastFlagReached = new Label();
			lastFlagReachedLabel.getAllStyles().setFgColor(ColorUtil.BLUE);		
			this.add(lastFlagReachedLabel);
			this.add(lastFlagReached);		
			
			Label foodLevelLabel = new Label("Food Level:");
			foodLevel = new Label();
			foodLevelLabel.getAllStyles().setFgColor(ColorUtil.BLUE);			
			this.add(foodLevelLabel);
			this.add(foodLevel);
		
			Label healthLevelLabel = new Label("Health Level: ");
			healthLevel = new Label();
			healthLevelLabel.getAllStyles().setFgColor(ColorUtil.BLUE);		
			this.add(healthLevelLabel);
			this.add(healthLevel);
		
			Label soundLabel = new Label("Sound: ");
			sound = new Label();
			soundLabel.getAllStyles().setFgColor(ColorUtil.BLUE);			
			this.add(soundLabel);
			this.add(sound);			
		}
			
		@Override
		public void update (Observable o, Object arg) {
			
			lives.setText(" " + gw.getLivesRemaining());
			clock.setText(" " + gw.getCurrentClock());
			lastFlagReached.setText(" " + gw.getLastFlagReached());
			foodLevel.setText(" " + gw.getFoodLevel());
			healthLevel.setText(" " + gw.getHealthLevel());
			
			// Checking to see if sound is on or off
			if (gw.getSound()) {
				sound.setText("ON");		
			} 
			else {
				sound.setText("OFF");			
			}			
			
			this.revalidate();		
		}
}
