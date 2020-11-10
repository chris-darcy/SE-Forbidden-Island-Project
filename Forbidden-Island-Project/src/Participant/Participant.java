package Participant;

import java.util.Scanner;
import Cards.*;

public abstract class Participant {
	protected String name;
	protected String occupation;
	protected Hand hand;
	protected int actionsRemaining;
	protected int numberOfActions = 3;
	protected int location;
	protected int currentLocation;
	protected boolean sameTile;
	protected Participant participant; 
	protected int maxCards = 5;
	
	//------------------------------ CONSTRUCTORS ---------------------------------//
	protected Participant(String name, String occupation, Hand hand, int location, int actionsRemaining) {
		this.name = name;
		this.occupation = occupation;
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
	
	protected String getOccupation() {
		return occupation;
	}
	
	protected void setOccupation(String occupation) {
		this.occupation = name;
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
	
	public int getLocation() {
		return location;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	
	// This method will be overridden by the Messenger Participant
	protected void giveCard(Participant receiver, TreasureCard TreasureCardToGive) {
		// all players can give another player a treasure card if on the same tile
		if (receiver.getLocation() == participant.getLocation()) {
			Hand giversHand = participant.getHand();
			Hand receiversHand = receiver.getHand();
			
			if(receiversHand.numberOfCards() < maxCards) {
				receiversHand.addCardToHand(TreasureCardToGive);
				giversHand.removeCardFromHand(TreasureCardToGive);
			}
			else{
				receiversHand.tooManyCards(); // tell user they have too many cards and have them remove another card
			}
		}
		else {
			System.out.println(receiver.getName() + ", otherwise known as "+ receiver.getOccupation() + " is not on the same tile as you!\n Please choose another action.");
		}
	}
	
	 // !!! location numbers/ operation may change
	protected void moveParticipant() { // participant has ability to move once for one action
		System.out.println("You are currently on tile " + participant.location + "\n" 
	+ "You have " + actionsRemaining + "actions remaining. \n Choose a tile to move to!");
		currentLocation = participant.getLocation();
		Scanner findNewLocation = new Scanner(System.in); // !!!
		int newLocation = findNewLocation.nextInt();
		
		if(Math.abs(currentLocation/6 - newLocation/6) == 1 || Math.abs(currentLocation%6 - newLocation%6) == 1) { // if the user has chosen a tile that is one move away
			participant.setLocation(newLocation);
			participant.actionUsed();
		}
		else {
			System.out.println("You chose a tile further than one action away, please choose a closer tile!");
		}
	}
}
