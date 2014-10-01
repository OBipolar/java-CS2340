package SpaceTrader.Goods;

import SpaceTrader.Model.ResourcesLevel;

public class Medicine extends Good {
    
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
