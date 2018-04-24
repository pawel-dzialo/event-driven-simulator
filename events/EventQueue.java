package events;

import java.util.ArrayList;
import state.State;

/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström
 *         The class created an EventQueue, which sotres different kinds of Events and
 *         puts them in an ArrayList of Events.
 *
 */

public class EventQueue {

	private ArrayList<Event> eventList;

	/**
	 * 
	 * @param state
	 *            The constructor, creaters an EventQueue and creates and adds a
	 *            StartEvent to the queue.
	 * 
	 */
	public EventQueue(State state) {
		this.eventList = new ArrayList<Event>();
	}

	/**
	 * 
	 * @return the EventQueue.
	 * 
	 */
	public ArrayList<Event> getEventQueue() {
		return this.eventList;

	}

	/**
	 * 
	 * @return the first element in the EventQueue.
	 * 
	 */
	public Event getEvent() {
		Event temp = this.eventList.get(0);
		removeFirst();
		return temp;
	}

	/**
	 * 
	 * @param event
	 *            Adds an Event to the EventQueue.
	 * 
	 */
	public void addEvent(Event event) {
		this.eventList.add(event);
		SortedSequence sorter = new SortedSequence();
		this.eventList = sorter.sort(eventList);
	}

	// removes the first element in the queue
	private void removeFirst() {
		this.eventList.remove(0);
	}

	/**
	 * 
	 * @return true if the queue is empty, false if not
	 */
	public boolean isEmpty() {
		if (eventList.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @return the eventqueue
	 */
	public ArrayList<Event> getQueue() {
		return this.eventList;
	}

}
