package Participant;

import java.util.ArrayList;

import Board.Tile;
import Board.TileStatus;

public class Explorer extends Participant{

	public Explorer(String name, Hand hand, int playerNum, int location, int actionsRemaining) {

		super(name, hand, playerNum, location, actionsRemaining);
	}
	
	//
	// the Explorer can move diagnolly if their current tile is sunk (as well as to their adjacent tiles)
	//
	@Override 
	public ArrayList<Integer> onSunkTile(ArrayList<Tile> board) {
		boolean shoreUp = false;
		ArrayList<Integer> sunkRelevantTiles = new ArrayList<Integer>();
		this.getLocation();
		
		//verify the participant is on a sunk tile
		if(board.get(this.getLocation()).getTileStatus() == TileStatus.SUNK) {
			sunkRelevantTiles.addAll(this.getRelevantTiles(board,shoreUp));    // get up, down, left, right tiles
			int xd = this.getLocation()%6;
			int yd = this.getLocation()/6;
			
			int[][] diagMoveOptions = {{xd-1, yd-1}, {xd-1, yd+1}, {xd+1, yd-1}, {xd+1, yd+1}};
			
			for(int[] i : diagMoveOptions) {
				if(!(i[0]<0 || i[0]>5 || i[1]>5 || i[1]<0)) {
					location = (i[0]) + 6*(i[1]);
					if(!(board.get(location).getTileStatus()== TileStatus.SUNK)) {
						sunkRelevantTiles.add(location);
					}
				}
			}
			if(sunkRelevantTiles.isEmpty()) {
				notifyAllObservers(); // gameover as explorer can't move
			}
		}
		return sunkRelevantTiles;
	}
}
