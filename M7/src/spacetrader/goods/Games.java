package spacetrader.goods;

import spacetrader.planets.ResourcesLevel;

/**
 * Child of Good, Games
 * 
 * @author Menghang Li
 *
 */
public class Games extends Good {
    
    /**
     * Constructor thats sets all properties for Games
     */
    public Games() {
        super();
        setName("Games");
        setMtlp(3);
        setMtlu(1);
        setTtp(6);
        setBasePrice(250);
        setIpl(-10);
        setVar(5);
        setPriceIncrease(PriceIncrease.BOREDOM.ordinal());
        setPriceLowCase(ResourcesLevel.ARTISTIC.ordinal());
        setPriceHighCase(-1);
        setMinPriceWithTrader(160);
        setMaxPriceWithTrader(270);
        
    }

}
