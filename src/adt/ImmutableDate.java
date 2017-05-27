package adt;

/**
 * This class is immutable..
 * 
 * @author ANKIT DAIM
 * 
 */
public class ImmutableDate implements Comparable<ImmutableDate> {
	// Each Date value is a past, present, or future date.
	// This date is represented by a year number y, a month number m
	// (1 < m < 12), and a day-in-month number d (1 < d < month length < 31),

	private final int y;
	private final int m;
	private final int d;

	/**
	 * Construct a date with year y, month m, and day-in-month d.Throw an
	 * exception if they constitute an improper date.
	 * 
	 * @param y
	 * @param m
	 * @param d
	 */
	public ImmutableDate(int y, int m, int d) {
		super();
		if (m < 1 || m > 12 || d < 1 || d > length(m, y))
			throw new IllegalArgumentException("Improper date : day is: " + d
					+ ", month is: " + m + ", year is: " + y);
		this.y = y;
		this.m = m;
		this.d = d;
	}

	/**
	 * 
	 * this method is used to find possible no. of days in a particular month of
	 * a particular year..
	 * 
	 * @param m
	 *            month whose max no. of days are to be found
	 * @param y
	 *            year in which m lies..
	 * @return the number of days in month m in year y.
	 */
	private int length(int m, int y) {
		switch (m) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			return (isLeap(y) ? 29 : 28);
		}
		return 0;
	}

	/**
	 * @param y
	 *            year which is to checked whether leap or not..
	 * @return Return true if and only if y is a leap year.
	 */
	private boolean isLeap(int y) {
		return (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ImmutableDate [y=" + y + ", m=" + m + ", d=" + d + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + d;
		result = prime * result + m;
		result = prime * result + y;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ImmutableDate other = (ImmutableDate) obj;
		if (d != other.d) {
			return false;
		}
		if (m != other.m) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ImmutableDate o) {
		int i = Integer.valueOf(this.y).compareTo(o.y);
		if (0 == i)
			i = Integer.valueOf(this.m).compareTo(o.m);
		if (0 == i)
			i = Integer.valueOf(this.d).compareTo(o.d);
		return i;
	}

	// Date operations

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the m
	 */
	public int getM() {
		return m;
	}

	/**
	 * @return the d
	 */
	public int getD() {
		return d;
	}

	/**
	 * this method is used to increment given date by noOfDays..
	 * 
	 * @param noOfDays
	 *            no. of days by which this date is to be move ahead..
	 * @return new date with noOfDays ahead of previous..
	 */
	public ImmutableDate advance(int noOfDays) {
		int currDate = d + noOfDays;
		int currMonth = m;
		int currYear = y;
		int last = 0;

		while (currDate > (last = length(currMonth, currYear))) {
			currDate -= last;
			if (currMonth < 12)
				currMonth++;
			else {
				currMonth = 1;
				currYear++;
			}
		}
		return new ImmutableDate(currYear, currMonth, currDate);
	}
}
