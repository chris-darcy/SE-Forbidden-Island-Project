package Cards;

import java.util.Stack;

public class TreasureCardDeck {
	public TreasureCard earthTreasureCard = new TreasureCard("Earth");
	public TreasureCard fireTreasureCard = new TreasureCard("Fire");
	public TreasureCard windTreasureCard = new TreasureCard("Wind");
	public TreasureCard oceanTreasureCard = new TreasureCard("Ocean");
	public HelicopterTreasureCard helicopterTreasureCard = new HelicopterTreasureCard("Helicopter");
	public SandbagTreasureCard sandbagTreasureCard = new SandbagTreasureCard("Sandbag");
	public RiseWaterTreasureCard riseWaterTreasureCard = new RiseWaterTreasureCard("RiseWater");
	Stack<TreasureCard> cardDeck = new Stack<TreasureCard>();
	
	//------------------------------ CONSTRUCTORS ---------------------------------//
	public TreasureCardDeck() {
		initialise(); // calls to initialise the cardDeck
	}
	
	//-------------------------------- METHODS ------------------------------------//
	public void initialise() { // type of card input determines type of deck created (flood or treasure)
		
		
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
		
	}
	
	public String toString() {
		for (TreasureCard card: cardDeck) {
			System.out.println(card.getName().toString());
		}
		return null;
	}
}
