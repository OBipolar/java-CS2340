package spacetrader.goods;

import spacetrader.planets.ResourcesLevel;

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
        super();
        setName("Water");
        setMtlp(0);
        setMtlu(0);
        setTtp(2);
        setBasePrice(30);
        setIpl(3);
        setVar(4);
        setPriceIncrease(PriceIncrease.DROUGHT.ordinal());
        setPriceLowCase(ResourcesLevel.LOTSOFWAR.ordinal());
        setPriceHighCase(ResourcesLevel.DESERT.ordinal());
        setMinPriceWithTrader(30);
        setMaxPriceWithTrader(50);
        
    }
    
}
