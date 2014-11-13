/**
 * 
 */
package spacetrader.equipment;

/**
 * Parent class of all lasers
 * 
 * @author mli
 *
 */
public class Weapon extends Equipment {

 
    private int damage;
    
    /**
     * Constructor
     * 
     * @param name the name 
     * @param price the price 
     * @param damage the damage it can cause
     * @param minTechLevel the minimum tech level requied to produce it
     */
    public Weapon(String name, int price, int damage, int minTechLevel) {
        super(name, price, minTechLevel);
        setDamage(damage);
        
    }
    
    /**
     * Copy Constructor
     * 
     * @param laser A laser
     */
    public Weapon(Weapon laser) {
        super(laser);
        setDamage(laser.getDamage());
    }

    public int getDamage() {
        return damage;
    }


    public void setDamage(int damage) {
        this.damage = damage;
    }



    
   
    
}
