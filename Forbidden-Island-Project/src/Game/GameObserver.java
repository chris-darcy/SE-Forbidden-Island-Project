package Game;

import Board.Tile;
import WaterLevel.WaterLevel;

public abstract class GameObserver {
	public boolean GameOver = false;
	
	public void update(Object obj) {
		if(obj instanceof Tile) {
			GameOver = true;
		if(obj instanceof WaterLevel){
			if(WaterLevel.getCurrentWaterLevel() >= WaterLevel.getMaxWaterLevel()) {
				GameOver = true;
				}
			}
		// add one here to check for relevantTiles empty - i.e participant can't move
		else {
			GameOver = false;
			}
		}
	}
}
