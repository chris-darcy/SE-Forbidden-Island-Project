package Game;
import Participant.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Board.Tile;
import Board.TileType;

public class GUI {	
	private Scanner input;
	private boolean proceed;
	private boolean valid;
	private Map<String,String> tl = new HashMap<>();
	private int playernums = 0;
	
	
	public GUI() {
		input = new Scanner(System.in);
		proceed = true;
		valid = false;
		initTileDict();
	}
	
	
	//
	// Creates graphical display of board
	//
	public void showBoard(ArrayList<Tile> board,PlayerList playerList) {
		String[] tlSlices = new String[] {"","","","","","","",""};
		int idx;
		int playerSliceIdx = 3;
		Tile tile;
		
		for (int row=0; row<6; row++ ){
			for(int col=0; col<6; col++) {
				
				idx = 6*row + col;
				tile = board.get(idx); 
				
				if(tile.getTileType() == TileType.EMPTY) {
					for(int i=0; i<8; i++) {
						tlSlices[i] = tlSlices[i] + tl.get("EMPTY");
					}
				}
				
				else {
					
					tlSlices[0] = tlSlices[0] + tl.get(tile.name);
					tlSlices[1] = tlSlices[1] + tl.get(tile.getTileType().toString());
					tlSlices[2] = tlSlices[2] + tl.get(tile.getTileStatus().toString());					
					
					if(playerList.playersOnTile(idx)) {
						for(Participant p: playerList.getPlayersOnTile(idx)){
							tlSlices[playerSliceIdx] = tlSlices[playerSliceIdx] + tl.get(p.getRoleName());
							playerSliceIdx++;
						}		
					}
					
					for(int i=playerSliceIdx; i<7; i++) {
							tlSlices[i] = tlSlices[i] + tl.get("BLANK");
					}
					
					playerSliceIdx = 3;
					
					tlSlices[7] = tlSlices[7] + tl.get("NORMAL");	
				}
			}	
			
			for(String slice: tlSlices) { System.out.println(slice); }
			System.out.println("");				
			Arrays.fill(tlSlices,"");
			
		}
	}
	
	
	//
	// Get the difficulty of the game with the waterlevel at an initial height
	//
	public int setDifficulty() {
		
		System.out.println("Please enter the initial water level mark");
		System.out.println("1: Novice\n2: Normal\n3: Elite\n4: Legendary");
		
		while(!input.hasNextInt()) {
			System.out.println("please enter an integer for the difficulty level");
			input.nextLine();
		}	
		return input.nextInt();	
	}
	
	//
	// Retrieve number of players in group
	//
	public int inputPlayerNumbers() {		
		valid = false;
		
		System.out.println("Please enter the number of players (min 2, max 4)");
		
		while(!valid) {
			
			while(!input.hasNextInt()) {
				System.out.println("please enter an integer for player numbers");
				input.nextLine();
			}
			
			playernums = input.nextInt();
			
			if(playernums<2 || playernums>4) {
				System.out.println("please enter a valid number of players (2-4)");
			}
			else {
				valid = true;
			}
		}
		return playernums;
	}
	
	//
	// Query player for their character name
	//
	public String inputPlayerName(int i) {	
		String name = "";
		proceed = false;
		
		while(!proceed) {
			System.out.println("Please enter your character name");
			name = input.nextLine();
				
			while(name.equals("") || name.equals(" ")) {
				System.out.println("Not a valid name, please choose another");
				name = input.nextLine();
			}
			
			System.out.println("Player " + i + ", your name will be " + name);
			System.out.println("Confirm name: y/n");
			proceed = queryYN();
		}
			return name;
	}	
	
	
	//
	// Ask player if they wish to continue
	//
	public boolean queryYN() {
		//input.nextLine();
		
		while(true) {
			switch(input.next()) {
			case "y":
				return true;
			case "Y":
				return true;
			case "n":
				return false;
			case "N":
				return false;
			default:
				System.out.println("please reply with tokens y or n");
			}
		}
		
	}
	
	private void initTileDict() {
		tl.put("NORMAL"            ," O#########O ");
		tl.put("EMPTY"             ,"             ");
		tl.put("OCEAN"             ," O~~OCEAN~~O ");
		tl.put("FIRE"              ," O~~Fire~~~O ");
		tl.put("WIND"              ," O~~Wind~~~O ");
		tl.put("EARTH"             ," O~~Earth~~O ");
		
		tl.put("SUNK"              ,"             ");
		tl.put("UNFLOODED"         ," #         # ");
		tl.put("FLOODED"           ," #    F!   # ");
			
		tl.put("Pilot"             ," #   [P]   # ");
		tl.put("Engineer"          ," #   [G]   # ");
		tl.put("Explorer"          ," #   [E]   # ");
		tl.put("Diver"             ," #   [D]   # ");
		tl.put("Messenger"         ," #   [M]   # ");
		tl.put("Navigator"         ," #   [N]   # ");
		tl.put("BLANK"             ," #         # ");
		
		tl.put("HOWLING GARDEN"    ,"  HOWLING G  ");
		tl.put("WHISPERING GARDEN" ," WHISPERING G");
		tl.put("CORAL PALACE"      ,"   CORAL P   ");
		tl.put("TIDAL PALACE"      ,"   TIDAL P   ");
		tl.put("CAVE OF SHADOWS"   ,"  CAVE O. S. ");
		tl.put("CAVE OF EMBERS"    ,"  CAVE O. E. ");
		tl.put("TEMPLE OF THE MOON","TEMPLE O.T M.");
		tl.put("TEMPLE OF THE SUN" ,"TEMPLE O.T S.");
		tl.put("FOOLS LANDING"     ,"   FOOLS L.  ");
		tl.put("SILVER GATE"       ,"  SILVER G.  ");
		tl.put("BRONZE GATE"       ,"  BRONZE G.  ");
		tl.put("COPPER GATE"       ,"  COPPER G.  ");
		tl.put("GOLD GATE"         ,"   GOLD G.   ");
		tl.put("IRON GATE"         ,"   IRON G.   ");
		tl.put("OBSERVATORY"       ," OBSERVATORY ");
		tl.put("PHANTOM ROCK"      ,"  PHANTOM R. ");     
		tl.put("WATCHTOWER"        ," WATCHTOWER  ");       
		tl.put("CRIMSON FOREST"    ,"  CRIMSON F. ");
		tl.put("DUNES OF DECEPTION"," DUNES O. D  ");
		tl.put("LOST LAGOON"       ," LOST LAGOON ");
		tl.put("MISTY MARSH"       ," MISTY MARSH ");   
		tl.put("TWILIGHT HOLLOW"   ," TWILIGHT H. ");  
		tl.put("BREAKERS BRIDGE"   ," BREAKERS B. ");  
		tl.put("CLIFFS OF ABANDON" ," CLIFFS O. A.");
	}

	
	
    
	
	
	
	
}

