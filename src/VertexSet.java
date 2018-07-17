import java.util.ArrayList;
import java.util.List;

/**
 * VertexSet represents a set of vertices
 * @author Daniel Zamoshchin
 *
 */

public class VertexSet {
	
	/*
	 * List of vertices
	 */
	private List<Vertex> set;

	/**
	 * Get list of vertices
	 * @return list
	 */
	public List<Vertex> getSet() {
		return set;
	}
	
	/**
	 * Add a vertex to list
	 * @param v vertex to add
	 */
	public void add(Vertex v) {
		set.add(v);
	}

	/**
	 * Set the list
	 * @param set to set
	 */
	public void setSet(List<Vertex> set) {
		this.set = set;
	}

	/**
	 * Constructor for empty list
	 */
	public VertexSet() {
		set = new ArrayList<Vertex>();
	}

	/**
	 * Constructor for given set
	 * @param set to set
	 */
	public VertexSet(List<Vertex> set) {
		this.set = set;
	}

}
