package trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedSortedTree<E extends Comparable<? super E>> implements
		Tree<E> {

	private Node<E> root;

	public LinkedSortedTree() {
		root = null;
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
		return ((Node<E>) node).childCount;
	}

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
		Node<E> newNode = new Node<>(element);
		Node<E> parent = ((Node<E>) node);
		newNode.parent = parent;
		if (parent.lastChild == null)
			addFirstNode(parent, newNode);
		else if (element.compareTo(parent.lastChild.element) > 0)
			addLastChild(parent, newNode);
		else if (element.compareTo(parent.firstChild.element) < 0)
			addFirstChild(parent, newNode);
		else {
			Node<E> greatestChild = parent.lastChild;
			while (greatestChild.element.compareTo(element) > 0)
				greatestChild = greatestChild.prevSib;
			addNext(greatestChild, newNode);
		}
		((Node<E>) node).childCount++;
		return newNode;
	}

	/**
	 * @param prevNode
	 * @param newNode
	 */
	private void addNext(Node<E> prevNode, Node<E> newNode) {
		prevNode.nextSib.prevSib = newNode;
		newNode.nextSib = prevNode.nextSib;
		newNode.prevSib = prevNode;
		prevNode.nextSib = newNode;
	}

	/**
	 * @param parent
	 *            parent node..
	 * @param newNode
	 *            node to be added as firstChild..
	 */
	private void addFirstChild(Node<E> parent, Node<E> newNode) {
		parent.firstChild.prevSib = newNode;
		newNode.nextSib = parent.firstChild;
		parent.firstChild = newNode;
	}

	/**
	 * @param parent
	 *            parent node..
	 * @param newNode
	 *            node to be added as first node in parent node..
	 */
	private void addFirstNode(Node<E> parent, Node<E> newNode) {
		parent.firstChild = newNode;
		parent.lastChild = newNode;
	}

	/**
	 * @param parent
	 *            parent node..
	 * @param newNode
	 *            node to be added as lastChild..
	 */
	private void addLastChild(Node<E> parent, Node<E> newNode) {
		parent.lastChild.nextSib = newNode;
		newNode.prevSib = parent.lastChild;
		parent.lastChild = newNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#remove(trees.Tree.Node)
	 */
	@Override
	public void remove(trees.Tree.Node<E> node) {
		if (node.equals(root)) {
			root = null;
			return;
		}
		Node<E> parent = ((Node<E>) node).parent;
		if (node.equals(parent.firstChild))
			removeFirstChild(parent);
		if (node.equals(parent.lastChild))
			removeLastChild(parent);
		else
			removeNode((Node<E>) node);
		parent.childCount--;
	}

	/**
	 * @param node
	 */
	private void removeNode(Node<E> node) {
		node.nextSib.prevSib = node.prevSib;
		node.prevSib.nextSib = node.nextSib;
	}

	/**
	 * @param parent
	 */
	private void removeLastChild(Node<E> parent) {
		parent.lastChild = parent.lastChild.prevSib;
		parent.lastChild.nextSib = null;
	}

	/**
	 * @param parent
	 */
	private void removeFirstChild(Node<E> parent) {
		parent.firstChild = parent.firstChild.nextSib;
		parent.firstChild.prevSib = null;
	}

	/**
	 * @param node
	 * @return
	 */
	@Override
	public trees.Tree.Node<E> greatestNode(trees.Tree.Node<E> node) {
		Node<E> greatestNode = (Node<E>) node;
		Node<E> temp = null;
		Iterator<Node<E>> iterator = entryIterator();
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
		Iterator<Node<E>> iterator = entryIterator();
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
	 * @author ANKIT DAIM
	 * 
	 */
	private class EntryIterator extends Itr<Node<E>> {

		public EntryIterator() {
			cursor = 0;
			nodesList = getNodesPostOrder(root, nodesList);
			nodesArr = nodesList.toArray();
			length = nodesArr.length;
		}

		@Override
		public Node<E> next() {
			return nextEntry();
		}
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private class PostOrderItr extends Itr<E> {
		public PostOrderItr() {
			cursor = 0;
			nodesList = getNodesPostOrder(root, nodesList);
			nodesArr = nodesList.toArray();
			length = nodesArr.length;
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
		public PreOrderItr() {
			cursor = 0;
			nodesList = getNodesPreOrder(root, nodesList);
			nodesArr = nodesList.toArray();
			length = nodesArr.length;
		}

		@Override
		public E next() {
			return nextEntry().element;
		}
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 * @param <T>
	 */
	private abstract class Itr<T> implements Iterator<T> {
		int cursor;
		int lastRef = -1;
		List<Node<E>> nodesList = new ArrayList<>();
		Object[] nodesArr;
		int length;

		@Override
		public boolean hasNext() {
			return cursor != length;
		}

		@SuppressWarnings("unchecked")
		final Node<E> nextEntry() {
			if (cursor >= length)
				throw new NoSuchElementException();
			lastRef = cursor;
			return (Node<E>) nodesArr[cursor++];
		}

		@SuppressWarnings("unchecked")
		@Override
		public void remove() {
			if (lastRef == -1)
				throw new IllegalStateException();
			LinkedSortedTree.this.remove((Node<E>) nodesArr[lastRef]);
			lastRef = -1;
			length--;
			cursor--;
		}
	}

	@Override
	public Iterator<E> children(trees.Tree.Node<E> node) {
		return new PostOrderItr();
	}

	/**
	 * @return
	 */
	private Iterator<Node<E>> entryIterator() {
		return new EntryIterator();
	}

	@Override
	public Iterator<E> nodesPreOrder() {
		return new PreOrderItr();
	}

	@Override
	public Iterator<E> nodesPostOrder() {
		return new PostOrderItr();
	}

	@Override
	public trees.Tree.Node<E> searchNode(E e) {
		return searchNode(e, root);
	}

	/**
	 * @param e
	 * @param curr
	 * @return
	 */
	private trees.Tree.Node<E> searchNode(E e, Node<E> curr) {
		if (curr != null) {
			if (curr.element.equals(e))
				return curr;
			searchNode(e, curr.firstChild);
			searchNode(e, curr.nextSib);
		}
		return null;
	}

	/**
	 * @param node
	 * @param nodesList
	 * @return
	 */
	private List<Node<E>> getNodesPostOrder(Node<E> node,
			List<Node<E>> nodesList) {
		if (node != null) {
			getNodesPostOrder(node.firstChild, nodesList);
			getNodesPostOrder(node.nextSib, nodesList);
			nodesList.add(node);
		}
		return nodesList;
	}

	/**
	 * @param node
	 * @param nodesList
	 * @return
	 */
	private List<Node<E>> getNodesPreOrder(Node<E> node, List<Node<E>> nodesList) {
		if (node != null) {
			getNodesPreOrder(node.firstChild, nodesList);
			getNodesPreOrder(node.nextSib, nodesList);
			nodesList.add(node);
		}
		return nodesList;
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
		result = prime * result + ((root == null) ? 0 : root.hashCode());
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
		LinkedSortedTree<?> other = (LinkedSortedTree<?>) obj;
		if (root == null) {
			if (other.root != null) {
				return false;
			}
		} else if (!root.equals(other.root)) {
			return false;
		}
		return true;
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
	 * @author ANKIT DAIM
	 * 
	 * @param <E>
	 */
	private static class Node<E> implements Tree.Node<E> {

		private Node<E> parent;
		private Node<E> firstChild;
		private Node<E> lastChild;
		private Node<E> nextSib;
		private Node<E> prevSib;
		private E element;
		private int childCount;

		public Node(E e) {
			this.element = e;
			this.childCount = 0;
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
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			E firstE = firstChild == null ? null : firstChild.element;
			E parentE = parent == null ? null : parent.element;
			E nextSibE = nextSib == null ? null : nextSib.element;
			E lastE = lastChild == null ? null : lastChild.element;
			E prevSibE = prevSib == null ? null : prevSib.element;
			return "Node [parent=" + parentE + ", firstChild=" + firstE
					+ ", lastChild=" + lastE + ", nextSib=" + nextSibE
					+ ", prevSib=" + prevSibE + ", element=" + element
					+ ", childCount=" + childCount + "]";
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
			result = prime * result + childCount;
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
			if (childCount != other.childCount) {
				return false;
			}
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
	}
}