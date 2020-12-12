package Testing;

import Participant.Hand;
import java.util.ArrayList;
import java.util.Stack;

import Board.Board;
import Cards.*;

public class HandCheck {
	
	public static void main(String [] args) {
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		FloodCardDeck floodCardDeck = new FloodCardDeck();
		Stack<String> discardFloodDeck = new Stack<String>();
		SandbagTreasureCard sandbagTreasureCard = new SandbagTreasureCard("Sandbag");
		TreasureCard oceanTreasureCard = new OceanTreasureCard("Ocean");
		String floodCard;
		Hand hand = new Hand(); 
		
		System.out.println("Your Hand:\n");
		for (int i = 0; i < 2; i++) {
			hand.populateHand(treasureCardDeck.draw()); // pop the top cards from the deck
		}
		ArrayList<String> printHand =  hand.getPrintableHand(); // !!! still not working
		System.out.println(printHand);
		
		System.out.println("\nFlood Card(s) Drawn:\n");
		for(int i = 0; i < 2 ;i++) { // should read in current water level !!!
			floodCard = floodCardDeck.draw();
			discardFloodDeck.add(floodCard); // add the cards to the discard pile (i.e bottom of the 
			System.out.println(floodCard);
		}
		
		hand.addCardToHand(sandbagTreasureCard);
		
		System.out.println("\n");
		System.out.println(hand.handContains(oceanTreasureCard));
	}

}
