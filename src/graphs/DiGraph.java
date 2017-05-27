package graphs;

import java.util.Iterator;

/**
 * Each DiGraph object is a directed graph whose elements and edge attributes
 * are arbitrary objects.
 * 
 * @author ANKIT DAIM
 * 
 * @param <E>
 */
public interface DiGraph<E> extends Graph<E> {

	/**
	 * @param node
	 *            whose outDegree had to be found
	 * @return Return the number of out-edges of node in this directed graph.
	 */
	int outDegree(Node<E> node);

	/**
	 * @param node
	 *            whose iterator had to be get
	 * @return Return an iterator that will visit all successors of node in this
	 *         directed graph, in no particular order.
	 */
	Iterator<Node<E>> successors(Node<E> node);

	/**
	 * @param node
	 *            whose outEdges had to be found..
	 * @return Return an iterator that will visit all out-edges of node in this
	 *         directed graph, in no particular order.
	 */
	Iterator<Edge> outEdges(Node<E> node);
}
