package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Board.*;
import Cards.*;
import Observers.ParticipantObserver;
import Observers.WaterLevelObserver;
import Observers.WinningObserver;
import Participant.*;
import WaterLevel.WaterLevel;

public class GameManager {
	private static GameManager uniqueInstance = null;
	private Board board;
	private TreasureCardDeck treasureCardDeck;
	private FloodCardDeck floodCardDeck;
	private GUI gui;
	private WaterLevel waterLevel;
	private PlayerList playerList;
	private Treasures treasures;
	private boolean gameOver;
	private Hand hand;
	
	GameManager() {
		gameOver = false;
		board 			 = Board.getInstance();
		waterLevel 		 = WaterLevel.getInstance();
		playerList		 = PlayerList.getInstance();
		gui              = GUI.getInstance();
		floodCardDeck    = new FloodCardDeck();
		treasureCardDeck = new TreasureCardDeck();
		treasures        = new Treasures();	
		
		new WinningObserver(treasures); //attach observer to check number of captured treasures
	}
	
	public static GameManager getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new GameManager();
		}
		return uniqueInstance;
	}
	
	public void setupGame() {
		gui.introduction();
		floodFirstSixTiles();
		createPlayerList();
		handOutCards();
		waterLevel.setWaterLevel(gui.setDifficulty());
		new WaterLevelObserver(waterLevel); // attach observer to check the waterLevel
	}
	
	public void runGame() {
		Participant player;
		int choice;
		
		while(!gameOver) {
			for(int i=0; i<playerList.getSize();i++) {
				player = playerList.getPlayer(i);
				while(player.getActionsRemaining() > 0  && !gameOver) {
					gui.updateBoard(board.getStringBoard(), playerList);
					gui.updatePlayerHands(playerList.getAllStringPlayers());
					gui.updateTreasures(treasures.captured());
					gui.display();
					choice = gui.chooseAction(player.toString());
					switch(choice) {
						case 0:
							movePlayer(player);
							break;
							
						case 1:
							giveCard(player);
							break;
							
						case 2:
							shoreUp(player);
							break;
							
						case 3:
							captureTreasure(player);
							break;
							
						case 4:
							useCard();
							break;
							
						case 5:
							player.setActionsRemaining(0);
							break;//end turn early
					}
					
					if(gameOver) {
						break;
					}
				}
				if(gameOver) {
					break;
				}
				player.setActionsRemaining(3);
				drawFromTreasureDeck(player);
				drawFloodCards();
				
			}
		}
		
	}
	
	// end of game logic
	public void endGame(boolean gameResult) {
		if(gameResult) {		
			gui.gameWon();
		}
		else{
			gameOver = true;
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
				
				new ParticipantObserver(player);   // attach observer
			}
			playerList.create();
			
		}
		
	}
	
	//
	// check for the loss of special tiles
	//
	public void updateSpecialTileStatus(Tile specialTile) {	
		
		if(specialTile.getTileType() != TileType.FOOLSLANDING) {
			ArrayList<Tile> set = board.getSpecialSet(specialTile.getTileType());
			gui.printSpecialTileSunk(specialTile.toString());
			if(set.get(0).getTileStatus() == TileStatus.SUNK && set.get(1).getTileStatus() == TileStatus.SUNK && treasures.uncaptured(specialTile.getTileType())) {
				endGame(false);
			}
		}
		else{	
			if(specialTile.getTileStatus() == TileStatus.SUNK) {
				endGame(false);
			}
		}		
	}
	
	//
	// Check users tile status
	//
	public void updateParticipantTileStatus(Tile tile) {
		ArrayList<Integer> availableTiles = new ArrayList<Integer>();
		int location = 0;
		for(Participant p : playerList.getPlayerList()) {
			if(p.getLocation() == tile.getLocation() && tile.getTileStatus() == TileStatus.SUNK) {
			   availableTiles = p.onSunkTile(board.getBoard());
			   gui.printOnSunkTile(p.getName());
			   location = gui.chooseLocationTo("swim", availableTiles, board.getBoard());
			   p.setLocation(location);
			}
		}
	}
	
	
	//
	// Letters represent character roles Engineer, Diver etc, shuffle and return String role list
	//
	public String[] shuffleRoles(){	
		String[] roles = {"P","M","G","E","D","N"};
		List<String> roleList = Arrays.asList(roles);
		Collections.shuffle(roleList);	
		
		return roleList.toArray(roles);
	}
	
	//
	// facilitate a player giving a card from their hand
	//
	public void giveCard(Participant player) {
		int cardChoice;
		boolean success;
		Participant receiver;
		Card card;
		
		if(!player.getHand().getTreasureCards().isEmpty()) {
			receiver = playerList.getPlayer(gui.chooseReceiver(playerList.getAllStringPlayersExcept(player)));
			cardChoice = gui.chooseCardToGive(player.toString());
			card = player.hand.getTreasureCards().get(cardChoice);
			success = player.giveCard(receiver,card);            
			gui.printGiveCardOutcome(player.getName(),receiver.getName(),card.getName(),success);
		}
		else {
			gui.printNoTreasureCards(player.getName());
		}	
	}
	
	//
	// facilitate a player using their special cards
	//
	public void useCard() {
		int choice = gui.useSpecialCard();
		ArrayList<Participant> specialParticipantList = new ArrayList<Participant>();
		int hasCardParticipant;
		Participant participantHasCard;
		boolean helicopter;
		
		if(choice == 0) { // type helicopter
			specialParticipantList = playerList.playerListContainsHelicopterCard();
			helicopter = true;
		}
		else { // type sandbag
			specialParticipantList = playerList.playerListContainsSandBagCard();
			helicopter = false;
		}

		if(specialParticipantList.isEmpty()) {
			gui.printNoSpecialCards();
		}
		else {
			hasCardParticipant = gui.choosePlayerToUseCard(playerList.playerListStringify(specialParticipantList));
			participantHasCard = playerList.getPlayer(hasCardParticipant);
			treasureCardDeck.addToDiscardPile(participantHasCard.getHand().removeSpecialCard(helicopter));			
			
			if(choice == 0) {
				helicopter();
			}
			else{
				sandbag(participantHasCard);
			}
			
		}
	
	}
	
	public void sandbag(Participant player) {
		int location = gui.chooseLocationTo("shore up", board.floodedTiles(), board.getBoard());
		board.getBoard().get(location).setTileStatus(TileStatus.UNFLOODED);;
	}
	
	//
	// facilitate moving the player
	//
	public void movePlayer(Participant player) {	
		int location = choosePlayerLocationTo("move to",player);
		player.move(location);
	}
	
	//
	// facilitate shoring up a tile
	//
	public void shoreUp(Participant player) {		
		int location = choosePlayerLocationTo("shore up", player);
		player.shoreUp(board.getBoard().get(location));
		if(player instanceof Engineer){
			gui.printEngineerShoreUoReminder();
		}
	}
	
	//
	// facilitate movement of players to a location with helicopter card
	//
	public void helicopter() {
		int location = gui.chooseLocationTo("helicopter", board.unSunkTiles(), board.getBoard()); // obtain location to helicopter participants to
		ArrayList<Integer> playersToMove = gui.chooseHelicopterParticipants(location);
		for (int i : playersToMove) {
			playerList.getPlayer(i).setLocation(location);
		}
	}
	
	//
	// facilitate the player capturing a treasure
	//
	public void captureTreasure(Participant player) {
		boolean success = player.canCaptureTreasure(board.getBoard());
		TileType type;
		String treasure="";
		
		type = board.getBoard().get(player.getLocation()).getTileType();
		if(treasures.uncaptured(type)) {
			if(success){
				treasure = treasures.capture(type);
				treasureCardDeck.receiveDiscarded(player.getHand().discardFour(type.name()));
			}
			gui.printTreasureCaptureOutcome(player.getName(),treasure,success);
		}
		else {
			gui.printTreasureDuplicate();
		}
	}
	
	//
	// randomly flood the first 6 tiles to initialise the board
	//
	public void floodFirstSixTiles() {
		for(int i=0; i<6; i++) {
			floodCardDeck.draw();
		}
	}
	
	//
	// facilitate a player drawing 2 Treasure Deck cards
	//
	public void drawFromTreasureDeck(Participant player) {
		Card card;
		if(!gameOver) {   // ensure that cards are not drawn after end of game
			for(int i=0;i<2;i++) {
				card = treasureCardDeck.draw();
				if(card instanceof RiseWaterTreasureCard) {
					waterLevel.increment();
					if(!gameOver) {
						gui.updateWaterLevel();
						gui.printWatersRise();
						floodCardDeck.mergeAndShuffle();
					}
				}
				else {
					player.addCardToHand(card);
				}
			}
		}
	}
	
	//
	// draw appropriate flood cards
	//
	public void drawFloodCards() {
		int level = waterLevel.getCurrentWaterLevel();
		if(!gameOver) {   // ensure that cards are not drawn after end of game
			for(int i=0; i<level;i++) {
				floodCardDeck.draw();		
			}
		}
	}
	
	//
	// hand 2 card out to each player at the beginning of the game
	//
	public void handOutCards() {		
		Card card;
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
	public int choosePlayerLocationTo(String action,Participant player) {
		ArrayList<Integer> relevantTiles;
		ArrayList<Tile> brd  = board.getBoard();
		boolean shoreUp = action.equals("shore up");
		
		relevantTiles = player.getRelevantTiles(brd,shoreUp);
		return gui.chooseLocationTo(action,relevantTiles, brd);
	}
	
	
	public Hand handAfterRemoval(Participant player) {
		int cardRemove;
		cardRemove = gui.chooseCardToDiscard(player.toString()); 
		treasureCardDeck.addToDiscardPile(player.getHand().removeCardFromHand(cardRemove));
		return player.getHand();
	}

}
