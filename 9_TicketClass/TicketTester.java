// CS210 BC, W.P. Iverson
// modified November 2020
public class TicketTester {
// testing code for Ticket Classes
// BJP text Exercises, Chapter 9
	public static void main(String[] args) {
		Ticket[] scam = new Ticket[15]; // an array of Tickets that don't exist
		for (Ticket tick: scam) {
			tick = new WalkupTicket(); // actually calls a constructor for each
		}
 
		AdvanceTicket one = new AdvanceTicket(10, 20); // constructor overloaded
		// Page 661: Above is ticket number 10, purchased 20 days in advance
		//one.setNumber(17); // not allowed, no such method, keeps # unique
		System.out.println(one); // with above, Number: 15, Price: 30.0
		try {
			System.out.println(new AdvanceTicket()); // calls this(0,0);
			System.out.println("error, cannot be 0 advance");
		} catch (IllegalArgumentException oops) {
			System.out.println("correct coding error thrown");
		}		
			
		WalkupTicket two = new WalkupTicket(2); // #2 attempted, but not allowed
		System.out.println(two); // output should be Number: 17, Price: 50.0
		try {
			System.out.println(new WalkupTicket()); // calls this(0);
			System.out.println("error, cannot be 0 advance");
		} catch (IllegalArgumentException oops) {
			System.out.println("correct coding error thrown");
		}
 
		StudentAdvanceTicket three = new StudentAdvanceTicket(99,9); // default number 
		System.out.println(three); // Number: 99, (use toString from Ticket) add (ID Required)
		try {
			System.out.println(new StudentAdvanceTicket()); // must call this(0,0);
			System.out.println("error, cannot be 0 advance");
		} catch (IllegalArgumentException oops) {
			System.out.println("correct coding error thrown");
		}
 
		AdvanceTicket last = new AdvanceTicket(99, 1); // 99 already used?
		System.out.println(last); // with above, Number: 100, Price: 40.0
		System.out.println(last.getNumber()); // with above, Number: 100, Price: 40.0
		
		WalkupTicket last2 = new WalkupTicket(99); // 99 already used?
		System.out.println(last2); // with above, Number: 100, Price: 40.0
		System.out.println(last2.getNumber()); // with above, Number: 100, Price: 40.0
		
		StudentAdvanceTicket last3 = new StudentAdvanceTicket(99, 50); // 99 already used?
		System.out.println(last3); // with above, Number: 100, Price: 40.0
		System.out.println(last3.getNumber()); // with above, Number: 100, Price: 40.0
	}
}
