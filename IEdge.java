
/** Interface for graph edges */
public interface IEdge<N,E> {
    /** Returns the label on an edge
     *@return edge label
     */
    public E getLabel();
    
    /** Returns the source node
     *@return edge's source
     */
    public INode<N> getSource();
    
    /** Return the destination node
     *@return edge's destination
     */
    public INode<N> getDestination();
}