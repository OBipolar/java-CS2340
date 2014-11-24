package spacetrader.planets;

/**
 * A solar system in universe
 * 
 * @author Menghang Li
 *
 */
public class SolarSystem {

    /**
     * The x coordinate of the solar system
     */
    private int xpos;
    /**
     * The y coordinate of the solar system
     */
    private int ypos;
    /**
     * The planet the solar system has 
     */
    private Capital planet;
    /**
     * The name of the solar system
     */
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

    /**
     * Return the planet the solar system has 
     * 
     * @return the planet the solar system has 
     */
    public Capital getPlanet() {
        return planet;
    }

    /**
     * Set the planet the solar system has 
     * 
     * @param planet an Planet
     */
    public void setPlanet(Capital planet) {
        this.planet = planet;
    }

    /**  
     * Return The x coordinate of the solar system
     * 
     * @return The x coordinate of the solar system
     */
    public int getX() {
        return xpos;
    }

    /**
     * Set The x coordinate of the solar system
     * 
     * @param xpos an int
     */
    public void setX(int xpos) {
        this.xpos = xpos;
    }

    /**
     * Return the y coordinate of the solar system
     * 
     * @return the y coordinate of the solar system
     */
    public int getY() {
        return ypos;
    }

    /**
     * Set the y coordinate of the solar system
     * 
     * @param ypos an int
     */
    public void setY(int ypos) {
        this.ypos = ypos;
    }

    /**
     * Return the name of the solar system
     * 
     * @return the name of the solar system
     */
    public String getName() {
        return name;
    }

    /**
     * set the name of the solar system
     * 
     * @param name a String
     */
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
