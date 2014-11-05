package spaceTrader.Planets;

public class Capital extends Planet {

    /**
     * Constructor for Capital that invokes super constructor
     * 
     * @param politicalSystem
     *            political system
     * @param resourcesLevel
     *            resources level
     * @param techLevel
     *            tech level
     * @param pirate
     *            pirate type
     * @param police
     *            police type
     * @param name
     *            name
     */
    public Capital(int politicalSystem, int resourcesLevel, int techLevel,
            int pirate, int police, String name) {
        super(name, politicalSystem, resourcesLevel, techLevel, pirate, police);

    }

    public Capital() {

    }

}
