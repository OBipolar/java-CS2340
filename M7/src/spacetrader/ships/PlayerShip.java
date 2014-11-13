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

    private Ship base;
    private List<Good> cargo;
    private int cargoSpace;
    private int numOfGoods;

    public PlayerShip(PlayerShip ship) {
        this(ship.getBase(), ship.getCargo());
    }
    
    
    
    
    public PlayerShip() {

        setBase(new Gnat());
        setCargoSpace(base.getCargoBay());
        cargo = new ArrayList<Good>();
        setNumOfGoods(cargo.size());
        loadShip();
    }

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
            goods += (" " + g.toString());
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

    private void loadShip() {
        cargo.add(new Water());
        cargo.add(new Water());
        cargo.add(new Water());
        cargo.add(new Furs());
        cargo.add(new Ore());
        cargo.add(new Food());
        numOfGoods = cargo.size();
    }

    public boolean isFull() {
        return (numOfGoods == cargoSpace);
    }

    public void add(Good good) {
        cargo.add(good);
        numOfGoods++;
    }

    public void remove(Good good) {
        cargo.remove(good);
        numOfGoods--;
    }

    public Ship getBase() {
        return base;
    }

    public void setBase(Ship base) {
        this.base = base;
    }

    public int getCargoSpace() {
        return cargoSpace;
    }

    public void setCargoSpace(int cargoSpace) {
        this.cargoSpace = cargoSpace;
    }

    public List<Good> getCargo() {
        return cargo;
    }

    public int getNumOfGoods() {
        return numOfGoods;
    }

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
    
    public void setShield(int shield) {
        base.setShield(shield);
    }
    
    public int getWeaponSlots() {
        return base.getWeaponSlots();
    }
    
    public void setWeaponSlots(int weaponSlots) {
        base.setWeaponSlots(weaponSlots);
    }
    
    public int getShieldSlots() {
        return base.getShieldSlots();
    }
    
    public void setShieldSlots(int shieldSlots) {
        base.setShieldSlots(shieldSlots);
    }
    
    public int getGadgetsSlots() {
        return base.getGadgetSlots();
        
    }
    
    public void setGadgetsSlots(int gadgetSlots) {
        base.setGadgetSlots(gadgetSlots);
        
    }

}
