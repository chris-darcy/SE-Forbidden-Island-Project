package Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.Scanner;
import Cards.*;
import Game.GameManager;

public class Hand {
	protected List<Card> hand = new ArrayList<Card>(); 
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
	
	public int findHandIndex(Card card){
		return hand.indexOf(card);
	}
	
	public int size() {
		return hand.size();
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
	
	public void addCardToHand(Card card) {
		if(hand.size() < maxCards ) {       // check if too many cards in hand
			hand.add(card);
		}
		else {
			GameManager.getInstance().handAfterRemoval(participant);
		}
	}
	
	public ArrayList<Card> discardFour(String type){
		ArrayList<Card> toDiscard = new ArrayList<Card>();
		int counter = 0;
		
		for(Card card: this.getTreasureCards()) {
			if(card.getName().equalsIgnoreCase(type) && counter < 4) {
				toDiscard.add(card);
				this.hand.remove(card);
				counter++;
			}
		}
		return toDiscard;
	}

	public ArrayList<Card> getTreasureCards(){
		ArrayList<Card> treasureCards = new ArrayList<Card>();
			for(Card card: hand) {
				if(!(card instanceof HelicopterTreasureCard)&& !(card instanceof SandbagTreasureCard)) {
					treasureCards.add(card);
				}
			}
		return treasureCards;
	}
	
	public boolean handContains(Object object) { // general check for card type
		return hand.contains(object);
	}
	
	@Override
	public String toString() {		
		return Arrays.toString(hand.toArray());
	}
}
