package Participant;

import Cards.TreasureCard;

public class Messenger extends Participant{
	public Messenger(String name, Hand hand, int location, int actionsRemaining) {
		super(name, hand, location, actionsRemaining);
	}
	@Override
	protected boolean giveCard(Participant receiver, TreasureCard TreasureCardToGive) {
		// all players can give another player a treasure card if on the same tile
		int count = 0; // initialise
		if (receiver.getLocation() == participant.getLocation() && count>2) {
			Hand giversHand = participant.getHand();
			Hand receiversHand = receiver.getHand();
			
			if(receiversHand.numberOfCards() < maxCards) {
				receiversHand.addCardToHand(TreasureCardToGive);
				giversHand.removeCardFromHand(TreasureCardToGive);
			}
			else{
				receiversHand.tooManyCards(TreasureCardToGive); // tell user they have too many cards and have them remove another card
			}
			count = count+1;
			return true;
		}
		else {
			return false;
		}
		
	}
}
