package spaceTrader.Goods;

import spaceTrader.Planets.ResourcesLevel;

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
        setMTLP(4);
        setMTLU(1);
        setTTP(6);
        setBasePrice(650);
        setIPL(-20);
        setVar(10);
        setPriceIncrease(PriceIncrease.PLAGUE.ordinal());
        setPriceLowCase(ResourcesLevel.LOTSOFHERBS.ordinal());
        setPriceHighCase(-1);
        setMinPriceWithTrader(400);
        setMaxPriceWithTrader(700);
        
    }

}
