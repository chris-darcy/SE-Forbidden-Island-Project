package Testing;

import Cards.*;
import Participant.*;

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
		
		hand.addCardToHand(treasureCardDeck.draw());
		hand.addCardToHand(treasureCardDeck.draw());
		
		Participant engineerTest = new Engineer(name, hand, playerNumber, location, actionsRemaining);
		
		engineerTest.hand.getPrintableHand();
//		engineerTest.getRelevantTiles(board.getBoard());
	}
}
