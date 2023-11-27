package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Flag extends Fixed {
	
	private int sequenceNumber;
	
	public Flag(int sequenceNumber, float x, float y, GameWorld gw) {
		super(ColorUtil.YELLOW, 100, x, y, gw);
		this.sequenceNumber = sequenceNumber;
		
		
	}
	
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	public int getSequenceNumber() {
		return this.sequenceNumber;
	}
	
	@Override
	//empty body method to block flag objects from changing color
	public void setColor(int color) {
		
	}
	
	@Override
	public String toString() {
		return "Flag: loc = " + (Math.round(this.getLocation().getX()*10.0)/10.0) + ", " + this.getLocation().getY() +  
				" color = [" + ColorUtil.red(this.getColor()) + ", " 
				+  ColorUtil.green(this.getColor()) + ", " 
				+ ColorUtil.blue(this.getColor()) + "] " 
				+ " size = "+ this.getSize()
				+ " seqNum = " + this.sequenceNumber;
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

		int objectX = (int) (this.getLocation().getX() + pCmpRelPrnt.getX() - this.getSize()/2) ;
		int objectY = (int) (this.getLocation().getY() + pCmpRelPrnt.getY() - this.getSize()/2);
		int[] objectXPoints = {objectX, objectX + this.getSize(), objectX + this.getSize()/2};
		int[] objectYPoints = {objectY, objectY, objectY + this.getSize()};	
	
		g.setColor(super.getColor());		
		if (isSelected()) {			
			g.drawPolygon(objectXPoints, objectYPoints, 3);			
		} 
		else {
			g.fillPolygon(objectXPoints, objectYPoints, 3);	
		}
			
		g.setColor(ColorUtil.BLACK);
				
		g.drawString("" + sequenceNumber, objectX + this.getSize()/3, objectY + this.getSize()/3);		
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