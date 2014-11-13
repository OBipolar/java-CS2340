package spacetrader.goods;

import spacetrader.planets.ResourcesLevel;

/**
 * Child of Good, Furs
 * 
 * @author Menghang Li
 *
 */
public class Furs extends Good {

    /**
     * Constructor thats sets all properties for Furs
     */
    public Furs() {
        setName("Furs");
        setMtlp(0);
        setMtlu(0);
        setTtp(0);
        setBasePrice(250);
        setIpl(10);
        setVar(10);
        setPriceIncrease(PriceIncrease.COLD.ordinal());
        setPriceLowCase(ResourcesLevel.RICHFAUNA.ordinal());
        setPriceHighCase(ResourcesLevel.LIFELESS.ordinal());
        setMinPriceWithTrader(230);
        setMaxPriceWithTrader(280);       
    }
    
}
