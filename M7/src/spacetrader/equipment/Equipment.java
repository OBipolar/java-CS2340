/**
 * 
 */
package spacetrader.equipment;

/**
 * Parent of all quippments
 * 
 * @author mli
 *
 */
public class Equipment {

    private String name;
    private int price;
    private int minTechLevel;
    
    
    
    /**
     * Constructor 
     * 
     * @param name the name
     * @param price the price
     * @param minTechLevel the minimum tech level required to produce this
     */
    public Equipment(String name, int price, int minTechLevel) {
        setName(name);
        setPrice(price);
        setMinTechLevel(minTechLevel);
    }
    
    /**
     * Copy Constructor
     * 
     * @param e
     */
    public Equipment(Equipment e) {
        this(e.getName(), e.getPrice(), e.getMinTechLevel());
    }
    
    public String getName() {
        return name;
    }
     
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    
    public int getMinTechLevel() {
        return minTechLevel;
    }

    public void setMinTechLevel(int minTechLevel) {
        this.minTechLevel = minTechLevel;
    }

}
