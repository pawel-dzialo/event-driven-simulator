package events;

import state.Customer;
import state.State;

/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström The class
 *         is the abstract class of the supermarket events. Creates events and
 *         gives them a time when they occur, a state which they affect aswell
 *         as an EventQueue where they can add newly generated events.
 * 
 */
public abstract class SupermarketEvent extends Event {
	/**
	 * 
	 * @param givenTime
	 * @param givenQueue
	 * @param givenState
	 *            The constructor. Creates an Event.
	 * 
	 */
	public SupermarketEvent(double givenTime, EventQueue givenQueue,
			State givenState) {
		super(givenTime, givenQueue, givenState);
	}

	public void runEvent() {
		super.runEvent();
		if (!(this instanceof StartEvent) && !(this instanceof StopEvent)) {
			this.state.updateStatistics();
		}
		this.state.setPrint();

	}

}
