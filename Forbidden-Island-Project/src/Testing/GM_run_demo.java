package Testing;

import java.util.ArrayList;
import java.util.Arrays;

import Board.Board;
import Board.Tile;
import Game.GameManager;

public class GM_run_demo {
	public static void main(String[] args) {
		GameManager GM = GameManager.getInstance();
		GM.setupGame();
		GM.runGame();
	}
}
