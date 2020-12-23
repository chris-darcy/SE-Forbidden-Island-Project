package Main;

import Game.GameManager;

public class Main {
	public static void main(String args[]) {
		GameManager GM = GameManager.getInstance();
		GM.setupGame();
		GM.runGame();
	}
}
