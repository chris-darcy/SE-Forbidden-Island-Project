package Cards;

import Board.*;
import WaterLevel.*;

public class TreasureCard extends Card {
	protected int[] location = new int[2];
	protected WaterLevel waterLevel;
	
	//------------------------------ CONSTRUCTORS ---------------------------------//
	
	protected TreasureCard(String name) {
		super(name);
	}
	
	//------------------------------ METHODS --------------------------------------//
		
	// Add treasure card to hand of participant
	public static void addTreasureCard() {
		//
		
	}
	
}