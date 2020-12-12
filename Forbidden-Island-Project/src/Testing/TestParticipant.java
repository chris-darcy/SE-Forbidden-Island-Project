package Testing;

import java.util.ArrayList;
import java.util.List;
import Cards.*;
import Game.GameManager;
import Participant.*;
import Board.Board;

public class TestParticipant {
	public static void main(String [] args) {
//		Board board = new Board();
		String name = "Bob";
		int actionsRemaining = 5;
		int location = 3; // uppermost edge
		Hand hand = new Hand();
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		FloodCardDeck floodCardDeck = new FloodCardDeck();
		int playerNumber = 1;
		
		hand.populateHand(treasureCardDeck.draw());
		hand.populateHand(treasureCardDeck.draw());
		
		Participant engineerTest = new Engineer(name, hand, playerNumber, location, actionsRemaining);
		
		engineerTest.hand.getPrintableHand();
//		engineerTest.getRelevantTiles(board.getBoard());
	}
}
