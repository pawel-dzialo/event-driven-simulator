package events;

import java.util.ArrayList;

import state.Customer;
import state.State;

/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström
 *        The class creates a GatherEvent for a specific customer.
 *
 */
public class GatherEvent extends SupermarketEvent {
	private Customer customer;

	/**
	 * 
	 * @param givenTime
	 * @param givenQueue
	 * @param state
	 * @param givenCustomer
	 *            The constructor, creates a GatherEvent with the time when the
	 *            gathering is complete, the state of the supermarket, the
	 *            EventQueue which the GatherEvent is going to be added to when
	 *            called and the specific costumer that the Event regards.
	 */
	public GatherEvent(double givenTime, EventQueue givenQueue, State state, Customer givenCustomer) {
		super(givenTime, givenQueue, state);
		this.customer = givenCustomer;
	}

	/**
	 * The "do-me" method of the GatherEvent. When called, if there are any
	 * registers open, a PayEvent for the costumer is created and added to the
	 * EventQueue, and if not the costumer is added to the FIFO queue.
	 */
	public void runEvent() {
		super.runEvent();
		if (state.isRegisterOpen()) {
			PayEvent payEvent = new PayEvent(this.state.getTime().payTime(this.time), this.queue, this.state,
					this.customer);
			state.notifyUsedRegister();
			queue.addEvent(payEvent);
		} else{
			state.addCustomerQueue();
			state.getQueue().add(this.customer);
		}

	}

	/**
	 * 
	 * @return the costumer which the event regards.
	 * 
	 */
	public Customer getCustomer() {
		return this.customer;
	}

	/**
	 * Returns the name of the event in String-format.
	 */
	public String name() {
		return "Gather";
	}
}
