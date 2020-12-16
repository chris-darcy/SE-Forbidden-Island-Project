package Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Board.*;
import Cards.*;
import Game.GameManager;
import Game.GameObserver;

public abstract class Participant {
	protected String name; // !!! Need to reduce number of parameters - place in appropriate class
	public Hand hand;
	private int playerNum;
	protected int numberOfActions;
	protected int currentLocation;
	protected boolean sameTile;
	protected Participant participant; 
	protected int maxCards = 5;
	protected SandbagTreasureCard sandbagTreasureCard;
	protected ArrayList<Integer> relevantTileList = new ArrayList<Integer>();
	protected int location;
	private ArrayList<GameObserver> observerList = new ArrayList<GameObserver>();
	//------------------------------ CONSTRUCTORS ---------------------------------//

	protected Participant(String name, Hand hand, int playerNum, int location, int numberOfActions) {

		this.name = name;
		this.playerNum = playerNum;
		this.hand = hand;
		this.location = location;
		this.numberOfActions = numberOfActions;
	}
	
	//------------------------------ METHODS --------------------------------------//

	// !!!
	public boolean shoreUp(Tile tile) { // make boolean method canShoreUp()
		if(participant.getHand().handContains(sandbagTreasureCard) &&  // participant has the required card
		   tile.getTileStatus() != TileStatus.SUNK &&                  // tile they have chosen is not sunk 
		   isAdjacentTile(tile)) {                                     // tile is adjacent
				
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
	
	public boolean isAdjacentTile(Tile tile) {     // participant is on or adjacent to the tile
		if(tile.getLocation() == participant.getLocation() ||         
		   Math.abs(participant.getLocation()/6 - tile.getLocation()/6) == 1 || 
		   Math.abs(currentLocation%6 - tile.getLocation()%6) == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// This method will be overridden by the Messenger Participant
	public boolean giveCard(Participant receiver, TreasureCard treasureCardToGive) {
		// all players can give another player a treasure card if on the same tile
		if (receiver.getLocation() == this.location) {
			Hand giversHand = this.hand;
			Hand receiversHand = receiver.getHand();
			
			// observer!
			if(receiversHand.numberOfCards() >= maxCards - 1) { // after addition of new card, card hand will be too big
				notifyAllGameObservers(receiversHand);          //GameManager.handAfterRemoval(); // call method to get user to remove one of their cards
			}
			
			receiversHand.addCardToHand(treasureCardToGive);
			giversHand.removeCardFromHand(giversHand.findHandIndex(treasureCardToGive));
			
			participant.actionUsed();
			
			return true;
		}
		else {
			return false;
		}
	}

	public ArrayList<Integer> getRelevantTiles(ArrayList<Tile> board) { // returns relevant tiles that the participant can move to
		ArrayList<Integer> upDownLeftRight = new ArrayList<Integer>(Arrays.asList(+6, -6, -1, +1)); // input must be contained
		ArrayList<Integer> relevantTiles = new ArrayList<Integer>();
		
		for (int i : upDownLeftRight) {                                                       // check tiles up, down, left and right
			if(board.get(participant.getLocation() + i) != null &&                            // verify the tile is on the board
			   board.get(participant.getLocation() + i).getTileStatus() != TileStatus.SUNK && // verify the tile is not sunk
			   board.get(participant.getLocation() + i).getTileType() != TileType.EMPTY) {    // verify the tile is not a corner tile
				
				relevantTiles.add(participant.getLocation() + 6);
			}
		// observer !!!
		}
		return relevantTiles;
		
	}
	
	public void move(int newLocation) { // moves participant to new location
		this.location = newLocation;
		participant.actionUsed();
	}
	
	public boolean canCaptureTreasure(ArrayList<Tile> board) {
		int counter = 0;
		TileType tileType = board.get(this.location).getTileType();
		
		
		if(tileType == TileType.NORMAL || tileType == TileType.FOOLSLANDING) {
			return false;
		}
		for(int i=0; i<this.hand.numberOfCards();i++) {
			if(this.hand.getCardInHand(i).getName().equalsIgnoreCase(tileType.name())) {
				counter++;
			}
			if(counter == 4) {
				participant.actionUsed();
				return true;
			}
		}
		return false;
	}

	protected void onSunkTile(ArrayList<Tile> board) { // should possibly be called in Observer or something like that?
		//verify the participant is on a sunk tile
		if(board.get(participant.getLocation()).getTileStatus() != TileStatus.SUNK) {
			participant.getRelevantTiles(board); // for most participants, they will only be able to move to as normal
		}
	}
	
	@Override
	public String toString() {
		return this.name+"\n"+this.playerNum+"\n"+this.hand.toString()+"\n"+this.location+"\n"+this.numberOfActions+"\n"+this.getRoleName();
	}
	
	public void notifyAllGameObservers(Hand hand) {
		for(GameObserver observer : observerList) {
			observer.update(hand);
		}
	}
	
	public String getName() {
		return name;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	public int getPlayerNum() {
		return playerNum;
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
		return numberOfActions;
	}
	
	protected void setActionsRemaining(int numberOfActions) {
		this.numberOfActions = numberOfActions;
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
}
