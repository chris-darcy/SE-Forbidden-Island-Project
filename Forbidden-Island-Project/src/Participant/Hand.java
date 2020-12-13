package Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.Scanner;
import Cards.*;
import Game.GameManager;

public class Hand {
	protected List<TreasureCard> hand = new ArrayList<TreasureCard>(); 
	public int maxCards = 5;
	protected Card chosenCard;
	protected Participant participant;
	//------------------------------ CONSTRUCTORS ---------------------------------//
	public Hand() {
		
	}
	
	//------------------------------ METHODS --------------------------------------//
	public Card getCardInHand(int i) { // method to get card from hand at a given index
		return hand.get(i);
	}	

	private int size() {
		return participant.getHand().size();
	}
	public ArrayList<String> getPrintableHand() {
		ArrayList<String> handString = new ArrayList<String>();
		for(Card card : hand) {
			handString.add(card.toString());
		}
		return handString;
	}	
	
	public int numberOfCards() {
		return hand.size();
	}
	
	public void removeCardFromHand(int i) { // i is the index of the card wished to be removed
		hand.remove(i);
	}
	
	public void addCardToHand(TreasureCard card) {
		hand.add(card);
	}
	
	public boolean handContains(Object object) { // general check for card type
		return hand.contains(object);
	}
	
	public void populateHand(TreasureCard card) {
		if(hand.size() < maxCards) {
			hand.add(card);
		}
		else {
			hand.add(card); // add card so the user can chosen out of 6 cards
			GameManager.getInstance().handAfterRemoval();
		}
	}
	
}
