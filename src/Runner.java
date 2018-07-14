import java.util.List;
import java.util.Scanner;

public class Runner {
	
	
	public static int approximateTransitivity(RandomGraph rg) {
		int partSize = 2;
		boolean foundPart = false;
		while(true) {
			for(int i = 0; i < 100 * Math.pow(2, partSize); i++) {
				rg.getRandomPartition(partSize);
				Graph g = rg.toGraph();
				
				if(g.isTransitivePartition()) {
					int count = 0;
					for(List<Vertex> set: g.getSets()) {
						if(!set.isEmpty()) {
							count++;
						}
					}
					if(count == partSize) {
						partSize++;
						foundPart = true;
						break;
					} else {
						continue;
					}
				}
				
				if(i == (100 * Math.pow(2, partSize)) - 1) {
					if(foundPart != true) {
						partSize++;
						break;
					} else {
						g.setPartition(null);
						return partSize - 1;
					} 
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		RandomGraph random = new RandomGraph(10);
		System.out.println("Transitivity Number: " + approximateTransitivity(random));
		System.out.println(random.toGraph());
		
		
//		long grundyRun = 0;
//		
//		long partitionRun = 0;
//		
//		final long startTime = System.currentTimeMillis();
//
//		boolean grundyTrue = false;
//		
//		final long startTime1 = System.nanoTime();
//		RandomGraph random = new RandomGraph(5);
//		final long endTime1 = System.nanoTime();
//		System.out.println("Random Graph execution time: " + (endTime1 - startTime1));
//
//		while(!grundyTrue) {
//			final long startTime2 = System.nanoTime();
//			random.getRandomPartition(20); 
//			final long endTime2 = System.nanoTime();
//			partitionRun += endTime2-startTime2;
//			
//			Graph myGraph = random.toGraph();
//			
//			final long startTime3 = System.nanoTime();
//			if(!myGraph.isTransitivePartition()) {
//				continue;
//			}
//			final long endTime3 = System.nanoTime();
//			grundyRun += endTime3-startTime3;
//			
////			if(grundyTrue) {
////				System.out.println(myGraph);
////			}
//			
//			int count = 0;
//			for(List<Vertex> set: myGraph.getSets()) {
//				if(!set.isEmpty()) {
//					count++;
//				} else {
//					break;
//				}
//			}
//			System.out.println(count);
//		}
//		final long endTime = System.currentTimeMillis();
//
//		System.out.println("Total execution time: " + (endTime - startTime));
//		
//		System.out.println("Grundy: " + grundyRun);
//		System.out.println("Partition: " + partitionRun);
		
//		Graph myGraph = new Graph();
//		
//		//using given partitions
//		myGraph.addEdgesFromFile("graph.txt");
//		myGraph.groupVerticiesIntoSets("sets.txt");
//		
//		System.out.println(myGraph);
	}
	
	
}
