package Board;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Board {
	
	private int board_size = 36;
	private ArrayList<Tile> board =  new ArrayList<Tile>(); 
	private Integer[] corners = new Integer[]{0,1,4,5,6,11,24,29,30,31,34,35};
	
	public Board() {
		initialise();
	}
	
	public void initialise() {
	
		int tilePos;
		String line;
		String[] attributes;
		ArrayList<Integer> nums = new ArrayList<Integer>();
		TileType tiletype = TileType.NORMAL;
		
		// Randomise location indices
		for(int i=0;i<board_size;i++){
			nums.add(i);
		}
		Collections.shuffle(nums);
		Iterator<Integer> idx = nums.iterator();
		
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader("src/Tiles.txt"));
			for (int i = 0; i < board_size; i++) {    	
		    	try {
					
		    		tilePos = idx.next();
				    
				    if(isCorner(tilePos)) {
				    	Tile tile = new Tile("Corner",tilePos,TileStatus.SUNK,TileType.NORMAL);
				    	board.add(tile);
				    }
				    else {	
				    	line = scan.readLine();
						attributes = line.split("-"); //Delimiter is a hyphen
				    	
				    	switch(attributes[1]) {
				    		case "W":
				    			tiletype = TileType.WIND;
				    			break;
				    		case "O":
				    			tiletype = TileType.OCEAN;
				    			break;
				    		case "F":
				    			tiletype = TileType.FIRE;
				    			break;
				    		case "E":
				    			tiletype = TileType.EARTH;
				    			break;
				    		case "N":
				    			tiletype = TileType.NORMAL;
				    			break;
				    	}
				    	Tile tile = new Tile(attributes[0],tilePos,TileStatus.UNFLOODED,tiletype);
				    	board.add(tile);
				    }
				    
				    // Order board by location for easy reading
				    Collections.sort(board,new Sortbyloc());
				    
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}				
	}

	
	// display the board and special tiles in console
	public void show() {
		String[] layout = new String[] {"","","","","",""};
		int row = 0;
		
		for (Tile tile : board){
			if(row != tile.getLoc()/6) {
				row = tile.getLoc()/6;
			}
			if(isCorner(tile.getLoc())) {
				layout[row] = layout[row] + "   ";
			}
			else {
				switch(tile.getTileType()) {
	    		case WIND:
	    			layout[row] = layout[row] + "[W]";
	    			break;
	    		case OCEAN:
	    			layout[row] = layout[row] + "[O]";
	    			break;
	    		case FIRE:
	    			layout[row] = layout[row] + "[F]";
	    			break;
	    		case EARTH:
	    			layout[row] = layout[row] + "[E]";
	    			break;
	    		case NORMAL:
	    			layout[row] = layout[row] + "[ ]";
	    			break;
				}
			}
		}
		for (String line : layout) {
			System.out.println(line);
		}
	}
	
	// tiles not part of actual play board
	public boolean isCorner(int tilePos) {
		return Arrays.asList(corners).contains(tilePos);
	}
	
	// needed to sort the board by tile location 0-->35
	class Sortbyloc implements Comparator<Tile> 
	{ 
	    // Used for sorting in ascending order of 
	    // location
	    public int compare(Tile a, Tile b) 
	    { 
	        return a.getLoc() - b.getLoc(); 
	    } 
	} 
}
