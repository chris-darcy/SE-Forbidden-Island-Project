package Participant;

import java.util.ArrayList;
import Board.Tile;
import Board.TileStatus;
import Board.TileType;

public class Pilot extends Participant {
	
	public Pilot(String name, Hand hand, int playerNum, int location, int actionsRemaining) {
		super(name, hand, playerNum, location, actionsRemaining);
	}
	
	@Override 
	protected ArrayList<Integer> onSunkTile(ArrayList<Tile> board) { // should possibly be called in Observer or something like that?
		ArrayList<Integer> sunkRelevantTiles = new ArrayList<Integer>();
		//verify the participant is on a sunk tile
		if(board.get(participant.getLocation()).getTileStatus() != TileStatus.SUNK) {
			for(Tile tile : board) {
				if(tile.getTileStatus() != TileStatus.SUNK &&     // for each tile that is not sunk of a corner tile
				   tile.getTileType()   != TileType.EMPTY	 ) {
					sunkRelevantTiles.add(tile.getLocation()); 
				}
			}
			return sunkRelevantTiles;
		}
		else {
			// observer !!!
			return null;
		}
	}
}
