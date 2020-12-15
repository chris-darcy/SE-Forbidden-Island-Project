package Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.Scanner;
import Cards.*;
import Game.GameManager;
import Game.GameObserver;

public class Hand {
	protected List<TreasureCard> hand = new ArrayList<TreasureCard>(); 
	public int maxCards = 5;
	protected Card chosenCard;
	protected Participant participant;
	private ArrayList<GameObserver> observerList = new ArrayList<GameObserver>();
	//------------------------------ CONSTRUCTORS ---------------------------------//
	public Hand() {
		
	}
	
	//------------------------------ METHODS --------------------------------------//
	public Card getCardInHand(int i) { // method to get card from hand at a given index
		return hand.get(i);
	}	
	
	public int findHandIndex(TreasureCard treasureCard){
		return hand.indexOf(treasureCard);
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
	
	public void addCardToHand(TreasureCard card) {
		hand.add(card);
		notifyAllGameObservers(hand);
	}
	
	public boolean handContains(Object object) { // general check for card type
		return hand.contains(object);
	}
	
	public TreasureCard getCard(int cardIdx) {  // remove later !!!
		return hand.get(cardIdx);
	}
	
	public void notifyAllGameObservers(List<TreasureCard> hand) {
			for(GameObserver observer : observerList) {
				observer.update(hand);
			}
	}
	
	public void attach(GameObserver observer) {
		observerList.add(observer);
	}
	
	@Override
	public String toString() {		
		return Arrays.toString(hand.toArray());
	}
}
