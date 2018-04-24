package state;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström 
 * The class creates a FIFO queue.
 *
 */
public class Queue {

	private int maxSize;
	private ArrayList<Customer> queue;

	/**
	 * Creates a new Queue
	 */
	public Queue() {
		queue = new ArrayList<Customer>();
		maxSize = 0;
	}

	/**
	 * Add a Customer to the queue
	 * 
	 * @param Customer
	 */
	public void add(Customer customer) {
		queue.add(customer);
		if (queue.size() > maxSize) {
			maxSize = size();
		}
	}

	/**
	 * Remove the first Customer
	 * 
	 * @throws NoSuchElementException
	 *             if list is of size 0.
	 */
	public void removeFirst() throws NoSuchElementException {
		try {
			queue.remove(0);

		} catch (IndexOutOfBoundsException e) {
			throw new NoSuchElementException();
		}

	}

	/**
	 * 
	 * @return first Customer in queue
	 * @throws NoSuchElementException,
	 *             if queue is of length 0
	 */
	public Customer first() throws NoSuchElementException {
		try {
			return queue.get(0);

		} catch (IndexOutOfBoundsException e) {
			throw new NoSuchElementException();
		}
	}

	/*
	 * return maxsize of queue so far
	 */
	public int maxSize() {
		return maxSize;
	}

	/**
	 * Check if empty
	 * 
	 * @return true if empty
	 */
	public boolean isEmpty() {
		return queue.size() == 0;
	}

	/**
	 * 
	 * @return current size of queue
	 */
	public int size() {
		return queue.size();
	}

	/**
	 * Makes a String of the queue.
	 */
	public String toString() {
		String string = "Queue: ";
		for (Customer customer : queue) {
			string += "(" + String.valueOf(customer.getId()) + ") ";
		}
		return string;
	}

	/**
	 * 
	 * @return the queue itself.
	 */
	public ArrayList<Customer> getQueue() {
		return this.queue;
	}

	/**
	 * If two queues are equal
	 */
	public boolean equals(Object f) {
		if (f instanceof Queue) { // Checking if f is a fifo queue.
			Queue newF = (Queue) f;

			if (size() != newF.size()) {
				return false;
			}

			for (int i = 0; i < queue.size(); i++) {
				if (queue.get(i) != null && newF.queue.get(i) != null) {

					if (!queue.get(i).equals(newF.queue.get(i))) {
						return false;
					}

				} else if (queue.get(i) != newF.queue.get(i)) {
					return false;
				}
			}
			return true; // If they are the same size and every element is the same;

		} else {
			return false; // If both are not queues at all
		}
	}
}