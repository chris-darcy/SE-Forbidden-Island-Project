package Testing;

import java.util.ArrayList;

import Board.Board;
import Board.Tile;
import Board.TileStatus;
import Cards.*;
import Participant.*;

public class TestParticipant {
	public static void main(String [] args) {
		ArrayList<Tile> board = Board.getInstance().getBoard();
		String name = "Bob";
		int actionsRemaining = 5;
		int location = 3; // uppermost edge
		Hand hand = new Hand();
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		int playerNumber = 1;
		 
		hand.addCardToHand(treasureCardDeck.draw());
		hand.addCardToHand(treasureCardDeck.draw());
		
		Participant diver = new Diver(name, hand, playerNumber, location, actionsRemaining);
		
		System.out.println(board.get(diver.getLocation()).getTileStatus());
		board.get(diver.getLocation()).setTileStatus(TileStatus.SUNK);
		ArrayList<Integer> where = diver.onSunkTile(board);
		System.out.println("------------------");
		for(int i : where) {
			System.out.println(board.get(i));
		}
	}
}
