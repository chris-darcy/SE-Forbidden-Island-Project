package Board;

public class Tile {
	public String name;
	public int location;
	TileStatus tileStatus;
	TileType tileType;
	
	//------------------------------ CONSTRUCTORS ---------------------------------//
	
	public Tile (String name, int location, TileStatus tileStatus, TileType tileType){
		this.name = name;
		this.location = location;
		this.tileStatus = tileStatus;
		this.tileType = tileType;
	}
	
	//------------------------------ METHODS --------------------------------------//
	
	@Override
	public String toString() {
		return  this.name +"\n"+
				this.location +"\n"+ // !!! Currently prints the tile number, not location
				this.tileStatus +"\n"+
				this.tileType ;
	}
	
	// get the location of the tile
	public int getLocation() {
		return location;
	}
	
	// set location of tile
	public void setLocation(int location) {
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