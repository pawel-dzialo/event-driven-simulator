package events;

import java.util.ArrayList;

import state.Customer;
import state.State;
/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström 
 * The abstract general class for an Event.
 *
 *
 */
public abstract class Event {
	protected double time;
	protected EventQueue queue;
	protected State state;
	public String getEvent;
	public Object getCustomer;

	/**
	 * 
	 * @param givenTime
	 * @param givenQueue
	 * @param givenState
	 *            The constructor, creates an event by declaring the time of an
	 *            event, the state which it affects and the EventQueue it is put
	 *            into.
	 */
	public Event(double givenTime, EventQueue givenQueue, State givenState) {
		this.queue = givenQueue;
		this.time = givenTime;
		this.state = givenState;
	}

	/**
	 * All Events needs to have a runEvent method. The body is declared here.
	 */
	public void runEvent(){
		this.state.setTime(this.time);
		this.state.setCurrentEvent(this);
	}

	/**
	 * 
	 * @return the time of an Event.
	 * 
	 */
	public double getTime() {
		return this.time;
	}

	/**
	 * 
	 * @return the name
	 */
	public String name() {
		return null;
	}

	/**
	 * 
	 * @return the event
	 */
	public ArrayList<String> getEvent() {
		return null;

	}

	/**
	 * 
	 * @return the customer
	 */
	public Customer getCustomer() {
		return null;
	}

}
