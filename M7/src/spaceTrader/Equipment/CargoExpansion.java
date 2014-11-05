/**
 * 
 */
package spaceTrader.Equipment;

/**
 * Cargo Expansion equippment
 * 
 * @author mli
 *
 */
public class CargoExpansion extends Equipment {
    
    
    public final static String NAME = "Cargo Expansoin";
    private final static int PRICE = 1000;
    private final static int MIN_TECH_LEVEL = 4;
    private final static int EXPANSION = 5;
    
    private int expansion;

    
    public CargoExpansion() {
        this(NAME, PRICE, MIN_TECH_LEVEL, EXPANSION);
    }
    
    
    /**
     * Constructor
     * 
     * @param name the name 
     * @param price the price
     * @param minTechLevel the minimum tech level requied to buy this
     * @param expansion how many extra slots a ship can get once equipped with this 
     */
    public CargoExpansion(String name, int price, int minTechLevel, int expansion) {
        super(name, price, minTechLevel);
        setExpansion(expansion);
    }

    /**
     * Copy constructor
     * 
     * @param e a CargoExpansion to be copied
     */
    public CargoExpansion(CargoExpansion e) {
        super(e);
        setExpansion(e.getExpansion());
    }

    public int getExpansion() {
        return expansion;
    }

    public void setExpansion(int expansion) {
        this.expansion = expansion;
    }



}
