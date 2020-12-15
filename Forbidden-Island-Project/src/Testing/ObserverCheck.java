package Testing;

import Board.Tile;
import Board.TileStatus;
import Board.TileType;
import Game.GameObserver;

public class ObserverCheck {
	public static void main(String [] args) {
		GameObserver observer = new GameObserver();
		Tile tile = new Tile("Foolslanding", 15, TileStatus.FLOODED, TileType.FOOLSLANDING);
		tile.attach(observer);
		System.out.println(tile);
		
		tile.setTileStatus(TileStatus.SUNK);
		System.out.println(tile);
	}
	
}
