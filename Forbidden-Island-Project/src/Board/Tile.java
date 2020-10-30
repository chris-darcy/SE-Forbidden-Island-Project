package Board;

public class Tile {
	public String name;
	public int[]location = new int[2];
	TileStatus tileStatus;
	TileType tileType;
	
	public Tile (String name, int x, int y, TileStatus tileStatus, TileType tileType){
		this.name = name;
		this.location[0] = x;
		this.location[1] = y;
		this.tileStatus = tileStatus;
		this.tileType = tileType;
	}
	
	@Override
	public String toString() {
		return  this.name +"\n"+
				this.location[0] +"\n"+
				this.location[1] +"\n"+
				this.tileStatus +"\n"+
				this.tileType ;
	}
}