package state;
/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström
 * The class creates costumers which have a specific id. 
 *
 */
public class Customer{
	
	private State state;
	private int id;
	
	/**
	 * Sets id to size. Size is kept track of in the state.
	 * 
	 */
	public Customer(State state) {
		this.state = state;
		this.id = state.getCostumerIDSize();
		state.addCostumerID();
	}
	
	/**
	 * Returns specific id for object.
	 * 
	 * @return id
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * 
	 * @return the state of the customer
	 */
	public State getState() {
		return this.state;
	}
}
