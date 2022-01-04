
/** Interface for a node/edge graph representation */
public interface IGraph<N,E> {
    /** Creates a new node instance in the graph
     *  <p>Returns the exact node instance created
     *@param v the value stored at this node
     *@return the new node instance
     */
    public INode<N> newNode(N v);
    
    /** Deletes a node from the graph
     *  <p>The exact node instance will be passed in. Use == rather than equals
     *  to detect a match. When a node is deleted, all edges involving the node 
     *  should also be deleted.
     *@param n the node instance to delete from the graph
     *@throws NoSuchNodeException thrown if the node is not in the graph
     */
    public void deleteNode(INode<N> n);
    
    /** Gets all nodes in the graph containing a particular value
     *  <p>Returns the exact node instances matching the value
     *@param v the value to find
     *@return list of all nodes in the graph with value v
     */
    public IList<INode<N>> getNodes(N v);
    
    /** Gets all edges sourced at a particular node
     *  <p>Returns the exact edge instances
     *@param n the source node for the edge
     *@return all edges with a particular source
     *@throws NoSuchNodeException thrown if the node is not in the graph
     */
    public IList<IEdge<N,E>> getEdgesFrom(INode<N> n);
    
    /** Gets all edges ending at a particular node
     *  <p>Returns the exact edge instances
     *@param n the destination node for the edge
     *@return all edges with a particular destination
     *@throws NoSuchNodeException thrown if the node is not in the graph
     */
    public IList<IEdge<N,E>> getEdgesTo(INode<N> n);
    
    /** Creates a new edge in the graph
     *  <p>Returns the exact edge instance created
     *@param src source node for the new edge
     *@param dst destination node for the new edge
     *@param label label for the edge
     *@return the new edge
     *@throws NoSuchNodeException thrown if either node is not already in the graph
     */
    public IEdge<N,E> newEdge(INode<N> src, INode<N> dst, E label);
    
    /** Deletes an existing edge from the graph
     *  <p>The exact edge instance will be passed in. Use == rather than equals
     *  to detect a match. Edge deletion never deletes nodes from the graph.
     *@param e the edge to delete
     *@throws NoSuchEdgeException thrown if the edge is not in the graph
     */
    public void deleteEdge(IEdge<N,E> e);
}