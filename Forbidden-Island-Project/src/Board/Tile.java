package Board;

public class Tile {
	public String name;
	public int location;
	TileStatus tileStatus;
	TileType tileType;
	
	public Tile (String name, int location, TileStatus tileStatus, TileType tileType){
		this.name = name;
		this.location = location;
		this.tileStatus = tileStatus;
		this.tileType = tileType;
	}
	
	@Override
	public String toString() {
		return  this.name +"\n"+
				this.location +"\n"+
				this.tileStatus +"\n"+
				this.tileType ;
	}
	
	public String getName() {
		return this.name;
	}
	public TileType getTileType() {
		return this.tileType;
	}
	public int getLoc() {
		return location;
	}
}