/**
 * Name: W.P. Iverson
 * Date: November 2020
 * Course: CS210
 * Assignment: #9a
 * Ticket Exercises from text
 */

// DO NOT Change this code, it will be used as base for all grading
public abstract class Ticket {
	//INSTANCE VARIABLES
	private int number;  // The ticket number defined by constructor
	protected double price;  // Price determined only in sub-class
	
	// STATIC FIELDS: Only one for the whole Class
	private static int numSold = 0;
	public static int maxSales = 1000;
	private static boolean[] sold = new boolean[maxSales];  
	// Tells us if that index (number) is sold	
	
	// CONSTRUCTORS
	public Ticket(int value) {
		Ticket.numSold++;	
		while (sold[value]) {
			value++;  // find next available unique ticket number
		}
		sold[value] = true;
		number = value;  // Ticket constructor sets number
	}
	
	// ALWAYS use existing constructors as much as possible
	public Ticket() {
		this(0);
	}
	
	// ACCESSORS
	public double getPrice() {
		return price;  // set by sub-class constructors
	}
	
	// JAVA standard toString
    public String toString() {
        return "Number: " + this.number + ", Price: " + this.getPrice();
    }
}
