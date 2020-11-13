package Cards;
/**
 * Creating an abstract class for TreasureCard and FloodCard to extend from as they has different methods
 */
public class Card {
	protected String name;
	private CardType cardType;
	
	protected Card (String name) {
		this.name = name;
//		this.cardType = cardType;
	}
	
	private String getCardName() {
		return name;
	}

}