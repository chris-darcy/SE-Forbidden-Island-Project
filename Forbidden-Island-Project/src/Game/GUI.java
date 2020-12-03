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
	private int playernums = 0;
	private String border;
	private String indent;
	private Map<String,String> tl = new HashMap<>();
	private String[][] allHandSlices;
	private String[][] boardSlices = new String[6][8];
	
	public GUI() {
		input = new Scanner(System.in);
		proceed = true;
		valid = false;
		initTileDict();
		initHeaderStrings();
	}
	
	
	//
	// Display Board alongside player hands
	//
	public void display() {
		
		printSeparator(indent+"Board Layout"+indent+"   Player Hands");
		for(int i=0; i<6;i++) {		
			for(int slice=0; slice<8;slice++) {
				if(i<playernums) {
					System.out.println(boardSlices[i][slice] + " || " + allHandSlices[i][slice]);				
				}
				else {
					System.out.println(boardSlices[i][slice] + " || ");			
				}
			}
			System.out.println(indent+indent+"             || ");
		}
		printSeparator("");
	}
	
	//
	// Creates String representation of current board
	//
	public void updateBoard(ArrayList<Tile> board,PlayerList playerList) {
		int idx;
		int playerSliceIdx = 3;
		Tile tile;
		for(String[] rowSlices: boardSlices) { Arrays.fill(rowSlices,"");}
		
		for (int row=0; row<6; row++ ){
			for(int col=0; col<6; col++) {
				
				idx = 6*row + col;
				tile = board.get(idx); 
				
				if(tile.getTileType() == TileType.EMPTY) {
					for(int i=0; i<8; i++) {
						boardSlices[row][i] = boardSlices[row][i] + tl.get("EMPTY");
					}
				}		
				
				else {				
					boardSlices[row][0] = boardSlices[row][0] + tl.get(tile.name);
					boardSlices[row][1] = boardSlices[row][1] + tl.get(tile.getTileType().toString());
					boardSlices[row][2] = boardSlices[row][2] + tl.get(tile.getTileStatus().toString());					
					
					if(playerList.playersOnTile(idx)) {
						for(Participant p: playerList.getPlayersOnTile(idx)){
							boardSlices[row][playerSliceIdx] = boardSlices[row][playerSliceIdx] + tl.get(p.getRoleName());
							playerSliceIdx++;
						}		
					}
					
					for(int i=playerSliceIdx; i<7; i++) {
						boardSlices[row][i] = boardSlices[row][i] + tl.get("BLANK");
					}	
					
					boardSlices[row][7] = boardSlices[row][7] + tl.get("NORMAL");
					
					playerSliceIdx = 3;
				}
			}	
		}
	}
	
	//
	// Creates String representation of player hands
	//
	public void updatePlayerHands(PlayerList playerList) {
		
		for(int player=0; player<playernums; player++){
			
			allHandSlices[player][0] = playerList.getPlayer(player).getName() +" (" + playerList.getPlayer(player).getRoleName()+"):";	
			
			String[] hand = playerList.getPlayer(player).getHand().getPrintableHand();	
		
			//8 slices, first saved for player name
			for(int card=0;card<7;card++) {	
				if(card<hand.length) {
					allHandSlices[player][card+1] = hand[card];
				}
				else {
					allHandSlices[player][card+1] = "";
				}
			}		
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
		
		printSeparator(indent + "Player Numbers");
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
				input.nextLine();
				valid = true;
			}
		}
		
		// can set the size of the hand display array
		allHandSlices = new String[playernums][8];
	
		return playernums;
	}
	
	//
	// Query player for their character name
	//
	public String inputPlayerName(int i) {	
		String name = "";
		proceed = false;
		
		printSeparator(indent + "Player " + (i+1) + " Name");
		
		while(!proceed) {
			System.out.println("Please enter your character name");
			name = input.nextLine();
				
			while(name.isBlank() || name.length() > 12) {
				System.out.println("please choose a name (1-12 characters)");
				name = input.nextLine();
			}
			
			System.out.println("Player " + (i+1) + ", your name will be " + name);
			System.out.println("Confirm name: y/n");
			proceed = queryYN();
		}
			return name;
	}	
	
	
	//
	// Ask player if they wish to continue
	//
	private boolean queryYN() {
		//input.nextLine();
		
		while(true) {
			switch(input.nextLine()) {
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
	
	private void printSeparator(String title) {
		System.out.println(border);
		System.out.println(title);
		System.out.println(border);
	}
	
	public void printPlayerFinalised(Participant player){
		System.out.println(player.getName() + ", you have been assigned the role of " + player.getClass().getSimpleName() + "!\n");
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
	
	private void initHeaderStrings(){
		int tileWidth = 13;
		int cardNameLen = 20;
		char[]stars   = new char[tileWidth*6 + cardNameLen];
		char[]spaces   = new char[Math.round((tileWidth*6 - 12)/2)];
		
		Arrays.fill(stars, '*');
		border = new String(stars);
		Arrays.fill(spaces, ' ');
		indent = new String(spaces);

	}	
	
}

