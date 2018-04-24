package events;

import java.util.ArrayList;

import state.Customer;
import state.State;

/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström 
 * The class creates a ArrivalEvent for a specific customer.
 *
 */
public class ArrivalEvent extends SupermarketEvent {
	private Customer customer;

	/**
	 * 
	 * @param givenTime
	 * @param givenQueue
	 * @param state
	 * @param givenCustomer
	 *            The constructor, creates an ArrivalEvent by taking the time of the
	 *            arrival, the state of the supermarket, the EventQueue which exists
	 *            at the time aswell as the customer which is going to arrive.
	 * 
	 */
	public ArrivalEvent(double givenTime, EventQueue givenQueue, State state, Customer givenCustomer) {
		super(givenTime, givenQueue, state);
		this.customer = givenCustomer;
	}

	/**
	 * The "do-me" method of the ArrivalEvent. It creates a new ArrivalEvent for a
	 * new customer. If the store is not full and if its still open the method also
	 * creates a GatherEvent for the arriving customer.
	 */
	public void runEvent() {
		super.runEvent();
		if (state.isOpen()) {
			state.Customer newCust = new state.Customer(state);
			ArrivalEvent newArrival = new ArrivalEvent(state.getTime().arrivalTime(), queue, state, newCust);
			queue.addEvent(newArrival);
			this.state.updateCustomersCount();
			if (!state.isStoreFull()) {
				state.notifyCustomerEnteredStore();
				GatherEvent newGather = new GatherEvent(state.getTime().gatherTime(this.time), queue, state,
						this.customer);
				queue.addEvent(newGather);
			} else {
				state.addMissedCustomer();
			}
		}
	}

	/**
	 * 
	 * @return the customer who the event applies to.
	 * 
	 */
	public Customer getCustomer() {
		return this.customer;
	}

	/**
	 * 
	 * @return the time when the event occur
	 */
	public double getEventTime() {
		return time;
	}

	/**
	 * returns the name of the event as a string
	 */
	public String name() {
		return "Arrival";
	}
}
