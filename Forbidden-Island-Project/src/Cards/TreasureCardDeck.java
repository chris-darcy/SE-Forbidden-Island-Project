package Cards;

import java.util.Collections;
import java.util.Stack;

public class TreasureCardDeck {

	Stack<TreasureCard> cardDeck = new Stack<TreasureCard>();
	
	//------------------------------ CONSTRUCTORS ---------------------------------//
	
	public TreasureCardDeck() {
		initialise(); // calls to initialise the cardDeck
	}
	
	//-------------------------------- METHODS ------------------------------------//
	
	public void initialise() { // type of card input determines type of deck created (flood or treasure)
		TreasureCard earthTreasureCard = new TreasureCard("Earth");
		TreasureCard fireTreasureCard = new TreasureCard("Fire");
		TreasureCard windTreasureCard = new TreasureCard("Wind");
		TreasureCard oceanTreasureCard = new TreasureCard("Ocean");
		HelicopterTreasureCard helicopterTreasureCard = new HelicopterTreasureCard("Helicopter");
		SandbagTreasureCard sandbagTreasureCard = new SandbagTreasureCard("Sandbag");
		RiseWaterTreasureCard riseWaterTreasureCard = new RiseWaterTreasureCard("RiseWater");
		
		// add all the treasure cards to the deck of cards
		for (int i = 0; i < 5; i++) { // 5 of each treasure
			cardDeck.push(earthTreasureCard);
			cardDeck.push(fireTreasureCard);
			cardDeck.push(windTreasureCard);
			cardDeck.push(oceanTreasureCard);
		}
		
		for (int i = 0; i < 3; i++) { // 3 helicopter treasure cards and 3 water rise cards
			cardDeck.push(helicopterTreasureCard);
			cardDeck.push(riseWaterTreasureCard);
		}
		
		for (int i = 0; i < 2; i++) { // 2 sandbag treasure cards
			cardDeck.push(sandbagTreasureCard);
		}	
		
		Collections.shuffle(cardDeck); // shuffle the deck when initiated
	}
	
	public String printDeck() {
		for (TreasureCard card: cardDeck) {
			System.out.println(card.getName());
		}
		return null;
	}
	
	public int size() {
		return cardDeck.size();
	}
	
	public TreasureCard pop() {
		return cardDeck.pop();
	}
}
