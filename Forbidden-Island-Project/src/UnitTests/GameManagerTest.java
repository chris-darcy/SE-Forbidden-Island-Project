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
	
//	@Before
//	public void setup() {
//		GM = GameManager.getInstance();
//		playerlist = PlayerList.getInstance();
//		board = Board.getInstance();
//		waterlevel = WaterLevel.getInstance();
//		input = "2\nplayerA\ny\nplayerB\ny\n2\n";
//		in = new ByteArrayInputStream(input.getBytes());
//		System.setIn(in);
//		GUI.getInstance().setScanner(new Scanner(in));
//		GM.setupGame();
//	}
	
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
		
		
		//assertEquals("6 flooded tiles are expected after board setup",6,board.floodedTiles().size());
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
	
	

//	@After
//	public void tearDown() {
//		GM = null;
//		playerlist = null;
//		board = null;
//		waterlevel = null;
//	}
		
}
