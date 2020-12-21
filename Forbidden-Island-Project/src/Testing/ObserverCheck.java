package Testing;

import java.util.ArrayList;

import Board.Board;
import Board.Tile;
import Board.TileStatus;
import Board.TileType;
import Cards.TreasureCardDeck;
import Game.GameManager;
import Observers.ParticipantObserver;
import Participant.Engineer;
import Participant.Hand;

public class ObserverCheck {
	public static void main(String [] args) {
		Tile tile = new Tile("Foolslanding", 15, TileStatus.FLOODED, TileType.FOOLSLANDING);
		int playerNum = 1;
		Hand hand = new Hand();
		int numberOfActions = 2;
		GameManager.getInstance();
		
		Engineer p = new Engineer("A", hand, playerNum, tile.getLocation(), numberOfActions);
		ParticipantObserver observer = new ParticipantObserver(p);
		
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		
		for (int i = 0; i < 4; i++) {
			hand.addCardToHand(treasureCardDeck.draw()); // pop the top cards from the deck
		}
		
		tile.setTileStatus(TileStatus.SUNK); // sink the current tile
		
	}
	
}
