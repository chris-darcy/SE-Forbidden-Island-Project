package Participant;

import Board.Tile;
import Board.TileStatus;

public class Engineer extends Participant{
	protected int chance = 0;
	
	public Engineer(String name, Hand hand, int location, int actionsRemaining) {
		super(name, hand, location, actionsRemaining);
	}
	
	// can shore up two tiles for 1 action
	// !!! should read in 2 tiles
	@Override
	public boolean shoreUp(Tile tile) {
		int chance = 0; // initialise 
		if(participant.getActionsRemaining()>0 &&
		   chance > 2 && 
		   tile.getTileStatus() != TileStatus.SUNK &&
		   (tile.getLocation() == participant.getLocation() || 
			Math.abs(participant.getLocation()/6 - tile.getLocation()/6) == 1 || 
			Math.abs(currentLocation%6 - tile.getLocation()%6) == 1)) {
				
				chance++;
				
				switch (tile.getTileStatus()){
				case UNFLOODED:
					tile.setTileStatus(TileStatus.FLOODED);
				case FLOODED:
					tile.setTileStatus(TileStatus.SUNK);
				}
				
				if(chance == 1) { // has had two chances
					participant.actionUsed();
				}
				return true;		
		}
		else {
			return false;
		}
	}
}
