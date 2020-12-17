package Testing;

import Board.Tile;
import Board.TileStatus;
import Board.TileType;
import Cards.TreasureCardDeck;
import Game.GameObserver;
import Participant.Hand;
import Participant.Participant;
import Cards.TreasureCard;

public class ObserverCheck {
	public static void main(String [] args) {
		Tile tile = new Tile("Foolslanding", 15, TileStatus.FLOODED, TileType.FOOLSLANDING);
		GameObserver tileObserver = new GameObserver(tile);
//		tile.attach(tileObserver);
		tile.setTileStatus(TileStatus.SUNK);
		
		
		TreasureCardDeck treasureCardDeck = new TreasureCardDeck();
		Hand hand = new Hand();
		GameObserver handObserver = new GameObserver(hand);
//		hand.attach(observer);
		System.out.println(hand.toString());
		
		for (int i = 0; i < 6; i++) {
			hand.addCardToHand(treasureCardDeck.draw()); // pop the top cards from the deck
			System.out.println(hand.size());
		}
		
		
//		Participant participant = new Participant("Hello", hand, 1, 15, 5);
		
	}
	
}
