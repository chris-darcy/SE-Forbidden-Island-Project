package Observers;

import Board.Treasures;
import Game.GameManager;
import Participant.PlayerList;

public class WinningObserver extends Observer{
	private Subject subject;
	
	//
	// observer that determines if the players have won the game
	//
	public WinningObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	//
	// updates the observers by checking is criteria are met for winning the game
	//
	@Override
	public void update() {
		if(((Treasures)this.subject).remaining() == 0 &&                   // check all the treasures have been captured
			!PlayerList.getInstance().playerListContainsHelicopterCard().isEmpty()) { // check at least one participant has a helicopter card
			
			System.out.println("You have captured all the treaures!");
			GameManager.getInstance().endGame(true); // run gamewon logic to end the game
		}
	}
}
