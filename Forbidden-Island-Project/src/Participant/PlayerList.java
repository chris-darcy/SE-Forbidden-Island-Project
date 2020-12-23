package Participant;

import java.util.ArrayList;

import Cards.Card;

public class PlayerList {
	
	private static PlayerList uniqueInstance = null;
	private ArrayList<Participant> playerList;
	private boolean created;
	
	private PlayerList() {
		this.playerList = new ArrayList <Participant>();
		this.created = false;
	}
	
	//
	// return current status of the playerlist
	//
	public static PlayerList getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new PlayerList();
		}
		return uniqueInstance;
	}
	
	//
	// add player to the playerlist
	//
	public void addPlayer(Participant p) {
		playerList.add(p);
	}
	
	public ArrayList<Participant> getPlayerList(){
		return this.playerList;
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
	
	//
	// find playerlist individuals on any given tile
	//
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
	
	public int getSize() {
		return playerList.size();
	}
	
	//
	// return all playerlist members except for one
	//
	public ArrayList<String> getAllStringPlayersExcept(Participant player) {
		ArrayList<String> others = new ArrayList<String>();
		others = getAllStringPlayers();
		others.removeIf(n->(n.equals(player.toString())));
	
		return others;
	}
	
	//
	// returns playerlist in string format
	//
	public ArrayList<String> getAllStringPlayers(){
		ArrayList<String> players = new ArrayList<String>();
		for(Participant p: playerList) {
				players.add(p.toString());
		}
		return players;
	}
	
	public ArrayList<String> playerListStringify(ArrayList<Participant> list){
		ArrayList<String> stringPlayers = new ArrayList<String>();
		for (Participant p : list) {
			stringPlayers.add(p.toString());
		}
		return stringPlayers;
	}
	
	//
	// ensures at least one sandbag card between the players
	//
	public ArrayList<Participant> playerListContainsHelicopterCard() {
		ArrayList<Participant> playersWeWant = new ArrayList<Participant>();
		
		for (Participant p : playerList) {
			if(p.getHand().handContainsHelicopter()) {
				playersWeWant.add(p);
			}
		}
		return playersWeWant;
	}
	
	//
	// ensures at least one sandbag card between the players
	//
	public ArrayList<Participant> playerListContainsSandBagCard() {
		ArrayList<Participant> playersWeWant = new ArrayList<Participant>();
		
		for (Participant p : playerList) {
			if(p.getHand().handContainsSandbag()) {
				playersWeWant.add(p);
			}
		}
		return playersWeWant;
	}
	
	//
	// ensures at least one card of type x between the players
	//
	public ArrayList<Participant> playerListContains(Card o) {
		ArrayList<Participant> specialPlayerList = new ArrayList<Participant>();
		for (Participant p : playerList) {
			if(p.getHand().handContains(o)) {
				specialPlayerList.add(p);
			}
		}
		return specialPlayerList;
	}
	
	//
	// move selected players from playerlist
	//
	public void moveSelected(ArrayList<Integer> playersToMove, int location) {
		for(int i : playersToMove) {
			playerList.get(i).setLocation(location);
		}
	}
	
	//
	// for testing purposes
	//
	public void destroyMe(){
		   this.uniqueInstance = null;
	}
}
