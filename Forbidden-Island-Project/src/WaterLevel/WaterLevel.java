package WaterLevel;

import java.util.ArrayList;
import java.util.List;

public class WaterLevel {
	public int currentWaterLevel;
	public int maxWaterLevel;
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
	
	public int getCurrentWaterLevel() {
		return currentWaterLevel;
	}
	
	public void setCurrentWaterLevel(int currentWaterLevel) { // update current water level
		this.currentWaterLevel = currentWaterLevel;
	}
	
	public int getMaxWaterLevel() {
		return maxWaterLevel;
	}
	
	public void setMaxWaterLevel(int maxWaterLevel) {
		this.maxWaterLevel = maxWaterLevel;
	}
}
