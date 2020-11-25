package Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Cards.*;

public class Hand {
	protected List<Card> hand = new ArrayList<Card>(); 
	public int maxCards;
	protected Card chosenCard;
	
	//------------------------------ CONSTRUCTORS ---------------------------------//
	public Hand(List<Card> hand) {
		this.hand = hand;
	}
	
	//------------------------------ METHODS --------------------------------------//
	
	protected String printHand() {
		System.out.println("Your hand: \n");
		for (Card card : hand) {
			System.out.println(card.getName());
		}
		return null;
	}
	
	protected int numberOfCards() {
		return hand.size();
	}
	
	protected void removeCardFromHand(Card card) {
		hand.remove(card);
	}
	
	protected void add(Card card) {
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
