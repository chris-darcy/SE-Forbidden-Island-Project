package Cards;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
//import java.util.Collection;

public class FloodCardDeck {
	Stack<FloodCard> cardDeck = new Stack<FloodCard>();
	String line;
	String[] attributes;
	
	public FloodCardDeck() {
		initialise();
	}
	
	private void initialise() {
		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader("src/Tiles.txt"));
			for (int i = 0; i < 24; i++) {  // 24 flood cards
		    	try {
		    		line = scan.readLine();
					attributes = line.split("-"); //Delimiter is a hyphen
			    	
					for (int j = 0; j < attributes[1].length(); j++) {
		    		FloodCard floodCard = new FloodCard(attributes[0]);
		    		cardDeck.push(floodCard);
			    	}			
		    	} 
		    	catch (IOException e) {
					e.printStackTrace();
				}    
			}
		} 
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
//		Collection.shuffle(cardDeck);
	}
	public String printDeck() {
		for (FloodCard card: cardDeck) {
			System.out.println(card.getName());
		}
		return null;
	}
	
	public int size() {
		return cardDeck.size();
	}
	public FloodCard pop() {
		return cardDeck.pop();
	}
}

