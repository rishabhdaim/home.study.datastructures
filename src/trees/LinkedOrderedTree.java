package trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedOrderedTree<E extends Comparable<? super E>> implements
		Tree<E> {

	private Node<E> root;

	/**
	 * creates empty {@link LinkedOrderedTree}..
	 */
	public LinkedOrderedTree() {
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
	public trees.Tree.Node<E> parent(trees.Tree.Node<E> node) {
		return ((Node<E>) node).parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#childCount(trees.Tree.Node)
	 */
	@Override
	public int childCount(trees.Tree.Node<E> node) {
		Node<E> parent = (Node<E>) node;
		int count = 0;
		for (Node<E> child = parent.firstChild; child != null; child = child.nextSib)
			count++;
		System.out.println("the child count is : " + count);
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
	public trees.Tree.Node<E> addChild(trees.Tree.Node<E> node, E element) {
		Node<E> parent = (Node<E>) node;
		Node<E> newNode = new Node<>(element);
		newNode.parent = parent;
		if (parent.lastChild == null)
			addFirstNode(parent, newNode);
		else
			addLastChild(parent, newNode);
		return newNode;
	}

	/**
	 * @param parent
	 * @param newNode
	 */
	private void addLastChild(Node<E> parent, Node<E> newNode) {
		parent.lastChild.nextSib = newNode;
		newNode.prevSib = parent.lastChild;
		parent.lastChild = newNode;
	}

	/**
	 * @param parent
	 * @param newNode
	 */
	private void addFirstNode(Node<E> parent, Node<E> newNode) {
		parent.firstChild = newNode;
		parent.lastChild = newNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#remove(trees.Tree.Node)
	 */
	@Override
	public void remove(trees.Tree.Node<E> node) {
		/*
		 * Node<E> parent = ((Node<E>) node).parent; if (node ==
		 * parent.firstChild) parent.firstChild = ((Node<E>) node).nextSib; else
		 * { Node<E> prevSib = ((Node<E>) node).firstChild; while
		 * (prevSib.nextSib != node) prevSib = prevSib.nextSib; prevSib.nextSib
		 * = ((Node<E>) node).nextSib; }
		 */
		if (node == root) {
			root = null;
			return;
		}
		Node<E> parent = ((Node<E>) node).parent;
		Node<E> temp = parent.firstChild;

		while (temp != node)
			temp = temp.nextSib;
		if (temp != parent.lastChild)
			temp.nextSib.prevSib = temp.prevSib;
		else {
			parent.lastChild = temp.prevSib;
			temp.prevSib.nextSib = null;
		}
		if (temp != parent.firstChild)
			temp.prevSib.nextSib = temp.nextSib;
		else {
			parent.firstChild = temp.nextSib;
			temp.nextSib.prevSib = null;
		}
		temp = null;
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
	 * @see trees.Tree#children(trees.Tree.Node)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Iterator<E> children(trees.Tree.Node<E> node) {
		return new PostOrderItr();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#nodesPostOrder()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Iterator<E> nodesPostOrder() {
		return new PostOrderItr();
	}

	/**
	 * @return
	 */
	private Iterator<Node<E>> entryPostOrder() {
		return new EntryItr();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#nodesPreOrder()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Iterator<E> nodesPreOrder() {
		return new PreOrderItr();
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private class PostOrderItr extends Itr {
		@SuppressWarnings("unchecked")
		public PostOrderItr() {
			elements = getNodesPostOrder(root, elements);
			es = elements.toArray();
			length = es.length;
			cursor = 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			return (E) nextEntry().element;
		}
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private class PreOrderItr extends Itr {
		@SuppressWarnings("unchecked")
		public PreOrderItr() {
			elements = getNodesPreOrder(root, elements);
			es = (Node<E>[]) elements.toArray();
			length = es.length;
			cursor = 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			return (E) nextEntry().element;
		}
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private final class EntryItr extends Itr<Node<E>> {

		public EntryItr() {
			elements = getNodesPostOrder(root, elements);
			es = elements.toArray();
			length = es.length;
			cursor = 0;
		}

		@Override
		public Node<E> next() {
			return (Node<E>) nextEntry();
		}
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private abstract class Itr<T> implements Iterator<T> {

		int cursor;
		int length;
		List<Node<E>> elements = new ArrayList<>();
		Object[] es;
		int lastRef = -1;

		@Override
		public boolean hasNext() {
			return cursor != length;
		}

		@SuppressWarnings("unchecked")
		final Node<E> nextEntry() {
			if (cursor >= length)
				throw new NoSuchElementException();
			lastRef = cursor;
			return (Node<E>) es[cursor++];

		}

		@SuppressWarnings("unchecked")
		@Override
		public void remove() {
			if (lastRef == -1)
				throw new NoSuchElementException();
			LinkedOrderedTree.this.remove(new Node<>(
					((Node<E>) es[lastRef]).element));
			lastRef = -1;
			cursor--;
			length--;
		}
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

	/**
	 * to prints all node before their children..
	 */
	public void depthOrderTraversal() {
		System.out.println();
		depthOrderTraversal(root);
	}

	/**
	 * @param node
	 */
	private void depthOrderTraversal(Tree.Node<E> node) {
		if (node != null) {
			depthOrderTraversal(((Node<E>) node).firstChild);
			depthOrderTraversal(((Node<E>) node).nextSib);
			System.out.print(((Node<E>) node).element + " ");
		}
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
		LinkedOrderedTree<?> other = (LinkedOrderedTree<?>) obj;
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
	 * @return List<>E of elements of all nodes of this tree, in post-order
	 *         (i.e., each node is visited before its children).
	 */
	private List<E> getElements(Node<E> node, List<E> list) {
		return getElementsPostOrder(node, list);
	}

	/**
	 * @param node
	 * @param list
	 * @return
	 */
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
	 * @return List<>E of elements of all nodes of this tree, in post-order
	 *         (i.e., each node is visited before its children).
	 */
	private List<Node<E>> getNodesPostOrder(Node<E> node, List<Node<E>> list) {
		if (node != null) {
			getNodesPostOrder(node.firstChild, list);
			getNodesPostOrder(node.nextSib, list);
			list.add(node);
		}
		return list;
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
		Iterator<Node<E>> nodeItr = entryPostOrder();
		while (nodeItr.hasNext())
			if ((temp = nodeItr.next()).element.compareTo(greatestNode.element) > 0)
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

	/**
	 * @param node
	 * @param list
	 * @return List<>E of elements of all nodes of this tree, in pre-order
	 *         (i.e., each node is visited before its children).
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
	 * @see trees.Tree#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	private static class Node<E> implements Tree.Node<E> {
		E element;
		Node<E> parent;
		Node<E> firstChild;
		Node<E> nextSib;
		Node<E> lastChild;
		Node<E> prevSib;

		public Node(E element) {
			this.element = element;
			this.firstChild = null;
			this.lastChild = null;
			this.nextSib = null;
			this.parent = null;
			this.prevSib = null;
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
					+ ((lastChild == null) ? 0 : lastChild.element.hashCode());
			result = prime * result
					+ ((nextSib == null) ? 0 : nextSib.element.hashCode());
			result = prime * result
					+ ((parent == null) ? 0 : parent.element.hashCode());
			result = prime * result
					+ ((prevSib == null) ? 0 : prevSib.element.hashCode());
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
			if (lastChild == null) {
				if (other.lastChild != null) {
					return false;
				}
			} else if (!lastChild.element.equals(other.lastChild.element)) {
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
			if (prevSib == null) {
				if (other.prevSib != null) {
					return false;
				}
			} else if (!prevSib.element.equals(other.prevSib.element)) {
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
			E lastE = lastChild == null ? null : lastChild.element;
			E prevSibE = prevSib == null ? null : prevSib.element;
			return "Node [element=" + element + ", firstChild=" + firstE
					+ ", parent=" + parentE + ", nextSib=" + nextSibE
					+ ", prevSib=" + prevSibE + ", lastChild=" + lastE + "]";
		}
	}
}