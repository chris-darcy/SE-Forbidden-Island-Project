package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Board.*;
import Participant.*;

public class ParticipantTest {
	private Board board;
	private TileType emptyCorner;
	private Participant player;
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
	}

	@After
	public void tearDown() {
		board = null;
		emptyCorner = null;
		player = null;
		hand = null;
	}
}
