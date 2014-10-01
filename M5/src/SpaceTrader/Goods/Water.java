package SpaceTrader.Goods;

import SpaceTrader.Model.ResourcesLevel;

public class Water extends Good {

    public Water() {
        setName("water");
        setMTLP(0);
        setMTLU(0);
        setTTP(2);
        setBasePrice(30);
        setIPL(3);
        setVar(4);
        setPriceIncrease(PriceIncrease.DROUGHT.ordinal());
        setPriceLowCase(ResourcesLevel.LOTSOFWAR.ordinal());
        setPriceHighCase(ResourcesLevel.DESERT.ordinal());
        setMinPriceWithTrader(30);
        setMaxPriceWithTrader(50);
        
    }
    
}
