package UnitTests;

import java.util.Scanner;

import org.junit.*;

import Board.Board;
import Cards.FloodCardDeck;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import Game.GUI;
import Game.GameManager;
import Participant.PlayerList;

import java.io.InputStream;

public class GameManagerTest {
	InputStream in;
	GUI gui;
	String input;
	FloodCardDeck floodCardDeck;
	GameManager GM;
	Board board;
	PlayerList playerlist;
	
	@Before
	public void setUp() {
		floodCardDeck = new FloodCardDeck();
		GM = GameManager.getInstance();
		board = Board.getInstance();
		playerlist.getInstance();
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
	

	
	@After
	public void tearDown() {
		floodCardDeck = null;
		GM = null;
		board = null;
		playerlist = null;
	}
		
		
}
