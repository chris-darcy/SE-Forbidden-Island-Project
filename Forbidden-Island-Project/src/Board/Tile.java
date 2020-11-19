package Board;

public class Tile {
	public String name;
	public int location;
	TileStatus tileStatus;
	TileType tileType;
	Tile tile;
	
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
	
	// get the location of the tile
		public String getName() {
			return name;
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
	
	// get the type of the tile
	public TileType getTileType() {
		return tileType;
	}
			
	// set type of Tile
	public void setTileType(TileType tileType) {
		this.tileType = tileType;
	}
	
	public void shoreUpTile() {
		if(tile.getTileStatus() == TileStatus.FLOODED) {
			tile.setTileStatus(TileStatus.UNFLOODED);
		}
		if(tile.getTileStatus() == TileStatus.UNFLOODED || tile.getTileStatus() == TileStatus.SUNK) {
			System.out.println("This tile is " + tile.getTileStatus() + "\n You cannot shore up this card!");
		}
	}
}