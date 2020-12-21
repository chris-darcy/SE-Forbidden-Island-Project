package Participant;

import java.util.ArrayList;

import Cards.HelicopterTreasureCard;
import Cards.SandbagTreasureCard;

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
	
	public ArrayList<Participant> getPlayerList(){
		return playerList;
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
	
	public int getSize() {
		return playerList.size();
	}

	public ArrayList<String> getAllStringPlayersExcept(Participant player) {
		ArrayList<String> others = new ArrayList<String>();
		others = getAllStringPlayers();
		others.removeIf(n->(n.equals(player.toString())));
	
		return others;
	}
	
	public ArrayList<String> getAllStringPlayers(){
		ArrayList<String> players = new ArrayList<String>();
		for(Participant p: playerList) {
				players.add(p.toString());
		}
		return players;
	}
	
	public boolean playerListContainsHelicopterCard() {
		HelicopterTreasureCard helicopter = new HelicopterTreasureCard("Helicopter");
		return playerListContains(helicopter);
	}
	
	public boolean playerListContainsSandBagCard() {
		SandbagTreasureCard sandbag = new SandbagTreasureCard("Sandbag");
		return playerListContains(sandbag);
	}
	
	public boolean playerListContains(Object o) {
		for (Participant p : playerList) {
			if(p.getHand().handContains(o)) {
				return true;
			}
		}
		return false;
	}
}
