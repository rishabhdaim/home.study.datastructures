package trees.binary;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BinarySearchTree<E extends Comparable<? super E>>{

	private transient Node<E> root = new Node<E>(null, null, null, null);
	private int size = 0;

	/**
	 * 
	 */
	public BinarySearchTree() {
		super();
	}

	/**
	 * @param e
	 */
	public BinarySearchTree(E e) {
		super();
		if (e == null)
			throw new NullPointerException("null not allowed");
		if (root.element == null) {
			root.element = e;
			size++;
		} else {
			Node<E> node = new Node<E>(e, null, null, null);
			insertNode(node, root);
		}
	}

	// To insert the element elem into a BST:
	// 1. Set parent to null, and set curr to the BST's root.
	// 2. Repeat:
	// 2.1. If curr is null:
	// 2.1.1. Let ins be a newly-created node with element elem and no
	// children.
	// 2.1.2. Replace the null link from which curr was taken (either the
	// BST's root or parent's left child or parent's right child) by ins.
	// 2.1.3. Terminate.
	// 2.2. Otherwise, if elem is equal to node curr's element:
	// 2.2.1. Terminate.
	// 2.3. Otherwise, if elem is less than node curr's element:
	// 2.3.1. Set parent to curr, and set curr to node curr's left child.
	// 2.4. Otherwise, if elem is greater than node curr's element:
	// 2.4.1. Set parent to curr, and set curr to node curr's right child.

	/**
	 * @param e
	 */
	public Node<E> insertNode(E e) {
		if (e == null)
			throw new NullPointerException("null not allowed");
		if (root.element == null) {
			root.element = e;
			size++;
			return root;
		} else {
			Node<E> node = new Node<E>(e, null, null, null);
			return insertNode(node, root);
		}
	}

	/**
	 * @param curr
	 * @param parent
	 */
	private Node<E> insertNode(Node<E> curr, Node<E> parent) {
		int comp = curr.element.compareTo(parent.element);

		if (comp < 0) {
			if (parent.leftNode != null)
				insertNode(curr, parent.leftNode);
			else {
				parent.leftNode = curr;
				curr.parentNode = parent;
				size++;
			}
		} else if (comp > 0) {
			if (parent.rightNode != null)
				insertNode(curr, parent.rightNode);
			else {
				parent.rightNode = curr;
				curr.parentNode = parent;
				size++;
			}
		} else
			return null;
		return curr;
	}

	// To find which if any node of a BST contains an element equal to target:
	// 1. Set curr to the BST's root.
	// 2. Repeat:
	// 2.1. If curr is null:
	// 2.1.1. Terminate with answer none.
	// 2.2. Otherwise, if target is equal to node curr's element:
	// 2.2.1. Terminate with answer curr.
	// 2.3. Otherwise, if target is less than node curr's element:
	// 2.3.1. Set curr to node curr's left child.
	// 2.4. Otherwise, if target is greater than node curr's element:
	// 2.4.1. Set curr to node curr's right child.

	/**
	 * @param e
	 * @return
	 */
	public Node<E> searchNode(E e, boolean searchRecursive) {
		if (searchRecursive)
			return searchRecursive(e, root);
		if (e == null)
			throw new NullPointerException("element cann't be null");
		if (isEmpty())
			throw new NoSuchElementException("tree is empty..");
		Node<E> curr = root;
		for (;;) {
			if (curr == null)
				return null;
			int comp = e.compareTo(curr.element);
			if (comp < 0)
				curr = curr.leftNode;
			else if (comp > 0)
				curr = curr.rightNode;
			else
				return curr;
		}
	}

	/**
	 * @param e
	 * @param root
	 * @return
	 */
	private Node<E> searchRecursive(E e, Node<E> top) {

		if (top == null)
			return null;
		int c = e.compareTo(top.element);
		if (0 > c)
			return searchRecursive(e, top.leftNode);
		else if (0 < c)
			return searchRecursive(e, top.rightNode);
		else
			return top;
	}

	// To delete the leftmost element in the (nonempty) subtree whose topmost
	// node is this:
	// 1. If node this has no left child:
	// 1.1. Terminate with node this's right child as answer.
	// 2. If this has a left child:
	// 2.1. Set parent to this, and set curr to node this's left child.
	// 2.2. While node curr has a left child, repeat:
	// 2.2.1. Set parent to curr, and set curr to node curfs left child.
	// 2.3. Set parent's left child to node curr's right child.
	// 2.4. Terminate with this as answer.

	/**
	 * 
	 */
	public E deleteLeftMost() {
		if (isEmpty())
			throw new NoSuchElementException("tree is empty..");
		E temp = null;
		if (root.leftNode == null) {
			temp = root.element;
			if (root.rightNode != null)
				shiftRoot(root.rightNode);
			emptyNode(root);
		} else {
			Node<E> leftMost = getLeftMostNode(root);
			temp = leftMost.element;
			leftMost.parentNode.leftNode = leftMost.rightNode;
			emptyNode(leftMost);
		}
		size--;
		return temp;
	}

	/**
	 * @param curr
	 * @return
	 */
	private Node<E> getLeftMostNode(Node<E> curr) {
		while (curr.leftNode != null)
			curr = curr.leftNode;
		return curr;
	}

	// To delete the topmost element in the subtree whose topmost node is top:
	// 1. If node top has no left child:
	// 1.1. Terminate with node top's right child as answer.
	// 2. If node top has no right child:
	// 2.1. Terminate with node top's left child as answer.
	// 3. If node top has both a left child and a right child:
	// 3.1. Set top's element to the leftmost element in node top's right
	// subtree.
	// 3.2. Delete the leftmost element in node top's right subtree.
	// 3.3. Terminate with top as answer.

	// To determine the leftmost element in the (nonempty) subtree whose topmost
	// node is this:
	// 1. Set curr to this.
	// 2. While node curr has a left child, repeat:
	// 2.1. Set curr to node curr's left child.
	// 3. Terminate with node curr's element as answer.

	/**
	 * @return
	 */
	public Node<E> deleteTopMost() {
		if (isEmpty())
			throw new NoSuchElementException("tree is empty..");
		if (root.leftNode == null) {
			if (root.rightNode != null)
				shiftRoot(root.rightNode);
		} else if (root.rightNode == null) {
			if (root.leftNode != null)
				shiftRoot(root.leftNode);
		} else
			root.element = deleteLeftMostInSubTree(root.rightNode);
		size--;
		return root;
	}

	/**
	 * @param subTreeRoot
	 * @return
	 */
	private E deleteLeftMostInSubTree(Node<E> subTreeRoot) {
		Node<E> parent = subTreeRoot.parentNode;
		Node<E> leftMost = getLeftMostNode(subTreeRoot);
		if (leftMost.parentNode.equals(parent))
			root.rightNode = leftMost.rightNode;
		else
			leftMost.parentNode.leftNode = leftMost.rightNode;
		if (leftMost.rightNode != null)
			leftMost.rightNode.parentNode = leftMost.parentNode;
		E e = leftMost.element;
		emptyNode(leftMost);
		return e;
	}

	/**
	 * @param node
	 */
	private void emptyNode(Node<E> node) {
		node.element = null;
		node.leftNode = node.parentNode = node.rightNode = null;
	}

	/**
	 * @param newRoot
	 */
	private void shiftRoot(Node<E> newRoot) {
		root = newRoot;
		root.parentNode = null;
	}

	// To delete the element elem in a BST:
	// 1. Set parent to null, and curr to the BSTs root node.
	// 2. Repeat:
	// 2.1. If curr is null:
	// 2.1.1. Terminate.
	// 2.2. Otherwise, if elem is equal to node curr's element:
	// 2.2.1. Delete the topmost element in the subtree whose topmost node is
	// node curr, and let del be a link to the modified subtree.
	// 2.2.2. Replace the link to node curr by del.
	// 2.2.3. Terminate.
	// 2.3. Otherwise, if elem is less than node curr's element:
	// 2.3.1. Set parent to curr, and set curr to node curr's left child.
	// 2.4. Otherwise, if elem is greater than node curr's element:
	// 2.4.1. Set parent to curr, and set curr to node curr's right child.

	/**
	 * @return
	 */
	public E delete(E e) {
		if (e == null)
			throw new NullPointerException("element cann't be null");
		if (isEmpty())
			throw new NoSuchElementException("tree is empty..");
		Node<E> parent = null;
		Node<E> curr = root;
		E temp = null;
		for (;;) {
			if (curr == null)
				return null;
			temp = curr.element;
			int comp = e.compareTo(temp);
			if (comp == 0) {
				deleteTopInSubTree(curr, parent);
				return temp;
			}
			parent = curr;
			if (comp > 0)
				curr = parent.rightNode;
			else
				curr = parent.leftNode;
		}
	}

	/**
	 * @param curr
	 * @param parent
	 * @return
	 */
	private void deleteTopInSubTree(Node<E> curr, Node<E> parent) {
		if (parent == null) {
			deleteTopMost();
			return;
		}
		int i = curr.element.compareTo(parent.element);
		if (curr.leftNode == null) {
			if (i > 0)
				parent.rightNode = curr.rightNode;
			else
				parent.leftNode = curr.rightNode;
			if (curr.rightNode != null)
				curr.rightNode.parentNode = parent;
			emptyNode(curr);
		} else if (curr.rightNode == null) {
			if (i > 0)
				parent.rightNode = curr.leftNode;
			else
				parent.leftNode = curr.leftNode;
			if (curr.leftNode != null)
				curr.leftNode.parentNode = parent;
			emptyNode(curr);
		} else
			curr.element = deleteLeftMostInSubTree(curr.rightNode);
		// getAndDeleteLeftMost2(curr.rightNode);
		/*
		 * if (curr == root) root.element = e; else if (curr == parent.leftNode)
		 * parent.leftNode.element = e; else if (curr == parent.rightNode)
		 * parent.rightNode.element = e;
		 */
		size--;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder elements = new StringBuilder();
		elements = getElements(root, elements);
		return "Node [elements=" + elements.toString() + "]";
	}

	/**
	 * @param curr
	 * @param elements
	 * @return
	 */
	private StringBuilder getElements(Node<E> curr, StringBuilder elements) {

		if (curr != null) {
			getElements(curr.leftNode, elements);
			elements.append(curr.element + " ");
			getElements(curr.rightNode, elements);
		}

		return elements;
	}

	/**
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * @param top
	 * @return
	 */
	public int size(Node<E> top) {
		if (top == null)
			return 0;
		else
			return 1 + size(top.leftNode) + size(top.rightNode);
	}

	/**
	 * 
	 */
	public void printInOrder() {
		printInOrder(root);
	}

	/**
	 * 
	 */
	public void printPreOrder() {
		printPreOrder(root);
	}

	/**
	 * 
	 */
	public void printPostOrder() {
		printPostOrder(root);
	}

	public void printInOrderMirror() {
		printInOrderMirror(root);
	}

	/**
	 * 
	 */
	public void printPreOrderMirror() {
		printPreOrderMirror(root);
	}

	/**
	 * 
	 */
	public void printPostOrderMirror() {
		printPostOrderMirror(root);
	}

	// To traverse, in in-order, the subtree whose topmost node is top:
	// 1. If top is not null:
	// 1.1. Traverse, in in-order, node top's left subtree.
	// 1.2. Visit node top.
	// 1.3. Traverse, in in-order, node top's right subtree.
	// 2. Terminate.

	/**
	 * @param top
	 */
	private void printInOrder(Node<E> top) {
		// Print all the elements in the subtree whose topmost node is top, in
		// ascending order.
		if (top != null) {
			printInOrder(top.leftNode);
			System.out.print(top.element + " ");
			printInOrder(top.rightNode);
		}
	}

	/**
	 * @param top
	 */
	private void printInOrderMirror(Node<E> top) {
		// Print all the elements in the subtree whose topmost node is top, in
		// descending order.
		if (top != null) {
			printInOrder(top.rightNode);
			System.out.print(top.element + " ");
			printInOrder(top.leftNode);
		}
	}

	// To traverse, in pre-order, the subtree whose topmost node is top:
	// 1. If top is not null:
	// 1 . 1 . Visit node top.
	// 1.2. Traverse, in pre-order, node top's left subtree.
	// 1.3. Traverse, in pre-order, node top's right subtree.
	// 2. Terminate.

	/**
	 * to save the tree into file..
	 * 
	 * @param top
	 */
	private void printPreOrder(Node<E> top) {
		if (top != null) {
			System.out.print(top.element + " ");
			printPreOrder(top.leftNode);
			printPreOrder(top.rightNode);
		}
	}

	/**
	 * to invert the tree.. mirror image..
	 * 
	 * @param top
	 */
	private void printPreOrderMirror(Node<E> top) {
		if (top != null) {
			System.out.print(top.element + " ");
			printPreOrder(top.rightNode);
			printPreOrder(top.leftNode);
		}
	}

	// To traverse, in post-order, the subtree whose topmost node is top:
	// 1. If top is not null:
	// 1.1. Traverse, in post-order, node top's left subtree.
	// 1.2. Traverse, in post-order, node top's right subtree.
	// 1.3. Visit node top.
	// 2. Terminate.

	/**
	 * @param top
	 */
	private void printPostOrder(Node<E> top) {
		if (top != null) {
			printPostOrder(top.leftNode);
			printPostOrder(top.rightNode);
			System.out.print(top.element + " ");
		}
	}

	/**
	 * @param top
	 */
	private void printPostOrderMirror(Node<E> top) {
		if (top != null) {
			printPostOrder(top.rightNode);
			printPostOrder(top.leftNode);
			System.out.print(top.element + " ");
		}
	}

	/**
	 * @return
	 */
	public List<Node<E>> getElements() {
		List<Node<E>> bstNodes = new ArrayList<>();
		return getElements(root, bstNodes);
	}

	/**
	 * @param node
	 * @param bstNodes
	 */
	private List<Node<E>> getElements(Node<E> node,
			List<Node<E>> bstNodes) {
		if (node != null) {
			getElements(node.rightNode, bstNodes);
			getElements(node.leftNode, bstNodes);
			bstNodes.add(node);
		}
		return bstNodes;
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
		result = prime * result + size;
		result = prime * result + getElements().hashCode();
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
		BinarySearchTree<?> other = (BinarySearchTree<?>) obj;
		if (size != other.size) {
			return false;
		}

		if (!getElements().equals(other.getElements()))
			return false;
		return true;
	}

	/**
	 * Each Node object is a binary search tree (BST) node. This node
	 * consists of an element (element), a link to its left child (left), and a
	 * link to its right child (right). For every element x in the left subtree:
	 * x.compareTo(element) < 0. For every element y in the right subtree:
	 * y.compareTo(element) > 0
	 * 
	 * 
	 * @author ANKIT DAIM
	 * 
	 * @param <E>
	 */
	static class Node<E extends Comparable<? super E>> {
		private E element;
		private Node<E> leftNode;
		private Node<E> rightNode;
		private Node<E> parentNode;

		/**
		 * @param element
		 * @param leftNode
		 * @param rightNode
		 */
		private Node(E element, Node<E> leftNode, Node<E> rightNode,
				Node<E> parentNode) {
			super();
			this.element = element;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
			this.parentNode = parentNode;
		}

		E getParent() {
			return parentNode.element;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			E leftE = leftNode == null ? null : leftNode.element;
			E rightE = rightNode == null ? null : rightNode.element;
			E parentE = parentNode == null ? null : parentNode.element;
			return "Node [element=" + element + ", leftNode=" + leftE
					+ ", rightNode=" + rightE + ", parentNode=" + parentE + "]";
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
			result = prime * result
					+ ((leftNode == null) ? 0 : leftNode.element.hashCode());
			result = prime
					* result
					+ ((parentNode == null) ? 0 : parentNode.element.hashCode());
			result = prime * result
					+ ((rightNode == null) ? 0 : rightNode.element.hashCode());
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
			if (leftNode == null) {
				if (other.leftNode != null) {
					return false;
				}
			} else if (!leftNode.element.equals(other.leftNode.element)) {
				return false;
			}
			if (parentNode == null) {
				if (other.parentNode != null) {
					return false;
				}
			} else if (!parentNode.element.equals(other.parentNode.element)) {
				return false;
			}
			if (rightNode == null) {
				if (other.rightNode != null) {
					return false;
				}
			} else if (!rightNode.element.equals(other.rightNode.element)) {
				return false;
			}
			return true;
		}
	}
}