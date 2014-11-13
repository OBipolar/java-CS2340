package spacetrader.equipment;

/**
 * Pulse Laser
 * 
 * @author mli
 *
 */
public class PulseLaser extends Weapon {

    public static final String NAME = "Pulse Laser";
    private static final int PRICE = 1000;
    private static final int DAMAGE = 10;
    private static final int MIN_TECH_LEVEL = 4;
    
    
    
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
