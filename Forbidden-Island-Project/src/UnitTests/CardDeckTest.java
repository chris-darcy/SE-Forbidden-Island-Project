package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import Board.Board;
import Board.Tile;
import Board.TileStatus;
import Cards.*;
import Participant.Hand;

public class CardDeckTest {
	//
	// verify the correct number of flood tiles is included in the deck
	//
	@Test
	public void treasureCardDeckTest() {
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		assertEquals("Testing TreasureCardDeck output has 28 cards", 28, treasureCardDeck.size());
	} 
	
	//  
	// verify the correct number of flood tiles is included in the deck
	//
	@Test
	public void floodCardDeckTest() {
		FloodCardDeck floodCardDeck = new FloodCardDeck();
		assertEquals("Testing FloodCardDeck output has 24 cards", 24, floodCardDeck.size());
	} 
	
	//
	// check the treasuredeck contains all the cards required
	//
	@Test 
	public void treasureDeckContains() {
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		Card card;
		int helicopterCount = 0;
    	int sandbagCount = 0;
    	int riseWaterCount = 0;
    	int treasureCardCount = 0;
    	// for each card in the deck, ensure that it contains the right amount of each card
    	for(int i = 0 ; i < treasureCardDeck.size() ; i++) {
    		card = treasureCardDeck.draw();
    		if(card.getName() == "Sandbag") {
    			treasureCardCount++;
    		}
    		if(card.getName() == "Helicopter"){
    			helicopterCount++;
    		}
    		if(card.getName() == "Waters Rise"){
    			riseWaterCount++;
    		}
    		else {
    			treasureCardCount++;
    		}
    	}
    	assertEquals("TreasureCardDeck contains the correct number of Treasure cards", 20, treasureCardCount);
    	assertEquals("TreasureCardDeck contains the correct number of RiseWater cards", 3, riseWaterCount);
    	assertEquals("TreasureCardDeck contains the correct number of Helicopter cards", 3, helicopterCount);
    	assertEquals("TreasureCardDeck contains the correct number of Sandbag cards", 2, sandbagCount);	
    	
	}
	
	//
	// verify card added to the discard pile
	//
    @Test
    public void treasureCardDeckDrawTest() {
    	TreasureCardDeck testTreasureDeck = new TreasureCardDeck();
    	Card treasureCard1 = new HelicopterTreasureCard("Helicopter");
    	Card treasureCard2 = new SandbagTreasureCard("Sandbag");
    	Card treasureCard3 = new RiseWaterTreasureCard("Rise Water");
    	
        Hand hand = new Hand();
        
        hand.addCardToHand(testTreasureDeck.draw());
    	Card cardRemoved = hand.removeCardFromHand(0);
    	testTreasureDeck.addToDiscardPile(cardRemoved);
    	assertEquals("Discard pile should contain one card", 1, testTreasureDeck.getDiscardPile().size());
    	assertEquals("Discard pile should contain the same card that was removed", true, testTreasureDeck.getDiscardPile().contains(cardRemoved));
    	
    }
    
    //
    // verify that when the floodcard deck is empty, it uses the discard pile to continue, thereby the discard pile is empty
    //
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
