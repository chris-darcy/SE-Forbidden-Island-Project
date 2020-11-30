package Testing;
import java.util.ArrayList;

import Board.*;

public class Set_retrieve_demo {
	public static void main(String [] args) {
		Board board = Board.getInstance();
		
		for(ArrayList<Tile> set : board.getAllSpecialSets()) {
			System.out.println("The " + set.get(0).getTileType() + " Set has the following cards:");
			for(Tile specialTile: set) {
				System.out.println(specialTile.getName() + " - " + specialTile.getTileType());
			}
			System.out.println("\n");
		}
	}
}
