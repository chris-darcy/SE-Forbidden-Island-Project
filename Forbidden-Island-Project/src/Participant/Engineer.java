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
		if(this.getActionsRemaining()>0 &&
		   tile.getTileStatus() != TileStatus.SUNK && isAdjacentTile(tile)) {
				
				if(tile.getTileStatus() == TileStatus.FLOODED){
					tile.setTileStatus(TileStatus.UNFLOODED);
				}		
				if(chance == 0) { // remove action after first of two opportunities
					actionUsed();
					chance++;
				}
				else { // remove action after first of two opportunities
					chance = 0;
				}
				return true;		
		}
		else {
			return false;
		}
	}
	
	@Override
	public void setActionsRemaining(int numberOfActions) {
		this.numberOfActions = numberOfActions;
		this.chance = 0;
	}
}

