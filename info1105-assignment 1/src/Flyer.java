public class Flyer {
	// instance variables
	private String name;
	private double distance;
	
	// constructor
	public Flyer(String name, double distance) {
		this.name = name;
		this.distance = distance;
	}

	// access method
	public String getName() {
		return this.name;
	}

	public double getDistance() {
		return this.distance;
	}

	// update methods
	public void setName(String name) {
		this.name = name;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	// utility method - for main testing
	public void printNode() {
		System.out.println("{" + name + ", " + distance + "} ");
	}

}
