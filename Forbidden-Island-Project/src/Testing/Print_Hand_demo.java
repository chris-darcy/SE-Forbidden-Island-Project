package Testing;

import Cards.TreasureCardDeck;
import Participant.Hand;

public class Print_Hand_demo {
	public static void main(String[] args) {

		Hand H = new Hand();
		TreasureCardDeck deck = new TreasureCardDeck();
		
		H.addCardToHand(deck.draw());
		H.addCardToHand(deck.draw());
		H.addCardToHand(deck.draw());
		H.addCardToHand(deck.draw());
		
		for(String cardName: H.getPrintableHand()) {
			System.out.println(cardName);
		}	
		
	}
}
