package stacks;

public class SymbolStack {

	private static final String PATTERN = "( (4 + 8 ) * ( 3 - 2 )))";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(wellBracketed());
		;
	}

	public static boolean wellBracketed() {

		LinkedStack<Character> arrayStack = new LinkedStack<Character>();
		for (int i = 0, l = PATTERN.length(); i < l; i++) {
			char c = PATTERN.charAt(i);

			if (c == '(' || c == '{' || c == '[')
				arrayStack.addLast(c);
			else if (c == ')' || c == '}' || c == ']') {
				if (arrayStack.isEmpty())
					return false;
				else if(!matched(c, arrayStack.removeLast()))
					return false;
			}
		}
		return arrayStack.isEmpty();
	}

	private static boolean matched(char c, Character removeLast) {
		switch (c) {
		case ')':
			return removeLast == '(';
		case '}':
			return removeLast == '{';
		case ']':
			return removeLast == '[';
		default:
			break;
		}
		return false;
	}

}
