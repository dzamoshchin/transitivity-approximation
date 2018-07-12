import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Daniel Zamoshchin
 *
 */

public class RandomGraph {
	
	/*
	 * Matrix representation of full graph adjaceny list
	 */
	List<List<Integer>> fullAdjList = new ArrayList<List<Integer>>();
	
	/*
	 * Graph object representation
	 */
	Graph graphToCreate = new Graph();
		

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
	
	
	public void getRandomPartition(int size) {
		List<List<Vertex>> sets = new ArrayList<List<Vertex>>(); 
		for(int i = 0; i < size; i++) {
			sets.add(new ArrayList<Vertex>());
		}
		for(Vertex v: graphToCreate.getGraph().values()) {
			int random = (int) (Math.random() * size);
			sets.get(random).add(v);
		}
		graphToCreate.setPartition(sets);
	}
	
	public Graph toGraph() {
		return graphToCreate;
	}
	
	
	
}
