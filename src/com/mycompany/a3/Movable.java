package com.mycompany.a3;

public abstract class Movable extends GameObject {

	private int heading;
	private int speed;
	private int foodLevel;
	
	public Movable(int color, GameWorld gw) {
		super(color, gw);
		this.foodLevel = 100;
		
	}

	public Movable(int color, int size, GameWorld gw) {
		super(color, size, gw);
		this.foodLevel = 100;
		
	}
	
	public void setHeading(int heading) {
		this.heading = heading;
	}
	
	public int getHeading() {
		return this.heading;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}
	
	public int getFoodLevel() {
		return this.foodLevel;
	}
	
	public void move(int tickRate) {
		if(this.foodLevel > 0) {
			float radians = (float) Math.toRadians((90 - this.getHeading()));
			float deltaX = this.getLocation().getX() + (float)Math.cos(radians) * this.getSpeed() * tickRate/1000;
			float deltaY = this.getLocation().getY() + (float)Math.sin(radians) * this.getSpeed() * tickRate/1000;
			this.setLocation(deltaX, deltaY);
		}
		else {
			this.setSpeed(0);
		}
		
	}

}
