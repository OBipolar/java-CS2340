package spacetrader.planets;

/**
 * A planet
 * 
 * @author Menghang Li
 *
 */
public class Planet {

    private PoliticalSystem politicalSystem;
    private ResourcesLevel resourcesLevel;
    private String solarSystem;
    private TechLevels techLevel;
    private Pirates pirate;
    private Polices police;
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

    public PoliticalSystem getPoliticalSystem() {
        return politicalSystem;
    }

    public void setPoliticalSystem(int index) {
        this.politicalSystem = PoliticalSystem.values()[index];

    }

    public ResourcesLevel getResourcesLevel() {
        return resourcesLevel;
    }

    public void setResourcesLevel(int index) {
        this.resourcesLevel = ResourcesLevel.values()[index];
    }

    public String getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(String solarSystem) {
        this.solarSystem = solarSystem;
    }

    public TechLevels getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(int index) {

        this.techLevel = TechLevels.values()[index];

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pirates getPirate() {
        return pirate;
    }

    public void setPirate(int index) {

        this.pirate = Pirates.values()[index];

    }

    public Polices getPolice() {
        return police;
    }

    public void setPolice(int index) {
        this.police = Polices.values()[1];
    }

}
