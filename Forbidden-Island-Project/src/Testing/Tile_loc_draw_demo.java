package Testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import Board.TileType;

public class Tile_loc_draw_demo {
	public static void main(String [] args) {
		
		ArrayList<Integer> nums = new ArrayList<Integer>();
		
		for(int i=0;i<36;i++){
			nums.add(i);
		}
		Collections.shuffle(nums);
		Iterator<Integer> idx = nums.iterator();
		
		for(int i=0;i<36;i++){
			System.out.println(i);
			System.out.println(idx);
			System.out.println(idx.next()+"\n");
		}
	}
}
