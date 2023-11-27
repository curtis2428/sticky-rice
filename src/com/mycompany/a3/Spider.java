package com.mycompany.a3;


import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Spider extends Movable {
	
	public Spider(GameWorld gw) {
		super(ColorUtil.BLACK, gw);
		Random random = new Random();
		final int MIN = 100;
		final int MAX = 200;
		this.size = MIN + random.nextInt(MAX);
		super.setHeading(0 + random.nextInt(359));
		super.setSpeed(200 + random.nextInt(1000));
		this.gw = gw;
	}
	
	@Override
	public void setColor(int color) {
		
	}
	
	public void updateHeading(int width, int height) {
		Random random = new Random();
		this.setHeading(this.getHeading() + (random.nextInt(10)-5));
		
		if(this.getLocation().getX() > width || this.getLocation().getX() < 0
				|| this.getLocation().getY() > height || this.getLocation().getY() < 0) {
			this.setHeading(this.getHeading()-180);
		}
	}
	
	@Override
	public String toString() {
		return "Spider: loc = " + (Math.round(this.getLocation().getX()*10.0)/10.0) + ", " 
				+ (Math.round(this.getLocation().getY()*10.0)/10.0)   
				+ " color = [" + ColorUtil.red(this.getColor()) + ", " 
				+  ColorUtil.green(this.getColor()) + ", " 
				+ ColorUtil.blue(this.getColor()) + "] " 
				+ " heading = " + this.getHeading()
				+ " speed = " + this.getSpeed()
				+ " size = "+ this.getSize();
				
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {				
				int xLoc = (int) (this.getLocation().getX() + pCmpRelPrnt.getX() - this.getSize()/2) ;
				int yLoc = (int) (this.getLocation().getY() + pCmpRelPrnt.getY() - this.getSize()/2);
								
				g.setColor(super.getColor());
				
				int[] objectXPoints = {xLoc, xLoc + this.getSize(), xLoc + this.getSize()/2};
				int[] objectYPoints = {yLoc, yLoc, yLoc + this.getSize()};
				
				g.drawPolygon(objectXPoints, objectYPoints, 3);
					
	}

}