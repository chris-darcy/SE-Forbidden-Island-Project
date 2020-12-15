package WaterLevel;

import java.util.ArrayList;
import java.util.List;

public class WaterLevel {
	public static int currentWaterLevel;
	public static int maxWaterLevel;
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
			uniqueInstance = new WaterLevel(0,10);
		}
		return uniqueInstance;
	}
	
	public static int getCurrentWaterLevel() {
		return currentWaterLevel;
	}
	
	public void setWaterLevel(int currentWaterLevel) { // update current water level
		this.currentWaterLevel = currentWaterLevel;
//		notifyObservers(); // notify the observers that the waterlevel has been changed
	}
	
	public static int getMaxWaterLevel() {
		return maxWaterLevel;
	}
	
	public void setMaxWaterLevel(int maxWaterLevel) {
		this.maxWaterLevel = maxWaterLevel;
	}
	

}
