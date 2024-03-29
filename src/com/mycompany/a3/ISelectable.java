package com.mycompany.a3;

import com.codename1.ui.Graphics; 
import com.codename1.charts.models.Point;

public interface ISelectable {
	
	public void setSelected(boolean y);
	
	public boolean isSelected();
	
	public void draw(Graphics g, Point pCmpRelPrnt);
	
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrint);
	
}
