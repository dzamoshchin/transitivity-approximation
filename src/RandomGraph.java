import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * RandomGraph generates a random graph object with any
 * number of vertices and a partition of any size
 * @author Daniel Zamoshchin
 *
 */

public class RandomGraph {
	
	/*
	 * Matrix representation of full graph adjaceny list
	 */
	List<List<Integer>> fullAdjList = new ArrayList<List<Integer>>();
	
	/*
	 * Graph object representation of RandomGraph
	 */
	Graph graphToCreate = new Graph();
		
	/**
	 * Constructor for random vertices.
	 * Two vertices have a 50% chance of being connected.
	 * @param numOfVertices
	 */
	public RandomGraph(int numOfVertices) {
		for(int i = 0; i < numOfVertices; i++) {
			fullAdjList.add(new ArrayList<Integer>());
		}

		for(int r = 0; r < fullAdjList.size(); r++) {
			for(int c = 0; c < r; c++) {
				int connected = (int) (Math.random() + 0.5);
				fullAdjList.get(r).add(connected);
				if(connected == 1) {
					graphToCreate.addEdge(String.valueOf(r), String.valueOf(c));
					
				}
			}
		}
	}
	
	/**
	 * Constructor to convert graph to random graph
	 * @param graph graph to convert
	 */
	public RandomGraph(Graph graph) {
		graphToCreate = graph;
	}
	
	/**
	 * Each vertex is assigned a random partition
	 * @param size of partition
	 */
	public void getRandomPartition(int size) {
		List<VertexSet> sets = new ArrayList<VertexSet>(); 
		for(int i = 0; i < size; i++) {
			sets.add(new VertexSet());
		}
		for(Vertex v: graphToCreate.getGraph().values()) {
			int random = (int) (Math.random() * size);
			sets.get(random).add(v);
		}
		graphToCreate.setPartition(sets);
	}
	
	
	/**
	 * After finished with random graph, give back graph
	 * @return Graph object
	 */
	public Graph toGraph() {
		return graphToCreate;
	}
	
	
	
}
