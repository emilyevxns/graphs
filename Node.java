
public class Node<N> implements INode<N>{
	N label;
	
	/**
	 * Constructs a new node
	 * @param l label for node
	 */
	public Node(N l) {
		label = l;
	}
	/** Gets the value at a specific node
     *@return the value at this node
     */
	@Override
	public N getValue() {
		// TODO Auto-generated method stub
		return label;
	}
	
	/**
	 * Sets label for node
	 * @param l label for node
	 */
	public void setLabel(N l) {
		label = l;
	}

}
