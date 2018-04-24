package events;

import java.util.ArrayList;

import state.Customer;
import state.State;

/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström 
 *        The class creates a PayEvent for a specific customer.
 *
 */
public class PayEvent extends SupermarketEvent {

	private Customer customer;

	/**
	 * 
	 * @param givenTime
	 * @param givenQueue
	 * @param state
	 * @param givenCustomer
	 *            The constructor, creates a PayEvent by taking the time of the
	 *            payment, the state of the supermarket, the EventQueue which exists
	 *            at the time aswell as the customer which is going to pay.
	 * 
	 */
	public PayEvent(double givenTime, EventQueue givenQueue, State state, Customer givenCustomer) {
		super(givenTime, givenQueue, state);
		this.customer = givenCustomer;
	}

	/**
	 * The "do-me" method of PayEvent. If the queue is not empty a new PayEvent for
	 * the first customer in the FIFO queue.
	 */
	public void runEvent() {
		super.runEvent();
		this.state.notifyFreeRegister();
		this.state.notifyCustomerLeftStore();
		this.state.addPayedCustom();
		if (state.queueSize() != 0) {
			this.state.notifyUsedRegister();
			Customer cust = this.state.getQueue().first();
			state.getQueue().removeFirst();
			PayEvent newPay = new PayEvent(this.state.getTime().payTime(this.time), this.queue, this.state,
					cust);
			queue.addEvent(newPay);
			
			
		}
		this.state.setLastPayevent(this.time);
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
	 * returns the name of the event as a string
	 */
	public String name() {
		return "Pay \t";
	}
}
