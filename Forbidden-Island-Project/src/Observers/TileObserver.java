package Observers;

import Board.Tile;
import Board.TileStatus;
import Board.TileType;
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
		if(((Tile)this.subject).getTileType() != TileType.NORMAL &&   // double check the tile is a special tile
		   ((Tile)this.subject).getTileType() != TileType.EMPTY  &&
		   ((Tile)this.subject).getTileStatus() == TileStatus.SUNK) { // check the tile is sunk
			System.out.println(((Tile)this.subject).getName() + " has sunk!");
			//GameManager.getInstance().endGame(false);              // game over if this is true
			GameManager.getInstance().updateSpecialTileStatus((Tile)this.subject);
		}
	}
}
