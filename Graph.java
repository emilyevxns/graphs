
public class Graph<T1, T2> implements IGraph {
	ArrayList<Node> nodes;
	ArrayList<Edge> edges;
	
	/**
	 * Constructs an empty graph
	 */
	public Graph() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
	}
	
	 /** Creates a new node instance in the graph
     *  <p>Returns the exact node instance created
     *@param v the value stored at this node
     *@return the new node instance
     */
	@Override
	public INode newNode(Object v) {
		// TODO Auto-generated method stub
		// Make a new node
		Node n = new Node(v);
		// Add to list of nodes
		nodes.append(n);
		// Return new node
		return n;
	}

	/** Deletes a node from the graph
     *  <p>The exact node instance will be passed in. Use == rather than equals
     *  to detect a match. When a node is deleted, all edges involving the node 
     *  should also be deleted.
     *@param n the node instance to delete from the graph
     *@throws NoSuchNodeException thrown if the node is not in the graph
     */
	@Override
	public void deleteNode(INode n) {
		// Remove all edges that contain that node 
		// TODO Auto-generated method stub
		int count = 0;
		// Loop through list to find desired node 
		for (int i = 0; i < nodes.size; i++) {
			INode delete = nodes.get(i);
			if (delete == n) {
				nodes.delete(i);
				ArrayList<Edge> temp = new ArrayList<Edge>();
				// Loop through list to find what we want
				for (int j = 0; j < edges.size; j++) {
					INode source = edges.get(j).getSource();
					if (source == n) {
						// Get node value from array list 
						Edge s = edges.get(j);
						edges.delete(j);
					}
				}
				count++;
				return;
			}
		}
		if (count == 0) {
			throw new NoSuchNodeException();
		}
		else {
		return;
		}
	}

	/** Gets all nodes in the graph containing a particular value
     *  <p>Returns the exact node instances matching the value
     *@param v the value to find
     *@return list of all nodes in the graph with value v
     */
	@Override
	public IList getNodes(Object v) {
		// TODO Auto-generated method stub
		// Create new list to hold matching elements 
		ArrayList<Node> n = new ArrayList<Node>();
		// Find node matching data by scanning the list 
		for (int i = 0; i < nodes.size; i++) {
			if (nodes.get(i).getValue() == v) {
				Node x = nodes.get(i);
				n.append(x);
			}
		}
		return n;
	}

	/** Gets all edges sourced at a particular node
     *  <p>Returns the exact edge instances
     *@param n the source node for the edge
     *@return all edges with a particular source
     *@throws NoSuchNodeException thrown if the node is not in the graph
     */
	@Override
	public IList getEdgesFrom(INode n) {
		// TODO Auto-generated method stub
		int count = 0;
		// New list to hold only what we want to return
		ArrayList<Edge> temp = new ArrayList<Edge>();
		// Loop through list to find what we want
		for (int i = 0; i < edges.size; i++) {
			INode source = edges.get(i).getSource();
			if (source == n) {
				// Get node value from arraylist 
				Edge s = edges.get(i);
				// Add node to new array
				temp.append(s);
				count++;
			}
		}
		if (count == 0) {
			throw new NoSuchNodeException();
		}
		else {
		// All edges checked, return matching ones
		return temp;
		}
	}

	/** Gets all edges ending at a particular node
     *  <p>Returns the exact edge instances
     *@param n the destination node for the edge
     *@return all edges with a particular destination
     *@throws NoSuchNodeException thrown if the node is not in the graph
     */
	@Override
	public IList getEdgesTo(INode n) {
		// TODO Auto-generated method stub
		int count = 0;

		for (int i = 0; i < nodes.size; i++) {
			INode node = nodes.get(i);
			if (node == n) {
				count++;
			}
		}
		// Loop through the edges add matches to temp 
		// New list to hold only what we want to return
		ArrayList<Edge> temp = new ArrayList<Edge>();
		for (int i = 0; i < edges.size; i++) {
			INode destination = edges.get(i).getDestination();
			if (destination == n) {
				count++;
				// Get value of node from array list 
				Edge d = edges.get(i);
				// Add node to new array 
				temp.append(d);
			}
		}
		if (count == 0) {
			throw new NoSuchNodeException();
		}
		else {
		// All edges checked, return matching ones
		return temp;
		}
	}

	/** Creates a new edge in the graph
     *  <p>Returns the exact edge instance created
     *@param src source node for the new edge
     *@param dst destination node for the new edge
     *@param label label for the edge
     *@return the new edge
     *@throws NoSuchNodeException thrown if either node is not already in the graph
     */
	@Override
	public IEdge newEdge(INode src, INode dst, Object label) {
		// TODO Auto-generated method stub
//		INode s = new Node(src);
//		INode d = new Node(dst);
//		boolean found = false;
//		for (int i = 0; i < nodes.size; i++) {
//			INode check = nodes.get(i);
//			if (check == s || check == d) {
//				found = true;
//			}
//		}
//		if (!found) {
//			throw new NoSuchNodeException();
//		}
//		else {
			// Make a new edge 
			Edge e = new Edge(src, dst, label);
			// Add to edge list
			edges.append(e);
			return e;
		}
	

	/** Deletes an existing edge from the graph
     *  <p>The exact edge instance will be passed in. Use == rather than equals
     *  to detect a match. Edge deletion never deletes nodes from the graph.
     *@param e the edge to delete
     *@throws NoSuchEdgeException thrown if the edge is not in the graph
     */
	@Override
	public void deleteEdge(IEdge e) {
	// TODO Auto-generated method stub
		int count = 0;
	// Loop through list to find desired node 
	for (int i = 0; i < edges.size; i++) {
		IEdge delete = edges.get(i);
		if (delete == e) {
			edges.delete(i);
			count++;
			return;
		}
	}
	// If count was never incremented, edge was never found 
	if (count == 0) {
		throw new NoSuchEdgeException();
	}
	return;
	}	

}
