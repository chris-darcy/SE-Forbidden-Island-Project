package UnitTests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import Cards.FloodCardDeck;
import Cards.RiseWaterTreasureCard;
import Cards.TreasureCardDeck;
import Participant.Hand;
import Participant.Participant;

public class HandTest {
	
	// check the handContains and addCard to hand methods work
	@Test
    public void testSetupTreasure() {
    	TreasureCardDeck testTreasureDeck = new TreasureCardDeck();
        FloodCardDeck testFloodDeck = new FloodCardDeck();
        RiseWaterTreasureCard riseWaterCard = new RiseWaterTreasureCard("RiseWater");
        Hand hand = new Hand();	
        hand.addCardToHand(riseWaterCard);
        hand.addCardToHand(testTreasureDeck.draw());
        
        assertEquals("Hand contains added card", true, hand.handContains(riseWaterCard));
        assertEquals("Hand contains correct number of cards", 2, hand.size());
    }

}
