package Observers;

import Game.GameManager;
import Participant.Participant;

public class ParticipantObserver extends Observer{
	
	private Subject subject;
	
	public ParticipantObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update() {
		//the participant has no where to move game has been lost
		System.out.println(((Participant)this.subject).getName() + " the " + ((Participant)this.subject).getRoleName() + " has drowned!");
		GameManager.getInstance().endGame(false);
		}
}
