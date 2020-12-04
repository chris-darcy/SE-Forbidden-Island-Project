package Cards;


public class TreasureCard extends Card {
	
	//------------------------------ CONSTRUCTORS ---------------------------------//
	protected TreasureCard(String name) {
		super(name);
	}
	
	//------------------------------ METHODS --------------------------------------//
	public String toString(){
		return this.name;
	}
}
