package Testing;

import Participant.Hand;
//import WaterLevel.WaterLevel;
import java.util.Stack;
import Cards.*;

public class HandCheck {
	
	public static void main(String [] args) {
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		FloodCardDeck floodCardDeck = new FloodCardDeck();
		Stack<FloodCard> discardFloodDeck = new Stack<FloodCard>();
		
		FloodCard floodCard;
//		WaterLevel waterLevel;
		
		Hand hand = new Hand(); 
		
		System.out.println("Your Hand:\n");
		for (int i = 0; i < 2; i++) {
			hand.populateHand(treasureCardDeck.pop()); // pop the top cards from the deck
		}
		String[] printHand =  hand.getPrintableHand();
		System.out.println(printHand);
		
		System.out.println("\nFlood Card(s) Drawn:\n");
		for(int i = 0; i < 2 ;i++) { // should read in current water level !!!
			floodCard = floodCardDeck.pop();
			discardFloodDeck.push(floodCard); // add the cards to the discard pile (i.e bottom of the 
			System.out.println(floodCard.getName());
		}
	}

}
