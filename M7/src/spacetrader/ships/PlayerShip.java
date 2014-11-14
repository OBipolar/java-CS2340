package spacetrader.ships;

import spacetrader.equipment.CargoExpansion;
import spacetrader.equipment.Shield;
import spacetrader.equipment.Weapon;
import spacetrader.goods.Food;
import spacetrader.goods.Furs;
import spacetrader.goods.Good;
import spacetrader.goods.Ore;
import spacetrader.goods.Water;

import java.util.ArrayList;
import java.util.List;

public class PlayerShip {

    /**
     * base model Ship
     */
    private Ship base;
    /**
     * goods on ship
     */
    private List<Good> cargo;
    /**
     * cargo space
     */
    private int cargoSpace;
    /**
     * num of goods
     */
    private int numOfGoods;

    /**
     * Copy constructor
     * 
     * @param ship a Ship
     */
    public PlayerShip(PlayerShip ship) {
        this(ship.getBase(), ship.getCargo());
    }
    
    
    
    
    /**
     * Default constructor
     */
    public PlayerShip() {

        setBase(new Gnat());
        setCargoSpace(base.getCargoBay());
        cargo = new ArrayList<Good>();
        setNumOfGoods(cargo.size());
        loadShip();
    }

    /**
     * Constructor that takes in a base model ship and cargo
     * 
     * @param baseShip a base model of ship
     * @param cargo cargo
     */
    public PlayerShip(Ship baseShip, List<Good> cargo) {
        setBase(baseShip);
        setCargoSpace(base.getCargoBay());
        this.cargo = cargo;
        setNumOfGoods(cargo.size());
    }

    @Override
    public String toString() {
        String goods = "";
        for (Good g : cargo) {
            goods += " " + g.toString();
        }
        return String
                .format("Base ship: %s, cargo: %s", base.toString(), goods);
    }
    
    /**
     * Adds a Weapon to the ship
     * 
     * @param w a Weapon
     */
    public void addWeapon(Weapon weapon) {
        setAttack(getAttack() + weapon.getDamage());
        setWeaponSlots(getWeaponSlots() - 1);
    }
    
    /**
     * Adds a Shield to the ship
     * 
     * @param s a Shield
     */
    public void addShield(Shield sh) {
        setShield(getShield() + sh.getStrength());
        setShieldSlots(getShieldSlots() - 1);
    }
    
    /**
     * Add cargo expansion gadget to the ship
     * 
     * @param c a CargoExpansion
     */
    public void addCargoExpansoin(CargoExpansion cargoExp) {
        setCargoSpace(getCargoSpace() + cargoExp.getExpansion());
        setGadgetsSlots(getGadgetsSlots() - 1);
    }

    /** Loads some basic goods onto the ship
     * 
     */
    private void loadShip() {
        cargo.add(new Water());
        cargo.add(new Water());
        cargo.add(new Water());
        cargo.add(new Furs());
        cargo.add(new Ore());
        cargo.add(new Food());
        numOfGoods = cargo.size();
    }

    /**
     * Return true if the cargo space is full, false otherwise
     * 
     * @return true if the cargo space is full, false otherwise
     */
    public boolean isFull() {
        return numOfGoods == cargoSpace;
    }

    /**
     * Adds a good onto the ship
     * 
     * @param good a Good
     */
    public void add(Good good) {
        cargo.add(good);
        numOfGoods++;
    }

    /**
     * Remove a given good from the ship
     * 
     * @param good a Good
     */
    public void remove(Good good) {
        cargo.remove(good);
        numOfGoods--;
    }

    /**
     * Return the base model of the ship
     * 
     * @return the base model of the ship
     */
    public Ship getBase() {
        return base;
    }

    /**
     * Set the base model of the ship
     * 
     * @param base a Ship
     */
    public void setBase(Ship base) {
        this.base = base;
    }

    /**
     * Return the cargo space of the ship
     * 
     * @return the cargo space of the ship
     */
    public int getCargoSpace() {
        return cargoSpace;
    }

    /**
     * Set the cargo space on the ship
     * 
     * @param cargoSpace an int
     */
    public void setCargoSpace(int cargoSpace) {
        this.cargoSpace = cargoSpace;
    }

    /**
     * Return a list of Good on the ship
     * 
     * @return a list of Good on the ship 
     */
    public List<Good> getCargo() {
        return cargo;
    }

    /**
     * Return the number of goods on the ship
     * 
     * @return the attack attribute of the ship
     */
    public int getNumOfGoods() {
        return numOfGoods;
    }

    /**
     * Sets the number of goods on the ship
     * 
     * @param numOfGoods an int
     */
    public void setNumOfGoods(int numOfGoods) {
        this.numOfGoods = numOfGoods;
    }
    
    /**
     * Return the attack attribute of the player ship
     * 
     * @return the attack attribute of the player ship
     */
    public int getAttack() {
        return base.getAttack();
    }
    
    /**
     * Sets the attack attribute of the ship
     *  
     * @param attack the attack attribute of the ship
     */
    public void setAttack(int attack) {
        base.setAttack(attack);
    }
    
    /**
     * Return the shield attribute of the player ship
     * 
     * @return the shield attribute of the player ship
     */
    public int getShield() {
        return base.getShield();
    }
    
    /**
     * Sets the shield
     * 
     * @param shield an int
     */
    public void setShield(int shield) {
        base.setShield(shield);
    }
    
    /**
     * Return the weapons slots available
     * 
     * @return the weapons slots available
     */
    public int getWeaponSlots() {
        return base.getWeaponSlots();
    }
    
    /**
     * Sets the weapons slots the ship has
     * 
     * @param weaponSlots an int
     */
    public void setWeaponSlots(int weaponSlots) {
        base.setWeaponSlots(weaponSlots);
    }
    
    /**
     * Return the shield slots the ship has now
     * 
     * @return available shield slots
     */
    public int getShieldSlots() {
        return base.getShieldSlots();
    }
    
    /**
     * Set the shields slots
     * 
     * @param shieldSlots an int
     */
    public void setShieldSlots(int shieldSlots) {
        base.setShieldSlots(shieldSlots);
    }
    
    /**
     * Return the available gadgets slots
     * 
     * @return the available gadgets slots
     */
    public int getGadgetsSlots() {
        return base.getGadgetSlots();
        
    }
    
    /**
     * Sets the gadgets slots of the ship
     * 
     * @param gadgetSlots an int
     */
    public void setGadgetsSlots(int gadgetSlots) {
        base.setGadgetSlots(gadgetSlots);
        
    }

}
