package Observers;

import Board.Treasures;
import Game.GameManager;
import Participant.PlayerList;

public class WinningObserver extends Observer{
	private Subject subject;
	
	public WinningObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}

	@Override
	public void update() {
		if(((Treasures)this.subject).remaining() == 0 &&                   // check all the treasures have been captured
			PlayerList.getInstance().playerListContainsHelicopterCard()) { // check at least one participant has a helicopter card
			
			System.out.println("You have captured all the treaures!");
			GameManager.getInstance().endGame(true); // run gamewon logic to end the game
		}
	}
}
