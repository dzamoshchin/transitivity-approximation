import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a simple, undirected graph
 * with the option to color containing sets of vertices.
 * 
 * @author Daniel Zamoshchin
 */
public class Graph {

	/**
	 * HashMap that stores all Vertices in the Graph 
	 **/
	private Map<String, Vertex> map; 
	
	/**
	 * List of Lists that stores all sets of Vertices in the Graph 
	 **/
	private List<List<Vertex>> sets; 
	
	/**
	 * Default constructor to create an empty set of Vertices
	 */
	public Graph() {
		//initialize HashMaps
		map = new HashMap<String, Vertex>(); 
		sets = new ArrayList<List<Vertex>>();
	}
	
	/**
	 * Adds an edge between the vertices of the given names.
	 * @param firstLabel first vertex
	 * @param secondLabel second vertex
	 */
	public void addEdge(String firstLabel, String secondLabel) {
		Vertex first = new Vertex(firstLabel);
		Vertex second = new Vertex(secondLabel);
		
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
					sets.add(set);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File \"" + filename + "\" was not found. Please try again.");
		}
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
			output += v + "\n";
		}
		return output;		
	}
	
	/**
	 * 
	 * @param set
	 * @return
	 */
	public static boolean isIndependentSet(List<Vertex> set) {
		for(Vertex v: set) {
			for(Vertex adjacent:	v.getAdjacent()) {
				if(set.contains(adjacent)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * A domatic partition of a graph is when every set of vertices
	 * is a dominating set
	 * @return true if domatic partition
	 */
	public boolean isDomaticPartition() {
		boolean isDominating = true;
		
		//for each vertex in each set
		
		//for each set
		for(List<Vertex> set: sets) {
			
			//for each vertex in set
			List<Vertex> neighbors = new ArrayList<Vertex>();
			for(Vertex v: set) {
				for(Vertex adjacent: v.getAdjacent()) {
					if(!neighbors.contains(adjacent) && !set.contains(adjacent)) {
						neighbors.add(adjacent);
					}
				}
			}
			
			if(neighbors.size() + set.size() != map.size()) {
				return false;
			}
		}
		return isDominating;
	}
	
	
	public boolean isTransitivePartition() {
		boolean isTransitive = true;
		
		//for each vertex in each set
		
		//for each set
		for(int i = 0; i < sets.size(); i++) {
			List<Vertex> set = sets.get(i);
			for(int j = i + 1; j < sets.size(); j++) {
				List<Vertex> nextSet = sets.get(j);
	
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
	 * follows the transitive definition but with independent sets
	 * @return true if Grundy coloring of graph
	 */
	public boolean isGrundyColoring() {
		if(this.isTransitivePartition()) {
			for(List<Vertex> set: sets) {
				if(!isIndependentSet(set)) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	
//	public boolean isADominatingSet() {
//		
//	}
//	
//	
//	public boolean isMinimalDominatingSet(List<Vertex> set) {
//		for(Vertex v: set) {
//			if
//		}
//		return false;
//	}

}







