package UnitTests;

import java.util.Scanner;

import org.junit.*;

import Board.Board;
import Cards.FloodCardDeck;
import Cards.RiseWaterTreasureCard;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import Game.GUI;
import Game.GameManager;
import Participant.Participant;
import Participant.PlayerList;
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
	WaterLevel waterlevel;
	
	@Before
	public void setUp() {
		floodCardDeck = new FloodCardDeck();
		GM = GameManager.getInstance();
		board = Board.getInstance();
		playerlist = PlayerList.getInstance();
		waterlevel = WaterLevel.getInstance();
	}
	
	@Test
	public void test_setupGame() {
		//Game setup
		input = "2\nplayerA\ny\nplayerB\ny\n2\n";
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

	
	@After
	public void tearDown() {
		floodCardDeck = null;
		GM = null;
		board = null;
		playerlist = null;
		waterlevel = null;
	}
		
		
}
