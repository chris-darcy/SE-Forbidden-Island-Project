package Participant;

import java.util.ArrayList;
import Board.*;
import Cards.*;
import Board.*;

public abstract class Participant {
	protected String name; // !!! Need to reduce number of parameters - place in appropriate class
	protected Hand hand;
	protected int actionsRemaining;
	protected int numberOfActions = 3;
	protected int currentLocation;
	protected boolean sameTile;
	protected Participant participant; 
	protected int maxCards = 5;
	protected SandbagTreasureCard sandbagTreasureCard;
	protected ArrayList<Integer> relevantTileList = new ArrayList<Integer>();
	
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
	
	protected int getActionsRemaining() {
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
	
	public boolean canShoreUp(Tile tile) { // return if participant can shore up a tile
		if(participant.getActionsRemaining() > 0 && participant.getHand().handContains(sandbagTreasureCard)) { // participant needs to have a sandbag card and have enough actions remaining // !!! might not be necessary anymore
			if(tile.getLocation() == participant.getLocation() || Math.abs(participant.getLocation()/6 - tile.getLocation()/6) == 1 || Math.abs(currentLocation%6 - tile.getLocation()%6) == 1) {
				tile.shoreUpTile();
				participant.actionUsed();
				return true;
			}
			else {
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	// This method will be overridden by the Messenger Participant
	protected void giveCard(Participant receiver, TreasureCard TreasureCardToGive) {
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
		}
		else {
			System.out.println(receiver.getName() + ", " + " is not on the same tile as you!\n Please choose another action.");
		}
	}

	protected ArrayList<Integer> relevantTiles(ArrayList<Tile> board) { // returns relevant tiles that the participant can move to
		
		ArrayList<Integer> relevantTiles = new ArrayList<Integer>();
        
		// generally, the participant can move left, right, up and down, these tiles will have to be checked
		relevantTiles.add(participant.getLocation() + 1);
		relevantTiles.add(participant.getLocation() - 1);
		relevantTiles.add(participant.getLocation() + 6);
		relevantTiles.add(participant.getLocation() - 6);
		
		for (int location : relevantTiles) {
			// check the TileStatus is not EMPTY of SUNK
			if (board.get(location).getTileStatus() == TileStatus.SUNK || // if tile is sunk or is a corner piece (empty)
				board.get(location).getTileType() == TileType.EMPTY) {    // the participant can't move there
				relevantTiles.remove(location);
			}
		}
		
		// observer should check for the list is empty !!!
		
		return relevantTiles;
	}
	
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
	
//		
//	
//		int x, y, up, down, left, right;
//		
//		// convert location to x, y coordinates
//		x = participant.getLocation()%6;
//		y = participant.getLocation()/6;
//		
//		up   = y + 1;
//		down = y - 1;
//		right= x + 1;
//		left = x - 1;
//		
//		board.get(participant.getLocation()).getTileStatus();
//		
//		// check if up is relevant
//		switch (board.get(participant.getLocation()-6).getTileStatus()){ // -6 to go up
//		case SUNK:
//			
//		}
		
//		if(Math.abs(participant.getLocation()/6 - newLocation/6) == 1 || Math.abs(participant.getLocation()%6 - newLocation%6) == 1) { // if the user has chosen a tile that is one move away
//			participant.actionUsed();
//		}
//		
//		else {
//		}
		
//		if(participant.getLocation()/6 == 0) { // participant is on an upper edge tile
//			relevantTileList.add(getLocation() + 6); // participant can move down (+6 as each tile below is +6)
//			board.getBoard();
//		}
//	    if (participant.getLocation()/6 == 5) {
//	    	relevantTileList.add(getLocation() - 6); // participant can move up (-6 as each tile above is -6)
//	    }	    	
//		if(participant.getLocation()%6 == 0 ) { // participant is on an left edge tile
//			relevantTileList.add(getLocation() + 1); // participant can move left (+1 as each tile to the left is +1)
//		}
//		if(participant.getLocation()%6 == 1) { // participant is on an right edge tile
//			
//		}
		 
//		if(Math.abs(participant.getLocation()/6 - newLocation/6) == 1 || Math.abs(participant.getLocation()%6 - newLocation%6) == 1) { // if the user has chosen a tile that is one move away
//			participant.setLocation(newLocation);
//			participant.actionUsed();
//			return true; // they can move
//		}
//		else {
//			return false; // they can't move
//		}
		
	
	protected void onSunkTile() {
		//verify the participant is on a sunk tile
		participant.getLocation();
		
	}

	public void shoreUp(Tile tile) {
		// TODO Auto-generated method stub
		
	}
}
