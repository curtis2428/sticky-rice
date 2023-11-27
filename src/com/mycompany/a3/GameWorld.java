package com.mycompany.a3;


import java.util.ArrayList;
import java.util.Observable;

import java.util.Random;
import java.lang.Math;

public class GameWorld extends Observable{
	
	private int currentClock;
	private int livesRemaining = 3;
	private GameObjectCollection gameObjectList;
	private Boolean sound = false;
	private int width;
	private int height;
	private int tickRate;
	private boolean paused = false;
	private boolean position = false;
	private Sound spiderSound;
	private Sound foodSound;
	private Sound flagSound;
	private BGSound bgSound;
	
	/**
	 * Initializes the game world by creating game objects
	 */
	public void init() {
		setCurrentClock();
		Random random = new Random();
		
		this.gameObjectList = new GameObjectCollection();
		
		gameObjectList.add(new Flag(1, 100,100, this));
		for(int i = 2; i <= 4; i++ ) {
			gameObjectList.add(new Flag(i, 100 + random.nextInt(1200),100 + random.nextInt(1200), this));
		}
		
		gameObjectList.add(Ant.getAnt(100,100,100, this));
		gameObjectList.add(new Spider(this));
		gameObjectList.add(new Spider(this));
		gameObjectList.add(new FoodStation(this));
		gameObjectList.add(new FoodStation(this));
	}
	/**
	 * Getters and Setters for private fields
	 */
	public void setCurrentClock() {
		this.currentClock = 0;
	}
	
	public int getCurrentClock() {
		return this.currentClock;
	}
	
	public void setLivesRemaining(int livesRemaining) {
		this.livesRemaining = livesRemaining;
	}
	
	public int getLivesRemaining() {
		return this.livesRemaining;
	}
	
	public void setSound(Boolean sound) {
		this.sound = sound;
	}
	
	public Boolean getSound() {
		return this.sound;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return this.height;
	}

	/**
	 * Accelerates Ant objects based on their health and speed limits.
	 */
	public void accelerate() {
		
		IIterator iter = gameObjectList.getIterator();
		
		
		while (iter.hasNext()) {
			GameObject gameObject = iter.getNext();
			if (gameObject instanceof Ant) {
				Ant ant = (Ant) gameObject;
				int baseHealthLevel = ant.getHealthLevel();
				if(ant.getSpeed() < ant.getMaximumSpeed() && ant.getHealthLevel() == baseHealthLevel) {
					ant.setSpeed(ant.getSpeed()+100);
				}
				else if(ant.getHealthLevel() < baseHealthLevel) {
					int tempMaxSpeed = (int) Math.ceil(ant.getMaximumSpeed()*((double) ant.getHealthLevel()/10));
					if(ant.getSpeed() < tempMaxSpeed) {
						ant.setSpeed(ant.getSpeed()+100);
					}
					else {
						System.out.println("Max speed has been reached! Increase health level to"
								+ " reach higher speed!");
					}
				}
				else {
					System.out.println("Ant has reached maximum speed!");
				}
			}
		}
		this.setChanged();
		this.notifyObservers(this);
		
	}

	/**
	 * slows the speed of the ant by 5. The minimum speed of the ant is 0. If
	 * the brake is applied when the ant's speed is 0, it will remain 0 and not
	 * become negative.
	 */
	public void brake() {
		IIterator iter = gameObjectList.getIterator();
		while (iter.hasNext()) {
			GameObject gameObject = iter.getNext();
			if (gameObject instanceof Ant) {
				Ant ant = (Ant) gameObject;
				
				if(ant.getSpeed() > 0) {
					ant.setSpeed(ant.getSpeed()-100);
				}
				else if(ant.getSpeed() <= 0) {
					System.out.println("Ant's speed is already 0");
					ant.setSpeed(0);
				}
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 * changes heading of the ant 25 degrees to the left
	 */
	public void left() {
		IIterator iter = gameObjectList.getIterator();
		while (iter.hasNext()) {
			GameObject gameObject = iter.getNext();
			if (gameObject instanceof Ant) {
				Ant ant = (Ant) gameObject;
				ant.setHeading(ant.getHeading() - 25);
			}
		}
		this.setChanged();
		this.notifyObservers(this);
		
	}

	/**
	 * changes heading of the ant 25 degrees to the right
	 */
	public void right() {
		IIterator iter = gameObjectList.getIterator();
		while (iter.hasNext()) {
			GameObject gameObject = iter.getNext();
			if (gameObject instanceof Ant) {
				Ant ant = (Ant) gameObject;
				ant.setHeading(ant.getHeading() + 25);
			}
		}
		this.setChanged();
		this.notifyObservers(this);
		
	}
	
	/**
	 * Sets the food consumption rate for the Ant, which is initialized to zero
	 */
	public void consume() {
		System.out.println("Setting food consumption rate!");
		IIterator iter = gameObjectList.getIterator();
		while (iter.hasNext()) {
			GameObject gameObject = iter.getNext();
			if (gameObject instanceof Ant) {
				Ant ant = (Ant) gameObject;
				ant.setFoodConsumptionRate(5);
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/**
	 * Handles collisions between Ants and flags in the game.
	 * Checks if the Ant has collided with the appropriate flag number to advance in the game.
	 */
	public void flagCollide(int flagNum) {
		System.out.println("Ant has collided with Flag #" + flagNum);
		IIterator iter = gameObjectList.getIterator();
		while (iter.hasNext()) {
			GameObject gameObject = iter.getNext();
			if (gameObject instanceof Ant) {
				Ant ant = (Ant) gameObject;
				
				if((flagNum) == ant.getLastFlagReached() + 1) {
					ant.setLastFlagReached(flagNum);
					if(flagNum == 4) {
						System.out.println("Last Flag Reached! You've Won! Total time: " + this.getCurrentClock());
						System.exit(0);
					}
					System.out.println("Ant has reached the aproppriate flag");
					ant.setLastFlagReached(flagNum);
				}
				else {
					System.out.println("you must go to Flag #" + (ant.getLastFlagReached() + 1)
							+ " to advance");
				}
				if (getSound()) {
					flagSound.play();	
				}
			}
		}
		this.setChanged();
		this.notifyObservers(this);
		
		
	}
	

	/**
	 * Handles collisions between Ants and Food Stations in the game.
	 * When an Ant collides with a Food Station, it increases the Ant's food level,
	 * sets the Food Station's capacity to zero, adjusts its color, and adds a new Food Station.
	 * 
	 */
	public void foodCollide(FoodStation foodStation) {
	    System.out.println("Ant has collided with a food station!");

	    ArrayList<FoodStation> foodStationsToRemove = new ArrayList<>();

	    IIterator iter = gameObjectList.getIterator();

	    while (iter.hasNext()) {
	        GameObject antObject = iter.getNext();
	        if (antObject instanceof Ant) {
	            Ant ant = (Ant) antObject;
	            
	            if (foodStation.getCapacity() != 0) {
                    ant.setFoodLevel(ant.getFoodLevel() + foodStation.getCapacity());
                    foodStation.setCapacity(0);
                    foodStation.setColor(foodStation.getColor() - 10);
                    foodStationsToRemove.add(foodStation);
                    gameObjectList.remove(foodStation);
                }

	            if (getSound()) {
					foodSound.play();	
				}
	        }
	        
	    }

	    // add depleted food station back to keep them in the game
	    for (int i = 0; i < foodStationsToRemove.size() - 1; i++) {
	        gameObjectList.add(foodStationsToRemove.get(i));
	    }

	    // add new food station into the game
	    gameObjectList.add(new FoodStation(this));

	    // clear foodStationsToRemove list
	    foodStationsToRemove.clear();

	    this.setChanged();
	    this.notifyObservers(this);
	}


	/**
	 * Simulates a collision between ant and spider. Ant health level is reduced by one
	 * and color is changed when it collides with a spider
	 */
	public void spiderCollide() {
		System.out.println("A spider has collided with the ant!");
		
		IIterator iter = gameObjectList.getIterator();
		while(iter.hasNext()) {
			GameObject antObject = iter.getNext();
			if(antObject instanceof Ant) {
				Ant ant = (Ant) antObject;
				ant.setHealthLevel(ant.getHealthLevel()-10);
				ant.setColor(ant.getColor() - 50);
				if (getSound()) {
					spiderSound.play();	
				}
				if(ant.getHealthLevel() < 1) {
					setLivesRemaining(getLivesRemaining() -1);
					ant.setHealthLevel(100);
					System.out.println("You lost a life!! Be Careful!");
					//System.out.println("Game over! You Failed!");
					//System.exit(0);
				}
			}
			
		}
		this.setChanged();
		this.notifyObservers(this);
		
	}

	/**
	 * Updates the heading and location of Spider objects in the game.
	 * Updates the position and food level of Ant objects in the game.
	 * Advances the game clock by one time step.
	 */
	public void tick() {


		currentClock++;
		//map();
		
		// update spider's heading and location
		IIterator iter = gameObjectList.getIterator();
		while (iter.hasNext()) {
			GameObject spiderObject = iter.getNext();
			if (spiderObject instanceof Spider) {
				Spider spider = (Spider) spiderObject;
				spider.updateHeading(this.getWidth(), this.getHeight());
				spider.move(tickRate);
			}
		}
		
		//update Ant position and food level
		IIterator iter2 = gameObjectList.getIterator();
		while (iter2.hasNext()) {
			GameObject antObject = iter2.getNext();
			if (antObject instanceof Ant) {
				Ant ant = (Ant) antObject;				
				ant.move(tickRate);
				ant.checkHitWall(this.width, this.height);
				ant.setFoodConsumption();
				if(ant.getFoodLevel() <= 0) {
					ant.setFoodLevel(1000);
					setLivesRemaining(getLivesRemaining() - 1);
					
					init();
				}
			}
			
		}
		IIterator iter3 = gameObjectList.getIterator();
		while(iter3.hasNext()) {
			GameObject temp = iter3.getNext();
			if (temp instanceof ISelectable) {	
				((ISelectable) temp).setSelected(false);
				
			}
		}
		
		// Program exits when lives remaining is zero
		if(getLivesRemaining() < 1) {
			System.out.println("Game over! You Failed!");
			System.exit(0);
		}
		
		checkCollisions();
		if (getSound()) {
			bgSound.play();
			
		} 
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 * This method is designed for monitoring and provides key details about each Ant, 
	 * such as their remaining lives, game time, last flag reached, current food level, 
	 * and health.
	 */
	public void display() {
		
		IIterator iter = gameObjectList.getIterator();
		while (iter.hasNext()) {
			GameObject gameObject = iter.getNext();
			if (gameObject instanceof Ant) {
				Ant ant = (Ant) gameObject;
				int lives = getLivesRemaining();
				int timer = getCurrentClock();
				int lastFlagReached = ant.getLastFlagReached();
				int foodLevel = ant.getFoodLevel();
				int healthLevel = ant.getHealthLevel();

				System.out.println("Current Ant State: ");
				System.out.println("Lives left " + lives);
				System.out.println("Timer elasped: " + timer);
				System.out.println("Last flag reached: " + lastFlagReached);
				System.out.println("Current food level: " + foodLevel);
				System.out.println("Current health level: " + healthLevel);
			}
		}
		
		
	}

	/**
	 * Iterates through gameObjectList and prints toString information for each object
	 * utilizing overridden toString() method in concrete classes
	 */
	public void map() {
		
		IIterator iter = gameObjectList.getIterator();
		
		while(iter.hasNext()) {
			GameObject gameObject = iter.getNext();
			System.out.println(gameObject.toString());
		}
		
	}

	public void quit() {
		System.exit(0);
	}
	
	public int getLastFlagReached() {
		int lastFlagReached = 0;
		IIterator iter = gameObjectList.getIterator();
		while(iter.hasNext()) {
			GameObject gameObject = iter.getNext();
			if(gameObject instanceof Ant) {
				lastFlagReached = ((Ant) gameObject).getLastFlagReached(); 
				
			}
		}
		return lastFlagReached;
	}
	
	public int getHealthLevel() {
		int healthLevel = 0;
		IIterator iter = gameObjectList.getIterator();
		while(iter.hasNext()) {
			GameObject gameObject = iter.getNext();
			if(gameObject instanceof Ant) {
				healthLevel = ((Ant) gameObject).getHealthLevel(); 
				
			}
		}
		return healthLevel;
	}

	public int getFoodLevel() {
		int foodLevel = 0;
		IIterator iter = gameObjectList.getIterator();
		while(iter.hasNext()) {
			GameObject gameObject = iter.getNext();
			if(gameObject instanceof Ant) {
				foodLevel = ((Ant) gameObject).getFoodLevel(); 
				
			}
		}
		return foodLevel;
	}
	public IIterator getIterator() {
		IIterator iter = gameObjectList.getIterator();
		return iter;
	}
	public void setTickRate(int tickRate) {
		this.tickRate = tickRate;
		
	}
	
	public void checkCollisions() {		
		IIterator iter1 = gameObjectList.getIterator();		
		while(iter1.hasNext()) {
			
			GameObject temp = iter1.getNext();
			
			if (temp instanceof Ant) {			
				IIterator iter2 = gameObjectList.getIterator();
				
				while(iter2.hasNext()) {					
					GameObject temp2 = iter2.getNext();
					
					if ((temp != temp2) && (temp.collidesWith(temp2))) {
						temp.handleCollision(temp2);
					} 			
					else {
						temp.removeObject(temp2);
						
					}
						
				}
					
			}
		
		}	
			
	}
	public void setPosition(boolean position) {
		this.position = position;
	
	}
	public boolean getPosition() {
		return position;
	}
	public void revertPosition() {	
		if (position == true) {		
			position = false;
			
		} 
		else {
			position = true;	
		}
	}
	public boolean getPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	public void pauseSound() {
		if(sound) {
			bgSound.pause();
		}
	}
	public void playSound() {
			if (sound) {				
				bgSound.play();		
			}		
	}
	public void createSounds() {
		spiderSound = new Sound("spider.wav");
		foodSound = new Sound("food.wav");
		flagSound = new Sound("flag.wav");
		bgSound = new BGSound("bgsound.wav");
	}

}
