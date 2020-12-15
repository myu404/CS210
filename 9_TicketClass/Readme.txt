The Ticket program is written in Java 14.

This program defines a Ticket class to handle campus events. Ticket class is the super-class for three other types of tickets: walk-up tickets, advance tickets, and student advance tickets.

The walk-up tickets and advance tickets are sub-classes of Ticket class. The student advance tickets is sub-class of advance tickets.

**
Problem statement from Building Java Programs, 5th Edition, Stuart Reges, Marty Stepp:

No actual objects of type Ticket will be created (abstract class)

Ticket price is defined in each of the sub-class. The ticket price field is protected (accessible for all sub-classes) and defined in the Ticket class

Walk-up tickets are purchased the day of the event and cost $50.

Advance tickets purchased 10 or more days before the event cost $30, and advance tickets purchased fewer than 10 days before the event cost $40.

Student advance tickets are sold at half the price of normal advance tickets: When they are purchased 10 or more days early they cost $15, and when they are purchased fewer than 10 days early they cost $20.

For AdvanceTicket class, an exception is thrown if advanced ticket is purchased 0 days in advanced. StudentAdvanceTicket class exhibit the same behavior due to inhiertance.

Additional design constraint is that the ticket number field is private to the Ticket class (parent class). All sub-class has a number accessor method (getNumber()) that extracts the ticket number value through the inheirited toString() method and string manipulation. 
**

This program implements object-oriented programming concepts, inheiritance, and polymorphism.

This is an academic assignment. 

Student generated code: WalkupTicket.java, AdvanceTicket.java, StudentAdvanceTicket.java

Instructor generated code: Ticket.java

Client/Driver code (provided by instructor): TicketTester.java
