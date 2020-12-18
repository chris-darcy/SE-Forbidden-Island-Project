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
	private Board board;
	private TreasureCardDeck treasureCardDeck;
	private FloodCardDeck floodCardDeck;
	private GUI gui;
	private WaterLevel waterlevel;
	private PlayerList playerList;
	private Treasures treasures;
	private boolean treasureLost;
	private boolean foolsLandingLost;
	private Hand hand;
	private Participant participant;
	
	GameManager() {
		treasureLost = false;
		foolsLandingLost = false;
		board 			 = Board.getInstance();
		waterlevel 		 = WaterLevel.getInstance();
		playerList		 = PlayerList.getInstance();
		gui              = new GUI();
		floodCardDeck    = new FloodCardDeck();
		treasureCardDeck = new TreasureCardDeck();
		treasures        = new Treasures();		
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
		waterlevel.setWaterLevel(gui.setDifficulty());
	}
	
	public void runGame() {
		Participant player;
		int choice;
		
		while(!foolsLandingLost && !treasureLost) {
			gui.updateBoard(board.getStringBoard(), playerList);
			gui.updatePlayerHands(playerList.getAllStringPlayers());
			gui.updateTreasures(treasures.captured());
			gui.display();
			for(int i=0; i<playerList.getSize();i++) {
				player = playerList.getPlayer(i);
				while(player.getActionsRemaining() > 0) {
					choice = gui.chooseAction(player.toString());
					
					switch(choice) {
						case 0:
							movePlayer(player);
							break;
							
						case 1:
							giveCard(player);
							break;
						
						case 2:
							useCard(player);
							break;
							
						case 3:
							shoreUp(player);
							break;
							
						case 4:
							captureTreasure(player);
							break;
							
						case 5:
							player.setActionsRemaining(0);
							break;//end turn early
					}
					gui.updateBoard(board.getStringBoard(), playerList);
					gui.updatePlayerHands(playerList.getAllStringPlayers());
					gui.updateTreasures(treasures.captured());
					gui.display();
				}
				player.setActionsRemaining(3);
				drawFromTreasureDeck(player);
				drawFloodCards();
				gui.updateBoard(board.getStringBoard(), playerList);
				gui.updatePlayerHands(playerList.getAllStringPlayers());
				gui.updateTreasures(treasures.captured());
				gui.display();
			}
		}
		
	}

	public void endGame(boolean gameResult) {
		if(gameResult) {
			gui.gameWon();
		}
		else{
			gui.gameLost();
		}
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
				gui.printPlayerFinalised(player.toString());
				playerList.addPlayer(player);
			}
			playerList.create();
		}

	}
	
	public void updateSpecialTileStatus(Tile specialTile) {	
		
		if(specialTile.getTileType() != TileType.NORMAL) {
			ArrayList<Tile> set = board.getSpecialSet(specialTile.getTileType());
			
//			if(set.get(0).getTileStatus() == TileStatus.SUNK && set.get(1).getTileStatus() == TileStatus.SUNK) {
//				treasureLost = true;
				//potentially update gui 

//				notifyObserver(); // notify that an important tile has been changed
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
		boolean success;
		Participant receiver;
		TreasureCard card;
		
		receiver = playerList.getPlayer(gui.chooseReceiver(playerList.getAllStringPlayersExcept(player)));
		cardChoice = gui.chooseCardToGive(player.toString());
		card = player.hand.getTreasureCards().get(cardChoice);
		success = player.giveCard(receiver,card);
		gui.printGiveCardOutcome(player.getName(),receiver.getName(),card.getName(),success);
		
	}
	
	//
	// facilitate a player using their special cards
	//
	private void useCard(Participant player) {
		
		
	}
	//
	// facilitate moving the player
	//
	private void movePlayer(Participant player) {	
		int location = chooseLocationTo("move to",player);
		player.move(location);
	}
	
	//
	// facilitate shoring up a tile
	//
	private void shoreUp(Participant player) {		
		int location = chooseLocationTo("shore up", player);
		player.shoreUp(board.getBoard().get(location));
	}
	
	//
	// facilitate the player capturing a treasure
	//
	private void captureTreasure(Participant player) {
		boolean success = player.canCaptureTreasure(board.getBoard());
		TileType type;
		String treasure="";
		
		if(success) {
			type = board.getBoard().get(player.getLocation()).getTileType();
			treasure = treasures.capture(type);
		}
		gui.printTreasureCaptureOutcome(player.getName(),treasure,success);
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
	// facilitate a player drawing 2 Treasure Deck cards
	//
	private void drawFromTreasureDeck(Participant player) {
		TreasureCard card;
		
		for(int i=0;i<2;i++) {
			card = treasureCardDeck.draw();
			if(card instanceof RiseWaterTreasureCard) {
				waterlevel.increment();
				gui.updateWaterLevel();
				floodCardDeck.mergeAndShuffle();
			}
			else {
				player.getHand().addCardToHand(card);
			}
		}
	}
	
	//
	// draw appropriate flood cards
	//
	private void drawFloodCards() {
		int level = waterlevel.getCurrentWaterLevel();
		for(int i=0; i<level;i++) {
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
	
	//
	// facilitate getting choice of tile from relevant tiles for purpose e.g shoreUp, move
	//
	private int chooseLocationTo(String action,Participant player) {
		int location;
		ArrayList<Integer> relevantTiles;
		ArrayList<Tile> brd  = board.getBoard();
		
		relevantTiles = player.getRelevantTiles(brd);
		return gui.chooseLocationTo(action,relevantTiles, brd);
	}
	
	public Hand handAfterRemoval() {
		int cardRemove;
		cardRemove = gui.chooseCardToDiscard(participant.toString()); 
		participant.getHand().removeCardFromHand(cardRemove);
		return hand;
	}
	
	
 
}
