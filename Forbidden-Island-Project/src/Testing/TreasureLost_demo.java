package Testing;

import Board.Board;
import Board.TileStatus;
import Game.GameManager;

public class TreasureLost_demo {
	public static void main(String[] args) {
		
		GameManager GM = GameManager.getInstance();
		Board board = Board.getInstance();
		
		
		// this function may not take an argument depending
		System.out.println(GM.aTreasureLost());
		
		board.getFireSet().get(0).setTileStatus(TileStatus.SUNK);
		board.getFireSet().get(1).setTileStatus(TileStatus.FLOODED);
		
		board.getOceanSet().get(0).setTileStatus(TileStatus.SUNK);
		board.getOceanSet().get(1).setTileStatus(TileStatus.FLOODED);
		
		System.out.println(GM.aTreasureLost());
		
		board.getFireSet().get(1).setTileStatus(TileStatus.SUNK);
		
		System.out.println(GM.aTreasureLost());
		
		
	}
}

