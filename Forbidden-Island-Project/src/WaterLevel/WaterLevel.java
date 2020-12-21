package WaterLevel;

import Observers.Subject;

public class WaterLevel extends Subject{
	private int currentWaterLevel;
	private int maxWaterLevel = 5;
	private static WaterLevel uniqueInstance = null;
//	private List<Observer> obervers = new ArrayList<Observer>(); // !! Try and intergrate an observer
	//------------------------------ CONSTRUCTORS ---------------------------------//
	
	private WaterLevel(int currentWaterLevel, int maxWaterLevel) {
		this.currentWaterLevel = currentWaterLevel;
		this.maxWaterLevel = maxWaterLevel;
	}
	
	//------------------------------ METHODS --------------------------------------//
	
	public static WaterLevel getInstance() {
		if(uniqueInstance==null) {
			uniqueInstance = new WaterLevel(0,5);
		}
		return uniqueInstance;
	}
	
	public int getCurrentWaterLevel() {
		return this.currentWaterLevel;
	}
	
	public void setWaterLevel(int currentWaterLevel) { // update current water level
		this.currentWaterLevel = currentWaterLevel;
 
	}
	
	public int getMaxWaterLevel() {
		return this.maxWaterLevel;
	}
	
	public void setMaxWaterLevel(int maxWaterLevel) {
		this.maxWaterLevel = maxWaterLevel;
	}
	
	public void increment() {
		this.currentWaterLevel++;
		notifyAllObservers();// notify the observers that the waterlevel has been changed
	}
}
