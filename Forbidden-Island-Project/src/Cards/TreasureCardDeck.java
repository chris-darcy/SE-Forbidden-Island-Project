package Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class TreasureCardDeck {

	Stack<Card> cardDeck = new Stack<Card>();
	Stack<Card> treasureDiscardPile = new Stack<Card>();
	
	//------------------------------ CONSTRUCTORS ---------------------------------//
	
	public TreasureCardDeck() {
		initialise(); // calls to initialise the cardDeck
	}
	
	//-------------------------------- METHODS ------------------------------------//
	
	//
	// initialise the treasure card deck
	//
	public void initialise() { // type of card input determines type of deck created
		
		// add all the treasure cards to the deck of cards
		for (int i = 0; i < 5; i++) { // 5 of each treasure
			TreasureCard earthTreasureCard = new TreasureCard("Earth");
			TreasureCard fireTreasureCard = new TreasureCard("Fire");
			TreasureCard windTreasureCard = new TreasureCard("Wind");
			TreasureCard oceanTreasureCard = new TreasureCard("Ocean");
			cardDeck.push(earthTreasureCard);
			cardDeck.push(fireTreasureCard);
			cardDeck.push(windTreasureCard);
			cardDeck.push(oceanTreasureCard);
		}
		
		for (int i = 0; i < 3; i++) { // 3 helicopter treasure cards and 3 water rise cards
			HelicopterTreasureCard helicopterTreasureCard = new HelicopterTreasureCard("Helicopter");
			RiseWaterTreasureCard riseWaterTreasureCard = new RiseWaterTreasureCard("Waters Rise");
			cardDeck.push(helicopterTreasureCard);
			cardDeck.push(riseWaterTreasureCard);
		}
		
		for (int i = 0; i < 2; i++) { // 2 sandbag treasure cards
			SandbagTreasureCard sandbagTreasureCard = new SandbagTreasureCard("Sandbag");
			cardDeck.push(sandbagTreasureCard);
		}	
		
		Collections.shuffle(cardDeck); // shuffle cards initially

	}
	
	public String printDeck() {
		for (Card card: cardDeck) {
			System.out.println(card.toString());
		}
		return null;
	}
	
	public int size() {
		return cardDeck.size();
	}
	
	//
	// draw cards from deck
	//
	public Card draw() {
		if(cardDeck.isEmpty()) {
			Collections.shuffle(treasureDiscardPile); // shuffle
			Collections.reverse(treasureDiscardPile);// turn over
			cardDeck = treasureDiscardPile;
			treasureDiscardPile.clear();
		}
		
		return cardDeck.pop();
	}

	public void replace(Card drawnCard) {
		cardDeck.add(drawnCard);
		Collections.shuffle(cardDeck);
	}
	
	//
	// add treasure card to discard pile
	//
	public void addToDiscardPile(Card treasureCard){ // when chosen to remove/ used, add to discard pile
		treasureDiscardPile.add(treasureCard);
	}
	
	//
	// get cards discarded from treasure card deck and add to the discard pile
	//
	public void receiveDiscarded(ArrayList<Card> discarded) {
		for(Card card: discarded) {
			this.addToDiscardPile(card);
		}
	}
	
	//
	// returns discard pile
	//
	public Stack<Card> getDiscardPile(){
		return treasureDiscardPile;
	}

}
