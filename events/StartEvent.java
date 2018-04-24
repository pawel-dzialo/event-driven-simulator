package events;

import java.util.ArrayList;

import state.Customer;
import state.State;
/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström
 *The class created a StartEvent, which opens the supermarket. 
 *
 */
public class StartEvent extends SupermarketEvent {
	private Customer customer;
	/**
	 * 
	 * @param givenTime
	 * @param givenQueue
	 * @param givenState
	 * The constructor, creates a StartEvent
	 */
	public StartEvent(double givenTime, EventQueue givenQueue, State givenState) {
		super(givenTime, givenQueue, givenState);
		this.customer = new Customer(state);
	}
	/**
	 * The method opens the supermarket, 
	 * creates a new Arrivalevent for a new customer and 
	 * adds it to the EventQueue. 
	 */
	public void runEvent() {
		super.runEvent();
		
		state.setStop(false);
		this.state.setOpen(true);
		ArrivalEvent newArrival = new ArrivalEvent(state.getTime().arrivalTime(), queue, state, customer);
		if(newArrival.getTime()<state.getClose()){
			queue.addEvent(newArrival);
		}
		
	}
	/**
	 * returns the events name as a string
	 */
	public String name(){
		return "Start";
	}

}
