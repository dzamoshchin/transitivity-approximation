import java.util.List;

/**
 * VertexSet represents a set of vertices
 * @author Daniel Zamoshchin
 *
 */

public class VertexSet {
	
	
	private List<Vertex> set;
	
	
	public List<Vertex> getSet() {
		return set;
	}


	public void setSet(List<Vertex> set) {
		this.set = set;
	}


	public VertexSet(List<Vertex> set) {
		this.set = set;
	}

}
