/**
 * 
 */
package spaceTrader.Equipment;

/**
 * A child of Laser
 * 
 * @author mli
 *
 */
public class BeamLaser extends Weapon {
    
    public final static String NAME = "Beam Laser";
    private final static int PRICE = 2000;
    private final static int DAMAGE = 15;
    private final static int MIN_TECH_LEVEL = 5;
    
    
    
    public BeamLaser() {
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
    public BeamLaser(String name, int price, int damage, int minTechLevel) {
        super(name, price, damage, minTechLevel);
        // TODO Auto-generated constructor stub
    }

    /**
     * Copy Constructor
     * 
     * @param laser A laser
     */
    public BeamLaser(Weapon laser) {
        super(laser);
  
    }

}
