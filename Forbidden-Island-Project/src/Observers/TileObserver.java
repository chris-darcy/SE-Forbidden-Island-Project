package Observers;

import Board.Tile;
import Board.TileStatus;
import Board.TileType;
import Game.GameManager;

public class TileObserver extends Observer {
	private Subject subject;
	
	public TileObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update() {
		System.out.println(((Tile)this.subject).getName() + " has sunk!");
		GameManager.getInstance().updateParticipantTileStatus(((Tile)this.subject));
	}
}

