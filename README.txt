This directory contains the java source files for a project to implement a graph using a node/edge representation

You must complete 1 graph implementation.
Graph.java - A directed graph implementation that allows edges to be labeled/weighted.

Your graph must implement the IGraph interface; which also means your nodes must implement the INode interface and your edges must implement the IEdge interface.

Instead of a Test class, this project is distributed with GraphReader. GraphReader shows how the Graph constructor will be called and uses the Graph class to read and write graph files. The format of graph files is covered in the GraphReader javadoc and two example files, ring.graph and tree.graph, are provided. GraphReader requires you provide an ArrayList implementation conforming to the IList interface.

To generate the javadoc for this project:
> javadoc -d doc *.java

Running GraphReader:
> java GraphReader ring.graph ring.out node0
The above execution of GraphReader will read the file ring.graph and write the file ring.out. The numeric node identifiers may be different between the input and output file but the same nodes and edges should be present in both files.

Files:
README.txt                  - This text file
IGraph.java                 - An interface for classes that implement a graph
INode.java                  - An interface for graph nodes
IEdge.java                  - An interface for graph edges
NoSuchEdgeException.java    - Exception indicating that an edge is not in the graph
NoSuchNodeException.java    - Exception indicating that a node is not in the graph
GraphReader.java            - Program that uses a Graph implementation to read/write files
BadGraphFileException.java	- Exception used by the GraphReader for bad files
IList.java                  - The list interface used for lists returned by the graph
InvalidLocationException.java - Exception used by the IList interface
BadIndexException.java		  - Exception used by the IList interface
ElementNotFoundException.java - Exception used by the IList interface
ring.graph                  - Simple graph file shaped like a ring
tree.graph                  - Simple graph file shaped like a tree
ArrayList.java		    - Implementation of an ArrayList
Edge.java                   - Implementation for graph edges           
Node.java                   - Implementation for graph nodes
Graph.java                  - Implementation for a graph