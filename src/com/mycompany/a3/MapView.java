package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {

		private GameWorld gw;				
		public MapView (GameWorld gw) {
			
			this.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.rgb(255, 0, 0)));
			this.gw = gw;
			
		}		
		
		@Override
		public void update(Observable o, Object arg) {
			
			this.repaint();
			//gw.display();
		//	gw.map();
			//this.revalidate();			
		}
		
		@Override
		public void paint(Graphics g) {		
			super.paint(g);
			Point pCmpRelPrnt = new Point(getX(), getY());
			IIterator iterator = gw.getIterator();
			while(iterator.hasNext()) {
				GameObject tempObject = iterator.getNext();
				if (tempObject instanceof IDrawable) {					
					((IDrawable) tempObject).draw(g, pCmpRelPrnt);					
				}				
			}			
		}
		
		@Override
		public void pointerPressed(int xPointer, int yPointer) {					
			if(gw.getPaused()) {				
				int xCoordinate = xPointer - getParent().getAbsoluteX();
				int yCoordinate = yPointer - getParent().getAbsoluteY();				
				Point pPtrRelPrnt = new Point(xCoordinate, yCoordinate);				
				Point pCmpRelPrnt = new Point(getX(), getY());				
				
				IIterator iterator = gw.getIterator();				
				while(iterator.hasNext()) {					
					GameObject tempObject = iterator.getNext();					
					if(tempObject instanceof Fixed) {						
						Fixed fixedObject = ((Fixed) tempObject);												
						if(fixedObject.contains(pPtrRelPrnt, pCmpRelPrnt)) {							
							fixedObject.setSelected(true);							
							gw.setPosition(false);							
						}						
						else if(fixedObject.isSelected()) {						
							if(gw.getPosition() == true) {								
								float xCoordinateNew = pPtrRelPrnt.getX() - pCmpRelPrnt.getX();
								float yCoordinateNew = pPtrRelPrnt.getY() - pCmpRelPrnt.getY();									
								fixedObject.setLocation(xCoordinateNew, yCoordinateNew);								
							}							
							gw.setPosition(false);							
							fixedObject.setSelected(false);							
						}						
					}					
				}				
				revalidate();				
			}			
		}
}
