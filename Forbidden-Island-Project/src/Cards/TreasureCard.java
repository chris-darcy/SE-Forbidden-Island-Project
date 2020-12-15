package Cards;


public class TreasureCard extends Card {
	
	//------------------------------ CONSTRUCTORS ---------------------------------//
	public TreasureCard(String name) { // changed to public for observer tests
		super(name);
	}
	
	//------------------------------ METHODS --------------------------------------//
	@Override
	public String toString(){
		return this.name;
	}
}
