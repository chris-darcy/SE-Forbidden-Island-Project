package Cards;
/**
 * Creating an abstract class for TreasureCard and FloodCard to extend from as they has different methods
 */
public abstract class Card {
	protected String name;
	
	protected Card (String name) {
		this.name = name;
	}
	
	private String getCardName() {
		return this.name;
	}
}

// innocuous comment
//another innocuous comment... sneaky