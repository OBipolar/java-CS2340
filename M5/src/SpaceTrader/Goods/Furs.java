package SpaceTrader.Goods;

import SpaceTrader.Model.ResourcesLevel;

public class Furs extends Good {

    public Furs() {
        setName("Furs");
        setMTLP(0);
        setMTLU(0);
        setTTP(0);
        setBasePrice(250);
        setIPL(10);
        setVar(10);
        setPriceIncrease(PriceIncrease.COLD.ordinal());
        setPriceLowCase(ResourcesLevel.RICHFAUNA.ordinal());
        setPriceHighCase(ResourcesLevel.LIFELESS.ordinal());
        setMinPriceWithTrader(230);
        setMaxPriceWithTrader(280);       
    }
    
}
