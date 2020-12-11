package Testing;

import Cards.*;
import Cards.FloodCardDeck;

public class TestCardDeck {
	public static void main(String [] args) {
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		treasureCardDeck.printDeck();
		
		System.out.println("\n");
		FloodCardDeck floodCardDeck = new FloodCardDeck();
		floodCardDeck.getPrintableHand();
		
	} 
}
