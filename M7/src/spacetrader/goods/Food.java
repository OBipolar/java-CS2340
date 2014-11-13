package spacetrader.goods;

import spacetrader.planets.ResourcesLevel;

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
        setMtlp(1);
        setMtlu(0);
        setTtp(1);
        setBasePrice(100);
        setIpl(5);
        setVar(5);
        setPriceIncrease(PriceIncrease.CROPFAIL.ordinal());
        setPriceLowCase(ResourcesLevel.RICHSOIL.ordinal());
        setPriceHighCase(ResourcesLevel.POORSOIL.ordinal());
        setMinPriceWithTrader(90);
        setMaxPriceWithTrader(160);
        
    }
    
}
