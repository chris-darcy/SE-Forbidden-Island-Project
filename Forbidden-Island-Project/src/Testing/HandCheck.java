package Testing;

import Participant.Hand;

import java.util.Stack;

import Cards.*;

public class HandCheck {
	
	public static void main(String [] args) {
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		FloodCardDeck floodCardDeck = new FloodCardDeck();
		Stack<FloodCard> discardFloodDeck = new Stack<FloodCard>();
		FloodCard floodCard;
//		public static final String ANSI_RESET = "\u001B[0m";
		
		Hand hand = new Hand(); 
		
		for (int i = 0; i < 2; i++) {
			hand.populateHand(treasureCardDeck.pop()); // pop the top cards from the deck
			System.out.println();
			hand.printHand();
		}
		
		System.out.println("\nFlood Card(s) Drawn:\n");
		for(int i = 0; i< 2;i++) { //should read in current water level !!!
			floodCard = floodCardDeck.pop();
			discardFloodDeck.push(floodCard); // add the cards to the discard pile (i.e bottom of the 
			System.out.println(floodCard.getName());
		}
		
	}
}
