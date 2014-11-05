/**
 * 
 */
package spaceTrader.Equipment;

/**
 * Energy shield
 * 
 * @author mli
 *
 */
public class EnergyShield extends Shield {

    public final static String NAME = "Energy Shield";
    private final static int PRICE = 3000;
    private final static int MIN_TECH_LEVEL = 5;
    private final static int STRENGTH = 50;
    
    public EnergyShield() {
        this(NAME, PRICE, MIN_TECH_LEVEL, STRENGTH);
    }
    
    /**
     * Constructor
     * 
     * @param name its name
     * @param price its price
     * @param minTechLevel the minimum tech level required to produce it
     * @param strength how much energy it can absorb
     */
    public EnergyShield(String name, int price, int minTechLevel,
            int strength) {
        super(name, price, minTechLevel, strength);
        // TODO Auto-generated constructor stub
    }

    /**
     * Copy Constructor
     * 
     * @param s a Shield
     */
    public EnergyShield(Shield s) {
        super(s);
     
    }

}
