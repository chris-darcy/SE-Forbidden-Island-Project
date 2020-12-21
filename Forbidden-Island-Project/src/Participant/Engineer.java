package Participant;

import Board.Tile;
import Board.TileStatus;

public class Engineer extends Participant{
	int chance = 0;
	public Engineer(String name, Hand hand, int playerNum, int location, int actionsRemaining) {
		super(name, hand, playerNum, location, actionsRemaining);
	}
	
	// can shore up two tiles for 1 action
	@Override
	public boolean shoreUp(Tile tile) {
		if(participant.getActionsRemaining()>0 &&
		   tile.getTileStatus() != TileStatus.SUNK && isAdjacentTile(tile) && chance<2) {
				
				switch (tile.getTileStatus()){
				case UNFLOODED:
					tile.setTileStatus(TileStatus.FLOODED);
				case FLOODED:
					tile.setTileStatus(TileStatus.SUNK);
				}
				if(chance == 0) { // remove action after first of two opportunities
					actionUsed();
					chance++;
				}
				if(chance == 1) {
					chance = 0;  // re-initialise for next turn
				}
				return true;		
		}
		else {
			return false;
		}
	}
}

