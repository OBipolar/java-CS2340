/**
 * 
 */
package spaceTrader.Equipment;

/**
 * Reflective shield
 * 
 * @author mli
 *
 */
public class ReflectiveShield extends Shield {
    
    public final static String NAME = "Flective Shield";
    private final static int PRICE = 8000;
    private final static int MIN_TECH_LEVEL = 6;
    private final static int STRENGTH = 100;
    
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
    public ReflectiveShield(Shield s) {
        super(s);
        // TODO Auto-generated constructor stub
    }

}
