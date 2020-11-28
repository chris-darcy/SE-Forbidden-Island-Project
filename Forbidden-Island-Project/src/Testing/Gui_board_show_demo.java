package Testing;

import Board.Board;
import Game.GUI;
import Game.GameManager;
import Participant.PlayerList;

public class Gui_board_show_demo {
	
	public static void main(String[] args) {
		GameManager GM = GameManager.getInstance();
		GM.createPlayerList();	
	}

}
