package Testing;

import Board.Tile;
import Board.TileStatus;
import Board.TileType;

public class ObserverCheck {
	public static void main(String [] args) {
	Tile tile = new Tile("HOWLING GARDEN", 15, TileStatus.FLOODED, TileType.FOOLSLANDING);
	tile.setTileStatus(TileStatus.SUNK);
	
	}
	
}
