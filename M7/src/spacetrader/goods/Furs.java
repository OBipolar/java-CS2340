package spacetrader.goods;

import spaceTrader.Planets.ResourcesLevel;

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
        setMTLP(0);
        setMTLU(0);
        setTTP(0);
        setBasePrice(250);
        setIPL(10);
        setVar(10);
        setPriceIncrease(PriceIncrease.COLD.ordinal());
        setPriceLowCase(ResourcesLevel.RICHFAUNA.ordinal());
        setPriceHighCase(ResourcesLevel.LIFELESS.ordinal());
        setMinPriceWithTrader(230);
        setMaxPriceWithTrader(280);       
    }
    
}
