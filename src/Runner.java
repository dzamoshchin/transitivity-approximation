import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		
		long grundyRun = 0;
		
		long partitionRun = 0;
		
		final long startTime = System.currentTimeMillis();

		boolean grundyTrue = false;
		final long startTime1 = System.nanoTime();
		RandomGraph random = new RandomGraph(12);
		final long endTime1 = System.nanoTime();
		System.out.println("Random Graph execution time: " + (endTime1 - startTime1));
		while(!grundyTrue) {
			final long startTime2 = System.nanoTime();
			random.getRandomPartition(10);
			final long endTime2 = System.nanoTime();
			partitionRun += endTime2-startTime2;
			
			Graph myGraph = random.toGraph();
			
			final long startTime3 = System.nanoTime();
			grundyTrue = myGraph.isGrundyColoring();
			final long endTime3 = System.nanoTime();
			grundyRun += endTime3-startTime3;
			
			if(grundyTrue) {
				System.out.println(myGraph);
			}
		}
		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));
		
		System.out.println("Grundy: " + grundyRun);
		System.out.println("Partition: " + partitionRun);
		
//		Graph myGraph = new Graph();
//		
//		//using given partitions
//		myGraph.addEdgesFromFile("graph.txt");
//		myGraph.groupVerticiesIntoSets("sets.txt");
//		
//		System.out.println(myGraph);
	}
	
	
}
