package events;

import java.util.ArrayList;

import state.State;

/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström 
 * Creates a CloseEvent which closes the supermarket when called upon.
 *
 */
public class CloseEvent extends SupermarketEvent {
	/**
	 * 
	 * @param givenTime
	 * @param givenQueue
	 * @param givenState
	 *            The constructor, creates a CloseEvent at a given time.
	 * 
	 */
	public CloseEvent(double givenTime, EventQueue givenQueue, State givenState) {
		super(givenTime, givenQueue, givenState);
	}

	/**
	 * The "do-me" method for CloseEvent, which changes the boolean which stands for
	 * the open store to false (closed).
	 */
	public void runEvent() {
		super.runEvent();
		this.state.setOpen(false);

	}

	/**
	 * returns the name of the event as a string
	 */
	public String name() {
		return "Close";
	}

}
