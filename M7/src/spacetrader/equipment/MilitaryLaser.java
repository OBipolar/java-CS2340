/**
 * 
 */
package spacetrader.equipment;

/**
 * Military Laser
 * 
 * @author mli
 *
 */
public class MilitaryLaser extends Weapon {

    public final static String NAME = "Military Laser";
    private final static int PRICE = 3000;
    private final static int DAMAGE = 50;
    private final static int MIN_TECH_LEVEL = 6;
    
    
    
    public MilitaryLaser() {
        this(NAME, PRICE, DAMAGE, MIN_TECH_LEVEL);
    }
    
    
    
    /**
     * Constructor
     * 
     * @param name the name 
     * @param price the price 
     * @param damage the damage it can cause
     * @param minTechLevel the minimum tech level requied to produce it
     */
    public MilitaryLaser(String name, int price, int damage,
            int minTechLevel) {
        super(name, price, damage, minTechLevel);
        // TODO Auto-generated constructor stub
    }

    /**
     * Copy Constructor
     * 
     * @param laser A laser
     */
    public MilitaryLaser(Weapon laser) {
        super(laser);
        // TODO Auto-generated constructor stub
    }

}
