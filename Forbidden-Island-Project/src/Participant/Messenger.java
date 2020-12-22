package Participant;

import Cards.*;

public class Messenger extends Participant{
	public Messenger(String name, Hand hand, int playerNum, int location, int actionsRemaining) {
		super(name, hand, playerNum, location, actionsRemaining);
	}
	@Override
	public boolean giveCard(Participant receiver, Card card) {
		// Messenger can give a card to any other player
			Hand giversHand = this.hand;


			if(cardChosenOkay(card)) {     
				receiver.addCardToHand(card); // after addition of new card, card hand will be too big										// call method to get user to remove one of their cards
			}
			
			
			giversHand.removeCardFromHand(giversHand.findHandIndex(card));
			
			this.actionUsed();
			
			return true;
	}
		
}
