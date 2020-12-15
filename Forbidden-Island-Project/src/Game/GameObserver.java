package Game;

import Board.Tile;
import Board.TileStatus;
import Board.TileType;
import Participant.Hand;
import WaterLevel.WaterLevel;

public abstract class GameObserver {
	public boolean GameOver = false; // initialise GameOver
	
	public void update(Object obj) {
		
		if (obj instanceof Tile) {
			if (((Tile) obj).getTileStatus() == TileStatus.SUNK &&  // if Foolslanding has sunk game is over
				((Tile) obj).getTileType() == TileType.FOOLSLANDING) {
				GameOver = true;
				System.out.println("GAMEOVER!!");
			}
		}
		
//		if (obj instanceof Hand) {
//			GameManager.handAfterRemoval(); // get the user to remove one of their cards
//		}
		
		
//		switch () {
//		case obj instanceof Hand:   // too many cards in users hand
//			// call too many cards
//		case obj instanceof Tile:   // tile update occurred that effects the game
//			GameOver = true;
//		case obj instanceof WaterLevel:
//			if(WaterLevel.getCurrentWaterLevel() >= WaterLevel.getMaxWaterLevel()) {
//				GameOver = true;
//				}
//		case obj instanceof ArrayList<>():
//			GameOver = true;
//		}
	}
}
