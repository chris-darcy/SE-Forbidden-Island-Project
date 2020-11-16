 package Board;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import Game.GameManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Board {
	
	private static Board uniqueInstance = null;
	private int board_size = 36;
	private ArrayList<Tile> board =  new ArrayList<Tile>(); 
	private ArrayList<Tile> fireSet =  new ArrayList<Tile>(); 
	private ArrayList<Tile> oceanSet =  new ArrayList<Tile>(); 
	private ArrayList<Tile> earthSet =  new ArrayList<Tile>(); 
	private ArrayList<Tile> windSet =  new ArrayList<Tile>();
	private ArrayList<ArrayList<Tile>> specialSets =  new ArrayList<ArrayList<Tile>>();
	private int pilotStartLoc;
	private int engineerStartLoc;
	private int explorerStartLoc;
	private int diverStartLoc;
	private int messengerStartLoc;
	private int navigatorStartLoc;	
	private Integer[] corners = new Integer[]{0,1,4,5,6,11,24,29,30,31,34,35};

	
	//
	// Implement Board as Singleton
	//
	public static Board getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new Board();
		}
		return uniqueInstance;
	}
	
	private Board() {
		initialise();
		createSet();
	}
	
	private void initialise() {

	
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
				    	Tile tile = new Tile("Corner",tilePos,TileStatus.SUNK,TileType.EMPTY);
				    	board.add(tile);
				    }
				    else {	
				    	line = scan.readLine();
						attributes = line.split("-"); //Delimiter is a hyphen
				    	
						//tiletype
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
				    	
				    	//character start points
				    	switch(attributes[2]) {
				    		case "P":
				    			pilotStartLoc = tilePos;
				    			break;
				    		case "M":
				    			messengerStartLoc = tilePos;
				    			break;
				    		case "G":
				    			engineerStartLoc = tilePos;
				    			break;
				    		case "E":
				    			explorerStartLoc = tilePos;
				    			break;
				    		case "D":
				    			diverStartLoc = tilePos;
				    			break;
				    		case "N":
				    			navigatorStartLoc = tilePos;
				    			break;
				    		default:
				    			break;
			    	}
				    	Tile tile = new Tile(attributes[0],tilePos,TileStatus.UNFLOODED,tiletype);
				    	board.add(tile);
				    	addToSpecialSet(tile,tiletype);
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
			if(row != tile.getLocation()/6) {
				row = tile.getLocation()/6;
			}
			
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
    		case EMPTY:
    			layout[row] = layout[row] + "   ";
    			break;
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
	
	public void addToSpecialSet(Tile treasureTile, TileType tiletype) {
		switch(tiletype) {
		case WIND:
			windSet.add(treasureTile);
			break;
		case OCEAN:
			oceanSet.add(treasureTile);
			break;
		case FIRE:
			fireSet.add(treasureTile);
			break;
		case EARTH:
			earthSet.add(treasureTile);
			break;
		default:
			break;
		}
	}
	
	private void createSet() {
		specialSets.add(windSet);
		specialSets.add(oceanSet);
		specialSets.add(fireSet);
		specialSets.add(earthSet);
	}
	
	public ArrayList <Tile> getWindSet(){
		return windSet;
	}
	
	public ArrayList <Tile> getOceanSet(){
		return oceanSet;
	}
	
	public ArrayList <Tile> getFireSet(){
		return fireSet;
	}
	
	public ArrayList <Tile> getEarthSet(){
		return earthSet;
	}
	
	public ArrayList<ArrayList <Tile>> getSpecialSets(){
		return specialSets;
	}
	
	public ArrayList<Tile> getBoard() {
		return board;
	}
	
	public int getPilotStartLoc() {
		return pilotStartLoc;
	}
	
	public int getEngineerStartLoc() {
		return engineerStartLoc;
	}
	
	public int getExplorerStartLoc() {
		return explorerStartLoc;
	}
	
	public int getDiverStartLoc() {
		return diverStartLoc;
	}
	
	public int getMessengerStartLoc() {
		return messengerStartLoc;
	}
	
	public int getNavigatorStartLoc() {
		return navigatorStartLoc;
	}
	
	
	// needed to sort the board by tile location 0-->35
	class Sortbyloc implements Comparator<Tile> 
	{ 
	    // Used for sorting in ascending order of 
	    // location
	    public int compare(Tile a, Tile b) 
	    { 
	        return a.getLocation() - b.getLocation(); 
	    } 
	} 
}