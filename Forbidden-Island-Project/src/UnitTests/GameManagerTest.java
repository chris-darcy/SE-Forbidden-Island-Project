package UnitTests;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.*;

import Board.Board;
import Board.Tile;
import Board.TileStatus;
import Board.TileType;
import Board.Treasures;
import Cards.Card;
import Cards.FloodCardDeck;
import Cards.HelicopterTreasureCard;
import Cards.RiseWaterTreasureCard;
import Cards.SandbagTreasureCard;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import Game.GUI;
import Game.GameManager;
import Participant.Participant;
import Participant.PlayerList;
import Cards.TreasureCard;
import WaterLevel.WaterLevel;

import java.io.InputStream;

public class GameManagerTest {
	InputStream in;
	GUI gui;
	String input;
	FloodCardDeck floodCardDeck;
	GameManager GM;
	Board board;
	PlayerList playerlist;
	Treasures treasures;
	WaterLevel waterlevel;
	
	//
	// verify player numbers
	//
	@Test
	public void test() {
		input = "2\nplayerA\ny\nplayerB\ny\n2\n";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM = GameManager.getInstance();
		
		GM.setupGame();
		
		playerlist = PlayerList.getInstance();
		System.out.println(playerlist.getPlayerList().get(1).getHand().getPrintableHand());
		assertEquals("2nd player has playernum 1",2,playerlist.getPlayerList().size());
		
		playerlist.destroyMe();
		GM.destroyMe();
	}
	
	//
	// verify cards drawn correct and waterLevel
	//
	@Test
	public void test_setupGame() {
		input = "2\nplayerA\ny\nplayerB\ny\n2\n";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM = GameManager.getInstance();
		GM.setupGame();
		playerlist = PlayerList.getInstance();
		waterlevel = WaterLevel.getInstance();
		
		assertEquals("2 players expected after playerlist finalisation",2,playerlist.getPlayerList().size());
		for(Participant p: playerlist.getPlayerList()) {
			System.out.println(p.getHand().getPrintableHand());
			assertEquals("2 cards should be added to each hand, no watersrise",2,p.getHand().numberOfCards());
			assertFalse("no hand should contain a waters rise card",p.getHand().handContains(new RiseWaterTreasureCard("Rise Water")));
		}
		assertEquals("waterlevel set to 2",2,waterlevel.getCurrentWaterLevel());
		assertEquals("max waterlevel always set to 5",5,waterlevel.getMaxWaterLevel());
		
		playerlist.destroyMe();
		GM.destroyMe();
		waterlevel.destroyMe();
	}

	//
	// runGame's methods had to be tested individually as they required various inputs that
	// were not necessarily from the scanner.
	//
	@Test
	public void testMove() {	
		GM = GameManager.getInstance();
		playerlist = PlayerList.getInstance();
		board = Board.getInstance();
		//Game setup
		input = "\n2\nplayerA\ny\nplayerB\ny\n2\n";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM.setupGame();
		//
		// action 0 - move
		// move player
		board.getBoard().get(19).setTileStatus(TileStatus.UNFLOODED);
		playerlist.getPlayerList().get(0).setLocation(19);
		board.getBoard().get(13).setTileStatus(TileStatus.UNFLOODED);
		
		input = "\n13\n";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM.movePlayer(playerlist.getPlayerList().get(0));
		assertEquals("Player succesfully moved to tile 13",13 , playerlist.getPlayerList().get(0).getLocation());
		
		playerlist.destroyMe();
		GM.destroyMe();
		board.destroyMe();
	}
	
	//
	// verify giveCard() method in runGame()
	//
	@Test
	public void testGiveCard() {
		GM = GameManager.getInstance();
		playerlist = PlayerList.getInstance();
		board = Board.getInstance();
		//Game setup
		input = "\n2\nplayerA\ny\nplayerB\ny\n1\n0\n0\n";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM.setupGame();
		//
		// action 1 - giveCard
		//
		board.getBoard().get(13).setTileStatus(TileStatus.UNFLOODED);
		playerlist.getPlayer(0).setLocation(13);
		playerlist.getPlayer(1).setLocation(13);
		int initialSize = playerlist.getPlayer(1).getHand().size();
		GM.giveCard(playerlist.getPlayer(0));
		
		// give card to other player
		input = "0\n1\n";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM.createPlayerList();	
		
		assertEquals("Player B's hand is 1 card longer than before",initialSize + 1, playerlist.getPlayer(1).getHand().size());
		playerlist.destroyMe();
		GM.destroyMe();
		board.destroyMe();
	}
	
	//
	// verify shoreUp() method in runGame()
	//
	@Test
	public void testShoreUp() {
		GM = GameManager.getInstance();
		playerlist = PlayerList.getInstance();
		board = Board.getInstance();
		//Game setup
		input = "\n2\nplayerA\ny\nplayerB\ny\n2\n";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM.setupGame();
		//
		// action 2 - shoreUp
		//
		board.getBoard().get(13).setTileStatus(TileStatus.FLOODED); // set tile 13 to flooded
		board.getBoard().get(13).setTileType(TileType.NORMAL); // set tile 13 to normal
		
		// shoreUp tile 13
		input = "\n13\n";
		playerlist.getPlayer(0).setLocation(13);
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));	
		GM.shoreUp(playerlist.getPlayer(0));
		
		assertEquals("Player A has shored up tile 13",true, board.getBoard().get(13).getTileStatus()==TileStatus.UNFLOODED);
		
		playerlist.destroyMe();
		GM.destroyMe();
		board.destroyMe();
	}
	
	//
	// verify captureTreasure() method in runGame()
	//
	@Test
	public void testCaptureTreasure() {
		GM = GameManager.getInstance();
		board = Board.getInstance();
		playerlist = PlayerList.getInstance();
		treasures = new Treasures();
		//Game setup
		input = "\n2\nplayerA\ny\nplayerB\ny\n2\n";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM.setupGame();
		//
		// action 3 - capture treasure
		//
		GM.captureTreasure(playerlist.getPlayer(0));
		Card card1 = new TreasureCard("Ocean");   // set up the hand so the player can capture the treasure
		Card card2 = new TreasureCard("Ocean");
		Card card3 = new TreasureCard("Ocean");
		Card card4 = new TreasureCard("Ocean");
		playerlist.getPlayer(1).getHand().addCardToHand(card1);
		playerlist.getPlayer(1).getHand().addCardToHand(card2);
		playerlist.getPlayer(1).getHand().addCardToHand(card3);
		playerlist.getPlayer(1).getHand().addCardToHand(card4);
		ArrayList<Tile> oceanLocation = board.getOceanSet();
		playerlist.getPlayer(1).setLocation(oceanLocation.get(0).getLocation()); // set users location to an Ocean tile
		
		assertEquals("Player B has captured a treasure", 4, treasures.remaining());
		
		playerlist.destroyMe();
		GM.destroyMe();
		board.destroyMe();
	}

	//
	// verify useCard() method in runGame()
	//
	@Test
	public void testUseCard() {
		GM = GameManager.getInstance();
		playerlist = PlayerList.getInstance();
		board = Board.getInstance();
		input = "\n2\nplayerA\ny\nplayerB\ny\n1\n";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM.setupGame();
		
		
		Card card1 = new HelicopterTreasureCard("Helicopter");   // set up the hand so the player can capture the treasure
		Card card2 = new SandbagTreasureCard("Sandbag");
		
		board.getBoard().get(13).setTileStatus(TileStatus.UNFLOODED);
		board.getBoard().get(14).setTileStatus(TileStatus.UNFLOODED);
		
		playerlist.getPlayerList().get(0).setLocation(13);
		
		playerlist.getPlayerList().get(0).addCardToHand(card1);
		playerlist.getPlayerList().get(0).addCardToHand(card2);
		
		input = "\n0\n0\n14\n1\n0\n";                              // test helicopter
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM.useCard();
		
		assertEquals("Player A should be on tile 14", 14, playerlist.getPlayerList().get(0).getLocation());
		
		playerlist.destroyMe();
		GM.destroyMe();
		board.destroyMe();
	}
		
}
