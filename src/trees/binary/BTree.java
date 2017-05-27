package trees.binary;

import java.util.Iterator;

import trees.Tree;

/*
 * Each BTree object is a B-tree header.This B-tree is represented as follows: 
 * arity is the maximum number of children per node, and root is a link to its root node. 
 * Each B-tree node is represented as follows: 
 * size contains its size; a subarray elems[0. ..size-1] contains its elements;
 * and a subarray childs[0...size] contains links to its child nodes. 
 * For each element elems[i], childs[i] is a link to its left child, 
 * and childs[i+l] is a link to its right child. 
 * In a leaf node, all child links are null.
 * Moreover, for every element x in the left subtree of element y: x.compareTo(y) <0 and 
 * for every element z in the right subtree of element y: z.compareTo(y)> 0.
 */
/**
 * @author ANKIT DAIM
 * 
 * @param <E>
 */
public class BTree<E extends Comparable<? super E>> implements Tree<E> {

	/**
	 * the arity of this BTree..
	 */
	private final int arity;
	/**
	 * root node..
	 */
	private Node<E> root;

	/**
	 * size of this BTree..
	 */
	@SuppressWarnings("unused")
	private int size;

	public BTree(int arity) {
		this.arity = arity;
		root = null;
		size = 0;
	}

	@Override
	public trees.Tree.Node<E> root() {
		return root;
	}

	@Override
	public trees.Tree.Node<E> parent(trees.Tree.Node<E> node) {
		return ((Node<E>) node).parent;
	}

	@Override
	public int childCount(trees.Tree.Node<E> node) {
		return ((Node<E>) node).size + 1;
	}

	@Override
	public void makeRoot(E element) {
		root = new Node<>(this.arity, element, null, null, null);
	}

	@Override
	public trees.Tree.Node<E> addChild(trees.Tree.Node<E> node, E element) {
		return null;
	}

	@Override
	public void remove(trees.Tree.Node<E> node) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<E> children(trees.Tree.Node<E> node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> nodesPreOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> nodesPostOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	// Find which if any node of this B-tree contains an element equal to
	// target. Return a link to that node, or null if there is no such node.
	@Override
	public trees.Tree.Node<E> searchNode(E e) {
		if (root == null)
			return null;
		Node<E> curr = root;
		for (;;) {
			int pos = curr.searchInNode(e);
			if (e.equals(curr.e[pos]))
				return curr;
			else if (curr.isLeafNode())
				return null;
			else
				curr = curr.childs[pos];
		}
	}

	@Override
	public trees.Tree.Node<E> greatestNode(trees.Tree.Node<E> node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public trees.Tree.Node<E> smallestNode(trees.Tree.Node<E> node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	static class Node<E extends Comparable<? super E>> implements Tree.Node<E> {

		private int size;
		private E[] e;
		private Node<E>[] childs;
		private Node<E> parent;

		// Construct a B-tree node of arity k, initially with one element, elem,
		// and two children, left and right
		@SuppressWarnings("unchecked")
		private Node(int k, E e, Node<E> left, Node<E> right, Node<E> parent) {
			this.e = (E[]) new Comparable[k];
			this.childs = new Node[k + 1];
			this.size = 1;
			this.e[0] = e;
			this.childs[0] = left;
			this.childs[1] = right;
			this.parent = parent;
		}

		@SuppressWarnings("unchecked")
		private Node(int k, E[] elems, Node<E>[] childs, int l, int r,
				Node<E> parent) {
			// Construct a B-tree node of arity k, with its elements taken from
			// the subarray e 1 ems [ 1... r–1 ] and its children from the
			// subarray childs[l...r].
			this.e = (E[]) new Comparable[k];
			this.childs = new BTree.Node[k + 1];
			this.parent = parent;
			this.size = 0;
			for (int j = 1; j < r; j++) {
				this.e[this.size] = elems[j];
				this.childs[this.size] = childs[j];
				this.size++;
			}
			this.childs[this.size] = childs[r];
		}

		/**
		 * @return Return true if and only of this node is a leaf.
		 */
		private boolean isLeafNode() {
			return childs[0] == null;
		}

		@Override
		public E getElement() {
			return null;
		}

		@Override
		public void setElement(E elem) {
		}

		/**
		 * @param e
		 * @return Return the index of the least element in this node that is
		 *         not less than target.
		 */
		private int searchInNode(E e) {
			int i = 0;
			while (this.e[i] != null && this.e[i].compareTo(e) < 0)
				i++;
			return i;
		}
	}
}