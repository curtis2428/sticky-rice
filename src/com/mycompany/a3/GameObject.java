package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

import java.util.ArrayList;
import java.util.Random;

public abstract class GameObject implements IDrawable, ICollider {
	
	protected int size;
	private Point location;
	private int color;
	protected GameWorld gw;
	private ArrayList<GameObject> objectCollisionList;
	
	
	public GameObject(int color, GameWorld gw) {
		Random random = new Random();
		float x = 1000*random.nextFloat();
		float y = 1000*random.nextFloat();
		setLocation(x,y);
		this.color = color;
		this.objectCollisionList = new ArrayList<GameObject>();
		this.gw = gw;
	}
	
	public GameObject(int color, int size, GameWorld gw) {
		Random random = new Random();
		float x = 1000*random.nextFloat();
		float y = 1000*random.nextFloat();
		setLocation(x,y);
		this.color = color;
		this.size = size;
		this.gw = gw;
		this.objectCollisionList = new ArrayList<GameObject>();
	}
	
	 
	public int getSize() {
		return this.size;
	}

	public void setLocation(float x, float y) {
		this.location = new Point(x,y);
		
	}
	
	public Point getLocation() {
		return this.location;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return this.color;
	}
	
	public String toString() {
		return "loc = " + (Math.round(this.getLocation().getX()*10.0)/10.0) 
				+ ", " + (Math.round(this.getLocation().getY()*10.0)/10.0)   
				+ " color = [" + ColorUtil.red(this.getColor()) + ", " 
				+  ColorUtil.green(this.getColor()) + ", " 
				+ ColorUtil.blue(this.getColor()) + "] " + "size = "
				+ this.getSize();
	}
	
	@Override
	public boolean collidesWith(GameObject otherObject) {
		float thisCenterX = this.getLocation().getX(); 
		float thisCenterY = this.getLocation().getY();
		float otherCenterX = otherObject.getLocation().getX();
		float otherCenterY = otherObject.getLocation().getY();
				
		float dx = thisCenterX - otherCenterX;
		float dy = thisCenterY - otherCenterY;
				
		float distBetweenCentersSqr = (dx*dx + dy*dy);
				
		int thisRadius = this.getSize()/2;
		int otherRadius = otherObject.getSize()/2;
				
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
				
		if (distBetweenCentersSqr <= radiiSqr) {
					
			return true ;				
		}
				
		return false ;
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		if (!objectCollisionList.contains(otherObject)) {					
			if (this instanceof Ant && otherObject instanceof FoodStation) {						
				FoodStation foodStation = (FoodStation) otherObject;							
				if (foodStation.getCapacity() != 0) {						
					gw.foodCollide(foodStation);								
				}													
			}						
			else if (this instanceof Ant && otherObject instanceof Flag) {						
				Flag flag = (Flag) otherObject;						
				gw.flagCollide(flag.getSequenceNumber());						
			}					
			else if (this instanceof Ant && otherObject instanceof Spider) {					
				gw.spiderCollide();						
			}			
			objectCollisionList.add(otherObject);					
		}
	}
	
	public void removeObject (GameObject otherObject) {		
		if (objectCollisionList.contains(otherObject)) {	
			objectCollisionList.remove(otherObject);			
		}			
	}
	
}

