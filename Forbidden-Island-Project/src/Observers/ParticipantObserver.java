package Observers;

import java.util.ArrayList;

import Board.Board;
import Game.GameManager;
import Participant.Participant;
import Board.Tile;
import Board.TileStatus;

public class ParticipantObserver extends Observer{
	
	private Subject subject;
	private ArrayList<Tile> board = Board.getInstance().getBoard();
	public ParticipantObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update() {
		//the participant has no where to move game has been lost
		if(
//			(((Participant)this.subject).getRelevantTiles(board).isEmpty() &&
//	     	(board.get(((Participant)this.subject).getLocation()).getTileStatus() != TileStatus.SUNK))||  // participant can't move
				
		   (board.get(((Participant)this.subject).getLocation()).getTileStatus() == TileStatus.SUNK &&   // on a sunk tile
		   ((Participant)this.subject).onSunkTile(board).isEmpty())) {       // on a sunk tile and has nowhere to go
			
			System.out.println(((Participant)this.subject).getName() + "the" + ((Participant)this.subject).getRoleName() + ", has drowned!");
			GameManager.getInstance().endGame(false); // run gamelost logic to end the game
		}
	}
}
