package events;

import java.util.ArrayList;

/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström 
 * The class sorts an ArrayList of events depending on the occuring time of the
 * events, with the first occuring event first.
 *
 */
public class SortedSequence {
	/**
	 * 
	 * @param eventList
	 * @return The method sorts the eventList after the events time(when they
	 *         occur).
	 * 
	 */
	public ArrayList<Event> sort(ArrayList<Event> eventList) {
		ArrayList<Event> sortedList = new ArrayList<Event>();
		ArrayList<Event> randomList = (ArrayList<Event>) eventList.clone();
		double minTime;
		int id;
		for (int i = 0; i < eventList.size(); i++) {
			minTime = randomList.get(0).getTime();
			id = 0;
			for (int j = 0; j < randomList.size(); j++) {

				if (randomList.get(j).getTime() < minTime) {
					minTime = randomList.get(j).getTime();
					id = j;
				}
			}
			sortedList.add(randomList.get(id));
			randomList.remove(id);
		}
		return sortedList;
	}
}
