package hashtables;

public class TestOBHT {
	public static void main(String[] args) {
		OBHT<String, String> obht = new OBHT<>();
		obht.put("asd", "asd");
		System.out.println(obht.getEntry("asd"));
		obht.put("asd", "asf");
		obht.put("aswd", "asf");
		obht.put("aswwd", "asf");
		obht.put("aqsd", "asf");

		System.out.println(obht.getEntry("asd"));

		System.out.println(obht.removeEntry("aqsd"));

		System.out.println(obht.toString());
	}
}
