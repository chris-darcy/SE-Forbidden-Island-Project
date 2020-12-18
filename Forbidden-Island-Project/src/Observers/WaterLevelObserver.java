package Observers;

import Game.GameManager;
import WaterLevel.WaterLevel;

public class WaterLevelObserver extends Observer{
	private Subject subject;
	
	public WaterLevelObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update() {
		if(((WaterLevel)this.subject).getCurrentWaterLevel() == ((WaterLevel)this.subject).getMaxWaterLevel()) {
			System.out.println("You have reached the maximum water level!");
			GameManager.getInstance().endGame(false); // run gamelost logic to end the game
		}
	}
}
