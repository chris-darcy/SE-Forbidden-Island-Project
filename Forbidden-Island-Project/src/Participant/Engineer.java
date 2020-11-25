package Participant;

import Board.Tile;

public class Engineer extends Participant{
	protected int chance = 0;
	protected Engineer(String name, Hand hand, int location, int actionsRemaining) {
		super(name, hand, location, actionsRemaining);
	}
	
	// can shore up two tiles for 1 action
	@Override
	public void shoreUp(Tile tile) {
		if(participant.getActionsRemaining()>0) {
			if(chance>2 & tile.getLocation() == participant.getLocation() || 
			   Math.abs(participant.getLocation()/6 - tile.getLocation()/6) == 1 || 
			   Math.abs(currentLocation%6 - tile.getLocation()%6) == 1) {
				
				chance++;
				tile.shoreUpTile();
				
				if(chance == 2) {
					participant.actionUsed();
				}
			}
			else {
				System.out.println(tile.getName() + ", is too far away for you shore up, try a closer tile.");
			}
		}
		else{
			System.out.println("You don't have enough actions left!");
		}
		
	}
}
