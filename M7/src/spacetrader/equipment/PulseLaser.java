/**
 * 
 */
package spacetrader.equipment;

/**
 * Pulse Laser
 * 
 * @author mli
 *
 */
public class PulseLaser extends Weapon {

    public final static String NAME = "Pulse Laser";
    private final static int PRICE = 1000;
    private final static int DAMAGE = 10;
    private final static int MIN_TECH_LEVEL = 4;
    
    
    
    public PulseLaser() {
        this(NAME, PRICE, DAMAGE, MIN_TECH_LEVEL);
    }
    
    
    
    /**
     * @param name
     * @param price
     * @param damage
     * @param minTechLevel
     */
    public PulseLaser(String name, int price, int damage, int minTechLevel) {
        super(name, price, damage, minTechLevel);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param laser
     */
    public PulseLaser(Weapon laser) {
        super(laser);
        // TODO Auto-generated constructor stub
    }

}
