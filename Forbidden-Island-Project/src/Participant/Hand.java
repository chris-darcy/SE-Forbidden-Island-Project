package Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Cards.*;

public class Hand {
	protected List<Card> hand = new ArrayList<Card>(); 
	public int maxCards = 5;
	protected Card chosenCard;
	protected Participant participant;
	//------------------------------ CONSTRUCTORS ---------------------------------//
	public Hand() {
		
	}
	
	//------------------------------ METHODS --------------------------------------//
	//
	// returns card of index i in the participant's hand
	//
	public Card getCardInHand(int i) { // method to get card from hand at a given index
		return hand.get(i);
	}	
	
	//
	// finds the index of a given card for the participant's hand
	//
	public int findHandIndex(Card card){
		return hand.indexOf(card);
	}
	
	public int size() {
		return hand.size();
	}
	
	//
	// get hand that is in printable form
	//
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
	
	//
	// discard four cards of the same treasurecard type to capture a treasure
	//
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

	//
	// return the treasure cards in the hand
	//
	public ArrayList<Card> getTreasureCards(){
		ArrayList<Card> treasureCards = new ArrayList<Card>();
			for(Card card: hand) {
				if(!(card instanceof HelicopterTreasureCard)&& !(card instanceof SandbagTreasureCard)) {
					treasureCards.add(card);
				}
			}
		return treasureCards;
	}
	
	//
	// confirm the hand contains a card of a certain type
	//
	public boolean handContains(Object o) { // general check for card type
		return hand.contains(o);
	}
	
	//
	// confirm that the hand contains a helicopter card
	//
	public boolean handContainsHelicopter() {
		for(Card card : this.hand) {
			if(card instanceof HelicopterTreasureCard) {
				return true;
			}
		}
		return false;
	}
	
	//
	// confirm the hand contains a sandbag card
	//
	public boolean handContainsSandbag() {
		for(Card card : this.hand) {
			if(card instanceof SandbagTreasureCard) {
				return true;
			}
		}
		return false; 
	}
	
	//
	// remove special card from hand after useCard() method in runGame has been chosen and the card has been used
	//
	public Card removeSpecialCard(boolean helicopter){
		int count = 0;
		if (helicopter) {
			for(Card card : this.hand) {
				if(card instanceof HelicopterTreasureCard) {  // check there index is a helicopter card
					return this.hand.remove(count);
				}
				count ++;
			}
		}
		else {
			for(Card card : this.hand) {
				if(card instanceof SandbagTreasureCard) { // check the index is a sandbag card
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
