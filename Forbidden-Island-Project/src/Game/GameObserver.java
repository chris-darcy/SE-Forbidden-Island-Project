package Game;

import Board.Tile;
import Board.TileStatus;
import Board.TileType;
import Participant.Hand;
import WaterLevel.WaterLevel;

public class GameObserver extends Observer {
	public boolean GameOver = false; // initialise GameOver
	public Object obj;
	public Tile tile;
	
//	public GameObserver(Tile tile) {
//		this.tile = tile;
//		this.tile.attach(this);
//	}
	
	@Override
	public void update(Object o) {
		if (o instanceof Tile) {
			if (((Tile) o).getTileStatus() == TileStatus.SUNK &&  // if Foolslanding has sunk game is over
				((Tile) o).getTileType() == TileType.FOOLSLANDING) {
				GameOver = true;
				System.out.println("GAMEOVER!!");
			}
			System.out.println("Instance Tile");
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
