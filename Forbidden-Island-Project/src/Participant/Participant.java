package Participant;

import java.util.ArrayList;
import Board.*;
import Cards.*;

public abstract class Participant {
	protected String name; // !!! Need to reduce number of parameters - place in appropriate class
	public Hand hand;
	protected int actionsRemaining;
	protected int numberOfActions = 3;
	protected int currentLocation;
	protected boolean sameTile;
	protected Participant participant; 
	protected int maxCards = 5;
	protected SandbagTreasureCard sandbagTreasureCard;
	protected ArrayList<Integer> relevantTileList = new ArrayList<Integer>();
	protected int location;
	//------------------------------ CONSTRUCTORS ---------------------------------//

	protected Participant(String name, Hand hand, int location, int actionsRemaining) {

		this.name = name;
		this.hand = hand;
		this.location = location;
		this.actionsRemaining = actionsRemaining;
	}
	
	//------------------------------ METHODS --------------------------------------//
	public String getName() {
		return name;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	public String getRoleName() {
		return this.getClass().getSimpleName();
	}
	
	public Hand getHand() {
		return hand;
	}
	
	protected void setHand(Hand hand) {
		this.hand = hand;
	}
	
	public int getActionsRemaining() {
		return actionsRemaining;
	}
	
	protected void setActionsRemaining(int numberOfActions) {
		this.numberOfActions = actionsRemaining;
	}
	
	protected void actionUsed() { // each time the user uses an action
		numberOfActions--; 
	}
	
	public int getLocation() {
		return location;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	
	public boolean shoreUp(Tile tile) {
		if(participant.getActionsRemaining()>0 &&
		   tile.getTileStatus() != TileStatus.SUNK &&
		   (tile.getLocation() == participant.getLocation() || 
			Math.abs(participant.getLocation()/6 - tile.getLocation()/6) == 1 || 
			Math.abs(currentLocation%6 - tile.getLocation()%6) == 1)) {
				
				switch (tile.getTileStatus()){
					case UNFLOODED:
						tile.setTileStatus(TileStatus.FLOODED);
					case FLOODED:
						tile.setTileStatus(TileStatus.SUNK);
				}
				participant.actionUsed();
				return true;
		}
		else {
				return false;
			}
		}
	
	// This method will be overridden by the Messenger Participant
	protected boolean giveCard(Participant receiver, TreasureCard TreasureCardToGive) {
		// all players can give another player a treasure card if on the same tile
		if (receiver.getLocation() == participant.getLocation()) {
			Hand giversHand = participant.getHand();
			Hand receiversHand = receiver.getHand();
			
			if(receiversHand.numberOfCards() < maxCards) {
				receiversHand.addCardToHand(TreasureCardToGive);
				giversHand.removeCardFromHand(TreasureCardToGive);
			}
			else{
				receiversHand.tooManyCards(TreasureCardToGive); // tell user they have too many cards and have them remove another card
			}
			participant.actionUsed();
			return true;
		}
		else {
			return false;
		}
	}

	public ArrayList<Integer> getRelevantTiles(ArrayList<Tile> board) { // returns relevant tiles that the participant can move to
		
		ArrayList<Integer> relevantTiles = new ArrayList<Integer>();
        
		// generally, the participant can move left, right, up and down, these tiles will have to be checked
		relevantTiles.add(participant.getLocation() + 1);
		relevantTiles.add(participant.getLocation() - 1);
		relevantTiles.add(participant.getLocation() + 6);
		relevantTiles.add(participant.getLocation() - 6);
		
		for (int location : relevantTiles) { // check the TileStatus is not EMPTY of SUNK
			if (board.get(location).getTileStatus() == TileStatus.SUNK || // if tile is sunk or is a corner piece (empty)
				board.get(location).getTileType() == TileType.EMPTY) {    // the participant can't move there
				relevantTiles.remove(location);
			}
		}
		
		// observer should check for the list is empty !!!
		
		return relevantTiles;
	}
	
	// should be moved to GameManager? !!!
	protected boolean booleanMove(int newLocation, ArrayList<Integer> relevantTiles) {
		if(relevantTiles.contains(newLocation)) { // checks if the user has chosen a relevant location
			return true;
		}
		else {
			return false;
		}
		
	} 
	
	protected void move(int newLocation) { // moves participant to new location
		participant.setLocation(newLocation);
	}
	
	public void captureTreasure() {
		
	}

	protected void onSunkTile(ArrayList<Tile> board) { // should possibly be called in Observer or something like that?
		//verify the participant is on a sunk tile
		participant.getRelevantTiles(board); // for most participants, they will only be able to move to as normal
	}

}
