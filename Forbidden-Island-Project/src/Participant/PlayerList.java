package Participant;

import java.util.ArrayList;

public class PlayerList {
	
	private static PlayerList uniqueInstance = null;
	private ArrayList<Participant> playerList;
	private boolean created;
	
	private PlayerList() {
		this.playerList = new ArrayList <Participant>();
		this.created = false;
	}
	
	public static PlayerList getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new PlayerList();
		}
		return uniqueInstance;
	}
	
	public void addPlayer(Participant p) {
		playerList.add(p);
	}
	
	public ArrayList<Integer> getPlayerLocs(){
		ArrayList<Integer> playerLocs = new ArrayList<Integer>();
		
		for (Participant player: playerList){
			playerLocs.add(player.getLocation());
		}		
		return playerLocs;
	}
	
	public ArrayList<Participant> getPlayersOnTile(int tilePos){
		ArrayList<Participant> playersOnTile = new ArrayList<Participant>();
		
		for(Participant player: playerList) {
			if(player.getLocation() == tilePos) {
				playersOnTile.add(player);
			}
		}
		return playersOnTile;
	}
	
	public boolean playersOnTile(int tilePos){
		return getPlayerLocs().contains(tilePos);
	}
	
	public void create() {
		created = true;
	}
	public boolean isCreated() {
		return created;
	}
	public Participant getPlayer(int i) {
		return playerList.get(i);
	}
	
}
