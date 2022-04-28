import java.util.Objects;

/**
 * @author Allan Kangethe
 * 
 * class
 *
 */

public class Town implements Comparable<Town> {
	private String theName;

	/**
	 * @param name of town
	 */
	public Town(String name) {
		this.theName = name;
	}

	/**
	 * @return String of the name
	 */
	public String getName() {
		return theName;
	}
	
	@Override
	public int compareTo(Town object) {
		if (this.theName == object.theName) {
			return 0;
		} else {
			return 1;
		}
	}
	@Override
	public int hashCode() {
		return Objects.hash(theName);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		Town otherTown = (Town) object;
		return Objects.equals(theName, otherTown.theName);
	}

	
	
	@Override
	public String toString() {
		return "Town [name=" + theName + "]";
	}

}