package UnitTests;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import Board.Board;
import Board.Tile;
import Board.TileType;
import Board.Treasures;

public class BoardTest {
	private Board board;
	private TileType emptyCorner;
	private Integer[] corners;
	private Treasures treasures;
	
	@Before
	public void setUp() {
		board = Board.getInstance();
		emptyCorner = TileType.EMPTY;
		corners = new Integer[] {0,1,4,5,6,11,24,29,30,31,34,35};
		treasures = new Treasures();
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

		for(int cornerId: corners) {
			assertEquals("should be an empty tile",emptyCorner,board.getBoard().get(cornerId).getTileType());
		}	
	}
	
	@Test
	public void test_board_is_ordered_by_location() {
		for(int i=0; i<36;i++) {
			assertEquals("tile location should equal array index if ordered",i,board.getBoard().get(i).getLocation());
		}
	}
	
	@Test
	public void test_roles_start_on_correct_tiles() {
		
		for(Tile tile: board.getBoard()) {
			
			switch(tile.getName()) {
			case "SILVER GATE":
				assertEquals("Messenger should start at Silver Gate",board.getMessengerStartLoc(),tile.getLocation());
				break;
			case "BRONZE GATE":
				assertEquals("Engineer should start at Bronze Gate",board.getEngineerStartLoc(),tile.getLocation());
				break;
			case "COPPER GATE":
				assertEquals("Explorer should start at Copper Gate",board.getExplorerStartLoc(),tile.getLocation());
				break;
			case "IRON GATE":
				assertEquals("Diver should start at Iron Gate",board.getDiverStartLoc(),tile.getLocation());
				break;
			case "GOLD GATE":
				assertEquals("Navigator should start at Gold Gate",board.getNavigatorStartLoc(),tile.getLocation());
				break;
			case "FOOLS LANDING":
				assertEquals("Pilot should start at Fools Landing",board.getPilotStartLoc(),tile.getLocation());
				break;
			default:
				break;
			}
		}
	}
	
	@Test
	public void test_treasure_capture() {
		ArrayList<String> validationList = new ArrayList<String>();
		
		assertEquals("Treasure retrieved is The Crystal of Fire","The Crystal of Fire",treasures.capture(TileType.FIRE));
		assertEquals("The number of treasures remaining should be 1",3,treasures.remaining());
		validationList.add("The Crystal of Fire");
		assertEquals("Incorrect treasure treasures in treasureBag",validationList,treasures.captured());

		assertEquals("Treasure retrieved is The Earth Stone","The Earth Stone",treasures.capture(TileType.EARTH));
		assertEquals("The number of treasures remaining should be 2",2,treasures.remaining());
		validationList.add("The Earth Stone");
		assertEquals("Incorrect treasure treasures in treasureBag",validationList,treasures.captured());
		
		assertEquals("Treasure retrieved is The Statue of the Wind","The Statue of the Wind",treasures.capture(TileType.WIND));
		assertEquals("The number of treasures remaining should be 2",1,treasures.remaining());
		validationList.add("The Statue of the Wind");
		assertEquals("Incorrect treasure treasures in treasureBag",validationList,treasures.captured());
		
		assertEquals("Treasure retrieved is The Ocean's Chalice","The Ocean's Chalice",treasures.capture(TileType.OCEAN));
		assertEquals("The number of treasures remaining should be 2",0,treasures.remaining());
		validationList.add("The Ocean's Chalice");
		assertEquals("Incorrect treasure treasures in treasureBag",validationList,treasures.captured());
		
		assertNull("cannot remove more than one instance of a treasure",treasures.capture(TileType.OCEAN));
		assertEquals("The number of treasures remaining should be 0",0,treasures.remaining());
		
		assertNull("cannot remove more than one instance of a treasure",treasures.capture(TileType.WIND));
		assertEquals("The number of treasures remaining should be 0",0,treasures.remaining());
		
		assertNull("cannot remove more than one instance of a treasure",treasures.capture(TileType.FIRE));
		assertEquals("The number of treasures remaining should be 0",0,treasures.remaining());
		
		assertNull("cannot remove more than one instance of a treasure",treasures.capture(TileType.EARTH));
		assertEquals("The number of treasures remaining should be 0",0,treasures.remaining());
	}
	
	@After
	public void tearDown() {
		board = null;
		emptyCorner = null;
		corners = null;
		treasures = null;
	}

}
