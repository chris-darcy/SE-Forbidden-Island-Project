package Game;

import javax.security.auth.Subject;

import Board.Tile;
import Board.TileStatus;
import Board.TileType;
import Observers.Observer;
import Participant.Hand;
import WaterLevel.WaterLevel;

public class GameObserver extends Observer {
	
	private Subject subject;
	
	public GameObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update(){
		if(this.getTileStatus() == TileStatus.SUNK &&
		   (this.getTileType() == TileType.EARTH ||
		    this.getTileType() == TileType.FIRE ||
		    this.getTileType() == TileType.WIND ||
		    this.getTileType() == TileType.OCEAN ||
		    this.getTileType() == TileType.FOOLSLANDING)) {
			GameManager.getInstance().endGame(false);
//		}
	}

}
