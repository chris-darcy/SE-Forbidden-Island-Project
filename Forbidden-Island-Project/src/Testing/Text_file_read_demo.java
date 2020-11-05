package Testing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Text_file_read_demo {

	public static void main(String [] args){
		String line;
		String[] attributes;

		BufferedReader scan;
		try {
			scan = new BufferedReader(new FileReader("src/Tiles.txt"));
			for (int i = 0; i < 24; i++) {    	
		    	try {
					line = scan.readLine();
					attributes = line.split("-"); //Delimiter is a hyphen
				    System.out.println("tile name: " + attributes[0]); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			
			    //Inconsistently Tiles.txt is not found with the first try and is found the second 	
	}
}
