package SpaceTrader.Goods;

import SpaceTrader.Model.ResourcesLevel;

public class Narcotics extends Good {
    
    public Narcotics () {
        setName("Narcotics");
        setMTLP(5);
        setMTLU(0);
        setTTP(5);
        setBasePrice(3500);
        setIPL(-125);
        setVar(150);
        setPriceIncrease(PriceIncrease.BOREDOM.ordinal());
        setPriceLowCase(ResourcesLevel.WEIRDMUSHROOMS.ordinal());
        setPriceHighCase(-1);
        setMinPriceWithTrader(2000);
        setMaxPriceWithTrader(3000);
        
    }
    
}
