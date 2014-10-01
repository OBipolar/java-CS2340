package SpaceTrader.Goods;

import SpaceTrader.Model.ResourcesLevel;

public class Games extends Good {
    
    public Games() {
        setName("Games");
        setMTLP(3);
        setMTLU(1);
        setTTP(6);
        setBasePrice(250);
        setIPL(-10);
        setVar(5);
        setPriceIncrease(PriceIncrease.BOREDOM.ordinal());
        setPriceLowCase(ResourcesLevel.ARTISTIC.ordinal());
        setPriceHighCase(-1);
        setMinPriceWithTrader(160);
        setMaxPriceWithTrader(270);
        
    }

}
