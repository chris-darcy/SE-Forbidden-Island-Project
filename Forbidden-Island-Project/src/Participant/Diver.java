package Participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

import Board.Tile;
import Board.TileStatus;
import Board.TileType;

public class Diver extends Participant{
	
	public Diver(String name, Hand hand, int playerNum, int location, int actionsRemaining) {
		super(name, hand, playerNum, location, actionsRemaining);
	}
	
	// !!!! Issue here
	public ArrayList<Integer> onSunkTile(ArrayList<Tile> board) { // should possibly be called in Observer or something like that?
		//verify the participant is on a sunk tile
		ArrayList<Integer> sunkRelevantTiles = new ArrayList<Integer>();

		if(board.get(this.location).getTileStatus() == TileStatus.SUNK) {
			
			sunkRelevantTiles.addAll(this.getRelevantTiles(board));    // get up, down, left, right tiles is possible as these will be the shortest
			sunkRelevantTiles.add(getShortestDistance(board).getLocation()); // add the shortest
			
			if(removeDuplicate(sunkRelevantTiles).isEmpty()) {
				notifyAllObservers(); // getRelevant tiles is empty - game is lost
			}
			return removeDuplicate(sunkRelevantTiles);
		}
		
		else {
			return null;
		}
	}
	
	private Tile getShortestDistance(ArrayList<Tile> board) {
		ArrayList<Double> shortestDistance = new ArrayList<Double>();
		int xd = participant.getLocation()%6;
		int yd = participant.getLocation()/6;
		for (Tile tile : board) {
			if(tile.getTileType() != TileType.EMPTY && tile.getTileStatus() != TileStatus.SUNK ) {
				int xt = tile.getLocation()%6;
				int yt = tile.getLocation()/6;
				shortestDistance.add(Math.sqrt((xd - xt) * (xd - xt) + (yd - yt) * (yd - yt))); // calculate distance
			}
		}
		LinkedHashSet<Double> hashSet = new LinkedHashSet<Double>(shortestDistance);  // remove if there are any duplicate locations
		ArrayList<Double> removedDuplicates = new ArrayList<Double>(hashSet);         // return hashSet to ArrayList array
		return board.get(shortestDistance.indexOf(Collections.min(removedDuplicates))); // the shortest value index will be the index of the board also
	}
	
	private ArrayList<Integer> removeDuplicate(ArrayList<Integer> list) {
		LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(list);  // remove if there are any duplicate locations
		ArrayList<Integer> removedDuplicates = new ArrayList<>(hashSet);
		return removedDuplicates;
	}
	
}
