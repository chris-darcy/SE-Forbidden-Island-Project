package Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Observers.Subject;

public class Treasures extends Subject{
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
			
		if(treasures.containsKey(tileType)) {
			remaining--;
			captured.add(treasures.get(tileType));
			return treasures.remove(tileType);
		}
		return null;		
	}
	
	public int remaining() {
		notifyAllObservers(); // notify observers each time a treasure is captured
		return remaining;
	}
	
	public ArrayList<String> captured() {
		return captured;
	}
	
	public boolean uncaptured(TileType tileType) {
		return treasures.containsKey(tileType);
	}
}

