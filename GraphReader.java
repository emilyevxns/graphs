
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// Standard JAVA collections classes are not allowed for programming assignments
// in this course. Included here since this is instructor code and the dictionary
// assignment was not required. If the dictionary assignment had been required, 
// the IDict interface would have been used instead.
import java.util.HashMap;

/** A tool for reading files that describe graphs
 *  <p>GraphReader can be run stand alone to provide a basic functionality test of 
 *  IGraph implementations. If an IGraph is needed in some other application, a
 *  GraphReader instance can be used to read a graph from file. IGraph reader only 
 *  produces graphs with String labels on nodes and Integer labels on edges.
 *  <p>Graph files are plaintext files. Lines starting with a # are comments. Non-
 *  comment lines start with a line type identifier followed by the fields needed
 *  for that type of line.
 *  <ul>
 *  <li><b>node lines</b> - Lines declaring nodes start with the character 'N'. 
 *  Following the 'N' is the numeric identifier for the node that will be used 
 *  else where in the file. Numeric identifiers must be integers. After the 
 *  node identifier, the next continuous sequence of non-whitespace characters
 *  are the node value. 
 *  <li><b>edge lines</b> - Lines declaring edges start with the character 'E'.
 *  Following the 'E' are the numeric identifiers for the source and destination
 *  of the edge. Last, an integer is expected for the edge label.
 */
public class GraphReader {
    // Regex patterns to recognize node and edge lines
    private static final Pattern NODELINE=Pattern.compile("N\\s+(\\d+)\\s+(\\S+)");
    private static final Pattern EDGELINE=Pattern.compile("E\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)");
    
    /** Instantiate a new GraphReader */
    public GraphReader() {
    }
    
    /** Produce a graph instance from a graph file
     *@param filename file to read from
     *@return the graph instance
     *@throws BadGraphFileException if the file cannot be used
     */
    public IGraph<String,Integer> readFile(String filename) throws BadGraphFileException {
        // Make a new Graph instance to work with
        IGraph ret = new Graph<String,Integer>();
        // Open the file for reading
        try {
            Scanner s = new Scanner(new File(filename));
            // Setup a hashmap since nodes instances will need to be looked up
            HashMap<Integer,INode<String>> nodes = new HashMap<Integer,INode<String>>();
            // Read each line from the file
            while(s.hasNextLine()) {
                String raw = s.nextLine();
                // Comment line... skip
                if(raw.charAt(0)=='#') { continue; }
                // Node line... process into the graph and keep a mapping from id to node
                // instance for use in constructing edges
                if(raw.charAt(0)=='N') {
                    Matcher m = NODELINE.matcher(raw);
                    if(!m.matches()) {
                        throw new BadGraphFileException(filename,"Bad node line: "+raw);
                    }
                    Integer   nid = new Integer(m.group(1));
                    String  label = m.group(2);
                    if(nodes.containsKey(nid)) {
                        throw new BadGraphFileException(filename,"Duplicate node: "+raw);
                    }
                    INode<String> n = ret.newNode(label);
                    nodes.put(nid,n);
                    continue;
                }
                // Edge line... process into an edge
                if(raw.charAt(0)=='E') {
                    Matcher m = EDGELINE.matcher(raw);
                    if(!m.matches()) {
                        throw new BadGraphFileException(filename,"Bad edge line: "+raw);
                    }
                    Integer sid = new Integer(m.group(1));
                    Integer eid = new Integer(m.group(2));
                    Integer lbl = new Integer(m.group(3));
                    INode<String> sn = nodes.get(sid);
                    INode<String> en = nodes.get(eid);
                    if(sn==null || en==null) {
                        throw new BadGraphFileException(filename,"Undeclared nodes in: "+raw);
                    }
                    ret.newEdge(sn, en, lbl);
                    continue;
                }
                // Bad file format...
                throw new BadGraphFileException(filename,"Malformed line: "+raw);
            }
            return ret;
        } catch(FileNotFoundException err) {
            throw new BadGraphFileException(filename, "File does not exist");
        }
    }
    
    /** Write a connected subgraph to disk
     *@param graph graph instance to write
     *@param start node to start writing with
     *@param filename file to write to
     */
    public void writeFile(IGraph<String,Integer> graph, String start, String filename) throws IOException { 
        // Node counter
        int nodeCounter = 0;
        // File stream to write to
        FileWriter fw = new FileWriter(filename);
        // Get the starting nodes
        IList<INode<String>> slist = graph.getNodes(start);
        // Gather all the connected nodes
        HashMap<INode<String>,Integer> nodes = new HashMap<INode<String>,Integer>();
        for(int i=0; i<slist.size(); i++) {
            nodeCounter = traverseGraph(nodeCounter, fw, graph, nodes, slist.get(i));
        }
        fw.close();
    }
    
    /** Use recursion to spool the graph to a file
     *  <p>Strategy for visiting all nodes starting from a particular source node
     *  is based on floodfill and DFS algorithms.
     *@param nodeCounter next node id for the file
     *@param file where to write the data
     *@param graph graph to dump
     *@param nodes hashmap of nodes already visited
     *@param node search from this node
     */
    private int traverseGraph(int counter, FileWriter file, IGraph<String,Integer> graph, HashMap<INode<String>,Integer> nodes, INode<String> src) throws IOException {
        // Already visited this node
        if(nodes.containsKey(src)) { return counter; }
        // Add the node to the visit set
        nodes.put(src, counter);
        file.write("N "+counter+" "+src.getValue()+"\n");
        counter++;
        // Get all the edges starting here
        IList<IEdge<String,Integer>> edges = graph.getEdgesFrom(src);
        for(int i=0; i<edges.size(); i++) {
            INode<String> dst = edges.get(i).getDestination();
            counter = traverseGraph(counter, file, graph, nodes, dst);
            file.write("E "+nodes.get(src)+" "+nodes.get(dst)+" "+edges.get(i).getLabel()+"\n");
        }
        return counter;
    }
        
    
    /** Test the GraphReader by reading a graph file from disk
     *@args command line arguments
     */
    public static void main(String[] args) {
        if(args.length!=3) {
            System.err.println("Usage: java GraphReader <in filename> <out filename> <start node>");
            return;
        }
        try {
            GraphReader gr = new GraphReader();
            IGraph g = gr.readFile(args[0]);
            gr.writeFile(g, args[2], args[1]);
        } catch(BadGraphFileException err) {
            System.out.println("Oops! There appears to be something wrong with the graph file.");
            System.out.println(err.getMessage());
            err.printStackTrace();
        } catch(IOException ioe) {
            System.out.println("Oops! Something went wrong when trying to write the file.");
            ioe.printStackTrace();
        }
    }
}