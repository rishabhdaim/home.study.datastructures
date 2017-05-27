package trees;

import java.util.Iterator;

//1. It must be possible to access the root of a tree.
//2. It must be possible to access all the ancestors of any node in a tree.
//3. It must be possible to access all the descendants of any node in a tree.
//4. It must be possible to add a new node to a tree, either as the root node or as the child of an existing node.
//5. It must be possible to remove a given node from a tree, together with all its descendants.
//6. It must be possible to traverse a tree.

/**
 * 
 * Each Tree object is a tree whose elements are arbitrary objects.
 * 
 * @author ANKIT DAIM
 * 
 * @param <E>
 */
public interface Tree<E> {

	/**
	 * @return Return the root node of this tree, or null if this tree is
	 *         empty..
	 */
	public Tree.Node<E> root();

	/**
	 * @param node
	 * @return Return the parent of node in this tree, or null if node is the
	 *         root node.
	 */
	public Tree.Node<E> parent(Tree.Node<E> node);

	/**
	 * @param node
	 * @return Return the number of children of node in this tree.
	 */
	int childCount(Tree.Node<E> node);

	/**
	 * @param element
	 *            Make this tree consist of just a root node containing element
	 *            element..
	 */
	void makeRoot(E element);

	/**
	 * Add a new node containing element E as a child of node in this tree, and
	 * return the new node. The new node has no children of its own.
	 * 
	 * @param node
	 * @param element
	 * @return the new node..
	 */
	public Tree.Node<E> addChild(Tree.Node<E> node, E element);

	/**
	 * @param node
	 *            Remove node from this tree, together with all its descendants.
	 */
	public void remove(Tree.Node<E> node);

	/**
	 * @param node
	 * @return Return an iterator that will visit all the children of node in
	 *         this tree.
	 */
	public Iterator<E> children(Tree.Node<E> node);

	/**
	 * @return Return an iterator that will visit all nodes of this tree, in
	 *         pre-order (i.e., each node is visited before its children).
	 */
	public Iterator<E> nodesPreOrder();

	/**
	 * @return Return an iterator that will visit all nodes of this tree, in
	 *         post-order (i.e., each node is visited after its children).
	 */
	public Iterator<E> nodesPostOrder();

	/**
	 * @param e
	 *            the element whose node to be searched in this tree..
	 * @return Node<E> corresponding to this element..
	 */
	Node<E> searchNode(E e);

	/**
	 * @param node
	 *            In which greatest node is to be found..
	 * @return greatest node in this node..
	 */
	Node<E> greatestNode(Tree.Node<E> node);

	/**
	 * @param node
	 *            In which smallest node is to be found..
	 * @return smallest node in this node..
	 */
	Node<E> smallestNode(Tree.Node<E> node);

	/**
	 * @return size i.e. no. of nodes of this tree..
	 */
	int size();

	/**
	 * @return return true if and only if size of this tree is zero..
	 */
	boolean isEmpty();

	/**
	 * Each Tree.Node object is a node of a tree, and contains a single element.
	 * 
	 * @author ANKIT DAIM
	 * 
	 * @param <E>
	 */
	public interface Node<E> {

		// Return the element contained in this node.
		public E getElement();

		// Change the element contained in this node to be elem.
		public void setElement(E elem);

	}
}
