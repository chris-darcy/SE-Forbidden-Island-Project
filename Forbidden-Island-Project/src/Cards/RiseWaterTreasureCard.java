package Cards;
import WaterLevel.WaterLevel;

public class RiseWaterTreasureCard extends TreasureCard{
	protected RiseWaterTreasureCard(String name) {
		super(name);
	}
	
	public void riseWaterLevel() {
		if(WaterLevel.getCurrentWaterLevel()< WaterLevel.getMaxWaterLevel()) {
			WaterLevel.setCurrentWaterLevel(WaterLevel.getCurrentWaterLevel() + 1); // increment water level
		}
		else{
			// GAME OVER!!
		}
	}
}
