package Observers;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
	//
	// abstract class created to give all that extend the observer functionality
	//
	private List<Observer> observers = new ArrayList<Observer>();
	
	//
	// attach to ecah subject to be able to observe
	//
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	//
	// notify all observers when updated
	//
	public void notifyAllObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}
}
