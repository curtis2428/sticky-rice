package com.mycompany.a3;


public abstract class Fixed extends GameObject implements ISelectable {
	
	protected boolean selected;
	
	public Fixed(int color, GameWorld gw) {
		super(color, gw);		
	}

	public Fixed(int color, int size, float x, float y, GameWorld gw) {
		super(color, size, gw);
		super.setLocation(x,y);		
	}
	
	/*@Override
	//empty body method so that Fixed object locations cannot be changed after being created
	public void setLocation(float x, float y) {
		
	}*/
}
