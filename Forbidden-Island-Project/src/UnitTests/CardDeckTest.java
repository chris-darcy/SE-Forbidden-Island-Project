package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.*;
import Cards.*;
import Participant.Engineer;
import Participant.Hand;
import Participant.Participant;

class CardDeckTest {
	// verify the correct number of flood tiles is included in the deck
	@Test
	public void treasureCardDeckTest() {
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		assertEquals("Testing TreasureCardDeck output has 28 cards", 28, treasureCardDeck.size());
	}  
	    
	// verify the correct number of flood tiles is included in the deck
	@Test
	public void floodCardDeckTest() {
		FloodCardDeck floodCardDeck = new FloodCardDeck();
		assertEquals("Testing FloodCardDeck output has 24 cards", 24, floodCardDeck.size());
	} 
    
    @Test
    public void treasureCardDeckDrawTest() {
    	TreasureCardDeck testTreasureDeck = new TreasureCardDeck();
        FloodCardDeck testFloodDeck = new FloodCardDeck();
        Hand hand = new Hand();
        
        hand.addCardToHand(testTreasureDeck.draw());
    	Card cardRemoved = hand.removeCardFromHand(1);
    	testTreasureDeck.addToDiscardPile(cardRemoved);
//    	assertEquals("Discard pile should contain one card", , testTreasureDeck);
    }
}
