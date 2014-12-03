package spacetrader.equipment;

/**
 * Reflective shield
 * 
 * @author mli
 *
 */
public class ReflectiveShield extends Shield {
    
    public static final String NAME = "Flective Shield";
    private static final int PRICE = 8000;
    private static final int MIN_TECH_LEVEL = 6;
    private static final int STRENGTH = 100;
    
    /**
     * Default Constructor 
     */
    public ReflectiveShield() {
        this(NAME, PRICE, MIN_TECH_LEVEL, STRENGTH);
    }

    /**
     * @param name
     * @param price
     * @param minTechLevel
     * @param strength
     */
    public ReflectiveShield(String name, int price, int minTechLevel,
            int strength) {
        super(name, price, minTechLevel, strength);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param s
     */
    public ReflectiveShield(Shield shield) {
        super(shield);
        // TODO Auto-generated constructor stub
    }

}
