package spacetrader.goods;

import spacetrader.planets.ResourcesLevel;

/**
 * Child of Good, Medicine
 * 
 * @author Menghang Li
 *
 */
public class Medicine extends Good {
    
    /**
     * Constructor thats sets all properties for Medicine
     */
    public Medicine() {
        setName("Medicine");
        setMtlp(4);
        setMtlu(1);
        setTtp(6);
        setBasePrice(650);
        setIpl(-20);
        setVar(10);
        setPriceIncrease(PriceIncrease.PLAGUE.ordinal());
        setPriceLowCase(ResourcesLevel.LOTSOFHERBS.ordinal());
        setPriceHighCase(-1);
        setMinPriceWithTrader(400);
        setMaxPriceWithTrader(700);
        
    }

}
