package UnitTests;

import static org.junit.Assert.*;
import org.junit.*;
import Cards.*;
import Participant.Hand;
import Participant.Participant;

class CardDeckTest {

	@Test
	public void treasureCardDeckTest() {
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		assertEquals("Testing TreasureCardDeck output has 28 cards", 28, treasureCardDeck.size());
	}  
	    
    // tests setup murder method checking three correct card types have been passed to murder.
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetupTreasure() {
    	TreasureCardDeck testTreasureDeck = new TreasureCardDeck();
        FloodCardDeck testTreasureDeck1 = new FloodCardDeck();
        RiseWaterTreasureCard riseWaterCard = new RiseWaterTreasureCard("RiseWater");
        
        assertTrue("TreasureCardDeck must contain RiseWater Level card", cards.contains(instanceof RiseWaterTreasureCard));
        assertTrue("Murder must contain a WeaponCard",  murderCards.get(1) instanceof WeaponCard  );
        assertTrue("Murder must contain a RoomCard",    murderCards.get(2) instanceof RoomCard    );
        
        for (int i=0; i<100; i++) {
            testDeck.setupMurder(); // Demonstrate Exception risk if called more than once
        }
        
    }
    
    
    @Test
    // tests the deal card function which returns a card. Also test hand by adding the cards to hand.
    public void testDealCard() {
        
        Card newCard;
        Participant player = new Engineer ("Debby", );
        Hand testHand = new Hand();
        TreasureCardDeck testDeck = new TreasureCardDeck();
        
        assertEquals("When first initialised, the hand should be of length 0", 0, testHand.size());
        
        
        
        
        
        for (int i=0; i<5; i++) {
            newCard = testDeck.dealCard();
            assertNotNull("Card dealt from Deck should be a real Card", newCard);
            
            testHand.addCard(testDeck.dealCard());
            assertEquals("Hand should contain a number of Cards equal to the number dealt by Deck", i+1, testHand.getCards().size());
        }
        testDeck.destroyMe();
    }
    

    @Test
    // tests that when all the cards have been dealt from the deck that it returns that it is empty.
    public void testIsEmpty() {
    	Deck testDeck= Deck.getInstance();
        
        @SuppressWarnings("unused")
        Card throwaway;
        
        // Initial Deck size is 21 cards
        assertFalse("Deck should not yet be empty!",testDeck.isEmpty());
        
        for (int i=0; i<20; i++) { // Deal 20 cards
            throwaway = testDeck.dealCard();
            assertFalse("Deck should not yet be empty!",testDeck.isEmpty());
        }
        
        throwaway = testDeck.dealCard();
        assertTrue("Deck should be empty!",testDeck.isEmpty());
        testDeck.destroyMe();
    }
}
