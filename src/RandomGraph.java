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
			for(int c = 0; c < fullAdjList.size(); c++) {
				int connected = (int) (Math.random() + 0.5);
				fullAdjList.get(r).add(connected);
				if(connected == 1 && r != c) {
					graphToCreate.addEdge(String.valueOf(r), String.valueOf(c));
				}
			}
		}
		
		System.out.println(graphToCreate);
	}
	
	
	
	
	
}
