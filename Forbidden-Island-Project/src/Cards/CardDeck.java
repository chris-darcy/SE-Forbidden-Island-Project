package Cards;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class CardDeck {
	
	public CardDeck() {
	}
	
	ArrayList<Card> cardDeck;
	
	public void initialise(int numberOfCardsInDeck) {
		String line;
		String[] attributes;
		ArrayList<Integer> nums = new ArrayList<Integer>();
		
		// Randomize Card indices
		for(int i=0;i<numberOfCardsInDeck;i++){ // number of cards depends on the deck made
			nums.add(i);
		}
		
		Collections.shuffle(nums); // shuffle cards
		Iterator<Integer> idx = nums.iterator();
		
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader("src/CardNames.txt"));
			for (int i = 0; i < numberOfCardsInDeck; i++) {    	
		    	try {
					
		    		line = scan.readLine();
					attributes = line.split("-"); //Delimiter is a hyphen
			    	
			    	switch(attributes[1]) {
			    		case "F":
			    			FireTreasureCard fireTreasureCard = new FireTreasureCard(attributes[0]);
			    			cardDeck.add(fireTreasureCard);
			    			break;
			    		case "O":
			    			OceanTreasureCard oceanTreasureCard = new OceanTreasureCard(attributes[0]);
			    			cardDeck.add(oceanTreasureCard);
			    			break;
			    		case "E":
			    			EarthTreasureCard earthTreasureCard = new EarthTreasureCard(attributes[0]);
			    			cardDeck.add(earthTreasureCard);
			    			break;
			    		case "W":
			    			WindTreasureCard windTreasureCard = new WindTreasureCard(attributes[0]);
			    			cardDeck.add(windTreasureCard);
			    			break;
			    		case "H":
			    			HelicopterTreasureCard helicopterTreasureCard = new HelicopterTreasureCard(attributes[0]);
			    			cardDeck.add(helicopterTreasureCard);
			    			break;
			    		case "S":
			    			SandbagTreasureCard sandbagTreasureCard = new SandbagTreasureCard(attributes[0]);
			    			cardDeck.add(sandbagTreasureCard);
			    			break;
			    		case "R":
			    			RiseWaterTreasureCard riseWaterTreasureCard = new RiseWaterTreasureCard(attributes[0]);
			    			cardDeck.add(riseWaterTreasureCard);
			    			break;
			    	}
				    
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}				
	}
}
