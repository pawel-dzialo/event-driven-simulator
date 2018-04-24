package events;

import java.util.ArrayList;

import state.Customer;
import state.State;
/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström
 * The class creates a StopEvent which stops the simulator. 
 *
 */
public class StopEvent extends SupermarketEvent {
	/**
	 * 
	 * @param givenTime
	 * @param givenQueue
	 * @param givenState
	 * The constructor, gives the event a time when it occurs, a state aswell as an EventQueue.
	 */
	public StopEvent(double givenTime, EventQueue givenQueue, State givenState) {
		super(givenTime, givenQueue, givenState);
	}

	/**
	 * The "do-me" method of a StopEvent
	 */
	public void runEvent() {
		super.runEvent();
		state.setStop(true);
		this.state.setTimeQueue();
		if(this.state.getCurrentEvent() instanceof PayEvent){
			this.state.addPayedCustom();
		}
	}
	/**
	 * returns the events name as a string
	 */
	public String name(){
		return "Stop";
	}
	/**
	 * returns null as the event does not apply to one customer.
	 * Returns null so that the utput can be printed out correctly.
	 */
	public Customer getCustomer() {
		return null;
	}

}
