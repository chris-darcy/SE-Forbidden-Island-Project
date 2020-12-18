package Observers;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
	private List<Observer> observers = new ArrayList<Observer>();
//	private int state;
	
//	public int getState() {
//		return state;
//	}
//	
//	public void setState(int State) {
//		this.state = state;
//		notifyAllObservers();
//	}
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void notifyAllObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}
}
