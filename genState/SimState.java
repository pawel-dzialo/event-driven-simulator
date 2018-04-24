package genState;

import java.util.Observable;

/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström 
 * 		   The class is an abstract class which defines the form of the state of the
 *         supermarket.
 *
 */
public abstract class SimState extends Observable {
	private boolean isStopped = false;

	/**
	 * The constructor is not defined here.
	 */
	public SimState() {
	}

	/**
	 * Stops the current simulation
	 * 
	 */
	public void stop() {
		if (!isStopped) {
			isStopped = true;
		}
	}

	/**
	 * Returns the current status, if the simulation should be interrupted or not.
	 * 
	 * @return if the process is stopped or not
	 */
	public boolean getStopStatus() {
		return isStopped;
	}

	/**
	 * Notify all observers
	 */
	public void notifyObs() {
		setChanged();
		notifyObservers();
	}

}