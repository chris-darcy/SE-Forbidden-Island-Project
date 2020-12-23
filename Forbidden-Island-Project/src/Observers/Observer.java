package Observers;

public abstract class Observer {
	//
	// abstract class to create other observer classes from
	//
	protected Object object;
	public abstract void update();
}
