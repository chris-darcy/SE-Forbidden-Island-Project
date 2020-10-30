package Board;

import java.util.ArrayList;

public class Board {
	
	private int size = 2;
	private Tile[][] board =  new Tile[size][size]; 
	private String[] names = new String[]{"1","2","3","4"};
	
	public Board() {
		initialise();
	}
	
	public void initialise() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				Tile tile = new Tile(names[i*size +j],i,j,TileStatus.UNFLOODED,TileType.NORMAL);
				board[i][j] = tile;
				System.out.println("added tile number" + (i*size +j));
			}
		}
	}
	
	public void show() {
		for (Tile[] row : board){
			for(Tile col : row) {
			//System.out.println(tile.toString());
				System.out.println("tile" + col.toString());
			}
		}	
	}
}
