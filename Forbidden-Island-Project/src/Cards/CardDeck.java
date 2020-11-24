package Cards;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import Cards.Card.*;
import Board.TileStatus;

public class CardDeck {
	
	public CardDeck() {
		initialise(28); // 28 cards in Treasure Deck
	}
	
	public ArrayList<TreasureCard> cardDeck = new ArrayList<TreasureCard>();
	
	public void initialise(int numberOfCardsInDeck) {
		String line;
		String[] attributes;
		ArrayList<Integer> nums = new ArrayList<Integer>();
		
		
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader("src/CardNames.txt"));
			for (int i = 0; i < numberOfCardsInDeck; i++) {    	
		    	try {
		    		line = scan.readLine();
					attributes = line.split("-"); //Delimiter is a hyphen
			    	
			    	switch(attributes[1]) {
			    		case "F":
			    			TreasureCard fireTreasureCard = new TreasureCard(attributes[0]);
			    			cardDeck.add(fireTreasureCard);
			    			break;
			    		case "O":
			    			TreasureCard oceanTreasureCard = new TreasureCard(attributes[0]);
			    			cardDeck.add(oceanTreasureCard);
			    			break;
			    		case "E":
			    			TreasureCard earthTreasureCard = new TreasureCard(attributes[0]);
			    			cardDeck.add(earthTreasureCard);
			    			break;
			    		case "W":
			    			TreasureCard windTreasureCard = new TreasureCard(attributes[0]);
			    			cardDeck.add(windTreasureCard);
			    			break;
			    		case "H":
			    			TreasureCard helicopterTreasureCard = new HelicopterTreasureCard(attributes[0]);
			    			cardDeck.add(helicopterTreasureCard);
			    			break;
			    		case "S":
			    			TreasureCard sandbagTreasureCard = new SandbagTreasureCard(attributes[0]);
			    			cardDeck.add(sandbagTreasureCard);
			    			break;
			    		case "R":
			    			TreasureCard riseWaterTreasureCard = new RiseWaterTreasureCard(attributes[0]);
			    			cardDeck.add(riseWaterTreasureCard);
			    			break;
			    	}
			    	System.out.println(cardDeck);				
		    	} 
		    	catch (IOException e) {
					e.printStackTrace();
				}    
			}
		} 
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}				
	}
}
