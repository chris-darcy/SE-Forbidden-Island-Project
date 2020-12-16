package Participant;

import java.util.ArrayList;
import java.util.Arrays;

import Board.Tile;
import Board.TileStatus;
import Board.TileType;

public class Explorer extends Participant{

	public Explorer(String name, Hand hand, int playerNum, int location, int actionsRemaining) {

		super(name, hand, playerNum, location, actionsRemaining);
	}
	
	@Override 
	// the Explorer can move diagnolly if their current tile is sunk
	protected ArrayList<Integer> onSunkTile(ArrayList<Tile> board) { // should possibly be called in Observer or something like that?
		//verify the participant is on a sunk tile
		if(board.get(participant.getLocation()).getTileStatus() != TileStatus.SUNK) {
			ArrayList<Integer> sunkRelevantTiles = new ArrayList<Integer>();
			sunkRelevantTiles.addAll(participant.getRelevantTiles(board));            // get up, down, left, right tiles
			
			ArrayList<Integer> diagMoveOptions = new ArrayList<Integer>(Arrays.asList((-6-1), (-6+1), (6-1), (6+1))); 
			
			for (int i : diagMoveOptions) {                                                       // check tiles up, down, left and right
				if(board.get(participant.getLocation() + i) != null &&                            // verify the tile is on the board
				   board.get(participant.getLocation() + i).getTileStatus() != TileStatus.SUNK && // verify the tile is not sunk
				   board.get(participant.getLocation() + i).getTileType() != TileType.EMPTY) {  
					sunkRelevantTiles.add(participant.getLocation() + i);
			
					return sunkRelevantTiles;
				}
			}
			
		}
		else {
			//Observer
			return null;
			}
	}
}
