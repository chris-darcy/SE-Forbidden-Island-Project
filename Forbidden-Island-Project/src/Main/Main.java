package Main;

import Game.GameManager;
//
// Class run to play the Forbidden Island Game!
//
public class Main {
	public static void main(String args[]) {
		GameManager GM = GameManager.getInstance(); // instanciates GUI
		GM.setupGame(); // sets up game
		GM.runGame(); // runs gameplay logic
	}
}
