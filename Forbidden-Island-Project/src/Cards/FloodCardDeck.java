package Cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


import Board.Board;
import Board.Tile;
import Board.TileStatus;
import Board.TileType;

public class FloodCardDeck {
	private Stack<Integer> cardDeck = new Stack<Integer>();
	private ArrayList<Integer> discardDeck = new ArrayList<Integer>();
	private ArrayList<Tile> board = new ArrayList<Tile> ();
	private Integer[] corners = new Integer[]{0,1,4,5,6,11,24,29,30,31,34,35};
	
	public FloodCardDeck() {	
		initialise();
	}
	
	private void initialise() {
		board = Board.getInstance().getBoard();
		
		List<Integer> cornerList = Arrays.asList(corners);
		for(int i=0;i<36;i++) {
			if(!cornerList.contains(i)) {
				cardDeck.add(i);
			}
		}	
		Collections.shuffle(cardDeck); // shuffle the deck when initiated

	}
	
	public int size() {
		return cardDeck.size();
	}
	
	public String draw() {
		int tilePos = cardDeck.pop();
		Tile tile = board.get(tilePos);
		
		switch(tile.getTileStatus()) {
			case UNFLOODED:
				tile.setTileStatus(TileStatus.FLOODED);
				discardDeck.add(tilePos);
			case FLOODED:
				tile.setTileStatus(TileStatus.SUNK);
				if(tile.getTileType() == TileType.FOOLSLANDING) {
				// Observer
					break;
				}
			default:
				//System.out.println("Something went wrong, please try again!");
		}	
		return tile.getName(); // will be shown to the user so they know what they drew
	}
}

