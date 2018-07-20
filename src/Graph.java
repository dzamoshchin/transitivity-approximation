import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a simple, undirected graph
 * with the option to separate into sets of vertices (or color).
 * 
 * @author Daniel Zamoshchin
 */
public class Graph implements Cloneable {
	
	/**
	 * Number of vertices in the graph 
	 **/
	private int numVertices; 

	/**
	 * HashMap that stores all Vertices in the Graph 
	 **/
	private Map<String, Vertex> map; 
	
	/**
	 * List of VertexSets that stores all sets of Vertices in the Graph 
	 **/
	private List<VertexSet> sets; 
	
	/**
	 * Default constructor to create an empty set of Vertices
	 */
	public Graph() {
		//initialize HashMaps
		map = new HashMap<String, Vertex>(); 
		sets = new ArrayList<VertexSet>();
		
		//empty graph has no vertices
		numVertices = 0;
	}
	
	/**
	 * Adds an edge between the vertices of the given names.
	 * @param firstLabel first vertex
	 * @param secondLabel second vertex
	 */
	public void addEdge(String firstLabel, String secondLabel) {
		Vertex first = new Vertex(firstLabel);
		Vertex second = new Vertex(secondLabel);
		
		numVertices+=2;
		
		//put the vertices into the map if they do not already exist
		map.putIfAbsent(firstLabel, first); 
		map.putIfAbsent(secondLabel, second);
		
		//adjust the adjacency lists of the vertices from the map
		map.get(firstLabel).addEdge(map.get(secondLabel));
		map.get(secondLabel).addEdge(map.get(firstLabel));
	}
	
	
	/**
	 * Reads from the file of specified name and colors
	 * or groups vertices into sets
	 * @param filename specified file
	 */
	public void groupVerticiesIntoSets(String filename) {
		File file = new File(filename);
		try {
			Scanner reader = new Scanner(file);
			while(reader.hasNext()) {
				String nextLine = reader.nextLine();
				if(nextLine.charAt(0) == 'V') {
					List<Vertex> set = new ArrayList<Vertex>();
					String[] verticies = reader.nextLine().split(" ");
					for(String v: verticies) {
						set.add(map.get(v));

					}
					sets.add(new VertexSet(set));
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File \"" + filename + "\" was not found. Please try again.");
		}
	}
	
	/**
	 * Get the map object for graph
	 * @return map representation of graph
	 */
	public Map<String, Vertex> getGraph() {
		return map;
	}
	
	/**
	 * Get a RandomGraph from the Graph
	 * @return RandomGraph object
	 */
	public RandomGraph getRandomGraph() {
		return new RandomGraph(this);
	}
	
	/**
	 * Get the list of VertexSets
	 * @return the partition of vertices
	 */
	public List<VertexSet> getPartition() {
		return sets;
	}
	
	/**
	 * Get the number of vertices in the graph
	 * @return number of vertices
	 */
	public int getNumVertices() {
		return numVertices;
	}

	/**
	 * Set the list of VertexSets
	 * @param sets list of VertexSet objects
	 */
	public void setPartition(List<VertexSet> sets) {
		this.sets = sets;
	}
	
	/**
	 * Reads from the file of specified name and adds the
	 * edges listed in the file.
	 * @param filename specified file
	 */
	public void addEdgesFromFile(String filename) {
		File file = new File(filename);
		try {
			Scanner reader = new Scanner(file);
			while(reader.hasNext()) {
				String nextLine = reader.nextLine();
				String[] labels = nextLine.split(" ");
				//add an edge between the two vertex labels on each line
				addEdge(labels[0], labels[1]);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File \"" + filename + "\" was not found. Please try again.");
		}
	}

	/**
	 * Returns information about each of the vertices 
	 * contained in the Graph
	 */
	public String toString() {
		String output = "Adjacency List:\n";
		for(Vertex v: map.values()) { //traverse through the HashMap
			output += v.adjacentToString() + "\n";
		}
		
		if(sets != null && !sets.isEmpty()) {
			output += "\nSets of Vertices:\n";
			
			for(int i = 0; i < sets.size(); i++) {
				output += "V" + i + " " + sets.get(i).toString();
				output += "\n";
			}
		}
		
		return output;		
	}
	
	/**
	 * Checks if a given set is independent (no two adjacent vertices)
	 * @param set to check
	 * @return if independent
	 */
	public static boolean isIndependentSet(VertexSet set) {
		List<Vertex> list = set.getSet();
		for(Vertex v: list) { //for each vertex in set
			for(Vertex adjacent: v.getAdjacent()) {  //for each adjacent vertex of that vertex
				if(list.contains(adjacent)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * A domatic partition of a graph is when every set of vertices
	 * is a dominating set
	 * @return if domatic partition
	 */
	public boolean isDomaticPartition() {
		boolean isDominating = true;
		
		//for each vertex in each set
		
		//for each set
		for(VertexSet set: sets) {
			List<Vertex> list = set.getSet();
			//for each vertex in set
			List<Vertex> neighbors = new ArrayList<Vertex>();
			for(Vertex v: list) {
				for(Vertex adjacent: v.getAdjacent()) {
					if(!neighbors.contains(adjacent) && !list.contains(adjacent)) {
						neighbors.add(adjacent);
					}
				}
			}
			
			if(neighbors.size() + list.size() != map.size()) {
				return false;
			}
		}
		return isDominating;
	}
	
	/**
	 * Determines if transitive partition where each set
	 * dominates each successive set. 
	 * @return if transitive partition
	 */
	public boolean isTransitivePartition() {
		boolean isTransitive = true;
				
		//for each set
		for(int i = 0; i < sets.size(); i++) {
			List<Vertex> set = sets.get(i).getSet();
			//for each successive set
			for(int j = i + 1; j < sets.size(); j++) {
				List<Vertex> nextSet = sets.get(j).getSet();
	
				List<Vertex> neighbors = new ArrayList<Vertex>();
				for(Vertex v: set) {
					for(Vertex adjacent: v.getAdjacent()) {
						if(!neighbors.contains(adjacent) && nextSet.contains(adjacent)) {
							neighbors.add(adjacent);
						}
					}
				}
				
				if(neighbors.size() != nextSet.size()) {
					return false;
				}
			}
		}
		return isTransitive;
	}
	
	/**
	 * Determines if Grundy coloring which
	 * follows the transitive definition 
	 * but every set is independent
	 * @return if Grundy coloring of graph
	 */
	public boolean isGrundyColoring() {
		if(this.isTransitivePartition()) {
			for(VertexSet set: sets) {
				if(!isIndependentSet(set)) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Implements Cloneable to clone Graph object
	 */
	public Graph clone() throws CloneNotSupportedException {  
		return (Graph) super.clone();
	}

}







