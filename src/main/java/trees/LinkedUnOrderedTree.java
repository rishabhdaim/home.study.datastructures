package trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 
 * Each LinkedUnorderedTree object is an unordered tree whose elements are
 * arbitrary objects. This tree is represented by a reference to its root node
 * (root), which is null if the tree is empty. Each tree node contains links to
 * its first child, to its parent, and to its next sibling.
 * 
 * @author ANKIT DAIM
 * 
 * @param <E>
 */
public class LinkedUnOrderedTree<E extends Comparable<? super E>> implements
		Tree<E> {

	private Node<E> root;

	/**
	 * Construct a tree, initially empty..
	 */
	public LinkedUnOrderedTree() {
		root = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#root()
	 */
	@Override
	public trees.Tree.Node<E> root() {
		return root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#parent(trees.Tree.Node)
	 */
	@Override
	public Tree.Node<E> parent(Tree.Node<E> node) {
		return ((Node<E>) node).parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#childCount(trees.Tree.Node)
	 */
	@Override
	public int childCount(Tree.Node<E> node) {
		Node<E> parent = (Node<E>) node;
		int count = 0;
		for (Node<E> child = parent.firstChild; child != null; child = child.nextSib)
			count++;
		System.out.println("the count is : " + count);
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#makeRoot(java.lang.Object)
	 */
	@Override
	public void makeRoot(E element) {
		root = new Node<>(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#addChild(trees.Tree.Node, java.lang.Object)
	 */
	@Override
	public Tree.Node<E> addChild(Tree.Node<E> node, E element) {

		Node<E> parent = (Node<E>) node;
		Node<E> newChild = new Node<>(element);
		newChild.parent = parent;
		newChild.nextSib = parent.firstChild;
		parent.firstChild = newChild;
		return newChild;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#remove(trees.Tree.Node)
	 */
	@Override
	public void remove(trees.Tree.Node<E> node) {
		if (node == root) {
			root = null;
			return;
		}
		Node<E> parent = ((Node<E>) node).parent;
		if (node == parent.firstChild)
			parent.firstChild = ((Node<E>) node).nextSib;
		else {
			Node<E> prevSib = parent.firstChild;
			while (prevSib.nextSib != node)
				prevSib = prevSib.nextSib;
			prevSib.nextSib = ((Node<E>) node).nextSib;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#searchNode(java.lang.Object)
	 */
	@Override
	public Node<E> searchNode(E e) {
		Node<E> curr = root;
		return searchNode(e, curr);
	}

	/**
	 * @param e
	 * @param curr
	 * @return first Node<E> having element equals to e or null if no Node<E>
	 *         has element equals to e..
	 */
	private Node<E> searchNode(E e, Node<E> curr) {
		if (curr != null) {
			if (curr.element.equals(e))
				return curr;
			searchNode(e, curr.firstChild);
			searchNode(e, curr.nextSib);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#nodesPreOrder()
	 */
	@Override
	public Iterator<E> nodesPreOrder() {
		return new PreOrderItr();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#nodesPostOrder()
	 */
	@Override
	public Iterator<E> nodesPostOrder() {
		return new PostOrderItr();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#children(trees.Tree.Node)
	 */
	@Override
	public Iterator<E> children(trees.Tree.Node<E> node) {
		return new PostOrderItr();
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private abstract class Itr<T> implements Iterator<T> {

		int cursor;
		private int lasfRef = -1;
		int length;
		List<Node<E>> elements = new ArrayList<>();
		Object[] es;

		@Override
		public boolean hasNext() {
			return cursor != length;
		}

		@SuppressWarnings("unchecked")
		public Node<E> nextEntry() {
			if (cursor >= length)
				throw new NoSuchElementException();
			lasfRef = cursor;
			return (Node<E>) es[cursor++];
		}

		@SuppressWarnings("unchecked")
		@Override
		public void remove() {
			if (lasfRef == -1)
				throw new NoSuchElementException();
			LinkedUnOrderedTree.this
					.remove(new Node<>(((Node<E>) es[lasfRef])).element);
			cursor--;
			lasfRef = -1;
			length--;
		}
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private class PostOrderItr extends Itr<E> {
		@SuppressWarnings("unchecked")
		public PostOrderItr() {
			elements = getNodesPostOrder(root, elements);
			es = (E[]) elements.toArray();
			length = es.length;
			cursor = 0;
		}

		@Override
		public E next() {
			return nextEntry().element;
		}
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private class PreOrderItr extends Itr<E> {
		@SuppressWarnings("unchecked")
		public PreOrderItr() {
			elements = getNodesPreOrder(root, elements);
			es = (E[]) elements.toArray();
			length = es.length;
			cursor = 0;
		}

		@Override
		public E next() {
			return nextEntry().element;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		E rootE = root == null ? null : root.element;
		List<E> list = new ArrayList<>();
		list = getElements(root, list);
		return "LinkedUnOrderedTree [root=" + rootE + ", Elements="
				+ list.toString() + "]";
	}

	/**
	 * @param node
	 * @param list
	 * @return
	 */
	private List<E> getElements(Node<E> node, List<E> list) {
		return getElementsPostOrder(node, list);
	}

	private List<E> getElementsPostOrder(Node<E> node, List<E> list) {
		if (node != null) {
			getElementsPostOrder(node.firstChild, list);
			getElementsPostOrder(node.nextSib, list);
			list.add(node.element);
		}
		return list;
	}

	/**
	 * @param node
	 * @param list
	 * @return
	 */
	private List<Node<E>> getNodesPostOrder(Node<E> node, List<Node<E>> list) {
		if (node != null) {
			getNodesPostOrder(node.firstChild, list);
			getNodesPostOrder(node.nextSib, list);
			list.add(node);
		}
		return list;
	}

	/**
	 * @param node
	 * @param list
	 * @return
	 */
	private List<Node<E>> getNodesPreOrder(Node<E> node, List<Node<E>> list) {
		if (node != null) {
			list.add(node);
			getNodesPreOrder(node.firstChild, list);
			getNodesPreOrder(node.nextSib, list);
		}
		return list;
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
		List<E> elements = new ArrayList<>();
		elements = getElements(root, elements);
		for (E e : elements)
			result = prime * result + ((e == null) ? 0 : e.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
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
		LinkedUnOrderedTree<?> other = (LinkedUnOrderedTree<?>) obj;
		if (root == null) {
			if (other.root != null) {
				return false;
			}
		} else if (!root.equals(other.root)) {
			return false;
		}
		List<E> elements = new ArrayList<>();
		List<E> otherElements = new ArrayList<>();
		elements = getElements(root, elements);
		otherElements = getElements(((Node<E>) other.root), otherElements);

		if (!elements.equals(otherElements))
			return false;

		return true;
	}

	/**
	 * to prints all node before their children..
	 */
	public void preOrderTraversal() {
		System.out.println();
		preOrderTraversal(root);
	}

	/**
	 * @param node
	 */
	private void preOrderTraversal(Tree.Node<E> node) {
		if (node != null) {
			System.out.print(((Node<E>) node).element + " ");
			preOrderTraversal(((Node<E>) node).firstChild);
			preOrderTraversal(((Node<E>) node).nextSib);
		}
	}

	/**
	 * to prints all node before their children..
	 */
	public void postOrderTraversal() {
		System.out.println();
		postOrderTraversal(root);
	}

	/**
	 * @param node
	 */
	private void postOrderTraversal(Tree.Node<E> node) {
		if (node != null) {
			postOrderTraversal(((Node<E>) node).firstChild);
			postOrderTraversal(((Node<E>) node).nextSib);
			System.err.print(((Node<E>) node).element + " ");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * 
	 * Each LinkedUnorderedTree . Node object is a node of an unordered tree,
	 * and contains a single element. This tree node consists of an element
	 * (element), a link to its first child (firstChild) a link to its parent
	 * (parent), and a link to its next sibling (nextSib).
	 * 
	 * @author ANKIT DAIM
	 * 
	 * @param <E>
	 */
	private static class Node<E> implements Tree.Node<E> {

		private E element;
		private Node<E> firstChild;
		private Node<E> parent;
		private Node<E> nextSib;

		/**
		 * Construct a tree node, containing element E, that has no children, no
		 * parent, and no siblings.
		 * 
		 * @param element
		 */
		private Node(E element) {
			this.element = element;
			this.firstChild = null;
			this.nextSib = null;
			this.parent = null;
		}

		@Override
		public E getElement() {
			return this.element;
		}

		@Override
		public void setElement(E elem) {
			this.element = elem;
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
			result = prime * result
					+ ((element == null) ? 0 : element.hashCode());
			result = prime
					* result
					+ ((firstChild == null) ? 0 : firstChild.element.hashCode());
			result = prime * result
					+ ((nextSib == null) ? 0 : nextSib.element.hashCode());
			result = prime * result
					+ ((parent == null) ? 0 : parent.element.hashCode());
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
			Node<?> other = (Node<?>) obj;
			if (element == null) {
				if (other.element != null) {
					return false;
				}
			} else if (!element.equals(other.element)) {
				return false;
			}
			if (firstChild == null) {
				if (other.firstChild != null) {
					return false;
				}
			} else if (!firstChild.element.equals(other.firstChild.element)) {
				return false;
			}
			if (nextSib == null) {
				if (other.nextSib != null) {
					return false;
				}
			} else if (!nextSib.element.equals(other.nextSib.element)) {
				return false;
			}
			if (parent == null) {
				if (other.parent != null) {
					return false;
				}
			} else if (!parent.element.equals(other.parent.element)) {
				return false;
			}
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			E firstE = firstChild == null ? null : firstChild.element;
			E parentE = parent == null ? null : parent.element;
			E nextSibE = nextSib == null ? null : nextSib.element;
			return "Node [element=" + element + ", firstChild=" + firstE
					+ ", parent=" + parentE + ", nextSib=" + nextSibE + "]";
		}
	}

	/**
	 * @return
	 */
	private Iterator<Node<E>> entryPostOrder() {
		return new EntryItr();
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private final class EntryItr extends Itr<Node<E>> {

		public EntryItr() {
			elements = getNodesPreOrder(root, elements);
			es = elements.toArray();
			length = es.length;
			cursor = 0;
		}

		@Override
		public Node<E> next() {
			return nextEntry();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#greatestNode(trees.Tree.Node)
	 */
	@Override
	public trees.Tree.Node<E> greatestNode(trees.Tree.Node<E> node) {
		Node<E> greatestNode = (Node<E>) node;
		Node<E> temp = null;
		Iterator<Node<E>> iterator = entryPostOrder();
		while (iterator.hasNext())
			if ((temp = iterator.next()).element
					.compareTo(greatestNode.element) > 0)
				greatestNode = temp;
		return greatestNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#smallestNode(trees.Tree.Node)
	 */
	@Override
	public trees.Tree.Node<E> smallestNode(trees.Tree.Node<E> node) {
		Node<E> smallestNode = (Node<E>) node;
		Node<E> temp = null;
		Iterator<Node<E>> iterator = entryPostOrder();
		while (iterator.hasNext())
			if ((temp = iterator.next()).element
					.compareTo(smallestNode.element) < 0)
				smallestNode = temp;
		return smallestNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#size()
	 */
	@Override
	public int size() {
		Iterator<E> iterator = nodesPostOrder();
		int size = 0;
		while (iterator.hasNext()) {
			iterator.next();
			size++;
		}
		return size;
	}
}