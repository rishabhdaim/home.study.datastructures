package adt;

import java.util.Arrays;

/**
 * Each String value is an immutable string of characters, of any length, with
 * consecutive indices starting at 0.
 * 
 * @author ANKIT DAIM
 * 
 */
public class StringADT implements Comparable<StringADT> {

	private final char[] value;
	private final int length;

	/**
	 * @param value
	 */
	public StringADT(char[] value) {
		super();
		if (value == null)
			throw new NullPointerException("value cannot be null..");
		this.value = value;
		this.length = value.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StringADT [value=" + Arrays.toString(value) + ", length="
				+ length + "]";
	}

	/**
	 * @return length of this StringADT..
	 */
	public int length() {
		return this.length;
	}

	/**
	 * @param index
	 *            index of the character
	 * @return char at index
	 */
	public char charAt(int index) {
		if (index < 0 || index >= this.length)
			throw new IndexOutOfBoundsException(
					"index out of bounds : index : " + index + ", length : "
							+ length);
		return value[index];
	}

	/**
	 * @param index
	 * @param endIndex
	 * @return
	 */
	public StringADT subString(int index, int endIndex) {
		if (index < 0 || endIndex > length || endIndex < index)
			throw new IndexOutOfBoundsException("index out of bounds..");

		char[] subArr = new char[endIndex - index + 1];
		for (int j = 0, i = index, l = subArr.length; j < l; i++, j++)
			subArr[j] = value[i];
		return new StringADT(subArr);

	}

	/**
	 * @param stringADT
	 * @return
	 */
	public StringADT concat(StringADT stringADT) {
		char[] newArr = new char[stringADT.length + length];
		for (int i = 0; i < length; i++)
			newArr[i] = value[i];
		for (int i = length, l = newArr.length; i < l; i++)
			newArr[i] = stringADT.value[i - length];
		return new StringADT(newArr);
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
		result = prime * result + length;
		result = prime * result + Arrays.hashCode(value);
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
		StringADT other = (StringADT) obj;
		if (length != other.length) {
			return false;
		}
		if (!Arrays.equals(value, other.value)) {
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
	public int compareTo(StringADT o) {
		return 0;
	}

}
