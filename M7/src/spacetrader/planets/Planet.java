package spacetrader.planets;

/**
 * A planet
 * 
 * @author Menghang Li
 *
 */
public class Planet {

    /**
     * The plolitical system of the planet
     */
    private PoliticalSystem politicalSystem;
    /**
     * The resources level of the planet
     */
    private ResourcesLevel resourcesLevel;
    /**
     * the name of the solar system the player is on
     */
    private String solarSystem;
    /**
     * the tech level of the planet
     */
    private TechLevels techLevel;
    /**
     * the pirate level of the planet
     */
    private Pirates pirate;
    /**
     * the police level of the planet
     */
    private Polices police;
    /**
     * the name of the planet
     */
    private String name;

    /**
     * Constructor that sets all properties for a Capital
     * 
     * @param politicalSystem
     *            its politicalSystem
     * @param resourcesLevel
     *            its resourcesLevel
     * @param techLevel
     *            its tech level
     * @param pirate
     *            pirates on it
     * @param police
     *            polices on it
     * @param name
     *            its name
     */
    public Planet(String name, int politicalSystem, int resourcesLevel,
            int techLevel, int pirate, int police) {

        setName(name);
        setPoliticalSystem(politicalSystem);
        setResourcesLevel(resourcesLevel);
        setSolarSystem(name);
        setTechLevel(techLevel);
        setPirate(pirate);
        setPolice(police);

    }
    
    /**
     * Default constructor
     */
    public Planet() {
        
    }


    @Override
    public String toString() {
        return String
                .format("Planet name: %s, political system: %s, "
                        + "resources level: %s, pirate type: %s, "
                        + "police type: %s, belongs to solar system: %s, "
                        + "techLevel: %s \n", name, politicalSystem.toString(),
                        resourcesLevel.toString(), pirate.toString(),
                        police.toString(), solarSystem.toString(),
                        techLevel.toString());
    }

    /**
     * Return the political system on the planet
     * 
     * @return the political system on the planet
     */
    public PoliticalSystem getPoliticalSystem() {
        return politicalSystem;
    }

    /**
     * Set the political system on the planet
     * 
     * @param index an int
     */
    public void setPoliticalSystem(int index) {
        this.politicalSystem = PoliticalSystem.values()[index];

    }

    /**
     * Return the resources level of the planet
     * 
     * @return the resources level of the planet
     */
    public ResourcesLevel getResourcesLevel() {
        return resourcesLevel;
    }

    /**
     * Set the resources level of the planet
     * 
     * @param index an int
     */
    public void setResourcesLevel(int index) {
        this.resourcesLevel = ResourcesLevel.values()[index];
    }

    /**
     * Return the solar system the planet belongs to 
     * 
     * @return the solar system the planet belongs to 
     */
    public String getSolarSystem() {
        return solarSystem;
    }

    /**
     * Set the solar system the planet belongs to 
     * 
     * @param solarSystem the solar system the planet belongs to 
     */
    public void setSolarSystem(String solarSystem) {
        this.solarSystem = solarSystem;
    }

    /**
     * Return the tech level of the planet
     * 
     * @return the tech level of the plante
     */
    public TechLevels getTechLevel() {
        return techLevel;
    }

    /**
     * Set the tech level on the planet
     * 
     * @param index an int
     */
    public void setTechLevel(int index) {

        this.techLevel = TechLevels.values()[index];

    }

    /**
     * Return the name of the planet
     * 
     * @return the name of the plante
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the planet
     * 
     * @param name a String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the pirate level of the plante
     * 
     * @return the pirate level of the planet
     */
    public Pirates getPirate() {
        return pirate;
    }

    /**
     * Set the pirate level of the plante
     * 
     * @param index an int
     */
    public void setPirate(int index) {

        this.pirate = Pirates.values()[index];

    }

    /**
     * Return the police level of the planet
     * 
     * @return the police level of the planet
     */
    public Polices getPolice() {
        return police;
    }

    /**
     * Set the police level on the planet
     * 
     * @param index an int 
     */
    public void setPolice(int index) {
        this.police = Polices.values()[1];
    }

}
