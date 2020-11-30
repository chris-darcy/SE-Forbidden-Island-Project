package UnitTests;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import Cards.*;

class CardDeckTest {

	@Test
	public void treasureCardDeckTest() {
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		assertEquals("Testing TreasureCardDeck output has 28 cards", 28, treasureCardDeck.size());
	}

}
