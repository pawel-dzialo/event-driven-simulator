package mainSim;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import events.CloseEvent;
import events.Event;
import events.EventQueue;
import events.StartEvent;
import events.StopEvent;
import state.State;

/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström
 *  The class optimizes different simulations
 *
 */
public class Optimize {

	private double closeTime;
	private int amountOfRegisters;
	private int maxCustomerAmount;
	private double lambda;
	private double minK;
	private double maxK;
	private double minP;
	private double maxP;
	private long seed;

	private int missedCustomers;

	/**
	 * 
	 * @param args
	 *            The constructor, assigns the value of a simulation
	 */
	public Optimize(String[] args) {
		this.closeTime = Double.parseDouble(args[0]);
		this.amountOfRegisters = 1;
		this.maxCustomerAmount = Integer.parseInt(args[2]);
		this.lambda = Double.parseDouble(args[3]);
		this.minK = Double.parseDouble(args[4]);
		this.maxK = Double.parseDouble(args[5]);
		this.minP = Double.parseDouble(args[6]);
		this.maxP = Double.parseDouble(args[7]);
		this.seed = Long.parseLong(args[8]);
	}

	/**
	 * 
	 * @param amountOfRegisters
	 * @param givenSeed
	 * @return the state of a simulation that has been run
	 */
	public State metod1(int amountOfRegisters, long givenSeed) {
		State state = new State(maxCustomerAmount, amountOfRegisters, lambda,
				minK, maxK, minP, maxP, givenSeed, closeTime);
		EventQueue eventQueue = new EventQueue(state);
		Event start = new StartEvent(0, eventQueue, state);
		Event close = new CloseEvent(closeTime, eventQueue, state);
		Event stop = new StopEvent(999, eventQueue, state);

		// SupermarketView view = new SupermarketView(state);

		eventQueue.addEvent(close);
		eventQueue.addEvent(start);
		eventQueue.addEvent(stop);

		Simulator simulator = new Simulator(eventQueue, state);
		simulator.run();

		return state;
	}

	/**
	 * 
	 * @param givenSeed
	 * @return the minumum amount of registers, so that there are no missed
	 *         customers
	 */
	public int metod2(long givenSeed) {
		int amountofRegisters = 1;
		int minAmountofRegisters = maxCustomerAmount;
		int missedCustomers = maxCustomerAmount;
		while (amountofRegisters <= maxCustomerAmount) {
			int tempCust = metod1(amountofRegisters, givenSeed)
					.getMissedCustomers();
			if (tempCust < missedCustomers) {
				missedCustomers = tempCust;
				minAmountofRegisters = amountofRegisters;
			}
			amountofRegisters++;
		}
		return minAmountofRegisters;

	}

	/**
	 * 
	 * @param givenSeed
	 * @return the minumum amount of registers, so that there are no missed
	 *         customers
	 */
	public int metod3(long givenSeed) {
		Random generator = new Random(givenSeed);
		int counter = 0;
		int pastAmount = 0;
		int tempAmount = 0;
		while (counter < 100) {
			tempAmount = metod2(generator.nextLong());
			if (tempAmount <= pastAmount) {
				counter++;
			} else {
				pastAmount = tempAmount;
				counter = 0;
			}

		}
		return pastAmount;
	}
}


