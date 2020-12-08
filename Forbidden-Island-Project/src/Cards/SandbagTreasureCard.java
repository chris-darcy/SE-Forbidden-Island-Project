package Cards;

import Board.Tile;
import Board.*;

public class SandbagTreasureCard extends TreasureCard {
	
	//----------------------------- CONSTRUCTORS ----------------------------------//
	protected SandbagTreasureCard(String name) {
		super(name);
	}
	
	//------------------------------ METHODS --------------------------------------//
	
	public static void sandbagATile(Tile tile) {
		switch(tile.getTileStatus()) {
			case UNFLOODED:
				System.out.println("You have chosen an unflooded tile, please try a flooded tile!");// user told unflooded tile chosen
				break;
				
			case FLOODED:
				tile.setTileStatus(TileStatus.UNFLOODED);
				break;
			
			case SUNK:
				System.out.println("You have chosen a sunk tile (already at the bottom of the sea), please try a flooded tile!");// user told sunk tile chosen
				break;
				
			default:
				System.out.println("Please try again");
				break;
		}
	}
}
