package adt;

public class Person implements Comparable<Person> {
	// Each Person value consists of a person's surname, forename, gender, and
	// year of birth.

	private final String firstName;
	private final String surName;
	private final int yearOfBirth;
	private final boolean female;

	/**
	 * @param firstName
	 * @param surName
	 * @param yearOfBirth
	 * @param female
	 */
	public Person(String firstName, String surName, int yearOfBirth,
			boolean female) {
		super();
		this.firstName = firstName;
		this.surName = surName;
		this.yearOfBirth = yearOfBirth;
		this.female = female;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", surName=" + surName
				+ ", yearOfBirth=" + yearOfBirth + ", female=" + female + "]";
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the surName
	 */
	public String getSurName() {
		return surName;
	}

	/**
	 * @return the yearOfBirth
	 */
	public int getYearOfBirth() {
		return yearOfBirth;
	}

	/**
	 * @return the female
	 */
	public boolean isFemale() {
		return female;
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
		result = prime * result + (female ? 1231 : 1237);
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((surName == null) ? 0 : surName.hashCode());
		result = prime * result + yearOfBirth;
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
		Person other = (Person) obj;
		if (female != other.female) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (surName == null) {
			if (other.surName != null) {
				return false;
			}
		} else if (!surName.equals(other.surName)) {
			return false;
		}
		if (yearOfBirth != other.yearOfBirth) {
			return false;
		}
		return true;
	}

	/*
	 * This method is used to compare two instances of Person..
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Person o) {

		int i = this.firstName.compareTo(o.firstName);

		if (0 == i)
			i = this.surName.compareTo(o.surName);
		if (0 == i)
			i = Integer.valueOf(this.yearOfBirth).compareTo(o.yearOfBirth);
		if (0 == i)
			i = Boolean.valueOf(this.female).compareTo(o.female);
		return i;
	}

	// Instance methods..

	/**
	 * @param person
	 * @param newName
	 * @return
	 */
	public Person changeName(String newName) {

		return new Person(firstName, newName, yearOfBirth, female);
	}
}