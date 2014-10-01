package SpaceTrader.Goods;

import SpaceTrader.Model.ResourcesLevel;

public class Firearms extends Good {
    
    public Firearms() {
        setName("Firearms");
        setMTLP(3);
        setMTLU(1);
        setTTP(5);
        setBasePrice(1250);
        setIPL(-75);
        setVar(100);
        setPriceIncrease(PriceIncrease.WAR.ordinal());
        setPriceLowCase(ResourcesLevel.WARLIKE.ordinal());
        setPriceHighCase(-1);
        setMinPriceWithTrader(600);
        setMaxPriceWithTrader(1100);
        
    }

}
