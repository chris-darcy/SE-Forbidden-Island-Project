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
	
	@Before
	public void setUp() {
		floodCardDeck = new FloodCardDeck();
		GM = GameManager.getInstance();
		board = Board.getInstance();
		playerlist = PlayerList.getInstance();
		treasures = new Treasures();
		waterlevel = WaterLevel.getInstance();
	}
	
	@Test
	public void test_setupGame() {
		//Game setup
		input = "\n2\nplayerA\ny\nplayerB\ny\n2\n";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM.setupGame();
		assertEquals("6 flooded tiles are expected after board setup",6,board.floodedTiles().size());
		assertEquals("2 players expected after playerlist finalisation",2,playerlist.getPlayerList().size());
		for(Participant p: playerlist.getPlayerList()) {
			assertEquals("2 cards should be added to each hand, no watersrise",2,p.getHand().numberOfCards());
			assertFalse("no hand should contain a waters rise card",p.getHand().handContains(new RiseWaterTreasureCard("Rise Water")));
		}
		assertEquals("waterlevel set to 2",2,waterlevel.getCurrentWaterLevel());
		assertEquals("max waterlevel always set to 5",5,waterlevel.getMaxWaterLevel());
	}

	//
	// runGame's methods had to be tested individually as they required various inputs that
	// were not necessarily from the scanner.
	//
	@Test
	public void testMove() {	
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
	}
	
	@Test
	public void testGiveCard() {
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
	}
	
	@Test
	public void testShoreUp() {
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
	}
	
	@Test
	public void testCaptureTreasure() {
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
	}

	//
	// action 4 - useCard
	//
	@Test
	public void testUseCard() {
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
	}
	
	@Test
	public void test() {
		input = "\n2\nplayerA\ny\nplayerB\ny\n";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM.setupGame();
		assertEquals("1st player has playernum 0",0,playerlist.getPlayer(0).getPlayerNum());
	}
	
	@After
	public void tearDown() {
		floodCardDeck = null;
		GM = null;
		board = null;
		playerlist = null;
		waterlevel = null;
	}
		
		
}
