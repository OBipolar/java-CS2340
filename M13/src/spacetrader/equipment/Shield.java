package spacetrader.equipment;

/**
 * Parent class of all shields
 * 
 * @author mli
 *
 */
public class Shield extends Equipment {
    
    /**
     * The amount of damage the shield can absorb
     */
    private int strength;
    
    
    /**
     * Constructor
     * 
     * @param name its name
     * @param price its price
     * @param minTechLevel the minimum tech level required to produce it
     * @param strength how much energy it can absorb
     */
    public Shield(String name, int price, int minTechLevel, int strength) {
        super(name, price, minTechLevel);
        setStrength(strength);
    }

    /**
     * Copy Constructor
     * 
     * @param s a Shield
     */
    public Shield(Shield sh) {
        super(sh);
        setStrength(sh.getStrength());
    }

    public int getStrength() {
        return strength;
    }


    public void setStrength(int strength) {
        this.strength = strength;
    }

}
