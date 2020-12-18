package Observers;

import Board.Tile;
import Game.GameManager;
import Participant.Participant;

public class TileObserver extends Observer {
	private Subject subject;
	
	public TileObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update() {
		System.out.println(((Tile)this.subject).getName() + " has sunk!");
		GameManager.getInstance().endGame(false);
	}
}
