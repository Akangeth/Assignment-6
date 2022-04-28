import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Allan Kangethe
 *
 */

public class TownGraphManager implements TownGraphManagerInterface {

	private Graph<Town, Road> theGraph;

	/**
	 * Constructor
	 */
	public TownGraphManager() {
		theGraph = new Graph<Town, Road>();
	}

	/**
	 * @param town1 the town1
	 * @param weight the weight
	 * @param town2 the town2
	 * @param name the name
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String name) {
		if (theGraph.addEdge(new Town(town1), new Town(town2), weight, name) != null) {			
			return true;
		}
		return false;
	}

	@Override
	public boolean addTown(String v) {
		return theGraph.addVertex(new Town(v));
	}
	
	/**
	 * @param town1 town1
	 * @param town2 town2
	 * @param road road
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		if (theGraph.removeEdge(new Town(town1), new Town(town2), 0, road) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteTown(String v) {
		return theGraph.removeVertex(new Town(v));
	}
	
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return theGraph.containsEdge(new Town(town1), new Town(town2));
		}
	
	@Override
	public boolean containsTown(String v) {
		return theGraph.containsVertex(new Town(v));
	}
	
	@Override
	public String getRoad(String town1, String town2) {
		return ((Road) theGraph.getEdge(new Town(town1), new Town(town2))).getName();
	}
	
	@Override
	public Town getTown(String name) {
		return new Town(name);
	}
	
	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (Road r : theGraph.edgeSet()) {
			arrayList.add(r.getName());
			Collections.sort(arrayList);
		}
		return arrayList;
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (Town town : theGraph.vertexSet()) {
			arrayList.add(town.getName());
			Collections.sort(arrayList);
		}
		return arrayList;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return theGraph.shortestPath(new Town(town1), new Town(town2));
	}

	/**
	 * @param file the file
	 * @throws IOException the exception that will be thrown
	 */
	public void populateTownGraph (File file) throws IOException {
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String[] string = new String[6];
		String line = null;

		try {
			while ((line = buffer.readLine()) != null) {
				string = line.split(";|\\,");
				addTown(string[2]);
				addTown(string[3]);
				addRoad(string[2], string[3], Integer.parseInt(string[1]), string[0]);
			}
			buffer.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}