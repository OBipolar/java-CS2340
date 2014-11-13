package spacetrader.planets;

/**
 * A solar system in universe
 * 
 * @author Menghang Li
 *
 */
public class SolarSystem {

    private int xpos;
    private int ypos;
    private Capital planet;
    private String name;

    /**
     * Constructor that sets all properties of a SolarSystem
     * 
     * @param x
     *            its x coordinate
     * @param y
     *            its y coordinate
     * @param name
     *            its name
     * @param planet
     *            its capital
     */
    public SolarSystem(int xpos, int ypos, String name, Capital planet) {
        setName(name);
        setPlanet(planet);
        setX(xpos);
        setY(ypos);
    }

    public Capital getPlanet() {
        return planet;
    }

    public void setPlanet(Capital planet) {
        this.planet = planet;
    }

    public int getX() {
        return xpos;
    }

    public void setX(int xpos) {
        this.xpos = xpos;
    }

    public int getY() {
        return ypos;
    }

    public void setY(int ypos) {
        this.ypos = ypos;
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
                + "it has the following planet:\n", name, xpos, ypos)
                + planet.toString();
    }

}
