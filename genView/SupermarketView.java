
package genView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Observable;

import state.Customer;
import state.State;
import events.Event;
import events.StartEvent;
import events.StopEvent;

/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström 
 * 			The class prints out in the console the current State of the supermarket
 *         through the simulation and its different events.
 *
 */
public class SupermarketView extends SimView {
	/**
	 * 
	 * @param state
	 *            The constructor, takes a state which it becomes an observer of
	 * 
	 */
	public SupermarketView(State state) {
		super(state);
		startOutput(viewState);
	}

	/**
	 * 
	 * @param value
	 * @return the param value but rounded off to 2 decimals
	 */
	public static double round(double value) {
		if (2 < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * 
	 * @param state
	 *            The start section of the output
	 * 
	 */
	public void startOutput(State state) {
		System.out.println("PARAMETRAR");
		System.out.println("==========");
		System.out.println("Antal kassor, N..........: " + state.getRegisterNum());
		System.out.println("Max som ryms, M..........: " + state.getMaxCustomerAmount());
		System.out.println("Ankomshastighet, lambda..: " + state.getLambda());
		System.out.println("Plocktider, [P_min..Pmax]: [" + state.getPMin() + ".." + state.getPMax() + "]");
		System.out.println("Betaltider, [K_min..Kmax]: [" + state.getKMin() + ".." + state.getKMax() + "]");
		System.out.println("Frö, f...................: " + state.getSeed());
		System.out.println();
		System.out.println("FÖRLOPP");
		System.out.println("==========");
		System.out.println(
				"Time       Event         ID      REG     IDLE      QT     CA      TC      PC      MC      NQ     QL     [Kassakö..]");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------");
	}

	// Prints out the content of an ArrayList of Strings
	private void output(ArrayList<String> output) {
		for (String k : output) {
			System.out.print(k + "  ");
		}
		System.out.println("");

	}

	/**
	 * 
	 * @param state
	 *            The output which is to be printed out at the end of the
	 *            simulation.
	 * 
	 */
	public void endOutput(State state) {
		System.out.println();
		System.out.println("RESULTAT");
		System.out.println("==========");
		System.out.println();
		System.out.println("1) Av " + state.getTotalCustomers() + " kunder handlade "
				+ (state.getTotalCustomers() - state.getMissedCustomers()) + " medan " + state.getMissedCustomers()
				+ " missades.");
		System.out.println();
		System.out.println("2) Total tid " + state.getRegisterNum() + " kassor varit lediga: "
				+ round(state.getTimeRegisters()) + " te.");
		System.out.println("   Genomsnittlig ledig kassatid: "
				+ (round(state.getTimeRegisters() / state.getRegisterNum())) + " te " + "(dvs "
				+ round(((state.getTimeRegisters() / state.getRegisterNum()) / state.getLastPayevent()) * 100)
				+ "% av tiden från öppning tills sista kunden betalat).");
		System.out.println();
		System.out.println("3) Total tid " + state.getQueueAmount() + " kunder tvingats köa: "
				+ round(state.getTimeCustomerQueue()) + " te.");
		System.out.println("   Genomsnittlig kötid: "
				+ (round(state.getTimeCustomerQueue() / state.getQueueAmount())) + " te.");
	}

	/**s
	 * 
	 * @param event
	 *            Creates the output for a state containing the vital information.
	 * 
	 */
	public void getString(Event event) {
		String id = "--";
		if (event.getCustomer() != null) {
			id = Integer.toString(event.getCustomer().getId());
		}
		String time = Double.toString(round(viewState.getCurrentEvent().getTime()));
		String name = event.name();
		String payedCust = Integer.toString(viewState.getPayedCustom());
		String timeRegFree = Double.toString(round(viewState.getTimeRegisters()));
		String queueTime = Double.toString(round(viewState.getTimeCustomerQueue()));
		String numCustomers = Integer.toString(viewState.getCurrentCustomerCount());
		String numCustomersPast = Integer.toString(viewState.getCustomersCount());
		String missedCustomers = Integer.toString(viewState.getMissedCustomers());
		String numQueue = Integer.toString(viewState.getQueueAmount());
		String lenQueue = Integer.toString(this.viewState.getQueue().size());
		String idQueue = "{";
		String registers = Integer.toString(this.viewState.getOpenRegisters());
		for (Customer k : viewState.getQueue().getQueue()) {
			idQueue += Integer.toString(k.getId()) + ", ";
		}
		idQueue += "}";
		ArrayList<String> output = new ArrayList<String>();
		output.add(time);
		output.add("\t");
		output.add(name);
		output.add("\t");
		output.add(id);
		output.add("\t");
		output.add(registers);
		output.add("\t");
		output.add(timeRegFree + " \t");
		output.add(queueTime + "\t");
		output.add(numCustomers);
		output.add("\t");
		output.add(numCustomersPast);
		output.add("\t");
		output.add(payedCust);
		output.add("\t");
		output.add(missedCustomers);
		output.add("\t");
		output.add(numQueue);
		output.add("\t");
		output.add(lenQueue);
		output.add("\t");
		output.add(idQueue);
		output.add("\t");
		output(output);
	}
	public void shortOutput(Event event) {
		String name = event.name();
		String time = Double.toString(round(viewState.getCurrentEvent().getTime()));
		ArrayList<String> output = new ArrayList<String>();
		output.add(time);
		output.add("\t");
		output.add(name);
		output(output);
		
	}

	/**
	 * The update method which is called and executed whenever the state changes.
	 */
	public void update(Observable arg0, Object arg1) {
		if (viewState.getCurrentEvent() instanceof StopEvent) {
			getString(viewState.getCurrentEvent());
			endOutput(viewState);
		} else if (viewState.getCurrentEvent() instanceof StartEvent) {
			shortOutput(viewState.getCurrentEvent());
		} else {
			if(viewState.getCurrentEvent() != null)  {
				getString(viewState.getCurrentEvent());
			}
		}

	}

}
