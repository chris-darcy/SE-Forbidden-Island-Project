package Testing;

import Board.Tile;
import Board.TileStatus;
import Board.TileType;
import Cards.TreasureCard;
import Cards.TreasureCardDeck;
import Game.GameObserver;
import Participant.Hand;
import Participant.Participant;

public class ObserverCheck {
	public static void main(String [] args) {
		GameObserver observer = new GameObserver();
//		Tile tile = new Tile("Foolslanding", 15, TileStatus.FLOODED, TileType.FOOLSLANDING);
//		tile.attach(observer);
//		tile.setTileStatus(TileStatus.SUNK);
		
		
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		Hand hand = new Hand();
		hand.attach(observer);
		System.out.println(hand.toString());
		
		for (int i = 0; i < 6; i++) {
			hand.addCardToHand(treasureCardDeck.draw()); // pop the top cards from the deck
			System.out.println(hand.size());
		}
		
		
//		Participant participant = new Participant("Hello", hand, 1, 15, 5);
		
	}
	
}
