package graphs;

import java.util.Iterator;

//1. It must be possible to make a graph empty.
//2. It must be possible to add a new node, or to add a new edge connecting two existing nodes, to a graph.
//3. It must be possible to remove a given node (together with all its connecting edges), 
//or to remove a given edge, from a graph.
//4. It must be possible to test whether a graph has an edge connecting two given nodes,
//5. It must be possible to access all nodes, or all edges, of a graph.
//6. It must be possible to access all neighbors, or all connecting edges, of a given node in a graph.
//7. It must be possible to inspect or update the element contained in a given node, 
//or to inspect or update the attribute (if any) contained in a given edge, of a graph.
//8. It must be possible to access all successors, or all out-edges, of a given node in a directed graph.
/**
 * Each Graph object is a (directed or undirected) graph whose elements and edge
 * attributes are arbitrary objects.
 * 
 * @author ANKIT DAIM
 * 
 * @param <E>
 */
public interface Graph<E> {
	/**
	 * @return Return the number of nodes in this graph.
	 */
	int size();

	/**
	 * @param node
	 *            node, whose edges are to be found..
	 * @return Return the number of edges connecting node in this graph.
	 */
	int degree(Node<E> node);

	/**
	 * @param sourceNode
	 *            source Node
	 * @param destinationNode
	 *            destination Node
	 * @return Return true if and only if there is an edge connecting sourceNode
	 *         and destinationNode in this graph. (If the graph is directed,
	 *         node0 is the edge's source and node1 is its destination.)
	 */
	boolean containsEdge(Node<E> sourceNode, Node<E> destinationNode);

	/**
	 * Make this graph empty.
	 */
	void clear();

	/**
	 * Add to this graph a new node containing element elem, but with no
	 * connecting edges, and return the new node.
	 * 
	 * @param e
	 *            element to be added into this new Node
	 * @return new Node added to this graph
	 */
	Node<E> addNode(E e);

	/**
	 * Add to this graph a new edge connecting sourceNode and destinationEdge,
	 * but containing no attribute, and return the new edge. (If the graph is
	 * directed,sourceNode is the edge's source and destinationEdge is its
	 * destination.)
	 * 
	 * @param sourceNode
	 *            source Node
	 * @param destinationNode
	 *            destination Node
	 * @return new added edge between source & destination Node
	 */
	Edge addEdge(Node<E> sourceNode, Node<E> destinationNode);

	/**
	 * Add to this graph a new edge connecting sourceNode and destinationEdge,
	 * and containing attribute , and return the new edge. (If the graph is
	 * directed,sourceNode is the edge's source and destinationEdge is its
	 * destination.)
	 * 
	 * @param sourceNode
	 * @param destinationNode
	 * @param attribute
	 * @return
	 */
	Edge addEdge(Node<E> sourceNode, Node<E> destinationNode, Object attribute);

	/**
	 * @param node
	 */
	void removeNode(Node<E> node);

	/**
	 * @param edge
	 */
	void removeEdge(Edge edge);

	/**
	 * @return Return an iterator that will visit all nodes of this graph, in no
	 *         particular order.
	 */
	Iterator<Node<E>> nodes();

	/**
	 * @return Return an iterator that will visit all edges of this graph, in no
	 *         particular order.
	 */
	Iterator<Edge> edges();

	/**
	 * @param node
	 *            whose neighbours are to be found..
	 * @return Return an iterator that will visit ail neighbours of node in this
	 *         graph,in no particular order.
	 */
	Iterator<Node<E>> neighbours(Node<E> node);

	/**
	 * @param node
	 * @return Return an iterator that will visit all connecting edges of node
	 *         in this graph, in no particular order.
	 */
	Iterator<Edge> connectingEdges(Node<E> node);

	/**
	 * 
	 * Each Graph. Node object is a graph node, and contains a single element.
	 * 
	 * @author ANKIT DAIM
	 * 
	 * @param <E>
	 */
	interface Node<E> {
		/**
		 * @return Return the element contained in this node.
		 */
		E getElement();

		/**
		 * Change the element contained in this node to e.
		 * 
		 * @param e
		 *            element to be changed to
		 */
		void setElement(E e);
	}

	/**
	 * 
	 * Each Graph. Edge object is a graph edge, and optionally contains an
	 * attribute.
	 * 
	 * @author ANKIT DAIM
	 * 
	 */
	interface Edge {
		/**
		 * @return Return an array containing the two nodes connected by this
		 *         edge. (If the graph is directed, the array will contain the
		 *         edge's source and destination nodes, in that order.)
		 */
		@SuppressWarnings("rawtypes")
		Node[] getNodes();

		/**
		 * @return Return the attribute contained in this edge, or null if there
		 *         is none.
		 */
		Object getAttribute();

		/**
		 * Change the attribute contained in this edge to attribute.
		 * 
		 * @param attribute
		 *            new attribute..
		 */
		void setAttribute(Object attribute);
	}
}
