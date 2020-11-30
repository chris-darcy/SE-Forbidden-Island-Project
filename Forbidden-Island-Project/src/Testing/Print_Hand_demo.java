package Testing;


import java.util.ArrayList;
import java.util.List;

import Cards.Card;
import Cards.TreasureCardDeck;
import Participant.Hand;
import Participant.Participant;
import Participant.Pilot;

public class Print_Hand_demo {
	public static void main(String[] args) {
		
		List<Card> hand = new ArrayList<Card>();

		Hand H = new Hand();
		TreasureCardDeck deck = new TreasureCardDeck();
		
		H.addCardToHand(deck.earthTreasureCard);
		H.addCardToHand(deck.fireTreasureCard);
		H.addCardToHand(deck.windTreasureCard);
		H.addCardToHand(deck.oceanTreasureCard);
		
		for(String cardName: H.getPrintableHand()) {
			System.out.println(cardName);
		}	
		
	}
}
