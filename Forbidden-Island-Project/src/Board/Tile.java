package Board;

public class Tile {
	public String name;
	public int[][] location = new int[1][1];
	TileStatus tileStatus;
	TileType tileType;
	
	public Tile (String name, int[][] location, TileStatus tileStatus, TileType tileType){
		this.name = name;
		this.location = location;
		this.tileStatus = tileStatus;
		this.tileType = tileType;
	}
}
