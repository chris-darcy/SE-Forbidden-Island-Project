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
	
	// one or more users can be moved, this will be called for each user to be moved
	public static void helicopterLift(Participant toBeMoved, int[] location) {  //!!! Participant has to be created !!!
		toBeMoved.setLocation(location); // relocated the chosen user 
	} 
	
	public static void sandbagATile(Tile tile) {
		switch(tile.getTileStatus()) {
			case UNFLOODED:
				System.out.println("You have chosen an unflooded tile, please try a flooded tile!");// user told unflooded tile chosen
				break;
				
			case FLOODED:
				tile.setTileStatus(UNFLOODED); // !!! enum not working here !!!
				break;
			
			case SUNK:
				System.out.println("You have chosen a sunk tile (already at the bottom of the sea), please try a flooded tile!");// user told sunk tile chosen
				break;
				
			default:
				System.out.println("Please try again");
				break;
		}
	}
	
	// Add treasure card to had of participant
	public static void addTreasureCard() {
		//
		
	}
	
	public void riseWaterLevel() {
		if(waterLevel.getCurrentWaterLevel()< waterLevel.getMaxWaterLevel()) {
			waterLevel.setCurrentWaterLevel(waterLevel.getCurrentWaterLevel() + 1); // increment water level
		}
		else{
			// GAME OVER!!
		}
	}
}
