package Game;

import java.util.ArrayList;

import Board.Board;
import Cards.CardDeck;
import Participant.Participant;

public class GameManager {
	private static GameManager uniqueInstance = null;
	protected Board board;
	protected CardDeck cardDeck;
	protected GUI gui;
	public ArrayList <Participant> playerList;
	
	private GameManager() {}
	
	public static GameManager getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new GameManager();
		}
		return uniqueInstance;
	}
	
	public void setupGame() {
		gui = new GUI();
		board = Board.getInstance();
		cardDeck = new CardDeck();
		
		
	
	}
	
	public void runGame() {
		
	}
	
	public void endGame() {
		
	}
	
	
 
}
