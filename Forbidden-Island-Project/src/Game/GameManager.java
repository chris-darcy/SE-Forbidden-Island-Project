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
	protected boolean treasureLost;
	protected boolean foolsLandingLost;

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
		while(!foolsLandingLost && !treasureLost) {
			
		}
		
	}
	
	public void endGame() {
		
	}
	
	public void updateSpecialTileStatus(Tile specialTile) {	
		
		if(specialTile.getTileType() != TileType.NORMAL) {
			ArrayList<Tile> set = board.getSpecialSet(specialTile.getTileType());
			
			if(set.get(0).getTileStatus() == TileStatus.SUNK && set.get(1).getTileStatus() == TileStatus.SUNK) {
				treasureLost = true;
				//potentially update gui 
			}
		}		
		else{	
			if(specialTile.getTileStatus() == TileStatus.SUNK) {
				foolsLandingLost = true;
				//potentially update gui
			}
		}		
	}
	
	
	public boolean checkTreasureLost() {	
		int lostOfSet = 0;
		Board board = Board.getInstance();
	
		for (ArrayList<Tile> set: board.getAllSpecialSets()) {		
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
	
	public boolean checkFoolsLandingLost() {
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
