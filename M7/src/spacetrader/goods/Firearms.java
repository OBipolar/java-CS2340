package spacetrader.goods;

import spacetrader.planets.ResourcesLevel;

/**
 * Child of Good, Firearms
 * 
 * @author Menghang Li
 *
 */
public class Firearms extends Good {
    
    /**
     * Constructor thats sets all properties for Firearms
     * 
     */
    public Firearms() {
        setName("Firearms");
        setMtlp(3);
        setMtlu(1);
        setTtp(5);
        setBasePrice(1250);
        setIpl(-75);
        setVar(100);
        setPriceIncrease(PriceIncrease.WAR.ordinal());
        setPriceLowCase(ResourcesLevel.WARLIKE.ordinal());
        setPriceHighCase(-1);
        setMinPriceWithTrader(600);
        setMaxPriceWithTrader(1100);
        
    }

}
