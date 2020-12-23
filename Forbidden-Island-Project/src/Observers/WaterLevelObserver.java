package Observers;

import Game.GameManager;
import WaterLevel.WaterLevel;

public class WaterLevelObserver extends Observer{
	private Subject subject;
	
	//
	// this obeserver checks the current waterlevel to check if the game has been lost
	//
	public WaterLevelObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	//
	// updates the observers by checking is criteria are met for losing the game
	//
	@Override
	public void update() {
		if(((WaterLevel)this.subject).getCurrentWaterLevel() == ((WaterLevel)this.subject).getMaxWaterLevel()) {
			System.out.println("You have reached the maximum water level!");
			GameManager.getInstance().endGame(false); // run gamelost logic to end the game
		}
	}
}
