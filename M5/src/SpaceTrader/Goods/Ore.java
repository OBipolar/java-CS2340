package SpaceTrader.Goods;

import SpaceTrader.Model.ResourcesLevel;

public class Ore extends Good {

    public Ore() {
        setName("Ore");
        setMTLP(2);
        setMTLU(2);
        setTTP(3);
        setBasePrice(350);
        setIPL(20);
        setVar(10);
        setPriceIncrease(PriceIncrease.WAR.ordinal());
        setPriceLowCase(ResourcesLevel.MINERALRICH.ordinal());
        setPriceHighCase(ResourcesLevel.MINERALPOOR.ordinal());
        setMinPriceWithTrader(350);
        setMaxPriceWithTrader(420);
        
    }
    
}
