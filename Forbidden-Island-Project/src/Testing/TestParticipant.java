package Testing;

import java.util.ArrayList;
import java.util.List;

import Cards.Card;
import Participant.Hand;
import Participant.Participant;
import Participant.Participant.*;

public class TestParticipant {
	protected List<Card> handNewParticipant = new ArrayList<Card>(); 
	handNewParticipant.add();
	handNewParticipant.add();
	handNewParticipant.add();
	Hand hand = new Hand(handNewParticipant);
	Participant newParticipant = new Participant("Bob", "Engineer", hand, 13, 5);
	
	protected TestParticipant() {
		
	}
	
	// test initialising the participant
	protected void initialiseParticipant() {
		
	}
}
