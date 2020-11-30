package Cards;
/**
 * Creating an abstract class for TreasureCard and FloodCard to extend from as they has different methods
 */
public class Card {
	protected String name;
	
	protected Card (String name) {
		this.name = name;
//		this.cardType = cardType;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.name;
	}

}