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

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import Game.GUI;
import Game.GameManager;
import Participant.PlayerList;
import Cards.TreasureCard;

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
	
	@Before
	public void setUp() {
		floodCardDeck = new FloodCardDeck();
		GM = GameManager.getInstance();
		board = Board.getInstance();
		playerlist = PlayerList.getInstance();
		treasures = new Treasures();
	}
	
	@Test
	public void test_setupGame() {
		
		// floodFirstSixTiles
		GM.floodFirstSixTiles();
		assertEquals("6 flooded tiles are expected after board setup",6,board.floodedTiles().size());
		
		// createPlayerList
		input = "2\nplayerA\ny\nplayerB\ny\n1";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM.createPlayerList();	
	}
	
	@Test
	public void testRunGame() {
		//
		// createPlayerList
		//
		input = "2\nplayerA\ny\nplayerB\ny\n1";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM.createPlayerList();	
		
		//
		// action 0 - move
		//
		GM.movePlayer(playerlist.getPlayer(0));
		
		// move player
		input = "13";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM.createPlayerList();	
		
		assertEquals("Player succesfully moved to tile 13",13 , playerlist.getPlayer(0).getLocation());
		
		//
		// action 1 - giveCard
		//
		GM.giveCard(playerlist.getPlayer(0));
		int initialSize = playerlist.getPlayer(1).getHand().size();
		
		// give card to other player
		input = "0\n1\n";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM.createPlayerList();	
		
		assertEquals("Player B's hand is 1 card longer than before",initialSize + 1, playerlist.getPlayer(1).getHand().size());
		
		//
		// action 2 - shoreUp
		//
		GM.shoreUp(playerlist.getPlayer(0));
		board.getBoard().get(13).setTileStatus(TileStatus.FLOODED); // set tile 13 to flooded
		
		// shoreUp tile 13
		input = "13";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GUI.getInstance().setScanner(new Scanner(in));
		GM.createPlayerList();	
		
		assertEquals("Player A has shored up tile 13",true, board.getBoard().get(13).getTileStatus()==TileStatus.UNFLOODED);
		
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
		
		//
		// action 4 - useCard
		//
		
	}
	
	@After
	public void tearDown() {
		floodCardDeck = null;
		GM = null;
		board = null;
		playerlist = null;
	}
		
		
}
