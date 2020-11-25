package Testing;

import Cards.*;
import Cards.FloodCardDeck;

public class TestCardDeck {
	public static void main(String [] args) {
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		treasureCardDeck.toString();
		
		System.out.println("\n \n");
		FloodCardDeck floodCardDeck = new FloodCardDeck();
		floodCardDeck.toString();
		
	} 
}
