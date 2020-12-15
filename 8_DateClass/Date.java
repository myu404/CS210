/*
 * Michael Yu, CS210
 * Bellevue College, Fall 2020
 * November 24, 2020
 * Coding Assignment 8b: Object-Oriented Programming, creating Date class
 * Java Program: Date.java
 */

public class Date {
	
	// *** Fields ***
	private int year;
	private int month;
	private int day;
	
	// *** Constructors ***
	
	// True initialization constructor
	public Date(int year, int month, int day) {
		setDate(year, month, day);
	}
	
	// Parameterless constructor that constructs default date 01/01/1970
	public Date() {
		this(1970, 1, 1);
	}
	
	// *** Instance Methods for Objects***
	
	// ** Accessor Methods
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
	
	// ** Mutator Methods
	public void setDay(int day) {
		setDate(year, month, day);
	}
	
	public void setMonth(int month) {
		setDate(year, month, day);
	}
	
	public void setYear(int year) {
		setDate(year, month, day);
	}
	
	public void setDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
		
		if(this.year < 0 || this.month < 1 || this.month > 12 || this.day < 1 || this.day > numDaysInMonth()) {
			throw new IllegalArgumentException("Illegal date entered: " + toString());
		}
	}
	// ** Printing methods
	
	// toString() method override Object default to allow for custom String for Date class
	public String toString() {
		return String.format("%d/%02d/%02d", year, month, day);
	}
	
	// longDate() method is another String method that prints out the date in long format
	public String longDate() {
		String[] monthName = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

		return monthName[this.month - 1] + " " + this.day + ", " + this.year;
	}
	
	// ** Object methods
	
	// addDays() method moves the Date object forward or backward in time by the number of days
	public void addDays(int days) {
		// Determine new Julian Day Number (JDN) by converting current Gregorian date to JDN and including the added/subtracted days
		int jdnNew = getJulianDayNumber() + days;
		
		// Obtain new Gregorian date by converting new JDN to Gregorian date
		int[] gregorianDate = getGregorianDate(jdnNew);
		
		this.year = gregorianDate[0];
		this.month = gregorianDate[1];
		this.day = gregorianDate[2];
	}
	
	// addWeeks() method moves the Date object forward or backward in time by the number of weeks
	// One week is considered to have 7 days
	public void addWeeks(int weeks) {
		addDays(weeks*7);
	}
	
	// daysTo() method counts the number of days from THIS object to OTHER object 
	public int daysTo(Date other) {
		return other.getJulianDayNumber() - this.getJulianDayNumber();
	}
	
	// getJulianDayNumber() method determines the calendar day number based on Julian day
	// https://en.wikipedia.org/wiki/Julian_day#Converting_Gregorian_calendar_date_to_Julian_Day_Number
	public int getJulianDayNumber() {
	    return (1461 * (this.year + 4800 + (this.month - 14) / 12)) / 4 + (367 * (this.month - 2 - 12 * ((this.month - 14) / 12))) / 12 - 
	    		(3 * ((this.year + 4900 + (this.month - 14) / 12) / 100)) / 4 + this.day - 32075;
	}
	
	// getGregorianDate method converts JDN to Gregorian date
	// Returns an array containing the Gregorian year, month, and day
	// Private instance method to limit method call to Date class
	// https://en.wikipedia.org/wiki/Julian_day#Converting_Gregorian_calendar_date_to_Julian_Day_Number
	private int[] getGregorianDate(int julianDayNumber) {
		// Constants used for JDN to Gregorian date conversion
		final int jdnB = 274277;
		final int jdnC = -38;
		final int jdnJ = 1401;
		final int jdnM = 2;
		final int jdnN = 12;
		final int jdnP = 1461;
		final int jdnR = 4;
		final int jdnS = 153;
		final int jdnU = 5;
		final int jdnV = 3;
		final int jdnW = 2;
		final int jdnY = 4716;
		
		int jdnF = julianDayNumber + jdnJ + (((4 * julianDayNumber + jdnB) / 146097) * 3) / 4 + jdnC;
		int jdnE = jdnR * jdnF + jdnV;
		int jdnG = jdnE % jdnP / jdnR;
		int jdnH = jdnU * jdnG + jdnW;
		
		int gregorianDay = (jdnH % jdnS) / jdnU + 1;
		int gregorianMonth = (jdnH / jdnS + jdnM) % jdnN + 1;
		int gregorianYear = jdnE / jdnP - jdnY + (jdnN + jdnM - gregorianMonth) / jdnN;
		
		int[] gregorianDate = {gregorianYear, gregorianMonth, gregorianDay};
		
		return gregorianDate;
	}

	// numDaysInMonth() method determines the number of days in the object's month. Accounts for leap years
	public int numDaysInMonth() {
		int[] numDaysMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};		
		return (this.month == 2 && isLeapYear()) ? (numDaysMonth[this.month - 1] + 1) : numDaysMonth[this.month - 1];
	}

	// isLeapYear() method checks if year is a leap year
	// Leap year occurs in a year divisible by 4, except when multiples of 100 that are not multiples of 400
	public boolean isLeapYear() {		
		return (this.year % 4 == 0 && this.year % 100 != 0) || (this.year % 400 == 0);
	}
		
	// *** Static methods for Class ***
	
	// daysTo() method (static version) is a class method that provides client code usage without creating an object. The Date class must still be called.
	// Counts the number of days between start and end Date objects. 
	public static int daysTo(Date startDate, Date endDate) {
		return startDate.daysTo(endDate);
	}
	
	/*
	****************************************START: ALTERNATIVE ALGORITHM PROVIDED FOR REFERENCE ONLY****************************************
	* The global constants and instance methods provided below provide another algorithm for the addDays() and daysTo() method.
	* Algorithm converts the Gregorian date to number of days elapsed with respect to a reference date.
	* The reference date is assumed to be 1/1/1582, which is the same year as the start of Gregorian calendar, but not the same date.
	* The start of the Gregorian calendar was on 10/15/1582.
	* The algorithm is similar to the Julian day algorithm, except for Day 0 reference point and conversion equations.	
	
	// Define class constants for adding/subtracting days from Date object
	// Assume reference year is 1582 (start of Gregorian calendar)
	public static final int REF_YEAR = 1582;
	public static final int REF_MONTH = 1;
	public static final int REF_DAY = 1;
	
	// addDays() method moves the Date object forward or backward in time by the number of days
	public void addDays(int days) {
		// Count the total number of days (including the added/subtracted days)
		int totalDays = convertDateToDaysFromRef() + days;
		
		// Below converts total number of calendar days to date format
		// Set Date object year to 1582 and increment until total calendar days is less than number of days in a year
		// Remaining total calendar days will be processed by incrementing the months
		this.year = REF_YEAR;
		int dayIndex = this.numDaysInYear();
		
		while (totalDays / dayIndex > 0) {
			totalDays -= dayIndex;
			this.setYear(++this.year);
			dayIndex = this.numDaysInYear();
		}
		
		// Set Date object month to 1 (January) and increment until total calendar days is less than number of days in a month
		// Remaining total calendar days will be set to the day of the month
		this.month = REF_MONTH;
		dayIndex = this.numDaysInMonth();
		
		while (totalDays / dayIndex > 0) {
			totalDays -= dayIndex;
			this.setMonth(++this.month);
			dayIndex = this.numDaysInMonth();
		}
		
		// Set Date object day to day of month (remaining calendar days)
		this.day = totalDays;
	}
	
	// daysTo() method counts the number of days from THIS object to OTHER object 
	public int daysTo(Date other) {
		return other.convertDateToDaysFromRef() - this.convertDateToDaysFromRef();
	}
	
	// convertDateToDaysFromRef() method determines the total number of calendar days after adding/subtracting days
	// Calendar count begins on 1/1/1582 (Day 0). Global variables are used for Day 0.
	public int convertDateToDaysFromRef() {
		int totalDays = 0;
		
		// Create a temporary Date object to dynamically add number of days
		Date tmpDate = new Date(REF_YEAR, REF_MONTH, REF_DAY);
		
		// Determine number of days from 1582 to the object's year (exclusive)
		for (int daysByYear = REF_YEAR; daysByYear < this.year; daysByYear++) {
			tmpDate.setYear(daysByYear);
			totalDays += tmpDate.numDaysInYear();
		}
		
		// Determine the number of days in the object's year from January to the object's month (exclusive)
		tmpDate.setYear(this.year);
		for (int daysByMonth = REF_MONTH; daysByMonth < this.month; daysByMonth++) {
			tmpDate.setMonth(daysByMonth);
			totalDays += tmpDate.numDaysInMonth();
		}
		
		// Add the object's day to the total day count
		totalDays += this.day;
		
		return totalDays;
	}
	
	// numDaysInYear() method determines the number of days in the object's year. Accounts for leap years
	public int numDaysInYear() {
		return isLeapYear() ? 366 : 365;
	}
	
	****************************************END: ALTERNATIVE ALGORITHM PROVIDED FOR REFERENCE ONLY****************************************
	*/ 
}
