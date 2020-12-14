package UnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Board.Board;
import Board.Tile;
import Board.TileType;

public class BoardTest {
	private Board board;
	private TileType emptyCorner;
	
	@Before
	public void setUp() {
		board = Board.getInstance();
		emptyCorner = TileType.EMPTY;
	}
	
	@Test
	public void test_board_tile_total_counts() {
		assertEquals("the number of tiles on the board should be 6x6=36",36,board.getBoard().size());
	}
	
	@Test
	public void test_earth_tile_counts() {
		assertEquals("the number of earth tiles on the board should be 2",2,board.getEarthSet().size());
	}
	
	@Test
	public void test_wind_tile_counts() {
		assertEquals("the number of wind tiles on the board should be 2",2,board.getWindSet().size());
	}
	
	@Test
	public void test_fire_tile_counts() {
		assertEquals("the number of fire tiles on the board should be 2",2,board.getFireSet().size());
	}
	
	@Test
	public void test_ocean_tile_counts() {
		assertEquals("the number of ocean tiles on the board should be 2",2,board.getOceanSet().size());
	}
	
	@Test
	public void test_empty_corner_tile_locations() {
		assertEquals("0 should be an empty tile",TileType.EMPTY,board.getBoard().get(0).getTileType());
		assertEquals("1 should be an empty tile",TileType.EMPTY,board.getBoard().get(1).getTileType());
		assertEquals("4 should be an empty tile",TileType.EMPTY,board.getBoard().get(4).getTileType());
		assertEquals("5 should be an empty tile",TileType.EMPTY,board.getBoard().get(5).getTileType());
		assertEquals("6 should be an empty tile",TileType.EMPTY,board.getBoard().get(6).getTileType());
		assertEquals("11 should be an empty tile",TileType.EMPTY,board.getBoard().get(11).getTileType());
		assertEquals("24 should be an empty tile",TileType.EMPTY,board.getBoard().get(24).getTileType());
		assertEquals("29 should be an empty tile",TileType.EMPTY,board.getBoard().get(29).getTileType());
		assertEquals("30 should be an empty tile",TileType.EMPTY,board.getBoard().get(30).getTileType());
		assertEquals("31 should be an empty tile",TileType.EMPTY,board.getBoard().get(31).getTileType());
		assertEquals("34 should be an empty tile",TileType.EMPTY,board.getBoard().get(34).getTileType());
		assertEquals("35 should be an empty tile",TileType.EMPTY,board.getBoard().get(35).getTileType());		
	}
	
	@After
	public void tearDown() {
		board = null;
	}

}
