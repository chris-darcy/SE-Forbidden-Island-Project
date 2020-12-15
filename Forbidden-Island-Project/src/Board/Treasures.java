package Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Treasures {
	private Map<TileType,String> treasures;
	private ArrayList<String> captured;
	private int remaining;
	
	public Treasures() {
		treasures = new HashMap<>();
		captured = new ArrayList<String>();
		
		treasures.put(TileType.FIRE,"The Crystal of Fire");
		treasures.put(TileType.EARTH,"The Earth Stone");
		treasures.put(TileType.WIND,"The Statue of the Wind");
		treasures.put(TileType.OCEAN,"The Ocean's Chalice");
		remaining = 4;
	}
	
	public String capture(TileType tileType) {
		remaining--;
		captured.add(treasures.get(tileType));
		
		return treasures.remove(tileType);	
	}
	
	public int remaining() {
		return remaining;
	}
	
	public ArrayList<String> captured() {
		return captured;
	}
}

