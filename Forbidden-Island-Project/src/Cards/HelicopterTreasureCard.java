package Cards;

import Board.*;
import Participant.Participant;

public class HelicopterTreasureCard extends Card{
	private String name;
	
	public HelicopterTreasureCard(String name) {
		super(name);
	}
	// one or more users can be moved, this will be called for each user to be moved
		public static void helicopterLift(Participant toBeMoved, int location) {  //!!! Participant has to be created !!!
			toBeMoved.setLocation(location); // relocated the chosen user 
		}
		// CHECK THAT A PARTICIPANT HAS A SPECIAL POWER FOR THIS!!
}
