package spaceTrader.Equipment;

/**
 * Parent class of all shields
 * 
 * @author mli
 *
 */
public class Shield extends Equipment {
    
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
    public Shield(Shield s) {
        super(s);
        setStrength(s.getStrength());
    }

    public int getStrength() {
        return strength;
    }


    public void setStrength(int strength) {
        this.strength = strength;
    }

}
