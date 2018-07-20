import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Analyze is an experimental class
 * to analyze the properties using the algorithms
 * @author Daniel Zamoshchin
 *
 */

public class Analyze {
	
	private static Graph maxGraphTransitivity = new Graph();
	private static Graph maxGraphGrundy = new Graph();
	private static Graph maxGraphDomatic = new Graph();
	
	public static int approximateDomaticNumber(RandomGraph rg) {
		int partSize = 2;
		while(true) {
			for(int i = 0; i < 10000 * Math.pow(2, partSize); i++) {
				rg.getRandomPartition(partSize);
				Graph g = rg.toGraph();
				
				if(partSize > g.getNumVertices()) {
					return partSize - 1;
				}
				
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
							maxGraphDomatic = g.clone();
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
		while(true) {
			for(int i = 0; i < 100000 * Math.pow(2, partSize); i++) {
				rg.getRandomPartition(partSize);
				Graph g = rg.toGraph();
				
				if(partSize > g.getNumVertices()) {
					return partSize - 1;
				}
				
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
							maxGraphGrundy = g.clone();
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
				if(i == 100000 * Math.pow(2, partSize) - 1) {
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
		while(true) {
			for(int i = 0; i < 20000 * Math.pow(2, partSize); i++) {
				rg.getRandomPartition(partSize);
				Graph g = rg.toGraph();
				
				if(partSize > g.getNumVertices()) {
					return partSize - 1;
				}
				
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
							maxGraphTransitivity = g.clone();
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
						break;
					} else {
						continue;
					}
				}
				if(i == (20000 * Math.pow(2, partSize)) - 1) {
//					System.out.println(maxGraph);
					return partSize - 1;
				}
			}
		}
	}
	
	public static void main(String[] args) {
//		calculateForGivenGraph("graph.txt");
	}
	
	public static void calculateForRandomGraph(int run) {
		List<Integer> transitivityNumbers = new ArrayList<Integer>();
		List<Integer> grundyNumbers = new ArrayList<Integer>();
		List<Integer> domaticNumbers = new ArrayList<Integer>();
		final long startTime = System.currentTimeMillis();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
			for(int i = 0; i < run; i++) {
				int num = i+1;
				System.out.println("Graph #" + num);
				writer.write(String.valueOf(num));
				
				//Generate a random graph
				RandomGraph random = new RandomGraph(10);
				
				//Transitivity
				int t1 = approximateTransitivity(random);
				int t2 = approximateTransitivity(random);
				int t = -1;
				if(t1 == t2) {
					t = t1;
				} else {
					int max = t1 > t2 ? t1 : t2;
					t = max;
				}
				String transitivity = "Transitivity Number: " + t + "\n";
				writer.write(transitivity);
				writer.write(maxGraphTransitivity.toString());
//				System.out.println(transitivity);
				transitivityNumbers.add(t);
				
				//Grundy Number
				int g1 = approximateGrundyNumber(random);
				int g2 = approximateGrundyNumber(random);
				int g = -1;
				if(g1 == g2) {
					g = g1;
				} else {
					int max = g1 > g2 ? g1 : g2;
					g = max;
				}
				String grundy = "Grundy Number: " + g + "\n";
				writer.write(grundy);
				writer.write(maxGraphGrundy.toString());
//				System.out.println(grundy);
				grundyNumbers.add(g);
				
				//Domatic Number
				int d1 = approximateDomaticNumber(random);
				int d2 = approximateDomaticNumber(random);
				int d = -1;
				if(d1 == d2) {
					d = d1;
				} else {
					int max = d1 > d2 ? d1 : d2;
					d = max;
				}
				String domatic = "Domatic Number: " + d + "\n";
				writer.write(domatic);
				writer.write(maxGraphDomatic.toString());
//				System.out.println(domatic);
				domaticNumbers.add(d);
				
				if(g>t || t-g >= 2) {
					System.out.println("ERROR AT " + num);
				}
				
				System.out.print("\n");
			}
			writer.write(transitivityNumbers.toString());
			writer.write(grundyNumbers.toString());
			writer.write(domaticNumbers.toString());
			System.out.println(transitivityNumbers);
			System.out.println(grundyNumbers);
			System.out.println(domaticNumbers);
			writer.close();
			final long endTime = System.currentTimeMillis();
			System.out.println("Total execution time: " + (endTime - startTime));
		} catch (IOException e) {
			System.out.println("The output file was not found. Error: " + e);
		}
	}
	
	public static void calculateForGivenGraph(String filename) {
		Graph myGraph = new Graph();
		
		//using given partitions
		myGraph.addEdgesFromFile(filename);		
		System.out.println(myGraph);
		
		RandomGraph random = myGraph.getRandomGraph();
		for(int i = 0; i < 10; i++) {
//			System.out.println("Transitivity: " + approximateTransitivity(random));
			System.out.println("Grundy Coloring: " + approximateGrundyNumber(random));
		}
	}
	
	
}
