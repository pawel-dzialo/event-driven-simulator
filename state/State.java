package state;

import events.ArrivalEvent;
import events.Event;
import genState.SimState;

import java.util.ArrayList;
import java.util.Observable;

/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström The class
 *         creates a state of the supermarket.
 * 
 */
public class State extends SimState {
	// Parameters
	private int registerAmount;
	private double lambda;
	private double kMax, kMin, pMax, pMin;
	private long seed;

	// Customers
	private int missedCustomers;
	private int maximumCustomerAmount;
	private int currentCustomerCount;
	private int allCustomersCount;
	private int costumerId;
	private int payedCustom;

	// Boolean values for the simulation.
	private boolean open;
	private boolean isStop = false;

	// FIFO queue
	private int queueAmount;
	private Queue queue;

	// Registers
	private int openRegisters;

	// Time tracking (and event)
	private double timeRegisters;
	private double timeCustomerQueue;
	private double closeTime;
	private Time time;
	private Event currentEvent;
	private double lastPayEvent;
	private Event previousEvent = null;

	/**
	 * 
	 * @param maxCustomerAmount
	 *            - set max Customers in store
	 * @param registerAmount
	 *            - set amount of registers in total
	 */
	public State(int maxCustomerAmount, int registerAmount, double lambda,
			double kMin, double kMax, double pMin, double pMax, long givenSeed,
			double close) {
		this.seed = givenSeed;
		this.lambda = lambda;
		this.kMax = kMax;
		this.kMin = kMin;
		this.pMin = pMin;
		this.pMax = pMax;
		this.queue = new Queue();
		this.time = new Time(lambda, kMin, kMax, pMin, pMax, givenSeed);
		this.closeTime = close;
		this.maximumCustomerAmount = maxCustomerAmount;
		this.registerAmount = registerAmount;
		this.openRegisters = registerAmount;
		this.open = false;
		this.queueAmount = 0;
		this.timeRegisters = 0;
		this.timeCustomerQueue = 0;
		this.costumerId = 0;
	}

	/**
	 * Add a payed customer
	 */
	public void addPayedCustom() {
		this.payedCustom++;
	}

	/**
	 * 
	 * @return all payed customers so far
	 */
	public int getPayedCustom() {
		return this.payedCustom;
	}

	/**
	 * 
	 * @return the seed of the state
	 */
	public long getSeed() {
		return this.seed;
	}

	/**
	 * 
	 * @param e
	 *            The method sets the states current event variable to the event
	 *            e.
	 */
	public void setCurrentEvent(Event e) {
		this.previousEvent = getCurrentEvent();
		this.currentEvent = e;
	}

	/**
	 * 
	 * @return the pMax of the state
	 */
	public double getPMax() {
		return this.pMax;
	}

	/**
	 * 
	 * @return the pMin of the state
	 */
	public double getPMin() {
		return this.pMin;
	}

	/**
	 * 
	 * @return the kMax of the state
	 */
	public double getKMax() {
		return this.kMax;
	}

	/**
	 * 
	 * @return the kMin of the state
	 */
	public double getKMin() {
		return this.kMin;
	}

	/**
	 * 
	 * @return the lambda of the state
	 */
	public double getLambda() {
		return this.lambda;
	}

	/**
	 * 
	 * @return the maximum of customers allowed in the store
	 */
	public int getMaxCustomerAmount() {
		return this.maximumCustomerAmount;
	}

	/**
	 * 
	 * @return the amount of open registers
	 */
	public int getOpenRegisters() {
		return openRegisters;
	}

	/**
	 * The method updates the total time that the states registers have been
	 * free
	 */
	public void setTimeRegisters() {
		this.timeRegisters += (this.time.getTime() - previousEvent.getTime())
				* openRegisters;
	}

	/**
	 * The method updates the total time that customers have queued in the FIFO
	 * queue
	 */
	public void setTimeQueue() {
		this.timeCustomerQueue += (this.time.getTime() - previousEvent
				.getTime()) * queue.size();
	}

	/**
	 * 
	 * @param time
	 *            Sets the current time of the simulation to the param time, and
	 *            notifies the view, which creates an output on to the console
	 */
	public void setPrint() {
		setChanged();
		notifyObservers();
	}

	public void setTime(double time) {
		this.time.setTime(time);
	}

	public void updateStatistics() {

		setTimeQueue();
		if (currentEvent instanceof ArrivalEvent) {
			if (isOpen()) {
				setTimeRegisters();
			}
		} else {
			setTimeRegisters();
		}
	}

	/**
	 * 
	 * @return the current event of the simulation
	 */
	public Event getCurrentEvent() {
		return this.currentEvent;
	}

	/**
	 * 
	 * @return the total time that customers have queued in the FIFO queue
	 */
	public double getTimeQueue() {
		return this.timeCustomerQueue;
	}

	/**
	 * 
	 * @param lasttime
	 *            Sets the simulations last payevent to the param lasttime
	 */
	public void setLastPayevent(double lasttime) {
		this.lastPayEvent = lasttime;
	}

	/**
	 * 
	 * @return the total amount of customers that have visited the store since
	 *         its opening
	 * 
	 */
	public int getCustomersCount() {
		return this.allCustomersCount;
	}

	/**
	 * 
	 * @return the current amount of customers in the store
	 */
	public int getCurrentCustomerCount() {
		return this.currentCustomerCount;
	}

	/**
	 * 
	 * @return the last payevent of the simulation
	 */
	public double getLastPayevent() {
		return lastPayEvent;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isStopped() {
		return this.isStop;
	}

	/**
	 * 
	 * @param k
	 *            Stops the simulation
	 */
	public void setStop(boolean k) {
		this.isStop = k;
	}

	/**
	 * 
	 * @param k
	 *            Opens or closes the store
	 */
	public void setOpen(boolean k) {
		this.open = k;
	}

	/**
	 * 
	 * @return if the store is open or not, true if it is open, false if not
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * 
	 * @return the FIFO queue
	 */
	public Queue getQueue() {
		return this.queue;
	}

	/**
	 * 
	 * @return time of the state
	 */
	public Time getTime() {
		return this.time;
	}

	/**
	 * 
	 * @return the closing time of the supermarket
	 */
	public double getClose() {
		return this.closeTime;
	}

	/**
	 * Reduces the current register amount by 1
	 */
	public void notifyUsedRegister() {
		openRegisters--;
	}

	/**
	 * Call when payEvent is done, notifies that one register is now open.
	 */
	public void notifyFreeRegister() {
		if (openRegisters == registerAmount) {
			openRegisters = registerAmount;
		} else {
			openRegisters++;
		}
	}

	/**
	 * Checks if a Register is open.
	 * 
	 * @return true if a Register is open, false otherwise.
	 */
	public boolean isRegisterOpen() {
		return openRegisters > 0 ? true : false;
	}

	/**
	 * Checks if the store is currently full or not
	 * 
	 * @return true if store is full, false otherwise.
	 */
	public boolean isStoreFull() {
		return currentCustomerCount == maximumCustomerAmount ? true : false;
	}

	/**
	 * Call when a Customer enters the store, updates current number of
	 * Customers in store.
	 */
	public void notifyCustomerEnteredStore() {
		if (currentCustomerCount == maximumCustomerAmount) {
			currentCustomerCount = maximumCustomerAmount;
		} else {
			currentCustomerCount++;
		}
	}

	/**
	 * Reduces the current customer amount of the store by 1
	 */
	public void notifyCustomerLeftStore() {
		this.currentCustomerCount--;
	}

	/**
	 * Adds a Customer to queueStatistics. (Should be called when a Customers
	 * needs to queue)
	 */
	public void addCustomerQueue() {
		queueAmount += 1;
	}

	/**
	 * 
	 * @return the size of the queue
	 */
	public int queueSize() {
		return this.queue.size();
	}

	/**
	 * Adds a missed Customer to the missedCustomer statistics. (Should be
	 * called when a person leaves the store without paying)
	 */
	public void addMissedCustomer() {
		missedCustomers += 1;
	}

	/**
	 * 
	 * @return total number of Customers that have queued.
	 */
	public int getQueueAmount() {
		return queueAmount;
	}

	/**
	 * 
	 * @return total number of Customers leaving without paying.
	 */
	public int getMissedCustomers() {
		return missedCustomers;
	}

	/**
	 * 
	 * @return total time registers are used in milliseconds since store opened
	 */
	// TODO used with Time object, in TIme update this variable
	public double getTimeRegisters() {
		return this.timeRegisters;
	}

	/**
	 * 
	 * @return total time Customers have been in queue since store opened.
	 */
	// TODO used with TIme object, in time update this variable
	public double getTimeCustomerQueue() {
		return timeCustomerQueue;
	}

	/**
	 * Updates the total customer count by adding 1
	 */
	public void updateCustomersCount() {
		allCustomersCount++;
	}

	/**
	 * 
	 * @return total customer amount since the startevent
	 */
	public int getTotalCustomers() {
		return allCustomersCount;
	}

	/**
	 * 
	 * @return number of registers active
	 */
	public int getRegisterNum() {
		return registerAmount;
	}

	/**
	 * Increases the number of the ID variable, so that the next customer gets a
	 * new ID.
	 */
	public void addCostumerID() {
		costumerId++;
	}

	/**
	 * 
	 * @return the current customer ID to assign
	 */
	public int getCostumerIDSize() {
		return costumerId;
	}
}