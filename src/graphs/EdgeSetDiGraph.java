package graphs;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Each EdgeSetDiGraph object is a directed graph whose elements and edge
 * attributes are arbitrary objects.This directed graph is represented by a node
 * set and edge set as follows. FirstNode is a link to the first node of a DLL
 * of EdgeSetDiGraph.Node objects, each of which contains an element, firstEdge
 * is a link to the first node of a DLL of EdgeSetDiGraph.Edge objects, each of
 * which contains an edge attribute and is linked to the edge's source and
 * destination nodes, size contains the graph's size.
 * 
 * @author ANKIT DAIM
 * 
 * @param <E>
 */
public class EdgeSetDiGraph<E> implements DiGraph<E> {

	private Node<E> firstNode;
	private Edge firstEdge;
	private int size;

	public EdgeSetDiGraph() {
		firstEdge = null;
		firstNode = null;
		size = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.Graph#size()
	 */
	@Override
	public int size() {
		return this.size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.Graph#degree(graphs.Graph.Node)
	 */
	@Override
	public int degree(graphs.Graph.Node<E> node) {
		return outDegree(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.Graph#containsEdge(graphs.Graph.Node, graphs.Graph.Node)
	 */
	@Override
	public boolean containsEdge(graphs.Graph.Node<E> sourceNode,
			graphs.Graph.Node<E> destinationNode) {
		Node<E> source = (Node<E>) sourceNode;
		Node<E> dest = (Node<E>) destinationNode;
		for (Edge e = firstEdge; e != null; e = e.nextEdge)
			if (e.source.equals(source) && e.dest.equals(dest))
				return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.Graph#clear()
	 */
	@Override
	public void clear() {
		firstEdge = null;
		firstNode = null;
		size = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.Graph#addNode(java.lang.Object)
	 */
	@Override
	public graphs.Graph.Node<E> addNode(E e) {
		Node<E> newNode = new Node<>(e, null, firstNode);
		if (firstNode != null)
			firstNode.prevNode = newNode;
		firstNode = newNode;
		size++;
		return newNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.Graph#addEdge(graphs.Graph.Node, graphs.Graph.Node)
	 */
	@Override
	public graphs.Graph.Edge addEdge(graphs.Graph.Node<E> sourceNode,
			graphs.Graph.Node<E> destinationNode) {
		return addEdge(sourceNode, destinationNode, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.Graph#addEdge(graphs.Graph.Node, graphs.Graph.Node,
	 * java.lang.Object)
	 */
	@Override
	public graphs.Graph.Edge addEdge(graphs.Graph.Node<E> sourceNode,
			graphs.Graph.Node<E> destinationNode, Object attribute) {
		Node<E> source = (Node<E>) sourceNode;
		source.outDegree++;
		Node<E> dest = (Node<E>) destinationNode;
		Edge newEdge = new Edge(attribute, source, dest, null, firstEdge);
		if (firstEdge != null)
			firstEdge.prevEdge = newEdge;
		firstEdge = newEdge;
		return newEdge;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.Graph#removeNode(graphs.Graph.Node)
	 */
	@Override
	public void removeNode(graphs.Graph.Node<E> node) {
		Node<E> tempNode = (Node<E>) node;
		if (tempNode.prevNode == null)
			firstNode = tempNode.nextNode;
		else
			tempNode.prevNode.nextNode = tempNode.nextNode;
		if (tempNode.nextNode != null)
			tempNode.nextNode.prevNode = tempNode.prevNode;

		// to remove all edges that has this node as either source or
		// destination..

		for (Edge e = firstEdge; e != null; e = e.nextEdge)
			if (e.source.equals(tempNode) || e.dest.equals(tempNode))
				removeEdge(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.Graph#removeEdge(graphs.Graph.Edge)
	 */
	@Override
	public void removeEdge(graphs.Graph.Edge edge) {
		Edge tempEdge = (Edge) edge;
		if (tempEdge.prevEdge == null)
			firstEdge = tempEdge.nextEdge;
		else
			tempEdge.prevEdge.nextEdge = tempEdge.nextEdge;
		if (tempEdge.nextEdge != null)
			tempEdge.nextEdge.prevEdge = tempEdge.prevEdge;
		tempEdge.nextEdge = tempEdge.prevEdge = null;// help GC
		tempEdge.attribute = null;
		tempEdge.dest = tempEdge.source = null;
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private abstract class NodeItr implements Iterator<Graph.Node<E>> {

		private Node<E> lastReturned = null;
		private Node<E> next;
		private int nextIndex;

		public NodeItr() {
			next = firstNode;
			nextIndex = 0;
		}

		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		public Node<E> nextEntry() {
			if (!hasNext())
				throw new NoSuchElementException();
			lastReturned = next;
			next = next.nextNode;
			nextIndex++;
			return lastReturned;
		}

		@Override
		public void remove() {
			if (lastReturned == null)
				throw new IllegalStateException();
			next = lastReturned;
			removeNode(lastReturned);
			lastReturned = null;
			nextIndex--;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.Graph#nodes()
	 */
	@Override
	public Iterator<graphs.Graph.Node<E>> nodes() {
		return new NodeIterator();
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private class NodeIterator extends NodeItr {

		@Override
		public Node<E> next() {
			return nextEntry();
		}
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private abstract class EdgeItr<T> implements Iterator<T> {

		Edge lastReturned = null;
		Edge next;

		public EdgeItr() {
			next = firstEdge;
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		public Edge nextEdge() {
			if (!hasNext())
				throw new NoSuchElementException();
			lastReturned = next;
			next = next.nextEdge;
			return lastReturned;
		}

		@Override
		public void remove() {
			if (lastReturned == null)
				throw new IllegalStateException();
			next = lastReturned;
			removeEdge(lastReturned);
			lastReturned = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.Graph#edges()
	 */
	@Override
	public Iterator<graphs.Graph.Edge> edges() {
		return new EdgeIterator();
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private class EdgeIterator extends EdgeItr<Graph.Edge> {

		@Override
		public Edge next() {
			return nextEdge();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.Graph#neighbours(graphs.Graph.Node)
	 */
	@Override
	public Iterator<graphs.Graph.Node<E>> neighbours(graphs.Graph.Node<E> node) {
		return new NeighBourItr((Node<E>) node);
	}

	private class NeighBourItr extends EdgeItr<Graph.Node<E>> {
		Node<E> node, neighBour;
		Edge edge;

		/**
		 * @param node2
		 */
		public NeighBourItr(Node<E> node) {
			super();
			this.node = node;
		}

		@SuppressWarnings("unchecked")
		@Override
		public graphs.Graph.Node<E> next() {

			edge = nextEdge();
			for (;;) {
				if (edge == null)
					return null;
				if (edge.source.equals(node))
					return neighBour = (Node<E>) edge.dest;
				else if (edge.dest.equals(node))
					return neighBour = (Node<E>) edge.source;
				else
					edge = nextEdge();
			}
		}

		@Override
		public void remove() {
			if (lastReturned == null)
				throw new IllegalStateException();
			next = lastReturned;
			removeNode(neighBour);
			lastReturned = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.Graph#connectingEdges(graphs.Graph.Node)
	 */
	@Override
	public Iterator<graphs.Graph.Edge> connectingEdges(graphs.Graph.Node<E> node) {
		return new ConnectingEdgeItr((Node<E>) node);
	}

	private class ConnectingEdgeItr extends EdgeItr<Graph.Edge> {
		Node<E> node;
		Edge edge;

		/**
		 * @param node
		 */
		public ConnectingEdgeItr(Node<E> node) {
			super();
			this.node = node;
		}

		@Override
		public graphs.Graph.Edge next() {
			edge = nextEdge();
			for (;;) {
				if (edge == null)
					return null;
				if (edge.source.equals(node) || edge.dest.equals(node))
					return edge;
				else
					edge = nextEdge();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.DiGraph#outDegree(graphs.Graph.Node)
	 */
	@Override
	public int outDegree(graphs.Graph.Node<E> node) {
		return ((Node<E>) node).outDegree;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.DiGraph#successors(graphs.Graph.Node)
	 */
	@Override
	public Iterator<graphs.Graph.Node<E>> successors(graphs.Graph.Node<E> node) {
		return new SuccessorItr((Node<E>) node);
	}

	/**
	 * @author ANKIT DAIM
	 */
	private class SuccessorItr extends NeighBourItr {

		public SuccessorItr(Node<E> node) {
			super(node);
		}

		@SuppressWarnings("unchecked")
		@Override
		public graphs.Graph.Node<E> next() {
			edge = nextEdge();
			for (;;) {
				if (edge == null)
					return null;
				if (edge.source.equals(node))
					return neighBour = (Node<E>) edge.dest;
				else
					edge = nextEdge();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphs.DiGraph#outEdges(graphs.Graph.Node)
	 */
	@Override
	public Iterator<graphs.Graph.Edge> outEdges(graphs.Graph.Node<E> node) {
		return new OutEdgeItr((Node<E>) node);
	}

	/**
	 * @author ANKIT DAIM
	 * 
	 */
	private class OutEdgeItr extends ConnectingEdgeItr {

		public OutEdgeItr(Node<E> node) {
			super(node);
		}

		@Override
		public graphs.Graph.Edge next() {
			edge = nextEdge();
			for (;;) {
				if (edge == null)
					return null;
				if (edge.source.equals(node))
					return edge;
				else
					edge = nextEdge();
			}

		}
	}

	/**
	 * @author ANKIT DAIM
	 *
	 * @param <E>
	 */
	private static class Node<E> implements Graph.Node<E> {

		private E e;
		private int outDegree;
		private Node<E> prevNode, nextNode;

		/**
		 * @param e
		 * @param outDegree
		 * @param prevNode
		 * @param nextNode
		 */
		public Node(E e, Node<E> prevNode, Node<E> nextNode) {
			super();
			this.e = e;
			this.prevNode = prevNode;
			this.nextNode = nextNode;
		}

		@Override
		public E getElement() {
			return e;
		}

		@Override
		public void setElement(E e) {
			this.e = e;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			E prevE = prevNode == null ? null : prevNode.e;
			E nextE = nextNode == null ? null : nextNode.e;
			return "Node [e=" + e + ", outDegree=" + outDegree + ", prevNode="
					+ prevE + ", nextNode=" + nextE + "]";
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
			result = prime * result + ((e == null) ? 0 : e.hashCode());
			result = prime * result
					+ ((nextNode == null) ? 0 : nextNode.e.hashCode());
			result = prime * result + outDegree;
			result = prime * result
					+ ((prevNode == null) ? 0 : prevNode.e.hashCode());
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
			if (e == null) {
				if (other.e != null) {
					return false;
				}
			} else if (!e.equals(other.e)) {
				return false;
			}
			if (nextNode == null) {
				if (other.nextNode != null) {
					return false;
				}
			} else if (!nextNode.e.equals(other.nextNode.e)) {
				return false;
			}
			if (outDegree != other.outDegree) {
				return false;
			}
			if (prevNode == null) {
				if (other.prevNode != null) {
					return false;
				}
			} else if (!prevNode.e.equals(other.prevNode.e)) {
				return false;
			}
			return true;
		}
	}

	/**
	 * @author ANKIT DAIM
	 *
	 */
	private static class Edge implements Graph.Edge {
		private Object attribute;
		private Node<?> source, dest;
		private Edge prevEdge, nextEdge;

		/**
		 * @param attribute
		 * @param source
		 * @param dest
		 * @param prevEdge
		 * @param nextEdge
		 */
		public Edge(Object attribute, Node<?> source, Node<?> dest,
				Edge prevEdge, Edge nextEdge) {
			super();
			this.attribute = attribute;
			this.source = source;
			this.dest = dest;
			this.prevEdge = prevEdge;
			this.nextEdge = nextEdge;
		}

		@Override
		public graphs.Graph.Node<?>[] getNodes() {
			return null;
		}

		@Override
		public Object getAttribute() {
			return attribute;
		}

		@Override
		public void setAttribute(Object attribute) {
			this.attribute = attribute;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Edge [attribute=" + attribute + ", source=" + source.e
					+ ", dest=" + dest.e + "]";
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
					+ ((attribute == null) ? 0 : attribute.hashCode());
			result = prime * result + ((dest == null) ? 0 : dest.e.hashCode());
			result = prime * result
					+ ((source == null) ? 0 : source.e.hashCode());
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
			Edge other = (Edge) obj;
			if (attribute == null) {
				if (other.attribute != null) {
					return false;
				}
			} else if (!attribute.equals(other.attribute)) {
				return false;
			}
			if (dest == null) {
				if (other.dest != null) {
					return false;
				}
			} else if (!dest.e.equals(other.dest.e)) {
				return false;
			}
			if (source == null) {
				if (other.source != null) {
					return false;
				}
			} else if (!source.e.equals(other.source.e)) {
				return false;
			}
			return true;
		}
	}
}