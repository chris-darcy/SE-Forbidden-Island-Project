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
		if(((Tile)this.subject).getTileStatus() == TileStatus.SUNK) {
			System.out.println("\n"+((Tile)this.subject).getName() + " has sunk!");
			GameManager.getInstance().updateParticipantTileStatus(((Tile)this.subject));
			if(((Tile)this.subject).getTileType() != TileType.NORMAL) {
				GameManager.getInstance().updateSpecialTileStatus(((Tile)this.subject));
			}
		}
		else if(((Tile)this.subject).getTileStatus() == TileStatus.FLOODED){
			System.out.println("\n"+((Tile)this.subject).getName() + " has flooded!");
		}
		else {
			System.out.println("\n"+((Tile)this.subject).getName() + " has been shored up!");
		}
	}
}

