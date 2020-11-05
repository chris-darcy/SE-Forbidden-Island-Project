package Cards;

public class RiseWaterTreasureCard extends TreasureCard{
	protected RiseWaterTreasureCard(String name) {
		super(name);
	}
	
	public void riseWaterLevel() {
		if(waterLevel.getCurrentWaterLevel()< waterLevel.getMaxWaterLevel()) {
			waterLevel.setCurrentWaterLevel(waterLevel.getCurrentWaterLevel() + 1); // increment water level
		}
		else{
			// GAME OVER!!
		}
	}
}
