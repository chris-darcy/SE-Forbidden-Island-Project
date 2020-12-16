package Participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

import Board.Tile;
import Board.TileStatus;
import Board.TileType;

public class Diver extends Participant{
	protected Diver diver;
	
	public Diver(String name, Hand hand, int playerNum, int location, int actionsRemaining) {
		super(name, hand, playerNum, location, actionsRemaining);
	}
	
	public ArrayList<Integer> onSunkTile(ArrayList<Tile> board) { // should possibly be called in Observer or something like that?
		//verify the participant is on a sunk tile
		ArrayList<Integer> sunkRelevantTiles = new ArrayList<Integer>();
		
		
		if(board.get(participant.getLocation()).getTileStatus() != TileStatus.SUNK) {
			// for most participants, they will only be able to move to as normal
			
			sunkRelevantTiles.addAll(participant.getRelevantTiles(board));   // get up, down, left, right tiles is possible as these will be the shortest
			sunkRelevantTiles.add(getShortestDistance(board).getLocation()); // add the shortest
			LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(sunkRelevantTiles);  // remove if there are any duplicate locations
			ArrayList<Integer> sunkRelevantTilesNoDuplicates = new ArrayList<>(hashSet);
			return sunkRelevantTilesNoDuplicates;
		}
		
		else {
			return null;
		}
	}
	
	private Tile getShortestDistance(ArrayList<Tile> board) {
		ArrayList<Double> shortestDistance = new ArrayList<Double>();
		int xd = diver.getLocation()%6;
		int yd = diver.getLocation()/6;
		for (Tile tile : board) {
			if(tile.getTileType() != TileType.EMPTY || tile.getTileStatus() != TileStatus.SUNK ) {
				int xt = tile.getLocation()%6;
				int yt = tile.getLocation()/6;
				shortestDistance.add(Math.sqrt((xd - xt) * (xd - xt) + (yd - yt) * (yd - yt))); // calculate distance
			}
		}
		return board.get(shortestDistance.indexOf(Collections.min(shortestDistance))); // the shortest value index will be the index of the board also
	}
	
}
