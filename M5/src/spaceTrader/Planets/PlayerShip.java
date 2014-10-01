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

    private Ship base;
    private List<Good> cargo;
    private int cargoSpace;
    private int numOfGoods;

    /**
     * Constructor that sets all properties of this ship
     */
    public PlayerShip() {

        setBase(new Flea());
        setCargoSpace(base.getCargoBay());
        cargo = new ArrayList<Good>();
        setNumOfGoods(cargo.size());
    }

    /**
     * Load the ship for testing purpose
     */
    public void loadShip() {
        cargo.add(new Water());
        cargo.add(new Water());
        cargo.add(new Water());
        cargo.add(new Furs());
        cargo.add(new Ore());
        cargo.add(new Food());
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
    public void add(Good good) {
        cargo.add(good);
        numOfGoods++;
    }

    /**
     * Reomve a good from the cargo
     * 
     * @param good
     */
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

}
