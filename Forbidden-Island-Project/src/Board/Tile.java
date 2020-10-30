package Board;

public class Tile {
	public String name;
	public int[] location;
	
	protected Tile (String name, int[] location){ // add enum here
		this.name = name;
		this.location = location;
	}
}
