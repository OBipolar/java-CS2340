package SpaceTrader.Model;

/*
* This enumerator represents a solar system in the universe.
*/
public class SolarSystem {
	
	
	private int x;
	private int y;
	private Capital planet;
	private String name;
	
	public SolarSystem (int x, int y, String name, Capital planet) {
		setName(name);
		setPlanet(planet);
		setX(x);
		setY(y);
	}
	
	
	public Capital getPlanet() {
		return planet;
	}
	public void setPlanet(Capital planet) {
		this.planet = planet;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return String.format("\n This system is called %s, "
				+ "its x coordinate is %s, its y coordinate is %s, "
				+ "it has the following planet:\n", name, x, y) + planet.toString();
	}
	




}
