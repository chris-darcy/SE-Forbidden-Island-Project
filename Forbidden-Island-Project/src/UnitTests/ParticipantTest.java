package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Board.*;
import Cards.Card;
import Cards.TreasureCard;
import Participant.*;

public class ParticipantTest {
	private Board board;
	private TileType emptyCorner;
	private Participant player;
	private Card card;
	private Hand hand;

	//  THE BOARD
	//  0   1 [ 2][ 3]  4   5      
	//  6 [ 7][ 8][ 9][10] 11
	//[12][13][14][15][16][17]
	//[18][19][20][21][22][23]
	// 24 [25][26][27][28] 29
	// 30  31 [32][33] 34  35
	@Before
	public void setUp() {
		board = Board.getInstance();
		emptyCorner = TileType.EMPTY;
		hand = new Hand();
		player = new Engineer("A",hand,1,0,3);// start player with empty hand at tile 0, row 0 col 0	
		
	}
	
	@Test
	public void retrieve_relevant_movement_tiles() {
		for(int i=0;i<36;i++) {
			Tile tile = board.getBoard().get(i);
			if(tile.getTileType() != TileType.EMPTY) {
				tile.setTileStatus(TileStatus.UNFLOODED);
			}
		}
		
		Integer[][] tl = {{    } ,{2,7}        ,{3,8}        ,{2,9}        ,{3,10}       ,{     },
  			              {7,12} ,{8,13}       ,{2,7,9,14}   ,{3,8,10,15}  ,{9,16}       ,{10,17},  
  			              {13,18},{7,12,14,19} ,{8,13,15,20} ,{9,14,16,21} ,{10,15,17,22},{16,23},
  			              {12,19},{13,18,20,25},{14,19,21,26},{15,20,22,27},{16,21,23,28},{17,22},
  			              {18,25},{19,26}      ,{20,25,27,32},{21,26,28,33},{22,27}      ,{23,28},
  			              {    } ,{25,32}      ,{26,33}      ,{27,32}      ,{28,33}      ,{     }};
		ArrayList<Integer> relevantTiles = new ArrayList<Integer>();
		ArrayList<Integer> validationSet = new ArrayList<Integer>();

		for(int i=0;i<36;i++) {
			player.move(i);
			Collections.addAll(validationSet,tl[i]);
			relevantTiles = player.getRelevantTiles(board.getBoard(), false);
			Collections.sort(relevantTiles);// order matters
			assertEquals("relevant positions expected not returned",validationSet,relevantTiles);
			validationSet.clear();
			relevantTiles.clear();
		}
		for(int i=0;i<36;i++) {
			Tile tile = board.getBoard().get(i);
			if(tile.getTileType() != TileType.EMPTY) {
				tile.setTileStatus(TileStatus.FLOODED);
			}
		}
		
		Integer[][] tlFlooded = {{    }    ,{2,7}           ,{2,3,8}         ,{2,3,9}         ,{3,10}          ,{     },
	              				 {7,12}    ,{7,8,13}        ,{2,7,8,9,14}    ,{3,8,9,10,15}   ,{9,10,16}       ,{10,17},  
	              				 {12,13,18},{7,12,13,14,19} ,{8,13,14,15,20} ,{9,14,15,16,21} ,{10,15,16,17,22},{16,17,23},
	              				 {12,18,19},{13,18,19,20,25},{14,19,20,21,26},{15,20,21,22,27},{16,21,22,23,28},{17,22,23},
	              				 {18,25}   ,{19,25,26}      ,{20,25,26,27,32},{21,26,27,28,33},{22,27,28}      ,{23,28},
	              				 {    }    ,{25,32}         ,{26,32,33}      ,{27,32,33}      ,{28,33}         ,{     }};
		for(int i=0;i<36;i++) {   
			player.move(i);
			Collections.addAll(validationSet,tlFlooded[i]);
			relevantTiles = player.getRelevantTiles(board.getBoard(), true);
			Collections.sort(relevantTiles);// order matters
			assertEquals("relevant shoreup positions expected not returned",validationSet,relevantTiles);
			validationSet.clear();
			relevantTiles.clear();
		}
		
		for(int i=0;i<36;i++) {
			Tile tile = board.getBoard().get(i);
			if(tile.getTileType() != TileType.EMPTY) {
				tile.setTileStatus(TileStatus.UNFLOODED);
			}
		}
	}
	
	@Test
	public void test_can_capture_treasure() {
	String [] types = {"Wind","Ocean","Fire","Earth"};
	
		for(int j=0;j<4;j++) {
			for(int i = 0;i<3 ;i++) {
				card = new TreasureCard(types[j]);
				player.addCardToHand(card);	
			}
			player.move(board.getAllSpecialSets().get(j).get(0).getLocation());
			assertFalse("not enough cards to capture",player.canCaptureTreasure(board.getBoard()));
			player.move(board.getAllSpecialSets().get(j).get(1).getLocation());
			assertFalse("not enough cards to capture",player.canCaptureTreasure(board.getBoard()));
			
			card = new TreasureCard(types[j]);
			player.addCardToHand(card);
			
			player.move(board.getAllSpecialSets().get(j).get(0).getLocation());
			assertTrue("4 card of same kind and on the right tile",player.canCaptureTreasure(board.getBoard()));
			player.getHand().discardFour(types[j]);
			player.move(board.getAllSpecialSets().get(j).get(1).getLocation());
			assertFalse("cards are now discarded and treasure is captured",player.canCaptureTreasure(board.getBoard()));
		}
	}
	
	@Test
	public void test_onSunkTile() {
		for(int i=0;i<36;i++) {
			Tile tile = board.getBoard().get(i);
			if(tile.getTileType() != TileType.EMPTY) {
				tile.setTileStatus(TileStatus.UNFLOODED);
			}
		}
		
		Stack<Integer> validationSet1 = new Stack<Integer>();
		validationSet1.push(14);validationSet1.push(19);validationSet1.push(21);validationSet1.push(26);validationSet1.push(0);
		
		player.move(20);
		board.getBoard().get(20).setTileStatus(TileStatus.SUNK);
		
		for(int i=0;i<5;i++) {
			board.getBoard().get(validationSet1.pop()).setTileStatus(TileStatus.SUNK);
			ArrayList<Integer> testSet = player.onSunkTile(board.getBoard());
			Collections.sort(testSet);
			assertEquals("reachable tiles diminished",validationSet1,testSet);
		}
		
		for(int i=0;i<36;i++) {
			Tile tile = board.getBoard().get(i);
			if(tile.getTileType() != TileType.EMPTY) {
				tile.setTileStatus(TileStatus.UNFLOODED);
			}
		}
	}	
	@Test 
	public void test_explorer_onSunkTile() {
		Participant explorer = new Explorer("B",hand,1,0,3);
		for(int i=0;i<36;i++) {
			Tile tile = board.getBoard().get(i);
			if(tile.getTileType() != TileType.EMPTY) {
				tile.setTileStatus(TileStatus.UNFLOODED);
			}
		}
		
		explorer.move(12);
		board.getBoard().get(12).setTileStatus(TileStatus.SUNK);
		//ArrayList<Integer> validationSet = new ArrayList<Integer>(Arrays.asList((13), (14),(15), (19),(21),(25),(26),(27))); 
		ArrayList<Integer> validationSet = new ArrayList<Integer>(Arrays.asList((7),(13),(18),(19))); 
		ArrayList<Integer> testSet = new ArrayList<Integer>();
		testSet = explorer.onSunkTile(board.getBoard());
		Collections.sort(testSet);
		assertEquals("explorer get onsunk tiles should inlcude diagonals",validationSet,testSet);
		for(int i=0;i<36;i++) {
			Tile tile = board.getBoard().get(i);
			if(tile.getTileType() != TileType.EMPTY) {
				tile.setTileStatus(TileStatus.UNFLOODED);
			}
		}
	}
		
	@Test 
	public void test_diver_onSunkTile() {
		for(int i=0;i<36;i++) {
			Tile tile = board.getBoard().get(i);
			if(tile.getTileType() != TileType.EMPTY) {
				tile.setTileStatus(TileStatus.UNFLOODED);
			}
		}
		
	
	}

	@After
	public void tearDown() {
		board = null;
		emptyCorner = null;
		player = null;
		hand = null;
	}
}
