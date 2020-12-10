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
	

	public ArrayList<String> getPrintableHand() {
		ArrayList<String> handString = new ArrayList<String>();
		for(TreasureCard card : hand) {
			handString.add(card.toString());
		}
		return handString;
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
