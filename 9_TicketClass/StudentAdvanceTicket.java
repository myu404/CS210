/*
 * Michael Yu, CS210
 * Bellevue College, Fall 2020
 * December 3, 2020
 * Coding Assignment 9a: Object-Oriented Programming and Inheritance, Ticket class and sub-classes
 * Java Program: StudentAdvanceTicket.java
 */

public class StudentAdvanceTicket extends AdvanceTicket {
	
	//*** Constructors ***
	// True initialization constructor
	public StudentAdvanceTicket(int value, int days) {
		super(value, days);
		this.price /= 2;
	}

	// Parameter-less constructor
	public StudentAdvanceTicket() {
		this(0, 0);
	}
	
	//*** Instance Methods ***
	public String toString() {
		return super.toString() + " (ID required)";
	}

}
