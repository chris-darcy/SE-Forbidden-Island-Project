package Participant;

import Cards.TreasureCard;

public class Messenger extends Participant{
	public Messenger(String name, Hand hand, int playerNum, int location, int actionsRemaining) {
		super(name, hand, playerNum, location, actionsRemaining);
	}
	@Override
	public boolean giveCard(Participant receiver, TreasureCard TreasureCardToGive) {
		// Messenger can give a card to any other player
			Hand giversHand = participant.getHand();
			Hand receiversHand = receiver.getHand();
			
			if(receiversHand.numberOfCards() < maxCards) {
				receiversHand.addCardToHand(TreasureCardToGive);
				giversHand.removeCardFromHand(TreasureCardToGive);
			}
			else{
				receiversHand.tooManyCards(TreasureCardToGive); // tell user they have too many cards and have them remove another card
			}
			return true;
	}
		
}
