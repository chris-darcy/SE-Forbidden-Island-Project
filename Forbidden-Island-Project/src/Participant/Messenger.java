package Participant;

import Cards.*;

public class Messenger extends Participant{
	public Messenger(String name, Hand hand, int playerNum, int location, int actionsRemaining) {
		super(name, hand, playerNum, location, actionsRemaining);
	}
	@Override
	public boolean giveCard(Participant receiver, Card treasureCardToGive) {
		// Messenger can give a card to any other player
		Hand giversHand = this.hand;
		Hand receiversHand = receiver.getHand();
		
		if(receiversHand.numberOfCards() >= maxCards - 1) { // after addition of new card, card hand will be too big
//			GameManager.handAfterRemoval(); // call method to get user to remove one of their cards
		}
		
		receiversHand.addCardToHand(treasureCardToGive);
		giversHand.removeCardFromHand(giversHand.findHandIndex(treasureCardToGive));
		participant.actionUsed();
		
		return true;
	}
		
}
