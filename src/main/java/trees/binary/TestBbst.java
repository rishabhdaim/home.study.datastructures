package trees.binary;

public class TestBbst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AvlTree<String> balancedBinarySearchTree = new AvlTree<>();
		balancedBinarySearchTree.makeRoot("a");
		balancedBinarySearchTree.root();
		balancedBinarySearchTree.addChild("b");
		balancedBinarySearchTree.addChild("c");
		balancedBinarySearchTree.addChild("d");
		balancedBinarySearchTree.addChild("e");
		balancedBinarySearchTree.addChild("f");
		balancedBinarySearchTree.addChild("g");
		balancedBinarySearchTree.addChild("h");
		balancedBinarySearchTree.addChild("i");
		balancedBinarySearchTree.addChild("j");
		balancedBinarySearchTree.addChild("k");
		balancedBinarySearchTree.addChild("l");
		System.out.println(balancedBinarySearchTree);
		balancedBinarySearchTree.deleteChild("c");
		balancedBinarySearchTree.deleteChild("a");
		balancedBinarySearchTree.deleteChild("b");
		balancedBinarySearchTree.deleteChild("d");
		balancedBinarySearchTree.deleteChild("e");
		System.out.println(balancedBinarySearchTree);
	}

}
