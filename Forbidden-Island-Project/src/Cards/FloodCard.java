package Cards;

import Board.Tile;
import Board.TileStatus;
import Board.Tiletype;

public class FloodCard extends Card {
	
	//------------------------------ CONSTRUCTORS ---------------------------------//
	
	protected FloodCard(String name) {
		super(name);
	}
	
	//------------------------------ METHODS --------------------------------------//
	
	// turn the tile indicated on the card over if unflooded to the flooded side
	protected void floodCardChosen(Tile tile) {
		switch(tile.getTileStatus()) {
		case UNFLOODED:
			tile.setTileStatus(FLOODED); // !!! unsure why there is an error here !!!
		case FLOODED:
			tile.setTileStatus(SUNK); // !!! unsure why there is an error here !!!
			if(tile.getName() == "FOOLSLANDING" || ) { // if fool's landing is sunk OR any two treasure tiles are sunk - game over!
			// !!! game over !!!
				break;
			}
		default:
			System.out.println("Something went wrong, please try again!");
		}	
	}
}
