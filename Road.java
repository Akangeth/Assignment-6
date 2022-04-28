/**
 * @author Allan Kangethe
 *
 */

public class Road implements Comparable<Road> {

	private Town town;
	private Town town2;
	private String theString;
	private int integer;

	/**
	 * @param source source
	 * @param destination destination
	 * @param name name
	 */
	public Road(Town source, Town destination, String name) {
		
		this(source, destination, 0, name);
	}

	/**
	 * @param source source
	 * @param destination destination
	 * @param weight weight
	 * @param name weight
	 */
	public Road(Town theSource, Town theDestination, int theWeight, String theName) {
		this.town = theSource;
		this.town2 = theDestination;
		this.integer = theWeight;
		this.theString = theName;
	}
	
	@Override
	public boolean equals(Object k) {
		if (town.equals(((Road) k).getSource()) && town2.equals(((Road) k).getDestination())) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param town town
	 * @return boolean whether towns match
	 */
	public boolean contains(Town town) {
		return town.getName().equals(town.getName()) || town2.getName().equals(town.getName());
	}

	@Override
	public int compareTo(Road theroad) {
		
		if (this.theString == theroad.theString)
		{
			return 0;

		}
			return 1;
	}
	
	/**
	 * @return Town
	 */
	public Town getSource() {
		return town;
	}

	/**
	 * @return Town2
	 */
	public Town getDestination() {
		return town2;
	}

	/**
	 * @return !
	 */
	public String getName() {
		return theString;
	}

	/**
	 * @return Weight
	 */
	public int getWeight() {
		return integer;
	}

	/**
	 * 
	 * @return String
	 * */
	@Override
	public String toString() {
		return "Road [source=" + town + ", destination=" + town2 + ", name=" + theString + ", weight=" + integer
				+ "]";
	}
}