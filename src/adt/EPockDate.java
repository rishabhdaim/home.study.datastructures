package adt;

/**
 * 
 * Each Date value is a past, present, or future date. This date is represented
 * by a day-in-epoch number e (0 for 1 January 2000, negative for earlier dates,
 * and positive for later dates).
 * 
 * @author ANKIT DAIM
 * 
 */
public class EPockDate implements Comparable<EPockDate> {
	private final int e;

	/**
	 * Construct a date with year y, month m, and day-in-month d. Throw an
	 * exception if they constitute an improper date.
	 * 
	 * @param y
	 *            year
	 * @param m
	 *            month
	 * @param d
	 *            day
	 */
	public EPockDate(int y, int m, int d) {
		if (m < 0 || m > 12 || d < 0 || d > length(m, y))
			throw new IllegalArgumentException("Invalid date : ");
		int e = d - 1;
		// Add lengths of months 1 through m–1 in year y,..
		for (int k = 1; k < m; k++)
			e += length(k, y);
		// Add lengths of years 2000 through y–1 . . ,
		e += 365 * (y - 2000);
		// Adjust for leap years . . .
		if (y > 2000)
			e += (y - 2001) / 4 - (y - 2001) / 100 + (y - 2001) / 400 + 1;
		else
			e += (y - 2000) / 4 - (y - 2000) / 100 + (y - 2000) / 400;

		this.e = e;
	}

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
			return (isLeapYear(y) ? 29 : 28);
		default:
			break;
		}
		return 0;
	}

	/**
	 * @param y
	 *            year
	 * @return return true only if year is a leap year
	 */
	private boolean isLeapYear(int y) {
		return (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * 
	 * 
	 * Return -1 if this date is earlier than o, or 0 if this date is equal to
	 * o, or +1 if this date is later than o.
	 */
	@Override
	public int compareTo(EPockDate o) {
		return this.e < o.e ? -1 : this.e > o.e ? 1 : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		int y = 2000, m = 1, d = this.e + 1;
		int last;

		if (d > 0) {
			while (d > (last = isLeapYear(y) ? 366 : 365)) {
				y++;
				d -= last;
			}
		} else {
			do {
				y--;
				d += (isLeapYear(y) ? 366 : 365);
			} while (d <= 0);
		}
		while (d > (last = length(m, y))) {
			m++;
			d -= last;
		}
		return (y + "-" + m + "-" + d);
	}

	/**
	 * @param n
	 * @return
	 */
	public EPockDate advance(int n) {
		int y = 2000, m = 1, d = this.e + n + 1, last;
		if (d > 0) {
			while (d > (last = isLeapYear(y) ? 366 : 365)) {
				y++;
				d -= last;
			}
		} else {
			do {
				y--;
				d += isLeapYear(y) ? 366 : 365;
			} while (d <= 0);
		}
		while (d > (last = length(m, y))) {
			m++;
			d -= last;
		}
		return new EPockDate(y, m, d);
	}
}