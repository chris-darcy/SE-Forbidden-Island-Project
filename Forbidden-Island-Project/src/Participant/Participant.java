package Participant;

import java.util.ArrayList;
import java.util.Arrays;
import Board.*;
import Cards.*;
import Game.GameManager;
import Observers.Subject;

public abstract class Participant extends Subject{
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
					default:
				}
				
				participant.actionUsed();
				return true;
		}
		else {
				return false;
			}
		}
	
	public boolean isAdjacentTile(Tile tile) {     // participant is on or adjacent to the tile
		int xyPlayer[] = xyLoc(this.location);
		int xyTile[] =  xyLoc(tile.getLocation());
		
		if(tile.getLocation() == this.location ||         
		   Math.abs(xyPlayer[0] - xyTile[0]) == 1 || 
		   Math.abs(xyPlayer[1] - xyTile[1]) == 1) {
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
				GameManager.getInstance().handAfterRemoval(); // call method to get user to remove one of their cards
			}
			
			receiversHand.addCardToHand(treasureCardToGive);
			giversHand.removeCardFromHand(giversHand.findHandIndex(treasureCardToGive));
			
			this.actionUsed();
			
			return true;
		}
		else {
			return false;
		}
	}

	public ArrayList<Integer> getRelevantTiles(ArrayList<Tile> board) {     // returns relevant tiles that the participant can move to
		ArrayList<Integer> relevantTiles = calculateRelevantTiles(board);
		if(!relevantTiles.isEmpty()) {
			notifyAllObservers(); // game over as participant can't move
			return relevantTiles;
		}
		else {
			return null;
		}
	}
	public ArrayList<Integer> calculateRelevantTiles(ArrayList<Tile> board){
		int[] xyPlayer = xyLoc(this.location);
		
		ArrayList<Integer> upDownLeftRight = new ArrayList<Integer>(Arrays.asList(6, -6, -1, +1)); // input must be contained
		ArrayList<Integer> relevantTiles = new ArrayList<Integer>();
	
		for (int i : upDownLeftRight) {                                     // check tiles up, down, left and right
			int testPos = this.location + i;
			
			if(testPos > 1 && testPos < 34) {
				if(board.get(testPos).getTileStatus() != TileStatus.SUNK && // verify the tile is not sunk
				   board.get(testPos).getTileType() != TileType.EMPTY){     // verify the tile is not a corner tile
					int[] xyTile = xyLoc(testPos);
					
					if(Math.abs(xyPlayer[0] - xyTile[0]) <= 1 && Math.abs(xyPlayer[1] - xyTile[1]) <= 1) { // verify position truly 1 away
						relevantTiles.add(testPos);
					}
				}
			}
		}
		return relevantTiles;
	}
	
	public void move(int newLocation) { // moves participant to new location
		this.location = newLocation;
		this.actionUsed();
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
				this.actionUsed();
				return true;
			}
		}
		return false;
	}

	public ArrayList<Integer> onSunkTile(ArrayList<Tile> board) { // should possibly be called in Observer or something like that?
		//verify the participant is on a sunk tile
		if(board.get(this.location).getTileStatus() != TileStatus.SUNK) {
			// for most participants, they will only be able to move to as normal
			if(this.getRelevantTiles(board).isEmpty()) {
				notifyAllObservers(); // getRelevant tiles is empty - game is lost
			}
			return this.getRelevantTiles(board); 
		}
		else {
			return null;
		}
	}
	
	@Override
	public String toString() {
		return this.name+"\n"+this.playerNum+"\n"+this.hand.toString()+"\n"+this.location+"\n"+this.numberOfActions+"\n"+this.getRoleName()+"\n"+this.hand.getTreasureCards().toString();
	}
	
	protected int[] xyLoc(int location) {
		int [] xyLoc = {location%6,location/6};
		return xyLoc;
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
	
	public void setActionsRemaining(int numberOfActions) {
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
