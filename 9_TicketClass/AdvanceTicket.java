/*
 * Michael Yu, CS210
 * Bellevue College, Fall 2020
 * December 3, 2020
 * Coding Assignment 9a: Object-Oriented Programming and Inheritance, Ticket class and sub-classes
 * Java Program: AdvanceTicket.java
 */

public class AdvanceTicket extends Ticket {
	
	// *** Fields ***
	private int days;

	//*** Constructors ***
	// True initialization constructor
	public AdvanceTicket(int value, int days) {
		super(value);
		if(days <= 0) {
			throw new IllegalArgumentException("ERROR: Number of days must be greater than 0");
		}
		this.days = days;
		this.price = (days >= 10) ? 30.0 : 40.0;
	}

	// Parameter-less constructor
	public AdvanceTicket() {
		this(0, 0);
	}
	
	// *** Accessor Methods ***
	// getNumber() method is an accessor that retrieves the ticket number
	public int getNumber() {
		String ticketInfo = toString();
		
		// String format is the following: "Number: " + this.number + ", Price: " + this.getPrice()
		// Ticket number is extracted by finding the index of the first " " and ","
		int startIndex = ticketInfo.indexOf(" ");
		int endIndex = ticketInfo.indexOf(",");
		
		// The +1 is required to start the string on a numeric digit
		String ticketNum = ticketInfo.substring(startIndex + 1, endIndex);
		return Integer.parseInt(ticketNum);
	}

}
