package hashtables;

public class TestCBHT {
	public static void main(String[] args) {

		CBHT<String, String> cbht = new CBHT<>(23);
		cbht.put("asd", "asd");
		System.out.println(cbht.getEntry("asd"));
		cbht.put("asd", "asf");
		cbht.put("aswd", "asf");
		cbht.put("aswwd", "asf");
		cbht.put("aqsd", "asf");

		System.out.println(cbht.getEntry("asd"));

		System.out.println(cbht.removeEntry("aqsd"));

		System.out.println(cbht.toString());
	}

}
