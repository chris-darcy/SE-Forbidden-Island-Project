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
	private String[] waterlevelBar = new String[3];
	
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
		printSeparator(indent+"Waterlevel");
		System.out.println(waterlevelBar[0]);
		System.out.println(waterlevelBar[0]);
		System.out.println(waterlevelBar[1]);
		System.out.println(waterlevelBar[2]);
		printSeparator("");
	}
	
	//
	// Creates String representation of current board
	//
	public void updateBoard(ArrayList<String> board,PlayerList playerList) {
		int idx;
		int playerSliceIdx = 3;

		//Tile tile;
		for(String[] rowSlices: boardSlices) { Arrays.fill(rowSlices,"");}
		
		for (int row=0; row<6; row++ ){
			for(int col=0; col<6; col++) {
								
				idx = 6*row + col;
				TilePrint tile = new TilePrint(board.get(idx));
				//tile = board.get(idx); 
				
				if(tile.type().equals("EMPTY")) {
					for(int i=0; i<8; i++) {
						boardSlices[row][i] = boardSlices[row][i] + tl.get("EMPTY");
					}
				}		
				
				else {				
					boardSlices[row][0] = boardSlices[row][0] + tl.get(tile.name());
					boardSlices[row][1] = boardSlices[row][1] + tl.get(tile.type());
					boardSlices[row][2] = boardSlices[row][2] + tl.get(tile.status());					
					
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
	public void updatePlayerHands(ArrayList<String> playerList) {
		
		for(int player=0; player<playernums; player++){	
			PlayerPrint p = new PlayerPrint(playerList.get(player));
			
			allHandSlices[player][0] = p.name() +" (" + p.role()+"):";	
		
			//8 slices, first saved for player name
			for(int card=0;card<7;card++) {	
				if(card<p.hand.length) {
					allHandSlices[player][card+1] = p.hand[card];
				}
				else {
					allHandSlices[player][card+1] = "";
				}
			}		
		}
	}
	
	//
	// Creates String representation of waterlevel
	//
	public void updateWaterLevel() {
		waterlevelBar[0] = waterlevelBar[0] + "####";
		waterlevelBar[1] = "    " + waterlevelBar[1];
	}
	
	//
	// Get the difficulty of the game with the waterlevel at an initial height
	//
	public int setDifficulty() {
		int startLevel = 0;
		
		System.out.println("~Please enter the initial water level mark~");
		System.out.println("   1: Novice\n   2: Normal\n   3: Elite\n   4: Legendary");
		
		startLevel =  getIntFor("the difficulty level");
		waterlevelBar[0] = makeLongString('#',startLevel*4);
		waterlevelBar[1] = makeLongString(' ',(startLevel-1)*4) + "   v";
		waterlevelBar[2] = "   .   2   .   .   3   .   4   .   5   X";
		
		return startLevel;
	}
	
	//
	// Retrieve number of players in group
	//
	public int inputPlayerNumbers() {		
		valid = false;
		
		printSeparator(indent + "Player Numbers");
		System.out.println("~Please enter the number of players (min 2, max 4)~");
		
		while(!valid) {
			
			playernums = getIntFor("player numbers");
			
			if(playernums<2 || playernums>4) {
				System.out.println("please enter a valid number of players (2-4)");
			}
			else {
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
			System.out.println("~Please enter your character name~");
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
	// Ask player for their next action
	//
	public int chooseAction(String player) {
		int choice = 0;
		PlayerPrint p = new PlayerPrint(player);
		
		System.out.println("~"+p.name+", what will your next move be?~  MOVES REMAINING: " + p.actionsRemaining());
		
		System.out.println("   0: Move\n   1: Give Card\n   2: Shore Up a Tile\n   3: Capture a Treasure");
		choice = getChoiceWithinBoundary("your action",
										 "no such option available",
										 0, 3);
		return choice;
	}
	
	//
	// Ask a player to choose the receiver of their give card action
	//
	public int chooseReceiver(ArrayList<String> everyoneElse) {
		int choice = 0;
		int choiceNum = 0;
		
		System.out.println("~Which player will you give a card to?~");	
		for(String o: everyoneElse) {
			PlayerPrint other_p = new PlayerPrint(o);
			
			System.out.println("   " + choiceNum + ": " + other_p.name() + " (" + other_p.role() + ")");
		}
		
		choice = getChoiceWithinBoundary("your action",
						 "no such option available",
						 0, everyoneElse.size()-1);
		
		PlayerPrint chosen = new PlayerPrint(everyoneElse.get(choice));
		
		return Integer.parseInt(chosen.playernum());
	}
	
	//
	// Ask player to choose which card to either discard or give
	//
	public int chooseCardTo(String action, String player) {
		int choiceNum = 0;
		int choice = 0;
		PlayerPrint p = new PlayerPrint(player);
		System.out.println("~Which card will you " + action + "?~");
		printAHand(p.hand);
		
		choice = getChoiceWithinBoundary("the card to " + action,
						 "no such card available",
						 0, (p.hand.length-1));
		
		System.out.println("you have chosen to " + action + " the " + p.hand[choice]);
	
		return choice;		
	}
	
	//
	// get choice of next location from player for action either shoreUp or move to
	//
	public int chooseLocationTo(String action,ArrayList<Integer> relevantTiles,ArrayList<Tile> board) {
		int choice = 0;
		int choiceNum = 0;
		valid = false;
		
		System.out.println("~Where will you " + action + "?~");
		
		for(int tilePos: relevantTiles) {
			System.out.println(tilePos + ": " + board.get(tilePos).getName());
		}

		while(!valid) {
			choice = getIntFor("the location");
			
			if(!relevantTiles.contains(choice)) {
				System.out.println("you cannot "+ action + " that location");
			}
			else{
				valid = true;
				System.out.println("you chose to " + action + " " + board.get(choice).getName());
			}
		}		
		return choice;		
	}
	
	//
	// get integer choice from player but must be within upper and lower limts, display error message otherwise.
	//
	private int getChoiceWithinBoundary(String purpose,String invalidMsg, int lowerLim, int upperLim) {
		int choice=0;		
		valid = false;
		
		while(!valid) {
			choice = getIntFor(purpose);
			
			if(choice < lowerLim || choice > upperLim) {
				System.out.println(invalidMsg);
			}
			else{
				valid = true;
			}
		}		
		return choice;		
	}
	
	//
	// get and validate an int from the user for a particular reason
	//
	private int getIntFor(String purpose) {
		int i;
		while(!input.hasNextInt()) {
			System.out.println("please enter an integer for " + purpose);
			input.nextLine();
		}		
		i = input.nextInt();
		input.nextLine();
		
		return i;
	}
	
	//
	// Ask player if they wish to continue
	//
	private boolean queryYN() {
		
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
	
	//
	// display a players hand along with the relative option numbers
	//
	private void printAHand(String[] playerHand) {
		int choiceNum = 0;
		
		for(String card: playerHand) {		
			System.out.println("   " + (choiceNum) + ": " + card);
			choiceNum++;
		}
	}
	
	private void printSeparator(String title) {
		System.out.println(border);
		System.out.println(title);
		System.out.println(border);
	}
	
	private void printWarning(String warning) {				
		String fslashes = makeLongString('/',warning.length());
		String spaces = makeLongString(' ',warning.length()-10);
		
		System.out.println(indent + "///" + fslashes + "///");
		System.out.println(indent + "   " + " !WARNING "  + spaces + " ");
		System.out.println(indent + "   " + warning + " ");
		System.out.println(indent + "///" + fslashes + "///\n");
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
		
		border = makeLongString('*',tileWidth*6 + cardNameLen);
		indent = makeLongString(' ',Math.round((tileWidth*6 - 12)/2));
	}
	
	private String makeLongString(char filler,int length){
		char[]longString   = new char[length];
		Arrays.fill(longString, filler);
		
		return new String(longString);
	}
	
	private class PlayerPrint{
		private String name;
		private String playernum;
		private String role;
		private String[] hand;
		private String location;
		private String actionsRemaining;
		
		public PlayerPrint(String player) {
			String[] fields = player.split("\n");
			this.name = fields[0];
			this.playernum = fields[1];
			this.hand = fields[2].substring(1,fields[2].length()-1).split(", ");
			this.location = fields[3];
			this.actionsRemaining = fields[4];
			this.role = fields[5];
		}	
		public String name() {return this.name;}
			
		public String playernum() {return this.playernum;}
				
		public String[] hand() {return this.hand;}
				
		public String location() {return this.location;}
			
		public String actionsRemaining() {return this.actionsRemaining;}
				
		public String role() {return this.role;}

	}
	
	private class TilePrint{
		private String name;
		private String location;
		private String status;
		private String type;
		
		public TilePrint(String tile) {
			String[] fields = tile.split("\n");
			this.name = fields[0];
			this.location = fields[1];
			this.status = fields[2];
			this.type = fields[3];
		}	
		public String name() {return this.name;}
				
		public String location() {return this.location;}
				
		public String status() {return this.status;}
				
		public String type() {return this.type;}

	}


	
	
}

