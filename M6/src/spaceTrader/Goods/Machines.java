/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceTrader.Goods;

/**
 * Child of Good, Machines
 * 
 * @author Menghang Li
 */
public class Machines extends Good {
    /**
     * Constructor thats sets all properties for Machines
     */
    public Machines() {
        setName("Machines");
        setMTLP(4);
        setMTLU(3);
        setTTP(5);
        setBasePrice(900);
        setIPL(-30);
        setVar(5);
        setPriceIncrease(PriceIncrease.LACKOFWORKERS.ordinal());
        setPriceLowCase(-1);
        setPriceHighCase(-1);
        setMinPriceWithTrader(30);
        setMaxPriceWithTrader(50);

    }

}
