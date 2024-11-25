package trees.binary;

import java.util.Iterator;
import java.util.NoSuchElementException;

import trees.Tree;

/**
 * @author ANKIT DAIM
 * 
 * @param <E>
 */
public class AvlTree<E extends Comparable<? super E>>
		implements Tree<E> {

	/**
	 * head of this tree..
	 */
	private Node<E> root;
	/**
	 * to store no. of elements i.e. size of this Tree..
	 */
	private int size;

	public AvlTree() {
		root = null;
		size = 0;
	}

	@Override
	public trees.Tree.Node<E> root() {
		return root;
	}

	@Override
	public trees.Tree.Node<E> parent(trees.Tree.Node<E> node) {
		return ((Node<E>) node).parentNode;
	}

	@Override
	public int childCount(trees.Tree.Node<E> node) {
		if (((Node<E>) node).leftNode != null
				&& ((Node<E>) node).rightNode != null)
			return 2;
		else if (((Node<E>) node).leftNode != null
				|| ((Node<E>) node).rightNode != null)
			return 1;
		else
			return 0;
	}

	@Override
	public void makeRoot(E element) {
		root = new Node<>(null, null, null, element);
		size++;
	}

	// To insert element elem into the AVL-tree tree:
	// 1, Perform a BST-insertion of elem into tree, and let ins be the
	// newly-created node containing elem.
	// 2. If ins or any ancestor of ins is now height-unbalanced:
	// 2.1. Let child be either ins or an ancestor of ins such that child's
	// grandparent is the nearest height-unbalanced ancestor of ins.
	// 2.2. Let rotated-subtree be the subtree obtained by rotating child, its
	// parent, and its grandparent.
	// 2.3. Make rotated-subtree replace the subtree of tree whose top node is
	// child's, grandparent.
	// 3. Terminate.
	/*
	 * (non-Javadoc)
	 * 
	 * @see trees.Tree#addChild(trees.Tree.Node, java.lang.Object)
	 */
	@Override
	public trees.Tree.Node<E> addChild(trees.Tree.Node<E> node, E element) {
		return node;
	}

	/**
	 * @param element
	 * @return
	 */
	public Node<E> addChild(E element) {

		if (root == null) {
			makeRoot(element);
			return root;
		}
		Node<E> curr = root;
		for (;;) {
			int comp = element.compareTo(root.element);
			if (0 < comp)
				if (curr.rightNode == null) {
					curr.rightNode = addChild(curr.rightNode, curr, element);
					return curr.rightNode;
				} else
					curr = curr.rightNode;
			else if (0 > comp)
				if (curr.leftNode == null) {
					curr.leftNode = addChild(curr.leftNode, curr, element);
					return curr.leftNode;
				} else
					curr = curr.leftNode;
			else
				return null;
		}

	}

	/**
	 * @param curr
	 * @param parent
	 * @param element
	 * @return
	 */
	private Node<E> addChild(Node<E> curr, Node<E> parent, E element) {
		curr = new Node<>(null, null, parent, element);
		increaseBalanceFactor(curr);
		if (checkBalance(curr))
			rotate(curr);
		size++;
		return curr;
	}

	// To rotate the nodes child, parent, and grandparent.
	// 1. Let (a, b, c) be a left-to-right enumeration of child, parent, and
	// grandparent.
	// 2. Let (Tl, T2, T3, T4) be a left-to-right enumeration of the four free
	// subtrees of a, b, and c (i.e., the subtrees whose top nodes are not a, b,
	// and c themselves).
	// 3. Let rotated-subtree be a subtree constructed as follows: (i) its top
	// node is b;
	// (ii)b's left and right children are a and c; (iii) a's left and right
	// subtrees are Tl and T2; (iv) c's left and right subtrees are T3 and T4.
	// 4. Terminate with answer rotated-subtree.
	/**
	 * @param curr
	 */
	private void rotate(Node<E> curr) {
		Node<E> child = getChild(curr);
		Node<E> parent, grandParent;
		int comp = 0;
		Node<E> t2 = null, t3;
		if (child.parentNode.balanceFactor > 1
				|| child.parentNode.balanceFactor < -1) {
			grandParent = child.parentNode;
			comp = grandParent.element.compareTo(child.element);
			if (0 > comp) {
				parent = child.leftNode;
				t3 = parent.leftNode;
				t2 = parent.rightNode;
				parent.rightNode = child;
				parent.leftNode = grandParent;
				child.leftNode = t2;
				grandParent.leftNode = t3;
			} else {
				parent = child.rightNode;
				t3 = parent.rightNode;
				t2 = parent.leftNode;
				parent.leftNode = child;
				parent.rightNode = grandParent;
				child.rightNode = t2;
				grandParent.rightNode = t3;
			}
		} else {
			parent = child.parentNode;
			grandParent = parent.parentNode;
			comp = grandParent.element.compareTo(child.element);
			if (0 > comp) {
				t3 = parent.leftNode;
				parent.leftNode = grandParent;
				grandParent.rightNode = t3;
			} else {
				t3 = parent.rightNode;
				parent.rightNode = grandParent;
				grandParent.leftNode = t3;
			}
		}
		if (t3 != null)
			t3.parentNode = grandParent;
		if (t2 != null)
			t2.parentNode = child;
		if (grandParent.parentNode == null)
			root = parent;
		else {
			comp = grandParent.element
					.compareTo(grandParent.parentNode.element);
			if (0 < comp)
				grandParent.parentNode.rightNode = parent;
			else
				grandParent.parentNode.leftNode = parent;
		}
		parent.parentNode = grandParent.parentNode;
		grandParent.parentNode = parent;
		child.balanceFactor = parent.balanceFactor = grandParent.balanceFactor = 0;
		decreaseBalanceFactor(parent);
	}

	/**
	 * this method is used to get the child element whose grandParent had
	 * ill-Balanced balanceFactor..
	 * 
	 * @param curr
	 *            the Node<E> whose ancestors had to be checked for child
	 *            element and grandChild element..
	 * @return
	 */
	private Node<E> getChild(Node<E> curr) {
		Node<E> child = curr;
		Node<E> illBalanced = child.parentNode.parentNode;
		while (illBalanced != null
				&& (illBalanced.balanceFactor <= 1 && illBalanced.balanceFactor >= -1)) {
			child = child.parentNode;
			illBalanced = child.parentNode.parentNode;
		}
		return child;
	}

	/**
	 * @param curr
	 * @return
	 */
	private boolean checkBalance(Node<E> parent) {
		Node<E> curr = parent;
		while (curr != null) {
			if (curr.balanceFactor >= 2 || curr.balanceFactor <= -2)
				return true;
			curr = curr.parentNode;
		}
		return false;
	}

	/**
	 * @param curr
	 */
	private void increaseBalanceFactor(Node<E> curr) {
		Node<E> parent = curr.parentNode;
		while (parent != null) {
			parent.balanceFactor++;
			parent = parent.parentNode;
		}
	}

	/**
	 * @param parent
	 */
	private void decreaseBalanceFactor(Node<E> curr) {
		Node<E> parent = curr.parentNode;
		while (parent != null) {
			parent.balanceFactor--;
			parent = parent.parentNode;
		}
	}

	// To delete element elem from the AVL-tree tree:
	// 1. Perform a BST-deletion of elem from tree, and let v be the parent of
	// the node that was actually deleted.
	// 2. While either v or an ancestor of v is height-unbalanced, repeat:
	// 2.1. Let grandparent be either v or an ancestor of v that is
	// heightunbalanced,choosing the one that is nearest to v.
	// 2.2. Let parent be the child of grandparent with the greater height,
	// choosing either child if they have equal height.
	// 2.3. Let child be the child of parent with the greater height, choosing
	// either child if they have equal height.
	// 2.4. Let rotated-subtree be the subtree obtained by rotating child,
	// parent, and grandparent.
	// 2.5. Make rotated-subtree replace the subtree of tree whose top node is
	// grandparent.
	// 2.6. Set v to the top node of rotated-subtree.
	// 3. Terminate.

	/**
	 * @param element
	 * @return
	 */
	public E deleteChild(E e) {
		if (e == null)
			throw new NullPointerException("element cann't be null");
		if (isEmpty())
			throw new NoSuchElementException("tree is empty..");
		Node<E> curr = root;
		int comp;
		for (;;) {
			if (curr == null)
				return null;
			comp = e.compareTo(curr.element);
			if (0 == comp) {
				deleteTopMost(curr);
				decreaseBalanceFactor(curr);
				while (checkBalance(curr.parentNode))
					rotateAfterDeletion(curr.parentNode);
				emptyNode(curr);
				size--;
				return e;
			} else if (0 > comp)
				curr = curr.leftNode;
			else
				curr = curr.rightNode;
		}
	}

	/**
	 * @param curr
	 */
	private void rotateAfterDeletion(Node<E> curr) {
		Node<E> grandParent = getGrandParent(curr);
		int comp = grandParent.element.compareTo(curr.element);
		Node<E> t2 = null, t3, parent, child;
		if (0 > comp) { // rotation to be done in leftPart of subTree with root
						// node as grandParent..
			parent = grandParent.leftNode;
			if (parent.leftNode != null) {
				child = parent.leftNode;
				t3 = parent.rightNode;
				parent.rightNode = grandParent;
				grandParent.leftNode = t3;
			} else {
				child = grandParent.leftNode;
				parent = child.rightNode;
				t2 = parent.leftNode;
				t3 = parent.rightNode;
				parent.leftNode = child;
				parent.rightNode = grandParent;
				child.rightNode = t2;
				grandParent.leftNode = t3;
			}
		} else {
			// rotation to be done in rightPart of subTree with root node as
			// grandParent..
			parent = grandParent.rightNode;
			if (parent.rightNode != null) {
				child = parent.rightNode;
				t3 = parent.leftNode;
				parent.leftNode = grandParent;
				grandParent.rightNode = t3;
			} else {
				child = grandParent.rightNode;
				parent = child.leftNode;
				t2 = parent.rightNode;
				t3 = parent.leftNode;
				parent.rightNode = child;
				parent.leftNode = grandParent;
				child.leftNode = t2;
				grandParent.rightNode = t3;
			}
		}
		if (t3 != null)
			t3.parentNode = grandParent;
		if (t2 != null)
			t2.parentNode = child;
		if (grandParent.parentNode == null)
			root = parent;
		else {
			comp = grandParent.element
					.compareTo(grandParent.parentNode.element);
			if (0 < comp)
				grandParent.parentNode.rightNode = parent;
			else
				grandParent.parentNode.leftNode = parent;
			grandParent.parentNode = parent;
			child.balanceFactor = parent.balanceFactor = grandParent.balanceFactor = 0;
			increaseBalanceFactor(parent);
		}
	}

	/**
	 * @param curr
	 * @return
	 */
	private Node<E> getGrandParent(Node<E> curr) {
		Node<E> grandParent = curr;
		while (grandParent != null
				&& (grandParent.balanceFactor < 2 && grandParent.balanceFactor > -2))
			grandParent = grandParent.parentNode;
		return grandParent;
	}

	/**
	 * @param node
	 * @return
	 */
	public E deleteLeftMost(Node<E> node) {
		if (isEmpty())
			throw new NoSuchElementException("tree is empty..");
		if (node == null)
			throw new NullPointerException("input node can't be null..");
		Node<E> leftMost = getLeftMostNode(node);
		E e = leftMost.element;
		leftMost.parentNode.leftNode = leftMost.rightNode;
		if (leftMost.rightNode != null)
			leftMost.rightNode.parentNode = leftMost.parentNode;
		return e;
	}

	/**
	 * @param node
	 */
	private void emptyNode(Node<E> node) {
		node.balanceFactor = 0;
		node.element = null;
		node.leftNode = node.parentNode = node.rightNode = null;
	}

	/**
	 * @param node
	 * @return leftMost node of this node..
	 */
	private Node<E> getLeftMostNode(Node<E> node) {
		Node<E> leftMost = node;
		while (leftMost.leftNode != null)
			leftMost = leftMost.leftNode;
		return leftMost;
	}

	/**
	 * @param node
	 * @return
	 */
	public E deleteTopMost(Node<E> node) {
		if (isEmpty())
			throw new NoSuchElementException("tree is empty..");
		if (node == null)
			throw new NullPointerException("input node can't be null..");
		Node<E> parent = node.parentNode;
		E e = node.element;
		if (parent == null) { // element is root element..
			removeRoot();
			return e;
		}

		// non root element..
		int comp = node.element.compareTo(parent.element);

		if (0 < comp) {// element greater than parent, so element is on
						// rightNode of parent..
			if (node.leftNode != null && node.rightNode != null)
				node.element = deleteLeftMost(node.rightNode);
			else {
				if (node.leftNode == null && node.rightNode == null)
					parent.rightNode = null;
				else if (node.rightNode == null && node.leftNode != null) {
					parent.rightNode = node.leftNode;
					node.leftNode.parentNode = parent;
				} else {
					parent.rightNode = node.rightNode;
					node.rightNode.parentNode = parent;
				}
			}
		} else { // element less than parent, so element is on leftNode of
					// parent..
			if (node.leftNode != null && node.rightNode != null)
				node.element = deleteLeftMost(node.rightNode);
			else {
				if (node.leftNode == null && node.rightNode == null)
					parent.leftNode = null;
				else if (node.rightNode == null && node.leftNode != null) {
					parent.leftNode = node.leftNode;
					node.leftNode.parentNode = parent;
				} else {
					parent.leftNode = node.rightNode;
					node.rightNode.parentNode = parent;
				}
			}
		}
		return e;
	}

	/**
	 * to remove root element of this tree..
	 */
	private void removeRoot() {
		if (root.leftNode == null && root.rightNode != null)
			shiftRoot(root.rightNode);
		else if (root.leftNode != null && root.rightNode == null)
			shiftRoot(root.leftNode);
		else if (root.leftNode == null && root.rightNode == null)
			root = null;
		else
			root.element = deleteLeftMost(root.rightNode);
	}

	/**
	 * @param newNode
	 */
	private void shiftRoot(Node<E> newNode) {
		root = newNode;
		root.parentNode = null;
	}

	@Override
	public void remove(trees.Tree.Node<E> node) {
	}

	@Override
	public Iterator<E> children(trees.Tree.Node<E> node) {
		return null;
	}

	@Override
	public Iterator<E> nodesPreOrder() {
		return null;
	}

	@Override
	public Iterator<E> nodesPostOrder() {
		return null;
	}

	@Override
	public trees.Tree.Node<E> searchNode(E e) {
		if (root == null)
			throw new NoSuchElementException("tree is empty..");
		Node<E> curr = root;
		for (;;) {
			int comp = e.compareTo(curr.element);
			if (0 < comp)
				curr = curr.rightNode;
			else if (0 > comp)
				curr = curr.leftNode;
			else
				return curr;
		}
	}

	@Override
	public trees.Tree.Node<E> greatestNode(trees.Tree.Node<E> node) {
		if (node == null)
			throw new IllegalArgumentException("node can't be null..");
		Node<E> greatest = (Node<E>) node;
		while (greatest.rightNode != null)
			greatest = greatest.rightNode;
		return greatest;
	}

	@Override
	public trees.Tree.Node<E> smallestNode(trees.Tree.Node<E> node) {
		if (node == null)
			throw new IllegalArgumentException("node can't be null..");
		Node<E> smallest = (Node<E>) node;
		while (smallest.leftNode != null)
			smallest = smallest.leftNode;
		return smallest;
	}

	@Override
	public int size() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb = getElements(root, sb);
		return "BalancedBinarySearchTree [root= " + root + ", size= " + size
				+ ", elements= " + sb.toString() + "]";
	}

	/**
	 * @param node
	 * @param sb
	 * @return
	 */
	private StringBuilder getElements(Node<E> node, StringBuilder sb) {
		if (node != null) {
			getElements(node.leftNode, sb);
			sb.append(node.element + " ");
			getElements(node.rightNode, sb);
		}
		return sb;
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
	static class Node<E> implements Tree.Node<E> {

		Node<E> leftNode;
		Node<E> rightNode;
		Node<E> parentNode;
		E element;
		int balanceFactor;

		/**
		 * @param leftNode
		 * @param rightNode
		 * @param parentNode
		 * @param element
		 */
		public Node(Node<E> leftNode, Node<E> rightNode, Node<E> parentNode,
				E element) {
			super();
			this.leftNode = leftNode;
			this.rightNode = rightNode;
			this.parentNode = parentNode;
			this.element = element;
			this.balanceFactor = 0;
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
			result = prime * result + balanceFactor;
			result = prime * result
					+ ((element == null) ? 0 : element.hashCode());
			result = prime * result
					+ ((leftNode == null) ? 0 : leftNode.hashCode());
			result = prime * result
					+ ((parentNode == null) ? 0 : parentNode.hashCode());
			result = prime * result
					+ ((rightNode == null) ? 0 : rightNode.hashCode());
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
			if (balanceFactor != other.balanceFactor) {
				return false;
			}
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
			} else if (!leftNode.equals(other.leftNode)) {
				return false;
			}
			if (parentNode == null) {
				if (other.parentNode != null) {
					return false;
				}
			} else if (!parentNode.equals(other.parentNode)) {
				return false;
			}
			if (rightNode == null) {
				if (other.rightNode != null) {
					return false;
				}
			} else if (!rightNode.equals(other.rightNode)) {
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
			E leftE = leftNode == null ? null : leftNode.element;
			E rightE = rightNode == null ? null : rightNode.element;
			E parentE = parentNode == null ? null : parentNode.element;
			return "Node [leftNode=" + leftE + ", rightNode=" + rightE
					+ ", parentNode=" + parentE + ", element=" + element
					+ ", balanceFactor=" + balanceFactor + "]";
		}
	}
}
