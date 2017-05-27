package sets;

public class LinkedHashSet<E> extends HashSet<E> {

	public LinkedHashSet() {
		super(16, .75f, false);
	}

	public LinkedHashSet(int initialCapacity) {
		super(initialCapacity, .75f, false);
	}

	public LinkedHashSet(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor, false);
	}

	public LinkedHashSet(int initialCapacity, float loadFactor,
			boolean accessOrder) {
		super(initialCapacity, loadFactor, accessOrder);
	}
}