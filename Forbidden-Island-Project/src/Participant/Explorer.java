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
	public ArrayList<Integer> onSunkTile(ArrayList<Tile> board) {
		boolean shoreUp = false;
		ArrayList<Integer> sunkRelevantTiles = new ArrayList<Integer>();
		this.getLocation();
		
		//verify the participant is on a sunk tile
		if(board.get(this.getLocation()).getTileStatus() == TileStatus.SUNK) {
			sunkRelevantTiles.addAll(this.getRelevantTiles(board,shoreUp));    // get up, down, left, right tiles
			
			ArrayList<Integer> diagMoveOptions = new ArrayList<Integer>(Arrays.asList((-6-1), (-6+1), (6-1), (6+1))); 
			
			for (int i : diagMoveOptions) {                                                // check tiles up, down, left and right
				if(board.get(this.getLocation() + i) != null &&                            // verify the tile is on the board
				   board.get(this.getLocation() + i).getTileStatus() != TileStatus.SUNK) { // verify the tile is not sunk  
					sunkRelevantTiles.add(this.getLocation() + i);
				}
			}
			if(sunkRelevantTiles.isEmpty()) {
				notifyAllObservers(); // gameover as explorer can't move
			}
		}
		return sunkRelevantTiles;
	}
}
