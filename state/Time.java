package state;

import random.ExponentialRandomStream;
import random.UniformRandomStream;
/**
 * 
 * @author Elsa Jonsson, Pawel Dzialo, Måns Oskarsson, Jacob Sjöström
 * The class calculates and creates the different times to different events. 
 *
 */
public class Time {
	private double time;
	private double lambda;
	private double kMax;
	private double kMin;
	private double pMax;
	private double pMin;
	private ExponentialRandomStream expRand;
	private UniformRandomStream uniGather;
	private UniformRandomStream uniPay;
	private long seed;
	/**
	 * 
	 * @param givenLambda
	 * @param givenKMin
	 * @param givenKMax
	 * @param givenPMin
	 * @param givenPMax
	 * @param givenSeed
	 * The constructor which creates the time object.
	 * 
	 */
	public Time(double givenLambda,double givenKMin,double givenKMax,double givenPMin,double givenPMax, long givenSeed){
		this.time = 0;
		this.lambda = givenLambda;
		this.kMin = givenKMin;
		this.kMax = givenKMax;
		this.pMin = givenPMin;
		this.pMax = givenPMax;
		this.seed = givenSeed;
		this.expRand = new ExponentialRandomStream(this.lambda,this.seed);
		this.uniPay = new UniformRandomStream(this.kMin,this.kMax,this.seed);
		this.uniGather = new UniformRandomStream(this.pMin, this.pMax, this.seed);
		
	}
	/**
	 * 
	 * @return the calculated (ramdomized) arrivaltime
	 * 
	 */
	public double arrivalTime(){
		return this.time + this.expRand.next();	
	}
	/**
	 * 
	 * @return the calculated (randomized) gathertime.
	 * 
	 */
	public double gatherTime(double givenTime){
		return givenTime + this.uniGather.next();
	}
	/**
	 * 
	 * @return the calculated (randomized) paytime
	 * 
	 */
	public double payTime(double givenTime){
		return givenTime + this.uniPay.next();
	}
	/**
	 * 
	 * @return the time
	 */
	public double getTime(){
		return this.time;
	}
	/**
	 * 
	 * @param givenTime
	 * Changes the current time to the param giventime
	 */
	public void setTime(double givenTime){
		this.time = givenTime;
	}
	
}
