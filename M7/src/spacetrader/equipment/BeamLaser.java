package spacetrader.equipment;

/**
 * A child of Laser
 * 
 * @author mli
 *
 */
public class BeamLaser extends Weapon {
    
    public static final String NAME = "Beam Laser";
    private static final int PRICE = 2000;
    private static final int DAMAGE = 15;
    private static final int MIN_TECH_LEVEL = 5;
    
    
    
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
