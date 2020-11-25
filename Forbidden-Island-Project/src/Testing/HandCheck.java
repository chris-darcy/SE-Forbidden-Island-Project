package Testing;

import java.util.ArrayList;
import java.util.List;

import Participant.Hand;
import Cards.*;

public class HandCheck {
	protected TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
	protected FloodCardDeck floodCardDeck = new FloodCardDeck();
	private List<Card> handList = new ArrayList<Card>(); 
	
	// populate the participants hand
	for (int i = 0; i < 6; i++) {
		handList.add(treasureCardDeck.pop());
	}
	Hand hand = new Hand(handList); 
	hand.printHand();
}}
