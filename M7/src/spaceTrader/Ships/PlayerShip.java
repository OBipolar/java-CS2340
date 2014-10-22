package spaceTrader.Ships;

import java.util.ArrayList;
import java.util.List;

import spaceTrader.Goods.Food;
import spaceTrader.Goods.Furs;
import spaceTrader.Goods.Good;
import spaceTrader.Goods.Ore;
import spaceTrader.Goods.Water;

public class PlayerShip {

    private Ship base;
    private List<Good> cargo;
    private int cargoSpace;
    private int numOfGoods;

    public PlayerShip(PlayerShip ship) {
    	this(ship.getBase(), ship.getCargo());
    }
    
    
    
    
    public PlayerShip() {

        setBase(new Flea());
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

}
