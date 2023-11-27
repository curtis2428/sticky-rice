package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class FoodStation extends Fixed {

	private int capacity;
	
	
	public FoodStation(GameWorld gw) {
		super(ColorUtil.BLUE, 0, locX(), locY(), gw);
		Random random = new Random();
		final int MIN = 100;
		final int MAX = 200;
		this.size = MIN + random.nextInt(MAX);
		this.capacity = this.getSize();	
	}
	
	public void setCapacity(int capacity) {
	        this.capacity = capacity;
	}
	 
	public int getCapacity() {
	        return this.capacity;
	}
	
	public Boolean isEmpty() {
		return this.capacity == 0;
	}
	
	private static float locX() {
		Random random = new Random();
		return 1000*random.nextFloat();
	}
	
	private static float locY() {
		Random random = new Random();
		return 1000*random.nextFloat();
	}
	
	@Override
	public String toString() {
		return "FoodStation: loc = " + (Math.round(this.getLocation().getX()*10.0)/10.0) + ", " 
				+ (Math.round(this.getLocation().getY()*10.0)/10.0)   
				+ " color = [" + ColorUtil.red(this.getColor()) + ", " 
				+  ColorUtil.green(this.getColor()) + ", " 
				+ ColorUtil.blue(this.getColor()) + "] " 
				+ " size = "+ this.getSize()
				+ " capacity = " + this.capacity;
	}

	@Override
	public void setSelected(boolean yes) {
		this.selected = yes;
		
	}

	@Override
	public boolean isSelected() {
		
		return this.selected;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {

		int xLoc = (int) (this.getLocation().getX() + pCmpRelPrnt.getX() - this.getSize()/2);
		int yLoc = (int) (this.getLocation().getY() + pCmpRelPrnt.getY() - this.getSize()/2);
		
		g.setColor(super.getColor());
		
		if (isSelected()) {	
			g.drawRect(xLoc, yLoc, this.getSize(), this.getSize());
			
		} 
		else {
			g.fillRect(xLoc, yLoc, this.getSize(), this.getSize());
			
		}	
		g.setColor(ColorUtil.BLACK);
		g.drawString("" + capacity, xLoc + this.getSize()/3, yLoc + this.getSize()/3);	
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {

		float px = pPtrRelPrnt.getX();
		float py = pPtrRelPrnt.getY();
		float xLoc = this.getLocation().getX() + pCmpRelPrnt.getX() - this.getSize()/2;
		float yLoc = this.getLocation().getY() + pCmpRelPrnt.getY() - this.getSize()/2;

		return ((px >= xLoc)&&(px <= xLoc + this.getSize())&&(py >= yLoc)&&(py <= yLoc + this.getSize()));

	}
	
}