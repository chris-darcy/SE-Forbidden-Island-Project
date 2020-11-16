package Game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

import Board.Board;
import Board.TileType;
import Participant.Hand;
import Participant.Participant;

public class GUI {	
	private Scanner input;
	private boolean proceed;
	private boolean valid;
	private Board board;
	private Iterator<Integer> roles;
	
	
	public GUI() {
		input = new Scanner(System.in);
		proceed = true;
		valid = false;
		board = Board.getInstance();
		roles = shuffleRoles();
	}
	
	
	//
	// Creates graphical display of board
	//
	public void showBoard(Board board) {
		board.show();
	}
	
	//
	// create the player characters at game startup
	//
	public void createPlayerList(){
		int playernums = 0;
		Participant player;
		String name;
		String occupation;
		Hand hand;
		int location;
		int role;
		
		playernums = inputPlayerNumbers();
				
		for(int i=0; i<playernums; i++) {
			name = inputPlayerName(i);
			role = roles.next();
			
			// roles still need to be made!!!
			switch(role) {
			case 0:
				location = board.getPilotStartLoc();
				player = new Pilot(name,hand,location,3);
				break;
			case 1:
				location = board.getEngineerStartLoc();
				player = new Engineer(name,hand,location,3);
				break;
			case 2:
				location = board.getExplorerStartLoc();
				player = new Explorer(name,hand,location,3);
				break;
			case 3:
				location = board.getDiverStartLoc();
				player = new Diver(name,hand,location,3);
				break;
			case 4:
				location = board.getMessengerStartLoc();
				player = new Messenger(name,hand,location,3);
				break;
			case 5:
				location = board.getNavigatorStartLoc();
				player = new Navigator(name,hand,location,3);
				break;
			}
			// Add player to playerList
		}
	}
	
	//
	// Retrieve number of players in group
	//
	private int inputPlayerNumbers() {
		int playernums = 0;
		valid = false;
		
		System.out.println("Please enter the number of players (min 2, max 4)\n");
		
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
	private String inputPlayerName(int i) {	
		String name = "";
		proceed = false;
		
		while(!proceed) {
			System.out.println("Please enter your character name\n");
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
		input.nextLine();
		
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
	
	private Iterator<Integer> shuffleRoles(){
		ArrayList<Integer> roleNumbers = new ArrayList<Integer>();
		Iterator<Integer> shuffledRoles;
		
		// Randomise roles
		for(int i=0;i<6;i++){
			roleNumbers.add(i);
		}
		Collections.shuffle(roleNumbers);
		shuffledRoles = roleNumbers.iterator();
		
		return shuffledRoles;
	}
	
}

