package spacetrader.equipment;

/**
 * Cargo Expansion equippment
 * 
 * @author mli
 *
 */
public class CargoExpansion extends Equipment {
    
    
    public static final String NAME = "Cargo Expansoin";
    private static final int PRICE = 1000;
    private static final int MIN_TECH_LEVEL = 4;
    private static final int EXPANSION = 5;
    
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
    public CargoExpansion(CargoExpansion cargoExp) {
        super(cargoExp);
        setExpansion(cargoExp.getExpansion());
    }

    public int getExpansion() {
        return expansion;
    }

    public void setExpansion(int expansion) {
        this.expansion = expansion;
    }



}
