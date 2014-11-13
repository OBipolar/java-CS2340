package spacetrader.goods;

import spaceTrader.Planets.ResourcesLevel;

/**
 * Child of Good, water
 * 
 * @author Menghang Li
 *
 */
public class Water extends Good {

    /**
     * Constructor for Water that sets its properties
     * 
     * 
     */
    public Water() {
        setName("Water");
        setMTLP(0);
        setMTLU(0);
        setTTP(2);
        setBasePrice(30);
        setIPL(3);
        setVar(4);
        setPriceIncrease(PriceIncrease.DROUGHT.ordinal());
        setPriceLowCase(ResourcesLevel.LOTSOFWAR.ordinal());
        setPriceHighCase(ResourcesLevel.DESERT.ordinal());
        setMinPriceWithTrader(30);
        setMaxPriceWithTrader(50);
        
    }
    
}
