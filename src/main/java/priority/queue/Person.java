package priority.queue;

public class Person implements Comparable<Person> {

	private final String firstName;
	private final String lastName;
	private final boolean gender;
	private final int yearOfBirth;

	/**
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param yearOfBirth
	 */
	public Person(String firstName, String lastName, boolean gender,
			int yearOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.yearOfBirth = yearOfBirth;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the gender
	 */
	public boolean isGender() {
		return gender;
	}

	/**
	 * @return the yearOfBirth
	 */
	public int getYearOfBirth() {
		return yearOfBirth;
	}

	@Override
	public int compareTo(Person o) {
		return this.yearOfBirth - o.yearOfBirth;
	}

	/**
	 * cache the hashcode of this class..
	 */
	private int hash;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (0 == hash) {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + (gender ? 1231 : 1237);
			result = prime * result
					+ ((lastName == null) ? 0 : lastName.hashCode());
			result = prime * result + yearOfBirth;
			hash = result;
		}
		return hash;
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
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (gender != other.gender) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (yearOfBirth != other.yearOfBirth) {
			return false;
		}
		return true;
	}
}
