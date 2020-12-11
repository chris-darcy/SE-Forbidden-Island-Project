package Testing;

import java.util.ArrayList;
import java.util.List;
import Cards.*;
import Game.GameManager;
import Participant.*;
import Board.Board;

public class TestParticipant {
	public static void main(String [] args) {
		Board board = new Board();
		String name = "Bob";
		int actionsRemaining = 5;
		int location = 3; // uppermost edge
		Hand hand = new Hand();
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		FloodCardDeck floodCardDeck = new FloodCardDeck();
		
		
		hand.populateHand(treasureCardDeck.pop());
		hand.populateHand(treasureCardDeck.pop());
		
		Participant engineerTest = new Engineer(name, hand, location, actionsRemaining);
		
		engineerTest.hand.getPrintableHand();
		engineerTest.getRelevantTiles(board.getBoard());
	}
}
