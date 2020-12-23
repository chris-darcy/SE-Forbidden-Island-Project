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
	
	public Card removeCardFromHand(int i) { // i is the index of the card wished to be removed
		return hand.remove(i);
	}
	
	public void addCardToHand(Card card) {
		hand.add(card);
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
	
	public boolean handContains(Object o) { // general check for card type
		return hand.contains(o);
	}
	
	public boolean handContainsHelicopter() {
		for(Card card : this.hand) {
			if(card instanceof HelicopterTreasureCard) {
				return true;
			}
		}
		return false;
	}
	
	public boolean handContainsSandbag() {
		for(Card card : this.hand) {
			if(card instanceof SandbagTreasureCard) {
				return true;
			}
		}
		return false; 
	}
	
	public Card removeSpecialCard(boolean helicopter){
		int count = 0;
		if (helicopter) {
			for(Card card : this.hand) {
				if(card instanceof HelicopterTreasureCard) {
					return this.hand.remove(count);
				}
				count ++;
			}
		}
		else {
			for(Card card : this.hand) {
				if(card instanceof SandbagTreasureCard) {
					return this.hand.remove(count);
				}
				count ++;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {		
		return Arrays.toString(hand.toArray());
	}
}
