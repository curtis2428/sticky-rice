package com.mycompany.a3;

import java.util.ArrayList;

public class GameObjectCollection implements ICollection {	
		private ArrayList<GameObject> gameObjectList;
				
		public GameObjectCollection() {
			
			gameObjectList = new ArrayList<GameObject>();			
		}

	@Override
	public void add(GameObject obs) {
		gameObjectList.add(obs);
	}
	
	public void remove(GameObject obs) {
		gameObjectList.remove(obs);
	}

	@Override
	public IIterator getIterator() {		
		return new CollectionIterator();
	}
	
	// Private iterator
		private class CollectionIterator implements IIterator {			
			
			private int currentIndex;
	
			public CollectionIterator() {
				currentIndex = -1;
			}
			
			@Override
			public Boolean hasNext() {			
				if(gameObjectList.size() <= 0 || currentIndex == gameObjectList.size() - 1) {
					return false;				
				} 
				else { 					
					return true;					
				}				
			}
	
			@Override
			public GameObject getNext() {			
				currentIndex++;		
				return gameObjectList.get(currentIndex);				
			}			
		}

		public int getSize() {
			return this.gameObjectList.size();
		}
}
