package Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.Scanner;
import Cards.*;

public class Hand {
	protected List<TreasureCard> hand = new ArrayList<TreasureCard>(); 
	public int maxCards = 5;
	protected Card chosenCard;
	
	//------------------------------ CONSTRUCTORS ---------------------------------//
	public Hand() {

	}
	
	//------------------------------ METHODS --------------------------------------//
	

	public String[] getPrintableHand() {	
		System.out.println("Your hand:\n");
		String handString = Arrays.toString(hand.toArray());
	    return handString.substring(1,handString.length()-1).split(", ");
	}
	
	public int numberOfCards() {
		return hand.size();
	}
	
	protected void removeCardFromHand(TreasureCard card) {
		hand.remove(card);
	}
	
	public void addCardToHand(TreasureCard card) {
		hand.add(card);
	}
	
	public boolean handContains(TreasureCard treasureCard) {
		return hand.contains(treasureCard);
	}
	
	protected void tooManyCards(TreasureCard card) {
//		Scanner cardIndex = new Scanner(System.in);
//		System.out.println("You have too many cards, choose the index of the card you wish to add to the discard pile:");
//		// User has to interact to indicate which card to remove !!!
//		int cardNumber = cardIndex.nextInt();
		hand.remove(card);
	}

	public void populateHand(TreasureCard card) {
		if(hand.size() < maxCards) {
			hand.add(card);
		}
		else {
			hand.add(card); // add card so the user can chosen out of 6 cards
			((Hand) hand).tooManyCards(card);
		}
	}
	
}
