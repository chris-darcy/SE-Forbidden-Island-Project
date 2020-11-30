package Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import Cards.*;

public class Hand {
	protected List<Card> hand = new ArrayList<Card>(); 
	public int maxCards;
	protected Card chosenCard;
	
	//------------------------------ CONSTRUCTORS ---------------------------------//
	public Hand() {
		
	}
	
	//------------------------------ METHODS --------------------------------------//
	
	public String[] getPrintableHand() {	
		String handString = Arrays.toString(hand.toArray());
	    return handString.substring(1,handString.length()-1).split(", ");
	}
	
	public int numberOfCards() {
		return hand.size();
	}
	
	protected void removeCardFromHand(Card card) {
		hand.remove(card);
	}
	
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	
	protected void tooManyCards() {
		Scanner cardIndex = new Scanner(System.in);
		System.out.println("You have too many cards, choose the index of the card you wish to add to the discard pile:");
		// User has to interact to indicate which card to remove !!!
		int cardNumber = cardIndex.nextInt();
		hand.remove(cardNumber);
	}

	public void populateHand(Card card, int maxCards) {
		if(hand.size() < maxCards) {
			hand.add(card);
		}
		else {
			hand.add(card); // add card so the user can chosen out of 6 cards
			((Hand) hand).tooManyCards();
		}
	}
	
}
