package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Board.*;
import Cards.*;
import Participant.*;
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
	protected Hand hand;
	protected Participant participant;
	
	GameManager() {
		board 			 = Board.getInstance();
		waterlevel 		 = WaterLevel.getInstance();
		playerList		 = PlayerList.getInstance();
		gui              = new GUI();
		floodCardDeck    = new FloodCardDeck();
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
		handOutCards();
		waterlevel.setMaxWaterLevel(gui.setDifficulty());
	}
	
	public void runGame() {
		Participant player;
		int choice;
		
		while(!foolsLandingLost && !treasureLost) {
			for(int i=0; i<playerList.getSize();i++) {
				player = playerList.getPlayer(i);
				
				while(player.getActionsRemaining() > 0) {
					choice = gui.chooseAction(player);
					
					switch(choice) {
						case 0:
							movePlayer(player);
							break;
							
						case 1:
							giveCard(player);
							break;
							
						case 2:
							player.shoreUp(gui.chooseTile());
							break;
							
						case 3:
							player.captureTreasure();
							break;
					}
				}
			}
		}
		
	}
	
	public void endGame() {
		
	}
	
	//
	// create the player characters at game startup
	//
	public void createPlayerList(){
		int playernums = 0;
		int location;
		String name;
		String[] roles = shuffleRoles();
		Participant player = null;
		
		if(!playerList.isCreated()) {
			playernums = gui.inputPlayerNumbers();
					
			for(int i=0; i<playernums; i++) {
				name = gui.inputPlayerName(i);
				Hand hand = new Hand();
				
				switch(roles[i]) {
				case "P":
					location = board.getPilotStartLoc();
					player = new Pilot(name,hand,i,location,3);
					break;
				case "G":
					location = board.getEngineerStartLoc();
					player = new Engineer(name,hand,i,location,3);
					break;
				case "E":
					location = board.getExplorerStartLoc();
					player = new Explorer(name,hand,i,location,3);
					break;
				case "D":
					location = board.getDiverStartLoc();
					player = new Diver(name,hand,i,location,3);
					break;
				case "M":
					location = board.getMessengerStartLoc();
					player = new Messenger(name,hand,i,location,3);
					break;
				case "N":
					location = board.getNavigatorStartLoc();
					player = new Navigator(name,hand,i,location,3);
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

//				notifyObserver(); // notify that an important tile has been changed
			}
		}		
		else{	
			if(specialTile.getTileStatus() == TileStatus.SUNK) {
				foolsLandingLost = true;
				//potentially update gui

//				notifyObserver(); // notify that an important tile has been changed
			}
		}		
	}
	
	//
	// Letters represent character roles Engineer, Diver etc, shuffle and return String role list
	//
	private String[] shuffleRoles(){	
		String[] roles = {"P","M","G","E","D","N"};
		List<String> roleList = Arrays.asList(roles);
		Collections.shuffle(roleList);	
		
		return roleList.toArray(roles);
	}
	
	
//	public boolean checkTreasureLost() {	
//		int lostOfSet = 0;
//		Board board = Board.getInstance();
//	
//		for (ArrayList<Tile> set: board.getAllSpecialSets()) {		
//			lostOfSet = 0;
//			
//			for(Tile tile: set) {
//				if(tile.getTileStatus() == TileStatus.SUNK) {
//					lostOfSet += 1;
//				}	
//			}		
//			if(lostOfSet > 1) {
//				return true;
//			}	
//		}
//		return false;	
//	}
//	
//	public boolean checkFoolsLandingLost() {
//		Board board = Board.getInstance();
//		
//		if(board.getFoolsLanding().getTileStatus() == TileStatus.SUNK) {
//			return true;
//		}
//		return false;
//	}
	
	//
	// facilitate a player giving a card from their hand
	//
	private void giveCard(Participant player) {
		int cardChoice;
		Participant receiver;
		TreasureCard card;
		
		cardChoice = gui.chooseCardToGive(player);
		
		receiver = playerList.getPlayer(gui.chooseReceiver(player,playerList.getAllPlayerExcept(player)));
		player.giveCard(receiver,card);
	}
	
	//
	// facilitate moving the player
	//
	private void movePlayer(Participant player) {
		int location;
		ArrayList<Integer> relevantTiles;
		ArrayList<Tile> brd  = board.getBoard();
		
		relevantTiles = player.getRelevantTiles(brd);
		location = gui.chooseNextLocation(relevantTiles, brd);
		player.move(location);
	}
	
	//
	// randomly flood the first 6 tiles to initialise the board
	//
	private void floodFirstSixTiles() {
		for(int i=0; i<6; i++) {
			floodCardDeck.draw();
		}
	}
	
	//
	// hand 2 card out to each player at the beginning of the game
	//
	private void handOutCards() {		
		TreasureCard card;
		Participant player;
		
		if(playerList.isCreated()) {
			for(int i=0; i<playerList.getSize();i++) {
				
				player = playerList.getPlayer(i);
				
				for(int cardsToDraw = 0; cardsToDraw<2;cardsToDraw++) {
					card = treasureCardDeck.draw();
					
					while(card instanceof RiseWaterTreasureCard) {
						treasureCardDeck.replace(card);
						card = treasureCardDeck.draw();
					}
					
					player.getHand().addCardToHand(card);
				}
			}
		}
	}
	
	// !!!!!!!REMOVE LATER !!!!!!!!!!
	public void callGUIDisplay(int playernums) {

		gui.updatePlayerHands(playerList);
		gui.updateBoard(board.getBoard(), playerList);
		gui.display();
		gui.chooseCardToDiscard(playerList.getPlayer(0),0);
	}
	
	public Hand handAfterRemoval() {
		int cardRemove;
		cardRemove = gui.chooseCardToDiscard(participant); 
		participant.getHand().removeCardFromHand(cardRemove);
		return hand;
	}
	
	
 
}
