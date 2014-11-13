package spacetrader.goods;

import spacetrader.planets.ResourcesLevel;

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
        setMtlp(2);
        setMtlu(2);
        setTtp(3);
        setBasePrice(350);
        setIpl(20);
        setVar(10);
        setPriceIncrease(PriceIncrease.WAR.ordinal());
        setPriceLowCase(ResourcesLevel.MINERALRICH.ordinal());
        setPriceHighCase(ResourcesLevel.MINERALPOOR.ordinal());
        setMinPriceWithTrader(350);
        setMaxPriceWithTrader(420);
        
    }
    
}
