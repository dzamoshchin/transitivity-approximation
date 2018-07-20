import java.util.ArrayList;
import java.util.List;

/**
 * Analyze is an experimental class
 * to analyze the properties using the algorithms
 * @author Daniel Zamoshchin
 *
 */

public class Analyze {
	
	public static int approximateDomaticNumber(RandomGraph rg) {
		int partSize = 2;
		Graph maxGraph = new Graph();
		while(true) {
			for(int i = 0; i < 10000 * Math.pow(2, partSize); i++) {
				rg.getRandomPartition(partSize);
				Graph g = rg.toGraph();
				
				int count = 0;
				for(VertexSet vs: g.getPartition()) {
					List<Vertex> set = vs.getSet();
					if(!set.isEmpty()) {
						count++;
					}
				}
				if(count == partSize) {
					if(g.isDomaticPartition()) {
						partSize++;
						try {
							maxGraph = g.clone();
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
						break;
					}
				} else {
					continue;
				}
				
				if(i == (10000 * Math.pow(2, partSize)) - 1) {
					return partSize - 1;
				}
			}
		}
	}
	
	public static int approximateGrundyNumber(RandomGraph rg) {
		int partSize = 2;
		boolean foundPart = false;
		int waitAmount = 10000;
		Graph maxGraph = new Graph();
		while(true) {
			for(int i = 0; i < waitAmount * Math.pow(2, partSize); i++) {
				rg.getRandomPartition(partSize);
				Graph g = rg.toGraph();
				
				int count = 0;
				for(VertexSet vs: g.getPartition()) {
					List<Vertex> set = vs.getSet();
					if(!set.isEmpty()) {
						count++;
					}
				}
				if(count == partSize) {
					if(g.isGrundyColoring()) {
//						System.out.println(partSize);
						try {
							maxGraph = g.clone();
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
						partSize++;
						foundPart = true;
						break;
					}
				} else {
					continue;
				}				
				if(i == waitAmount * Math.pow(2, partSize) - 1) {
//					System.out.println("could not find " + partSize);
					if(foundPart != true) {
						partSize++;
						break;
					} else {
//						System.out.println(maxGraphG);
						return partSize - 1;
					} 
				}
			}
		}
	}
	
	public static int approximateTransitivity(RandomGraph rg) {
		int partSize = 2;
		Graph maxGraph = new Graph();
		while(true) {
			for(int i = 0; i < 10000 * Math.pow(2, partSize); i++) {
				rg.getRandomPartition(partSize);
				Graph g = rg.toGraph();
				
				int count = 0;
				for(VertexSet vs: g.getPartition()) {
					List<Vertex> set = vs.getSet();
					if(!set.isEmpty()) {
						count++;
					}
				}
				if(count == partSize) {
					if(g.isTransitivePartition()) {
						partSize++;
						try {
							maxGraph = g.clone();
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
						break;
					} else {
						continue;
					}
				}
				if(i == (10000 * Math.pow(2, partSize)) - 1) {
//					System.out.println(maxGraph);
					return partSize - 1;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		List<Integer> transitivityNumbers = new ArrayList<Integer>();
		List<Integer> grundyNumbers = new ArrayList<Integer>();
		List<Integer> domaticNumbers = new ArrayList<Integer>();
		final long startTime = System.currentTimeMillis();
		for(int i = 0; i < 100; i++) {
			System.out.println(i);
			RandomGraph random = new RandomGraph(5);
			int t = approximateTransitivity(random);
			transitivityNumbers.add(t);
			int g = approximateGrundyNumber(random);
			grundyNumbers.add(g);
			int d = approximateDomaticNumber(random);
			domaticNumbers.add(d);
			System.out.println("Transitivity Number: " + t);
			System.out.println("Grundy Number: " + g);
			System.out.println("Domatic Number: " + d);
		}
		System.out.println(transitivityNumbers);
		System.out.println(grundyNumbers);
		System.out.println(domaticNumbers);
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime));
		
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
