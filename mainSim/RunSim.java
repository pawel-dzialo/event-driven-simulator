package mainSim;

import events.CloseEvent;
import events.Event;
import events.EventQueue;
import events.StartEvent;
import events.StopEvent;
import genView.SimView;
import genView.SupermarketView;
import state.State;

/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström 
 * The class is the main class which hold the main method.
 * 
 *
 */
public class RunSim {
	/**
	 * 
	 * @param args
	 *            The main method which takes all the parameters, assigns them to
	 *            the program state and creates and runs a simulator.
	 * 
	 */
	public static void main(String[] args) {
		double closeTime = Double.parseDouble(args[0]);
		int amountOfRegisters = Integer.parseInt(args[1]);
		int maxCustomerAmount = Integer.parseInt(args[2]);
		double lambda = Double.parseDouble(args[3]);
		double minK = Double.parseDouble(args[4]);
		double maxK = Double.parseDouble(args[5]);
		double minP = Double.parseDouble(args[6]);
		double maxP = Double.parseDouble(args[7]);
		long seed = Long.parseLong(args[8]);

		State state = new State(maxCustomerAmount, amountOfRegisters, lambda, minK, maxK, minP, maxP, seed, closeTime);
		SupermarketView view = new SupermarketView(state);
		EventQueue eventQueue = new EventQueue(state);
		Event start = new StartEvent(0, eventQueue, state);
		Event close = new CloseEvent(closeTime, eventQueue, state);
		Event stop = new StopEvent(999, eventQueue, state);
		eventQueue.addEvent(start);
		eventQueue.addEvent(close);
		eventQueue.addEvent(stop);

		Simulator simulator = new Simulator(eventQueue, state);
		simulator.run();
		Optimize optimize = new Optimize(args);
		System.out.println(optimize.metod2(seed));
	}
}
