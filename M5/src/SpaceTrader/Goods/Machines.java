package SpaceTrader.Goods;


public class Machines extends Good {
    
    public Machines() {
        setName("Machines");
        setMTLP(4);
        setMTLU(3);
        setTTP(5);
        setBasePrice(900);
        setIPL(-30);
        setVar(5);
        setPriceIncrease(PriceIncrease.LACKOFWORKERS.ordinal());
        setPriceLowCase(-1);
        setPriceHighCase(-1);
        setMinPriceWithTrader(30);
        setMaxPriceWithTrader(50);
        
    }

}
