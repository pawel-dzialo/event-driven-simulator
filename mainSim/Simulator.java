package mainSim;

import state.State;
import events.ArrivalEvent;
import events.CloseEvent;
import events.Event;
import events.EventQueue;
import events.GatherEvent;
import events.PayEvent;
import events.StartEvent;
import events.StopEvent;
import genView.SimView;
import genView.SupermarketView;

/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström The class
 *         creates a simulator which keeps the program running as long as the
 *         store is "open".
 * 
 */
public class Simulator {
	private EventQueue queue;
	private State simState;

	/**
	 * 
	 * @param eventQueue
	 * @param state
	 * @param view
	 *            The constructor, creates a simulator with an EventQueue and a
	 *            state
	 * 
	 */
	public Simulator(EventQueue eventQueue, State state) {
		this.queue = eventQueue;
		this.simState = state;

	}

	/**
	 * The run method of the simulator which loops through the EventQueue and
	 * runs the events "do-me" methods
	 * 
	 */
	public void run() {
		while (!simState.isStopped()) {
			while (!queue.isEmpty()) {
				queue.getEvent().runEvent();
			}
		}
	}
}
