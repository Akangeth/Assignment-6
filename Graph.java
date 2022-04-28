import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Allan Kangethe
 *
 * @param <E>
 * @param <V>
 */

public class Graph<E, V> implements GraphInterface<Town, Road> {

	private Set<Town> setTown = new HashSet<Town>();
	private Set<Road> setRoad = new HashSet<Road>();
	private Map<String, Town> townMap = new HashMap<String, Town>();
	ArrayList<String> shortestPath = new ArrayList<String>();
	
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException();
		}
		if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) {
			throw new IllegalArgumentException();
		}
		Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
		setRoad.add(newRoad);

		return newRoad;
	}

	@Override
	public boolean addVertex(Town theTown) {
		boolean e = true;
		if (theTown == null) {
			throw new NullPointerException();
		}
		if (!(setTown.contains(theTown))) {
			setTown.add(theTown);
			return true;
		}
		return e;
	}
	
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String theDescription) {
		Road road = null;
	    for (Road r : setRoad) {
	    	if (r.contains(destinationVertex) && r.contains(sourceVertex) && (weight > -1) && theDescription != null) {
	    		road = r;
	        }
	    }
	    if (setRoad.remove(road)) {
	      return road;
	    }
	    return null;
	  }

	@Override
	public boolean removeVertex(Town v) {
		if (v == null) {
			return false;
		}
		return setTown.remove(v);
	}
	
	@Override
	public Set<Road> edgeSet() {
		return setRoad;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> townEdges = new HashSet<Road>();
		for (Road r : setRoad) {
			if (r.contains(vertex)) {
				townEdges.add(r);
			}
		}
		return townEdges;
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		boolean contains = false;
		for (Road e : setRoad) {
			if (e.contains(destinationVertex) && e.contains(sourceVertex)) {
				contains = true;
			}
		}
		return contains;
	}

	@Override
	public boolean containsVertex(Town v) {
		return setTown.contains(v);
	}

	@Override
	public Set<Town> vertexSet() {
		return setTown;
	}
	
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if (containsEdge(sourceVertex, destinationVertex)) {
			for (Road e : setRoad) {
				if (e.contains(destinationVertex) && e.contains(sourceVertex)) {
					return new Road(sourceVertex, destinationVertex, e.getWeight(), e.getName());
				}
			}
		}
		return null;
	}
	
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		Town d = destinationVertex;
		while (!d.equals(sourceVertex)) {
			if (!townMap.containsKey(d.getName())) {
				shortestPath.clear();
				break;
			}
			Town town = townMap.get(d.getName());
			if (town == null) {
				return shortestPath;
			}
			Road r = getEdge(town, d);
			shortestPath.add(0, town.getName() + " via " + r.getName() + " to " + d.getName() + " " + r.getWeight() + " mi");
			d = town;
		}
		return shortestPath;
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		Set<Town> visit = new HashSet<Town>();
		ArrayList<Town> noVisiting = new ArrayList<Town>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (Town t : setTown) {
			noVisiting.add(t);
			map.put(t.getName(), Integer.MAX_VALUE);
			townMap.put(t.getName(), null);
		}
		map.put(sourceVertex.getName(), 0);
		while (!noVisiting.isEmpty()) {
			int s = 0;
			for (int i = 0; i < noVisiting.size(); i++) {
				Town unvisitedTown = noVisiting.get(i);
				if (map.get(noVisiting.get(s).getName()) > map.get(unvisitedTown.getName())) {
					s = i;
				}
			}
			Town closestTown = noVisiting.remove(s);
			visit.add(closestTown);
			for (Road roadEdge : edgesOf(closestTown)) {
				Town adj = roadEdge.getDestination();
				if (adj.equals(closestTown)) {
					adj = roadEdge.getSource();
				}
				if (visit.contains(adj)) {
					continue;
				}
				int adjDistance = map.get(closestTown.getName()) + roadEdge.getWeight();
				if (adjDistance < map.get(adj.getName())) {
					map.put(adj.getName(), adjDistance);
					townMap.put(adj.getName(), closestTown);
				}
			}
		}

	}

}