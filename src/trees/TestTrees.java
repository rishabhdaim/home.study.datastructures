package trees;

import trees.Tree.Node;

public class TestTrees {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// LinkedUnOrderedTree<String> unOrderedTree = new
		// LinkedUnOrderedTree<>();
		// LinkedOrderedTree<String> unOrderedTree = new LinkedOrderedTree<>();
		LinkedSortedTree<String> unOrderedTree = new LinkedSortedTree<>();
		unOrderedTree.makeRoot("0");
		Node<String> root = unOrderedTree.root();
		Node<String> first = unOrderedTree.addChild(root, "1");
		Node<String> second = unOrderedTree.addChild(root, "2");
		Node<String> third = unOrderedTree.addChild(root, "3");
		Node<String> fourth = unOrderedTree.addChild(root, "4");
		Node<String> fifth = unOrderedTree.addChild(root, "5");
		Node<String> sixth = unOrderedTree.addChild(root, "6");
		Node<String> seventh = unOrderedTree.addChild(root, "7");
		Node<String> eighth = unOrderedTree.addChild(root, "8");
		Node<String> ten = unOrderedTree.addChild(first, "10");
		Node<String> eleven = unOrderedTree.addChild(first, "19");
		Node<String> eighteen = unOrderedTree.addChild(first, "18");
		Node<String> twelve = unOrderedTree.addChild(first, "12");
		Node<String> sixteen = unOrderedTree.addChild(first, "16");
		System.out.println(twelve);
		System.out.println(first);
		//unOrderedTree.remove(sixteen);
		System.out.println(twelve);
		unOrderedTree.addChild(first, "91");
		unOrderedTree.addChild(first, "13");
		unOrderedTree.addChild(first, "14");
		unOrderedTree.addChild(first, "15");
		unOrderedTree.addChild(first, "16");
		unOrderedTree.addChild(ten, "100");
		unOrderedTree.addChild(eighth, "80");
		Node<String> eightyOne = unOrderedTree.addChild(eighth, "81");
		Node<String> eightyTwo = unOrderedTree.addChild(eighth, "82");
		unOrderedTree.addChild(eightyTwo, "820");
		unOrderedTree.addChild(eighth, "83");
		unOrderedTree.addChild(eighth, "84");
		unOrderedTree.addChild(eightyOne, "850");
		unOrderedTree.addChild(eleven, "90");

		// Iterator<String> iterator = unOrderedTree.children(root);
		// while (iterator.hasNext())
		// System.out.print(iterator.next() + " ");
		System.out.println(unOrderedTree.greatestNode(root));
		System.out.println(unOrderedTree.size());
		// unOrderedTree.preOrderTraversal();
		// unOrderedTree.postOrderTraversal();
		// unOrderedTree.depthOrderTraversal();
	}

}
