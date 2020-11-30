package Testing;

import Game.GameManager;


public class Gui_board_show_demo {
	
	public static void main(String[] args) {
		GameManager GM = GameManager.getInstance();
		GM.createPlayerList();
		
		// to be removed, for demonstration only
		GM.callGUIDisplay(2);
	}

}
