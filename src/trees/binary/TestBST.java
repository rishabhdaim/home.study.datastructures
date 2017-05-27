package trees.binary;

import trees.binary.BinarySearchTree.Node;

public class TestBST {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		BinarySearchTree<String> bst = new BinarySearchTree<String>();
		Node<String> rishu = bst.insertNode("rishu");
		Node<String> banu = bst.insertNode("banu");
		Node<String> anku = bst.insertNode("anku");
		Node<String> zippi = bst.insertNode("zippi");
		Node<String> yatin = bst.insertNode("yatin");
		Node<String> deep = bst.insertNode("deep");
		Node<String> meenu = bst.insertNode("meenu");
		Node<String> munna = bst.insertNode("munna");
		Node<String> chinki = bst.insertNode("chinki");
		Node<String> nulll = bst.insertNode("chinki");

		System.out.println(rishu);
		System.out.println(chinki);
		System.out.println(deep);
		System.out.println(banu);
		System.out.println(zippi);
		System.out.println(bst);
		bst.delete("banu");
		System.out.println(bst);
		System.out.println(deep);
		System.out.println(chinki);
	}
}
