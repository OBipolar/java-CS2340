package spacetrader.goods;

import spaceTrader.Planets.ResourcesLevel;

/**
 * Child of Good, Food
 * 
 * @author Menghang Li
 *
 */
public class Food extends Good{
    
    
    /**
     * Constructor thats sets all properties for Food
     * 
     */
    public Food() {
        setName("Food");
        setMTLP(1);
        setMTLU(0);
        setTTP(1);
        setBasePrice(100);
        setIPL(5);
        setVar(5);
        setPriceIncrease(PriceIncrease.CROPFAIL.ordinal());
        setPriceLowCase(ResourcesLevel.RICHSOIL.ordinal());
        setPriceHighCase(ResourcesLevel.POORSOIL.ordinal());
        setMinPriceWithTrader(90);
        setMaxPriceWithTrader(160);
        
    }
    
}
