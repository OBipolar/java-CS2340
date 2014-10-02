package spaceTrader.Planets;

import java.util.ArrayList;
import java.util.List;

import spaceTrader.Goods.Food;
import spaceTrader.Goods.Furs;
import spaceTrader.Goods.Good;
import spaceTrader.Goods.Ore;
import spaceTrader.Goods.Water;
import spaceTrader.Ships.Flea;
import spaceTrader.Ships.Ship;

/**
 * The ship player owns
 * 
 * @author Menghang Li
 *
 */
public class PlayerShip {
    
    private class Bucket {
        private Good item;
        private int num;
        
        public Bucket(Good item, int num) {
            this.item = item;
            this.num = num;
        }
    }

    private Ship base;
    private List<Bucket> cargo;
    private int cargoSpace;
    private int kindOfGoods;

    /**
     * Constructor that sets all properties of this ship
     */
    public PlayerShip() {

        setBase(new Flea());
        setCargoSpace(base.getCargoBay());
        cargo = new ArrayList<Bucket>();
        setKindOfGoods(cargo.size());
    }
    
    /**
     * get the max range the ship can travel
     */
    public int getMaxRange() {
        return base.getMaxRange();
    }

    /**
     * Load the ship for testing purpose
     */
    public void loadShip() {
        cargo.add(new Bucket(new Water(), 3));
        cargo.add(new Bucket(new Furs(), 1));
        cargo.add(new Bucket(new Ore(), 1));
        cargo.add(new Bucket(new Food(), 1));
        numOfGoods = cargo.size();
    }

    /**
     * Return true if the cargo is full
     * 
     * @return true if the cargo is full
     */
    public boolean isFull() {
        return (numOfGoods == cargoSpace);
    }

    /**
     * Add a good to the ship cargo
     * 
     * @param good
     */
    public void add(Good good, int num) {
        boolean exist = false;
        for (Bucket b : cargo) {
            if (b.item.getName().equals(good.getName())) {
                exist = true;
                b.num += num;
            }
        }
        if (!exist) {
            cargo.add(new Bucket(good, num));
        }
        
    }

    /**
     * Remove a good from the cargo
     * 
     * @param good
     */
    public void remove(Good good) {
        cargo.remove(good);
        kindOfGoods--;
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

    public List<Bucket> getCargo() {
        return cargo;
    }

    public int getKindOfGoods() {
        return kindOfGoods;
    }

    public void setKindOfGoods(int kindOfGoods) {
        this.kindOfGoods = kindOfGoods;
    }

}
