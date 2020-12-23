package UnitTests;

import static org.junit.Assert.*;
import org.junit.*;
import Board.Board;
import Board.Tile;
import Board.TileStatus;
import Cards.*;
import Participant.Hand;

public class CardDeckTest {
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
    
	// verify card added to the discard pile
    @Test
    public void treasureCardDeckDrawTest() {
    	TreasureCardDeck testTreasureDeck = new TreasureCardDeck();
        Hand hand = new Hand();
        
        hand.addCardToHand(testTreasureDeck.draw());
    	Card cardRemoved = hand.removeCardFromHand(0);
    	testTreasureDeck.addToDiscardPile(cardRemoved);
    	assertEquals("Discard pile should contain one card", 1, testTreasureDeck.getDiscardPile().size());
    	assertEquals("Discard pile should contain the same card that was removed", true, testTreasureDeck.getDiscardPile().contains(cardRemoved));
    }
    
    // verify that when the floodcard deck is empty, it uses the discard pile to continue, thereby the discard pile is empty
    @Test
    public void emptyFloodTest() {
    	FloodCardDeck cardDeck = new FloodCardDeck();
    	
    	for(Tile tile : Board.getInstance().getBoard()) {
    		tile.setTileStatus(TileStatus.FLOODED);   // card is only added to discard pile when tile has sunk
    	}
    	for(int i = 0 ; i<cardDeck.size()+1;i++) {
    		cardDeck.draw();
    	}
    	assertEquals("Discard pile should be empty after floodCardDeck re-initialised", 0, cardDeck.getDiscardDeck().size());//discard deck should be re-initialised to empty
    }
}
