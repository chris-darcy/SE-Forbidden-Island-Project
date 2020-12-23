package Main;

import Game.GameManager;
//
// Class run to play the Forbidden Island Game!
//
public class Main {
	public static void main(String args[]) {
		GameManager GM = GameManager.getInstance();
		GM.setupGame();
		GM.runGame();
	}
}
