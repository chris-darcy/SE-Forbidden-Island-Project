package Participant;

import java.util.Scanner;

public class Participant {
	protected String name;
	protected Character character;
	protected Hand hand;
	protected int actionsRemaining;
	protected int numberOfActions = 3;
	protected int location;
	protected int currentLocation;
	
	//------------------------------ CONSTRUCTORS ---------------------------------//
	protected Participant(String name, Character character, Hand hand, int location, int actionsRemaining) {
		this.name = name;
		this.character = character;
		this.hand = hand;
		this.location = location;
		this.actionsRemaining = actionsRemaining;
	}
	
	//------------------------------ METHODS --------------------------------------//
	protected String getName() {
		return name;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	protected Character getCharacter() {
		return character;
	}
	
	protected void setCharacter(Character character) {
		this.character = character;
	}
	
	protected Hand getHand() {
		return hand;
	}
	
	protected void setHand(Hand hand) {
		this.hand = hand;
	}
	
	protected int getActionsRemaining() {
		return actionsRemaining;
	}
	
	protected void setActionsRemaining(int numberOfActions) {
		this.numberOfActions = actionsRemaining;
	}
	
	protected void actionUsed() { // each time the user uses an action
		numberOfActions--; 
	}
	
	protected int getLocation() {
		return location;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	
	protected void moveParticipant(Participant participant, int increment) { // participant has ability to move once for one action
		System.out.println("You are currently on tile " + participant.location + "\n" 
	+ "You have " + actionsRemaining + "actions remaining. \n Choose a tile to move to!");
		currentLocation = participant.getLocation();
		Scanner findNewLocation = new Scanner(System.in);
		int newLocation = findNewLocation.nextInt();
		
		if(Math.abs(currentLocation/6 - newLocation/6) == 1 || Math.abs(currentLocation%6 - newLocation%6) == 1 ) { // if the user has chosen a tile that is one move away
			participant.setLocation(newLocation);
			participant.actionUsed();
		}
		else {
			System.out.println("You chose a tile further than one action away, please choose a closer tile!");
		}
	}
}
