package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Board.*;
import Cards.*;
import Participant.Diver;
import Participant.Engineer;
import Participant.Explorer;
import Participant.Hand;
import Participant.Messenger;
import Participant.Navigator;
import Participant.Participant;
import Participant.Pilot;
import Participant.PlayerList;
import WaterLevel.WaterLevel;

public class GameManager {
	private static GameManager uniqueInstance = null;
	protected Board board;
	protected TreasureCardDeck treasureCardDeck;
	protected FloodCardDeck floodCardDeck;
	protected GUI gui;
	protected WaterLevel waterlevel;
	protected PlayerList playerList;
	protected boolean treasureLost;
	protected boolean foolsLandingLost;

	GameManager() {
		board 			 = Board.getInstance();
		//waterlevel 		 = WaterLevel.getInstance();
		playerList		 = PlayerList.getInstance();
		gui              = new GUI();
		//floodCardDeck    = new FloodCardDeck();
		treasureCardDeck = new TreasureCardDeck();
	}
	
	public static GameManager getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new GameManager();
		}
		return uniqueInstance;
	}
	
	public void setupGame() {
		floodFirstSixTiles();
		createPlayerList();
		
		waterlevel.setCurrentWaterLevel(gui.setDifficulty());
		
	
	}
	
	public void runGame() {
		while(!foolsLandingLost && !treasureLost) {
			
		}
		
	}
	
	public void endGame() {
		
	}
	
	//
	// create the player characters at game startup
	//
	public void createPlayerList(){
		int playernums = 0;
		String name;
		Participant player = null;
		int location;
		String[] roles = shuffleRoles();

		
		if(!playerList.isCreated()) {
			playernums = gui.inputPlayerNumbers();
					
			for(int i=0; i<playernums; i++) {
				name = gui.inputPlayerName(i);
				Hand hand = new Hand();
				
				switch(roles[i]) {
				case "P":
					location = board.getPilotStartLoc();
					player = new Pilot(name,hand,location,3);
					break;
				case "G":
					location = board.getEngineerStartLoc();
					player = new Engineer(name,hand,location,3);
					break;
				case "E":
					location = board.getExplorerStartLoc();
					player = new Explorer(name,hand,location,3);
					break;
				case "D":
					location = board.getDiverStartLoc();
					player = new Diver(name,hand,location,3);
					break;
				case "M":
					location = board.getMessengerStartLoc();
					player = new Messenger(name,hand,location,3);
					break;
				case "N":
					location = board.getNavigatorStartLoc();
					player = new Navigator(name,hand,location,3);
					break;
				}
				gui.printPlayerFinalised(player);
				playerList.addPlayer(player);
			}
			playerList.create();
		}

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
	
	//
	// integers represent character roles Engineer, Diver etc, shuffle and return int role list
	//
	private String[] shuffleRoles(){	
		String[] roles = {"P","M","G","E","D","N"};
		List<String> roleList = Arrays.asList(roles);
		Collections.shuffle(roleList);	
		
		return roleList.toArray(roles);
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
	
	private void floodFirstSixTiles() {
		for(int i=0; i<6; i++) {
			
		}
	}
	
	// !!!!!!!REMOVE LATER !!!!!!!!!!
	public void callGUIDisplay(int playernums) {
		
		for(int i=0; i<playernums; i++) {
			playerList.getPlayer(i)	.getHand().addCardToHand(treasureCardDeck.pop());
			playerList.getPlayer(i)	.getHand().addCardToHand(treasureCardDeck.pop());
			playerList.getPlayer(i)	.getHand().addCardToHand(treasureCardDeck.pop());
			playerList.getPlayer(i)	.getHand().addCardToHand(treasureCardDeck.pop());
		}
		gui.updatePlayerHands(playerList);
		gui.updateBoard(board.getBoard(), playerList);
		gui.display();
	}
	
	private void handOutCards() {
		
		
		if(playerList.isCreated()) {
			
		}
	}
	
	
 
}
