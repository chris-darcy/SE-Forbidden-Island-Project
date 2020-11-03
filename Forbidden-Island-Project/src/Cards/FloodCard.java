package Cards;

import Board.*;

public class FloodCard extends Cards {
	
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
		default:
			System.out.println("Something went wrong, please try again!");
		}	
	}
}
