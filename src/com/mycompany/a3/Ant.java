package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Ant extends Movable implements IFoodie{
	
	private int maximumSpeed;
	private int foodComsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	private static Ant ant;

	public Ant(GameWorld gw) {
		super(ColorUtil.CYAN, gw);
		this.gw = gw;
		
	}

	public Ant(int size, float x, float y, GameWorld gw) {
		super(ColorUtil.CYAN, size, gw);
		this.setLocation(x, y);
		setHealthLevel(100);
		setMaximumSpeed(500);
		setLastFlagReached(1);
		setFoodLevel(1000);
		setFoodConsumptionRate(1);
		setSpeed(200);
		this.gw = gw;
	}
	public static Ant getAnt(int size, float x, float y, GameWorld gw) {
		if(ant == null) {
			ant = new Ant(size, x, y, gw);
		}
		return ant;
	}
	
	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}
	
	public int getMaximumSpeed() {
		return this.maximumSpeed;
	}
	
	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodComsumptionRate = foodConsumptionRate;
	}
	
	public int getFoodConsumptionRate() {
		return this.foodComsumptionRate;
	}
	
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}
	
	public int getHealthLevel() {
		return this.healthLevel;
	}
	
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	
	public int getLastFlagReached() {
		return this.lastFlagReached;
	}
	
	public void setFoodConsumption() {
		int newLevel = this.getFoodLevel()-getFoodConsumptionRate();
		this.setFoodLevel(newLevel);
	}
	public void checkHitWall(int width, int height) {
		if(this.getLocation().getX() > width || this.getLocation().getX() < 0
				|| this.getLocation().getY() > height || this.getLocation().getY() < 0) {
			this.setHeading(this.getHeading()-90);
		}
	}
	public String toString() {
		return "Ant: loc = " + (Math.round(this.getLocation().getX()*10.0)/10.0) 
				+ ", " + (Math.round(this.getLocation().getY()*10.0)/10.0)   
				+ " color = [" + ColorUtil.red(this.getColor()) + ", " 
				+  ColorUtil.green(this.getColor()) + ", " 
				+ ColorUtil.blue(this.getColor()) + "] " 
				+ " heading = " + this.getHeading()
				+ " speed = " + this.getSpeed()
				+ " size = "+ this.getSize()
				+ " maxSpeed = " + this.getMaximumSpeed()
				+ " foodConsumptionRate = " + this.getFoodConsumptionRate();
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
				
			int xLoc = (int) (this.getLocation().getX() + pCmpRelPrnt.getX() - this.getSize()/2);
			int yLoc = (int) (this.getLocation().getY() + pCmpRelPrnt.getY() - this.getSize()/2);
	
			g.setColor(super.getColor());		
			g.fillArc(xLoc, yLoc, this.getSize(), this.getSize(), 0, 360);
		
	}

}