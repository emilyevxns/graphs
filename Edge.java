
public class Edge<N, E> implements IEdge<N, E> {
	INode source;
	INode destination;
	E label;
	/**
	 * Constructs a new edge
	 * @param src source node
	 * @param dst destination node
	 * @param e label for node
	 */
	public Edge(INode src, INode dst, E e) {
		this.source = src;
		this.destination = dst;
		label = e;
	}
	 /** Returns the label on an edge
     *@return edge label
     */
	@Override
	public E getLabel() {
		// TODO Auto-generated method stub
		return label;
	}

	 /** Returns the source node
     *@return edge's source
     */
	@Override
	public INode<N> getSource() {
		// TODO Auto-generated method stub
		return source;
	}

	/** Return the destination node
     *@return edge's destination
     */
	@Override
	public INode<N> getDestination() {
		// TODO Auto-generated method stub
		return destination;
	}

}
