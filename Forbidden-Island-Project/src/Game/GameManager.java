package Game;

import java.util.ArrayList;

import Board.*;
import Cards.CardDeck;
import Participant.Participant;
import Participant.PlayerList;
import WaterLevel.WaterLevel;

public class GameManager {
	private static GameManager uniqueInstance = null;
	protected Board board;
	protected CardDeck cardDeck;
	protected GUI gui;
	protected WaterLevel waterlevel;
	protected PlayerList playerList;

	GameManager() {}
	
	public static GameManager getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new GameManager();
		}
		return uniqueInstance;
	}
	
	public void setupGame() {
		gui = new GUI();
		//board = Board.getInstance();
		cardDeck = new CardDeck();
		gui.createPlayerList();
		//playerList = PlayerList.getInstance();
		waterlevel = gui.setDifficulty();
		
		
	
	}
	
	public void runGame() {
		
	}
	
	public void endGame() {
		
	}
	
	public boolean aTreasureLost() {	
		int lostOfSet = 0;
		Board board = Board.getInstance();
	
		for (ArrayList<Tile> set: board.getSpecialSets()) {		
			lostOfSet = 0;
			
			for(Tile tile: set) {
				if(tile.getTileStatus() == TileStatus.SUNK) {
					lostOfSet += 1;
				}	
			}		
			if(lostOfSet > 1) {
				return true;
			}	
		}
		return false;	
	}
	
	public boolean foolsLandingLost() {
		Board board = Board.getInstance();
		
		if(board.getFoolsLanding().getTileStatus() == TileStatus.SUNK) {
			return true;
		}
		return false;
	}
	
	public void handOutCards() {
		PlayerList playerList = PlayerList.getInstance();
		
		
		if(playerList.isCreated()) {
			
		}
	}
	
	
 
}
