import java.util.ArrayList;
import java.util.List;

/**
 * Represents a vertex on a graph containing
 * a label, an indicator of whether it has been visited,
 * and a list of adjacent vertices
 * 
 * @author Daniel Zamoshchin
 */
public class Vertex {

	/**
	 * Each vertex carries a label/name
	 */
	private String label;
	
	/**
	 * List of adjacent Vertices
	 */
	private List<Vertex> adjList;
	
	/**
	 * Constructor to set the label
	 * @param l name
	 */
	public Vertex(String l) {
		label = l;
		adjList = new ArrayList<Vertex>();
	}
	
	/**
	 * Getter method to get label
	 * @return
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Returns the adjacency list
	 * @return adjacent vertices
	 */
	public List<Vertex> getAdjacent() {
		return adjList;
	}
	
	/**
	 * Adds an adjacent vertex if it isn't already added
	 * @param e vertex to add
	 */
	public void addEdge(Vertex e) {
		if(!adjList.contains(e)) {
			adjList.add(e);
		}
	}
	
	/**
	 * Gives the name of the vertex
	 */
	public String toString() {
		return label;
	}
	
	/**
	 * Gives the name of the vertex followed by a list of its neighbors
	 */
	public String adjacentToString() {
		String output = "";
		output+= label + " [";
		for(Vertex v: adjList) {
			output += v.getLabel() + ", ";
		}
		output = output.substring(0, output.length()-2); //remove last part of ", "
		output+= "]";
		return output;
	}

}