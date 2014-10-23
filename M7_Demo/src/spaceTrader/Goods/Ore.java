package spaceTrader.Goods;

import spaceTrader.Planets.ResourcesLevel;

/**
 * Child of Good, Ore
 * 
 * @author Menghang Li
 *
 */
public class Ore extends Good {

    /**
     * Constructor thats sets all properties for Ore
     */
    public Ore() {
        setName("Ore");
        setMTLP(2);
        setMTLU(2);
        setTTP(3);
        setBasePrice(350);
        setIPL(20);
        setVar(10);
        setPriceIncrease(PriceIncrease.WAR.ordinal());
        setPriceLowCase(ResourcesLevel.MINERALRICH.ordinal());
        setPriceHighCase(ResourcesLevel.MINERALPOOR.ordinal());
        setMinPriceWithTrader(350);
        setMaxPriceWithTrader(420);
        
    }
    
}
