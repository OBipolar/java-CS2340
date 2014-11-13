package spacetrader.goods;

import spacetrader.planets.ResourcesLevel;

/**
 * Child of Good, Narcotics
 * 
 * @author Menghang Li
 *
 */
public class Narcotics extends Good {
    
    /**
     * Constructor thats sets all properties for Narcotics
     */
    public Narcotics() {
        setName("Narcotics");
        setMtlp(5);
        setMtlu(0);
        setTtp(5);
        setBasePrice(3500);
        setIpl(-125);
        setVar(150);
        setPriceIncrease(PriceIncrease.BOREDOM.ordinal());
        setPriceLowCase(ResourcesLevel.WEIRDMUSHROOMS.ordinal());
        setPriceHighCase(-1);
        setMinPriceWithTrader(2000);
        setMaxPriceWithTrader(3000);
        
    }
    
}
