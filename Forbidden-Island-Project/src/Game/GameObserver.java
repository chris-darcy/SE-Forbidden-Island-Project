package Game;

import Board.Tile;
import Board.TileStatus;
import Board.TileType;
import Observers.Observer;
import Participant.Hand;
import WaterLevel.WaterLevel;

public class GameObserver extends Observer {
	public boolean GameOver = false; // initialise GameOver
	public Object obj;
	public Tile tile;
	private Object o;
	
	public GameObserver(Object o) {
		this.o = o;
		this.o.attach(this);
	}
	
	@Override
	public void update(Object o) {
		if (o instanceof Tile) {
			if (((Tile) o).getTileStatus() == TileStatus.SUNK &&      // if Foolslanding has sunk game is over
				(((Tile) o).getTileType() == TileType.FOOLSLANDING || 
				((Tile) o).getTileType() != TileType.FOOLSLANDING &&  // if a treasure tile has sunk
				((Tile) o).getTileType() != TileType.NORMAL)) {
				GameOver = true;
				System.out.println("GAMEOVER!!");
			}
			
		}	
		if (o instanceof Hand) {                          // too many cards in the participant's hand
			if(((Hand) o).size() > 5) {
				System.out.println("TO MANY CARDS IN YOUR HAND");
				System.out.println("Instance Hand");
				GameManager.getInstance().handAfterRemoval(); // get the user to remove one of their cards
			}
		}
				
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
