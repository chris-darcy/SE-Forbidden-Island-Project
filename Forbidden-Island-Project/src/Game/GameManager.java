package Game;

public class GameManager {
	private static GameManager uniqueInstance = null;
	
	private GameManager() {}
	
	public static GameManager getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new GameManager();
		}
		return uniqueInstance;
	}
	
}
