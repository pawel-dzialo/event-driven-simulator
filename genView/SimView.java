package genView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import state.State;
/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström
 * The class if the general view of a simulation.
 *
 */
public abstract class SimView implements Observer {
	protected State viewState;
	/**
	 * 
	 * @param state
	 * The constructor, takes a state and adds itself as an observer to it. 
	 * 
	 */
	public SimView(State state) {
		this.viewState = state;
		state.addObserver(this);
	}
	/**
	 * the update method which is empty. 
	 */
	public void update() {
		
	}
	
}
