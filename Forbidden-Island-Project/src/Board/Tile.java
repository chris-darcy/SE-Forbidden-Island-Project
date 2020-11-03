package Board;

public class Tile {
	public String name;
	public int[]location = new int[2];
	TileStatus tileStatus;
	TileType tileType;
	
	//------------------------------ CONSTRUCTORS ---------------------------------//
	
	public Tile (String name, int x, int y, TileStatus tileStatus, TileType tileType){
		this.name = name;
		this.location[0] = x;
		this.location[1] = y;
		this.tileStatus = tileStatus;
		this.tileType = tileType;
	}
	
	//------------------------------ METHODS --------------------------------------//
	
	@Override
	public String toString() {
		return  this.name +"\n"+
				this.location[0] +"\n"+
				this.location[1] +"\n"+
				this.tileStatus +"\n"+
				this.tileType ;
	}
	
	// get the location of the tile
	public int[] getLocation() {
		return location;
	}
	
	// set location of tile
	public void setLocation(int[] location) {
		this.location = location;
	}
	
	// get the status of the tile
	public TileStatus getTileStatus() {
		return tileStatus;
	}
		
	// set status of Tile
	public void setTileStatus(TileStatus tileStatus) {
		this.tileStatus = tileStatus;
	}
}