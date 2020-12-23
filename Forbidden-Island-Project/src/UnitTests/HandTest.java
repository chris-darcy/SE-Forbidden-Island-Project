package UnitTests;

import static org.junit.Assert.*;
import org.junit.Test;
import Cards.Card;
import Cards.FloodCardDeck;
import Cards.HelicopterTreasureCard;
import Cards.RiseWaterTreasureCard;
import Cards.SandbagTreasureCard;
import Cards.TreasureCard;
import Cards.TreasureCardDeck;
import Participant.Hand;

public class HandTest {
	
	// check the handContains and addCard to hand methods work
	@Test
    public void testSetupTreasure() {
    	TreasureCardDeck testTreasureDeck = new TreasureCardDeck();
        RiseWaterTreasureCard riseWaterCard = new RiseWaterTreasureCard("RiseWater");
        Hand hand = new Hand();	
        hand.addCardToHand(riseWaterCard);
        hand.addCardToHand(testTreasureDeck.draw());
        
        assertEquals("Hand contains added card", true, hand.handContains(riseWaterCard));
        assertEquals("Hand contains correct number of cards", 2, hand.size());
    }
	
	@Test
	public void findHandIndexTest () {
		Card card1 = new TreasureCard("Ocean");
		Card card2 = new TreasureCard("Fire");
		Card card3 = new RiseWaterTreasureCard("RiseWater");
		Hand hand = new Hand();
		
		hand.addCardToHand(card1);
		hand.addCardToHand(card2);
		hand.addCardToHand(card3);
		
		assertEquals("FindHandIndex returns index 1 for card at index 1", 1, hand.findHandIndex(card2));
		}
	
	// verify that only treasure cards of the same type are removed when treasure capture
	@Test
	public void capturedTreasureCardRemoval(){
		Card card1 = new TreasureCard("Fire");
		Card card2 = new TreasureCard("Fire");
		Card card3 = new TreasureCard("Ocean");
		Card card4 = new TreasureCard("Fire");
		Card card5 = new TreasureCard("Fire");
		Hand hand = new Hand();
		
		hand.addCardToHand(card1);
		hand.addCardToHand(card2);
		hand.addCardToHand(card3);
		hand.addCardToHand(card4);
		hand.addCardToHand(card5);
		
		hand.discardFour("Fire");
		
		assertEquals("After treasure captured, hand will contain 1 card", 1, hand.size());
		assertEquals("After treasure captured, remaining card type is Ocean", "Ocean", hand.getCardInHand(0).getName());
	}
	
	// verify that getTreasureCards only returns treasurecards that are used to capture treasures
	@Test
	public void getTreasureCardsFromHand() {
		Card card1 = new TreasureCard("Fire");
		Card card2 = new TreasureCard("Fire");
		Card card3 = new TreasureCard("Ocean");
		Card card4 = new TreasureCard("Fire");
		Card card5 = new TreasureCard("Fire");
		Card card6 = new HelicopterTreasureCard("Helicopter");
		Card card7 = new SandbagTreasureCard("Sandbag");
		Hand hand = new Hand();
		
		hand.addCardToHand(card1);
		hand.addCardToHand(card2);
		hand.addCardToHand(card3);
		hand.addCardToHand(card4);
		hand.addCardToHand(card5);
		hand.addCardToHand(card6);
		hand.addCardToHand(card7);
		
		assertEquals("getTreasureCards should return only treasure cards for collecting treasures", 5, hand.getTreasureCards().size());
	}
}
